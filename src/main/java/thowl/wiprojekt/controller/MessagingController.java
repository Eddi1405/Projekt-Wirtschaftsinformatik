package thowl.wiprojekt.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import thowl.wiprojekt.chat.GeneralCode;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.*;
import thowl.wiprojekt.objects.ChatType;
import thowl.wiprojekt.objects.ContentType;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.MessageRepository;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.FileValidator;

import java.util.Set;
import java.util.TreeSet;

/**
 * This {@link Controller} is responsible for the core functionality of the
 * messaging service.
 *
 * @version 26.05.2023
 */
@Transactional
@ThrowsInternal
@Slf4j
@Controller
public class MessagingController {


	// TODO replace HTTPServletResponse

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FileValidator validator;

	// TODO get messages after the fact
	// TODO extensively comment

	/**
	 * Method to be executed when a Websocket client sends a SUBSCRIBE
	 * message. <strong>Before subscribing to a {@link Chat} it is generally
	 * advisable to add the requesting {@link User} to the Chat.</strong> An
	 * Exception will be thrown if a User tries to access a personal chat
	 * they are not registered with. If they try to access a room chat they
	 * are not registered with they will be registered and the method will
	 * execute normally. A warning will be given in the HTTP response though.
	 *
	 * @param chatID The ID of the {@link Chat} the client wants to subscribe
	 * to.
	 * @param num The number of messages to be returned. Making this
	 * parameter 0 will result in all messages being returned.
	 * @param mTime
	 * @param userID The ID of the {@link User} the request is made for.
	 * @return A collection of messages from this chat.
	 *
	 * @throws RestAuthenticationException if an anonymous {@link User} tries
	 * to access a personal chat they are not registered with..
	 * @throws InsufficientRightsException if a logged-in {@link User} tries
	 * to access a personal chat they are not registered with.
	 * @throws MalformedRequestException if necessary header parameters like
	 * <code>mTime</code> or <code>userID</code> are not specified.
	 * @throws UnacceptableRequestException if the {@link User} with the
	 * specified ID does not exist.
	 * @throws ResourceNotFoundException if the {@link Chat} with the
	 * specified ID does not exist.
	 */
//	@Transactional
	@SendsError
	@SubscribeMapping({"/{chatID}"})
	public Set<Message> subscribeTo(@DestinationVariable long chatID,
			@Header long num, @Header String mTime, @Header Long userID) {
		log.info("Subscription event is being processed.");
		if (userID == null) {
			throw new MalformedRequestException("Field 'userID' must be "
					+ "specified");
		}
		/*
		 * If the User does not exist an Exception will be thrown.
		 */
		User user = userRepo.findById(userID).orElseThrow(() -> {
			return new UnacceptableRequestException("User does not exist.");
		});
//		else if (!userRepo.existsById(user)) {
//			throw new UnacceptableRequestException("User does not exist.");
//		}
		if (mTime == null) {
			throw new MalformedRequestException("Field 'mTime' must be "
					+ "specified");
		}
		/*
		 * The returned messages are ordered so that the highest ID comes first.
		 */
		TreeSet<Message> messages = new TreeSet<>((a, b) -> {
			long idA = a.getId();
			long idB = b.getId();
			return (idA > idB) ? -1 : (idA < idB) ? 1 : 0;
		});
		/*
		 * An Exception will be thrown if the Chat does
		 * not exist.
		 */
		Chat chat = chatRepo.findById(chatID).orElseThrow(
				ResourceNotFoundException::new);
		/*
		 * The validity of the request is checked and the server's reaction
		 * to the request are defined.
		 */
		this.handleUser(user, chat);
		// TODO messages
		if (num == 0) {
			messages.addAll(chat.getMessage());
		}
		log.info("Subscription done");
//		throw new RuntimeException("You should see an error.");
		return messages;
	}

	// TODO actual anonymous
	// TODO clone
	// TODO database validation
	// TODO chat validation
	// TODO actual error handling
	@SendsError
	@SendTo("/topic/{chatID}")
	@MessageMapping("/{chatID}")
//	@Transactional
	public Message forwardMessage(@DestinationVariable long chatID,
			@Payload Message msg) {
		log.info("Processing send event");
		if (msg == null) {
			throw new MalformedRequestException("Message must be given.");
		}
		/*
		 * This needs to be checked because of the next steps. DO NOT DELETE.
		 */
		else if (msg.getAuthorID() == null) {
			throw new MalformedRequestException("Author must be specified.");
		}
		/*
		 * An Exception resulting in a 404 will be thrown if the Chat does
		 * not exist.
		 */
		Chat chat = chatRepo.findById(chatID).orElseThrow(() -> {
			return new ResourceNotFoundException("Chat with the id " + chatID
					+ "does not exist.");
		});
		/*
		 * If the User does not exist an Exception will be thrown. A 404 is
		 * not thrown to not give the client the false idea that a Chat could
		 * not be found.
		 */
		User user = userRepo.findById(msg.getAuthorID().getId()).orElseThrow(() -> {
			return new UnacceptableRequestException("User does not exist.");
		});
		/*
		 * The validity of the action is checked.
		 */
		this.handleUser(user, chat);
		/*
		 * Check because next steps. DO NOT DELETE.
		 */
		if (msg.getContentType() == null || msg.getContentPath() == null) {
			throw new IllegalEntityException("Attribute that may not be null "
					+ "was null.", msg);
		}
		/*
		 * Check validity of file path.
		 */
		if ( msg.getContentType().equals(ContentType.FILE) &&
				!validator.isValidPath(msg.getContentPath()) ) {
			throw new IllegalEntityException("Invalid file path.", msg);
		}
		msg.setAuthorID(user);
		this.saveMessage(msg, chat);
		log.info(msg.getContentPath());
		return msg;
	}

	// TODO get message fom DB

	/**
	 * Saves a {@link Message} to the database. This method is {@link Transactional}
	 * and will do a rollback if it fails at any point.
	 *
	 * @param msg The {@link Message} to be saved.
	 * @param chat The {@link Chat} the {@link Message} is a part of.
	 */
//	@Transactional
	@UpholdsIntegrity
	public void saveMessage(Message msg, Chat chat) {
		messageRepo.save(msg);
		// The message is added and the Chat saved too
		Set<Message> msgs = chat.getMessage();
		msgs.add(msg);
		chat.setMessage(msgs);
		chatRepo.save(chat);
	}

	// TODO notifications

	/**
	 * Checks whether a {@link User} is allowed to make a given request in
	 * relation to the specified {@link Chat}. This check generally works
	 * like this:
	 *
	 * <ul>
	 *     <li>
	 *         If the Chat is a room chat the request is generally permitted.
	 *     </li>
	 *     <ul>
	 *        <li>
	 *            If the User was not registered with the room chat before
	 *            a warning may be given by the calling method.
	 *        </li>
	 *     </ul>
	 *     <li>
	 *         If the Chat is a personal chat the following actions may be
	 *         taken:
	 *     </li>
	 *     <ol>
	 *         <li>
	 *             If the User is anonymous a STOMP ERROR will be thrown.
	 *         </li>
	 *         <li>
	 *             If the User is registered with the Chat the request will be
	 *             considered valid.
	 *         </li>
	 *         <li>
	 *             If the User is logged in and not registered with the chat
	 *             a STOMP ERROR will be thrown.
	 *         </li>
	 *     </ol>
	 * </ul>
	 *
	 * <strong>Note that these checks are not supposed to enforce
	 * security. This {@link Controller} generally expects requests to
	 * be made in good faith and not by a bad actor. These checks are made
	 * for the sole purpose of upholding integrity and finding errors.</strong>
	 *
	 * @param user The {@link User} making the request.
	 * @param chat The {@link Chat} the request was made for.
	 * @return A number representing a {@link GeneralCode}.
	 *
	 * @throws RestAuthenticationException if an anonymous {@link User} tries
	 * to access a personal chat.
	 * @throws InsufficientRightsException if a logged-in {@link User} tries
	 * to access a personal chat they are not registered with.
	 */
	private int checkRequestValidity(User user, Chat chat) {
		ChatType type = chat.getChatType();
		/*
		 * An Exception will be thrown if the User is anonymous and the
		 * Chat is a personal one.
		 */
		ChatController.checkRegisterValidity(user, chat);
		// For Users who are logged in
		long userID = user.getId();
		/*
		 * Check whether the User is registered with the Chat.
		 */
		for (User iUser : chat.getUsers()) {
			if (iUser.getId() == userID) {
				return GeneralCode.OK;
			}
		}
		/*
		 * An Exception will be thrown if a logged-in User tries to access a
		 * Chat they are not registered with.
		 */
		if (type.equals(ChatType.PERSONAL)) {
			throw new InsufficientRightsException("The specified user is "
					+ "not registered with the chat.");
		}
		/*
		 * A status signalling a waring is returned.
		 */
		return GeneralCode.WARN;
	}

	/**
	 * Checks whether a request is valid ({@link MessagingController#checkRequestValidity(User, Chat)})
	 * and acts on a positive outcome by registering the specified
	 * {@link User} to the specified {@link Chat} if they were not registered
	 * before.
	 *
	 * @param user The {@link User} causing the request.
	 * @param chat The {@link Chat} associated with the request.
	 *
	 * @return An int in the form of {@link GeneralCode} values.
	 *
	 * @throws RestAuthenticationException if an anonymous {@link User} tries
	 * to access a personal chat.
	 * @throws InsufficientRightsException if a logged-in {@link User} tries
	 * to access a personal chat they are not registered with.
	 */
	private int handleUser(User user, Chat chat) {
		/*
		 * The validity status is retrieved. Exceptions will be thrown if there
		 * is a problem.
		 */
		int status = this.checkRequestValidity(user, chat);
		/*
		 * The User is registered if they were not registered before.
		 */
		if (status == GeneralCode.WARN) {
			chat.getUsers().add(user);
			chatRepo.save(chat);
			String warn = "User was not registered with the specified chat so "
					+ "it was registered by the service";
		}
		return status;
	}

}

package thowl.wiprojekt.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.*;
import thowl.wiprojekt.objects.ChatType;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.MessageRepository;
import thowl.wiprojekt.repository.UserRepository;

import java.util.Set;
import java.util.TreeSet;

/**
 * This {@link Controller} is responsible for the core functionality of the
 * messaging service.
 *
 * @version 26.05.2023
 */
@Controller
@ThrowsInternal
public class MessagingController {



	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private UserRepository userRepo;

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
	 * @param response The response sent by the server.
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
	@SubscribeMapping({"/topic/{chatID}"})
	public Set<Message> subscribeTo(@PathVariable long chatID,
			@Header long num, @Header String mTime, @Header Long userID,
			HttpServletResponse response) {
		if (userID == null) {
			throw new MalformedRequestException("Field 'user' must be "
					+ "specified");
		}
		/*
		 * If the User does not exist an Exception will be thrown. A 404 is
		 * not thrown to not give the client the false idea that a Chat could
		 * not be found.
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
		 * An Exception resulting in a 404 will be thrown if the Chat does
		 * not exist.
		 */
		Chat chat = chatRepo.findById(chatID).orElseThrow(
				ResourceNotFoundException::new);
		/*
		 * The validity of the request is checked and the server's reaction
		 * to the request are defined.
		 */
		response = this.handleUser(user, chat, response);
		// TODO messages
		if (num == 0) {
			messages.addAll(chat.getMessage());
		}
		return messages;
	}

	// TODO clone
	// TODO database validation
	// TODO chat validation

	@SendTo("/topic/{chatID}")
	@MessageMapping("{chatID}")
	public Message forwardMessage(@PathVariable long chatID, Message msg,
			HttpServletResponse response) {
		if (msg == null) {
			throw new MalformedRequestException("Message must be given.");
		}
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
		// TODO long instead of User
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
		response = this.handleUser(user, chat, response);
		msg.setAuthorID(user);
		this.saveMessage(msg, chat);
		return msg;
	}

	/**
	 * Saves a {@link Message} to the database. This method is {@link Transactional}
	 * and will do a rollback if it fails at any point.
	 *
	 * @param msg The {@link Message} to be saved.
	 * @param chat The {@link Chat} the {@link Message} is a part of.
	 */
	@Transactional
	private void saveMessage(Message msg, Chat chat) {
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
	 *             If the User is anonymous a 401 error code will be thrown.
	 *         </li>
	 *         <li>
	 *             If the User is registered with the Chat the request will be
	 *             considered valid.
	 *         </li>
	 *         <li>
	 *             If the User is logged in and not registered with the chat
	 *             403 error will be thrown.
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
	 * @return An {@link HttpStatus} used internally. If the status is a 202
	 * this means a warning should be given to the client because even though
	 * the request is valid it is unusual and may point towards an error.
	 *
	 * @throws RestAuthenticationException if an anonymous {@link User} tries
	 * to access a personal chat they are not registered with..
	 * @throws InsufficientRightsException if a logged-in {@link User} tries
	 * to access a personal chat they are not registered with.
	 */
	private HttpStatus checkRequestValidity(User user, Chat chat) {
		ChatType type = chat.getChatType();
		Role role = user.getRole();
		/*
		 * A 401 will be thrown if a non-logged-in User tries to access a
		 * personal chat.
		 */
		if (role.equals(Role.ANONYMOUS)) {
			if (type.equals(ChatType.PERSONAL)) {
				throw new RestAuthenticationException("Users have to be logged in"
						+ " to access personal chats.");
			}
		}
		// For Users who are logged in
		else {
			long userID = user.getId();
			/*
			 * Check whether the User is registered with the Chat.
			 */
			for (User iUser : chat.getUsers()) {
				if (iUser.getId() == userID) {
					return HttpStatus.OK;
				}
			}
			/*
			 * A 403 will be thrown if a logged-in User tries to access a
			 * Chat they are not registered with.
			 */
			if (type.equals(ChatType.PERSONAL)) {
				throw new InsufficientRightsException("The specified user is "
						+ "not registered with the chat.");
			}
		}
		/*
		 * If the status is not an error or a 200 a 202 will be returned to
		 * show that the request is valid but a warning should be returned
		 * because the request may result from error.
		 */
		return HttpStatus.ACCEPTED;
//		if (chat.getChatType().equals(ChatType.PERSONAL) &&
//				user.getRole().equals(Role.ANONYMOUS)) {
//			throw new RestAuthenticationException("Users have to be logged in"
//					+ " to access personal chats.");
//		}
//		long userID = user.getId();
//		for (User iUser : chat.getUsers()) {
//			if (iUser.getId() == userID) {
//				return HttpStatus.OK;
//			}
//		}
//
//		throw new InsufficientRightsException();
	}

	/**
	 * Checks whether a request is valid ({@link MessagingController#checkRequestValidity(User, Chat)})
	 * and acts on a positive outcome by registering the specified
	 * {@link User} to the specified {@link Chat} and issuing a warning in
	 * the HTTP response if the User had not been registered with the Chat
	 * before.
	 *
	 * @param user The {@link User} causing the request.
	 * @param chat The {@link Chat} associated with the request.
	 * @param response The response given by the server.
	 * @return The response given by the server.
	 *
	 * @throws RestAuthenticationException if an anonymous {@link User} tries
	 * to access a personal chat they are not registered with..
	 * @throws InsufficientRightsException if a logged-in {@link User} tries
	 * to access a personal chat they are not registered with.
	 */
	private HttpServletResponse handleUser(User user, Chat chat,
			HttpServletResponse response) {
		/*
		 * The HTTP status is retrieved. Exceptions will be thrown if there
		 * is a problem.
		 */
		int status = this.checkRequestValidity(user, chat).value();
		/*
		 * The User is registered.
		 */
		chat.getUsers().add(user);
		chatRepo.save(chat);
		String warn;
		/*
		 * Define warning.
		 */
		if (status == 202) {
			warn = "User was not registered with the specified chat so it was"
					+ " registered by the service";
		}
		else {
			warn = "None";
		}
		response.setHeader("Warning", warn);
		return response;
	}

	private String checkFile(String path) {

		if ( !(path.matches("file:///")) ) {

		}
		return new String();
	}

}

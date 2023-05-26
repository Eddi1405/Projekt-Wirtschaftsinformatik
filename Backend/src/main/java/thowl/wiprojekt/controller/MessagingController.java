package thowl.wiprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.errors.MalformedRequestException;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.errors.UnacceptableRequestException;
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
public class MessagingController {

	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private UserRepository userRepo;

	@SubscribeMapping({"/topic/{chatID}"})
	public Set<Message> subscribeTo(@PathVariable long chatID,
			@Header long num, @Header String mTime, @Header Long user) {
		if (user == null) {
			throw new MalformedRequestException("Field 'user' must be "
					+ "specified");
		}
		else if (!userRepo.existsById(user)) {
			throw new UnacceptableRequestException("User does not exist.");
		}
		if (mTime == null) {
			throw new MalformedRequestException("Field 'mTime' must be "
					+ "specified");
		}
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
		// TODO contains user
		if (num == 0) {
			messages.addAll(chat.getMessage());
		}
		return messages;
	}

}

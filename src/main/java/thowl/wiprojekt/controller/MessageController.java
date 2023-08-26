package thowl.wiprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.errors.ThrowsInternal;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.MessageRepository;

import java.util.Set;

/**
 * {@link org.springframework.stereotype.Controller} used to retrieve
 * {@link Message}s.
 */
@Transactional
@RestController
@ThrowsInternal
public class MessageController {

	// Used to retrieve messages
	@Autowired
	private MessageRepository messageRepo;

	// Used to retrieve chats
	@Autowired
	private ChatRepository chatRepo;

	/**
	 * Returns <strong>all</strong> {@link Message}s from the specified
	 * {@link Chat}.
	 *
	 * @param chatID The ID of the {@link Chat}.
	 *
	 * @return A {@link Set} of {@link Message}s in this {@link Chat}.
	 *
	 */
	@GetMapping(value = "/chats/{chatID}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<Message> getMessagesOfChat(@PathVariable long chatID) {
		Chat chat = chatRepo.findById(chatID).orElseThrow( () ->
				{return new ResourceNotFoundException("Chat with the ID " +
						chatID + " does not exist.");
				});
		Set<Message> messages = chat.getMessage();
		/*
		 * Init lazily loaded collection.
		 */
		messages.size();
		return messages;
	}
	@GetMapping(value = "/chats/{chatID}/complete", produces =
			MediaType.APPLICATION_JSON_VALUE)
	public Chat getCompleteChat(@PathVariable long chatID) {
		Chat chat = chatRepo.findById(chatID).orElseThrow( () ->
		{return new ResourceNotFoundException("Chat with the ID " +
				chatID + " does not exist.");
		});
		return chat;
	}
}

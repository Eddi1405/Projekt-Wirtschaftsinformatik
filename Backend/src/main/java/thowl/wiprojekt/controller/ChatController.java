package thowl.wiprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.*;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link org.springframework.stereotype.Controller} giving access to
 * {@link Chat}s.
 *
 * @version 27.05.2023
 */
@RestController
@ThrowsInternal
public class ChatController {

	// TODO EntityModels

	// Used to retrieve chats
	@Autowired
	private ChatRepository chatRepo;

	@Autowired
	private UserRepository userRepo;

	/**
	 * Returns a {@link Chat} object corresponding to the given ID. <strong>
	 * Note that this method will not return the messages associated with the
	 * given chat.</strong>
	 *
	 * @param chatID The ID of the requested {@link Chat}.
	 * @return The {@link Chat} corresponding to the ID.
	 */
	@GetMapping(value = "/chats/{chatID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Chat getChat(@PathVariable long chatID) {
		Chat chat = this.checkChatExists(chatID);
		// Messages inside of this chat are not returned
		chat.setMessage(new HashSet<Message>());
		return chat;
	}

	// TODO null

	/**
	 * Creates a new Chat.
	 *
	 * @param pChat The new {@link Chat} to be created.
	 * @return The newly created {@link Chat}.
	 */
	@PostMapping(value = "/chats/create/")
	public Chat addChat(@RequestBody Chat pChat) {
		// A newly created chat should not have any messages
		if (!pChat.getMessage().isEmpty()) {
			throw new MalformedRequestException("A newly created chat should "
					+ "not contain any messages.");
		}
		Chat chat = chatRepo.save(pChat);
		// Messages inside of this chat are not returned
		chat.setMessage(new HashSet<Message>());
		return chat;
	}

	/**
	 * Deletes the {@link Chat} with the specified ID.
	 *
	 * @param chatID the ID of the {@link Chat} to be deleted.
	 */
	@DeleteMapping("/chats/delete/{chatID}")
	public void deleteChat(@PathVariable long chatID) {
		this.checkChatExists(chatID);
		chatRepo.deleteById(chatID);
	}

	@PutMapping(value = "/chats/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public Chat updateChat() {
		return null;
	}

	/**
	 * Checks whether the {@link Chat} with the given ID exists.
	 *
	 * @param chatID The ID to be checked.
	 * @return The {@link Chat} if it exists.
	 */
	Chat checkChatExists(long chatID) {
//		if (!chatRepo.existsById(chatID)) {
//			throw new ResourceNotFoundException("Chat with the specified ID "
//					+ chatID + " does not exist.");
//		}
		return chatRepo.findById(chatID).orElseThrow(() -> {
			return new ResourceNotFoundException("Chat with the specified ID " +
					chatID + " does not exist.");
		});
	}

	// TODO anon
	// TODO room deletion
	// TODO admin

	@Deprecated
	Chat checkRequestValidity(long userID, long chatID) {
		Chat chat = this.checkChatExists(chatID);
		User potAdmin = userRepo.findById(userID).orElseThrow( () -> {
								return new UnacceptableRequestException("User " + userID +
									"does not exist.");
							});
		if (potAdmin.getRole().equals(Role.ADMIN)) {
			return chat;
		}
		Set<User> users = chat.getUsers();
		for (User user : users) {
			if (user.getId() == userID) {
				return chat;
			}
		}
		throw new InsufficientRightsException("The requesting user is not "
				+ "part of the specified chat and may not request the "
				+ "requested action.");
	}

}

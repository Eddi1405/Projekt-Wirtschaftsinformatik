package thowl.wiprojekt.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;
import thowl.wiprojekt.errors.MalformedRequestException;
import thowl.wiprojekt.repository.UserRepository;

import java.util.Map;
import java.util.Set;

/**
 * Intercepts STOMP {@link Message}s of the {@link StompCommand} types
 * CONNECT and DISCONNECT to maintain a list of
 * {@link thowl.wiprojekt.entity.User}s connected to the messaging service.
 *
 * @see thowl.wiprojekt.entity.Message
 */
@Slf4j
@Service
public class MessageInterceptor implements ChannelInterceptor {

	@Autowired
	private UserRepository userRepo;

	/**
	 * This method gets called before a {@link Message} is sent to its
	 * corresponding channel. If the Message implements the {@link StompCommand}
	 * CONNECT the {@link thowl.wiprojekt.entity.User} represented by the
	 * user ID in the header will be added to the list of online users. If it
	 * is of the DISCONNECT command the User is removed from the list.
	 */
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		// The headers of the Message
		MessageHeaders head = message.getHeaders();
		// The command type of the message
		StompCommand command = StompHeaderAccessor.getCommand(head);
		// Header fields and their respective values
		Set<Map.Entry<String, Object>> pairs = head.entrySet();
		// Unchecked assignment should not be a problem hopefully
		Map.Entry<String, Object>[] arr = new Map.Entry[pairs.size()];
		Map<String, Object> headers = Map.ofEntries(pairs.toArray(arr));
		// TODO check exists
		if (!headers.containsKey("userID")) {
			throw new MalformedRequestException("Message must have a header "
					+ "field userID.");
		}
		Long userID = (Long) headers.get("userID");
		// TODO other check exists
		if (command != null && command.equals(StompCommand.CONNECT)) {
			// TODO How to save
			log.info("User " + userID + " has connected to the service.");
		}
		else if (command != null && command.equals(StompCommand.DISCONNECT)) {
//			StompHeaderAccessor
			log.info("User " + userID + " has disconnected from the service.");
		}
		return message;
	}

}

package thowl.wiprojekt.chat;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.Map;
import java.util.Set;

public class MessageInterceptor implements ChannelInterceptor {

	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		MessageHeaders head = message.getHeaders();
		StompCommand command = StompHeaderAccessor.getCommand(head);
		Set<Map.Entry<String, Object>> pairs = head.entrySet();
		// Unchecked assignment should not be a problem hopefully
		Map.Entry<String, Object>[] arr = new Map.Entry[pairs.size()];
		Map<String, Object> headers = Map.ofEntries(pairs.toArray(arr));
		// TODO check exists
		Long userID = (Long) headers.get("userID");
		// TODO other check exists
		if (command != null && command.equals(StompCommand.CONNECT)) {
			// TODO How to save
		}
		else if (command != null && command.equals(StompCommand.DISCONNECT)) {
//			StompHeaderAccessor
		}
		return message;
	}

}

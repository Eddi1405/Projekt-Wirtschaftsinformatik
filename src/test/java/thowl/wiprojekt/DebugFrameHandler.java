package thowl.wiprojekt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import thowl.wiprojekt.entity.Message;

import java.lang.reflect.Type;

@Slf4j
public class DebugFrameHandler implements StompFrameHandler {

	@Override
	public Type getPayloadType(StompHeaders headers) {
		return Message.class;
	}

	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		Message msg = (Message) payload;
		log.info(msg.getContentPath());
	}
}

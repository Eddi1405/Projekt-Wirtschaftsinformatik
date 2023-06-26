package thowl.wiprojekt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import thowl.wiprojekt.entity.Message;

// Idea from https://www.baeldung.com/websockets-api-java-spring-client

@Slf4j
public class DebugSessionHandler extends StompSessionHandlerAdapter {

	@Override
	public void afterConnected(StompSession session, StompHeaders headers) {
		log.info("Connected, now doing stuff.");
		log.info(session.subscribe("ws://localhost:8080/gs-guide"
						+ "-websocket/topic/greetings", this)
				.getSubscriptionId());
		log.info("Just subscribed.");
//		Message mess = new Message();
//		mess.setContentPath("hello");
		Message msg = new Message();
		msg.setContentPath("hello, this is a message");
		log.info(session.send("ws://localhost:8080/gs-guide-websocket/app"
						+ "/hello",
				msg).getReceiptId());
		log.info("Just sent a message. Ha, just kidding.");
	}

	@Override
	public void handleFrame(StompHeaders headers, Object msg) {
		log.info(msg.toString());
		Message mess = (Message) msg;
		log.info(mess.getContentPath());
	}

	@Override
	public void handleTransportError(StompSession session, Throwable error) {
		log.error(error.getMessage());
	}

	@Override
	public void handleException(StompSession session,
			@Nullable StompCommand cmd, StompHeaders headers, byte[] payload,
			Throwable error) {
		log.error(error.getMessage());
		error.printStackTrace();
	}

}
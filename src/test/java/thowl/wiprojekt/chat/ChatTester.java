package thowl.wiprojekt.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.objects.ContentType;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ChatTester {

	// Base url for messaging
	private final static String url = "ws://localhost:8080/connection";
	// Used to get Messages with a possible delay
	private static CompletableFuture<Message> future =
			new CompletableFuture<>();

	public static void main(String[] args)
			throws ExecutionException, InterruptedException, TimeoutException {
		setup();
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Adam");
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("Steve");
		StompSession session1 = subscribeUser(user1, 1, false);
		StompSession session2 = subscribeUser(user2, 1, true);
		Message msg = new Message();
		/*
		 * The Message is a text message
		 */
		msg.setContentType(ContentType.FILE);
		msg.setContentPath("/srv/dosc/pics/henlo.jpg");
		// Author of the Message
		msg.setAuthorID(user1);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		// The Message is sent
		session1.send("/chat/" + 1,
				msg);
		TimeUnit.SECONDS.sleep(10);
		assertEquals("oi", future.get(300, TimeUnit.SECONDS).getContentPath());
	}

	private static void setup() {
		future = new CompletableFuture<>();
	}

	private static StompSession subscribeUser(User user, long chatID,
			boolean complete)
			throws ExecutionException, InterruptedException, TimeoutException {
		ArrayList<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		// Mockup client for the SockJS library
		SockJsClient client = new SockJsClient(transports);
		// Actually used client
		WebSocketStompClient stomp = new WebSocketStompClient(client);
		// Used for correctly converting messages
		stomp.setMessageConverter(new MappingJackson2MessageConverter());
		// Used to schedule tasks
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		stomp.setTaskScheduler(scheduler);
//		stomp.connectAsync(url,
//				new StompSessionHandlerAdapter() {});
		/*
		 * Client connects to the server and creates a session.
		 */
		StompSession session = stomp.connectAsync(url,
				new StompSessionHandlerAdapter() {}).get(30, TimeUnit.SECONDS);
		log.info(session.getSessionId());
		session.setAutoReceipt(true);
//		StompSession.Subscription subb =session.subscribe(
//				"topic/topic/1",
//				new DebugSessionHandler());
//		log.info(subb.getSubscriptionId());
		// Headers used for sending
		StompHeaders headers = new StompHeaders();
		headers.add("destination", "/topic/" + chatID);
		headers.add("num", "0");
		headers.add("mTime", "2023-07-01");
		headers.add("userID", String.valueOf(user.getId()));
		/*
		 * The client subscribes to the topic.
		 */
		StompSession.Subscription sub =session.subscribe(
//						"/topic/1",
				headers,
				new ChatTester.LocalDebugFrameHandler(complete));
		log.info(sub.getSubscriptionId());
		return session;
	}

	private static class LocalDebugFrameHandler implements StompFrameHandler {

		private boolean complete;

		public LocalDebugFrameHandler(boolean pComplete) {
			complete = pComplete;
		}

		/**
		 * Returns the {@link Type} the message payloads have. This is the
		 * type {@link Message} respectively.
		 *
		 * @param headers The headers of the frame.
		 * @return The {@link Message} {@link Class}.
		 */
		@Override
		public Type getPayloadType(StompHeaders headers) {
			return Message.class;
		}

		/**
		 * Handles incoming frames and completes the {@link CompletableFuture}
		 * with the payload {@link Message}.
		 *
		 * @param headers The headers of the frame.
		 * @param payload The payload of the frame.
		 */
		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			Message msg = (Message) payload;
			if (complete) {
				future.complete(msg);
			}
		}
	}

}

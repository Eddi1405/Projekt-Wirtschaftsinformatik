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
import thowl.wiprojekt.objects.Role;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Quick and dirty solution for testing WebSocket messaging and showing it
 * off. This is not supposed to be an actual test.
 *
 * @version 06.09.2023
 */
// Idea from https://medium.com/@MelvinBlokhuijzen/spring-websocket-endpoints-integration-testing-180357b4f24c
@Slf4j
public class ChatTester {

	// Base url for messaging
	private final static String url = "ws://localhost:8080/connection";
	// Used to get Messages with a possible delay
	private static CompletableFuture<Message> future =
			new CompletableFuture<>();
	private static ArrayDeque<CompletableFuture<Message>> futures =
			new ArrayDeque<>();

	static {
		for (int i = 0; i < 5; i++) {
			futures.push(new CompletableFuture<>());
		}
	}


	/**
	 * Execute the test.
	 *
	 * <p></p>
	 * <strong>Preconditions:</strong>
	 * <ul>
	 *     <li>
	 *         <strong>
	 *             Execution does not happen on PROD. This may only be
	 *             executed inside of a test environment.
	 *         </strong>
	 *     </li>
	 *     <li>
	 *         There exists a {@link User} with the ID 1 and the name
	 *         Adam who has a {@link Role} that is neither null nor
	 *         {@link Role#ANONYMOUS}.
	 *     </li>
	 *     <li>
	 *         There exists a {@link User} with the ID 2 and the name Steve
	 *         who has a {@link Role} that is neither null nor
	 *         {@link Role#ANONYMOUS}.
	 *     </li>
	 *     <li>
	 *         There exists a {@link thowl.wiprojekt.entity.Chat} with the ID
	 *         1 that is of the type
	 *         {@link thowl.wiprojekt.objects.ChatType#PERSONAL}.
	 *     </li>
	 *     <li>
	 *         There exists a {@link thowl.wiprojekt.entity.Chat} with the ID
	 *         2 that is of the type
	 *         {@link thowl.wiprojekt.objects.ChatType#ROOM}.
	 *     </li>
	 *     <li>
	 *         Both {@link User}s should be registered with the
	 *         {@link thowl.wiprojekt.entity.Chat} 1.
	 *     </li>
	 * </ul>
	 *
	 * @param args Possible arguments. Will not be used.
	 *
	 * @throws ExecutionException when there is a problem with the used
	 * {@link CompletableFuture}.
	 * @throws InterruptedException when the used {@link CompletableFuture} is
	 * interrupted.
	 * @throws TimeoutException when the used {@link CompletableFuture} is
	 * timed out.
	 */
	public static void main(String[] args)
			throws ExecutionException, InterruptedException, TimeoutException {
		// Initialize future
		setup();
		executeTest();
	}

	/**
	 * Does setup work for the test execution.
	 */
	private static void setup() {
		future = new CompletableFuture<>();
//		ArrayDeque<CompletableFuture<Message>> fu
	}

	private static void executeTest()
			throws ExecutionException, InterruptedException, TimeoutException {
		// The sender in the scenario
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Adam");
		// The receiver in the scenario
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("Steve");
		StompSession sess1 = getSession(user1);
		StompSession sess2 = getSession(user2);
		subscribeUser(user1, 1, sess1, false);
		subscribeUser(user2, 1, sess2, true);
		subscribeUser(user2, 2, sess2, true);
		Message msg1 = new Message();
		msg1.setContentType(ContentType.FILE);
		msg1.setContentPath("/srv/dosc/pics/henlo.jpg");
		// Author of the Message
		msg1.setAuthorID(user1);
		msg1.setTime(new Timestamp(System.currentTimeMillis()));
		try {
			sendMessage(sess1, msg1, 1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		User userAnon = new User();
		userAnon.setId(-1);
		StompSession sessionAnon = getSession(userAnon);
		subscribeUser(userAnon, 2, sessionAnon, false);
		Message msgAnon = new Message();
		msgAnon.setContentType(ContentType.TEXT);
		msgAnon.setContentPath("hello there");
		msgAnon.setAuthorID(userAnon);
		msgAnon.setTime(new Timestamp(System.currentTimeMillis()));
		try {
			sendMessage(sessionAnon, msgAnon, 2);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Subscribes a {@link User} to the specified {@link thowl.wiprojekt.entity.Chat}.
	 *
	 * @param user The {@link User} to be subscribed.
	 *
	 * @return The session the {@link User} is part of.
	 * @throws ExecutionException when there is a problem with the used
	 * {@link CompletableFuture}.
	 * @throws InterruptedException when the used {@link CompletableFuture} is
	 * interrupted.
	 * @throws TimeoutException when the used {@link CompletableFuture} is
	 * timed out.
	 */
	private static StompSession getSession(User user)
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
		return session;
	}

	private static void subscribeUser(User user, long chatID,
			StompSession session, boolean complete) {
		// Headers used for sending
		StompHeaders headers = new StompHeaders();
		headers.add("destination", "/topic/" + chatID);
		headers.add("num", "0");
		headers.add("mTime", "2023-07-01");
		headers.add("userID", String.valueOf(user.getId()));
		/*
		 * The client subscribes to the topic.
		 */
		StompSession.Subscription sub = session.subscribe(
//						"/topic/1",
				headers,
				new ChatTester.LocalDebugFrameHandler(complete));
		log.info(sub.getSubscriptionId());
	}

	private static void sendMessage(StompSession session, Message msg,
			long chatID)
			throws ExecutionException, InterruptedException, TimeoutException {
//		User user1 = new User();
//		user1.setId(1);
//		user1.setUsername("Adam");
//		// The receiver in the scenario
//		User user2 = new User();
//		user2.setId(2);
//		user2.setUsername("Steve");
		// The two users' sessions.
//		StompSession session1 = subscribeUser(user1, chatID);
//		StompSession session2 = subscribeUser(user2, chatID);
//		Message msg = new Message();
//		// The message is created
//		msg.setContentType(ContentType.FILE);
//		msg.setContentPath("/srv/dosc/pics/henlo.jpg");
//		// Author of the Message
//		msg.setAuthorID(user1);
//		msg.setTime(new Timestamp(System.currentTimeMillis()));
		// The Message is sent
		session.send("/chat/" + chatID,
				msg);
		// Give time because STOMP is asynchronous
		TimeUnit.SECONDS.sleep(10);
		log.info(futures.pop().get().getContentPath());
	}

	/**
	 * Used to handle incoming frames.
	 */
	private static class LocalDebugFrameHandler implements StompFrameHandler {

		private boolean complete;

		/**
		 * Constructor of the class.
		 *
		 * @param pComplete <code>true</code> if a {@link CompletableFuture}
		 * is supposed to be completed by this frame handler.
		 */
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
				futures.peek().complete(msg);
			}
		}
	}

}

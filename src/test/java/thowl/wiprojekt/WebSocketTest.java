package thowl.wiprojekt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import thowl.wiprojekt.controller.UserController;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.objects.ContentType;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.UserRepository;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Idea from https://medium.com/@MelvinBlokhuijzen/spring-websocket-endpoints-integration-testing-180357b4f24c

/**
 * Used to test some messaging capabilities. Not an actual Unit Test, this is
 * supposed to be a quick and dirty way of testing.
 *
 * @author Michael Hartmann
 */
@Slf4j
@Deprecated
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//classes = thowl.wiprojekt.WiProjektApplication.class)
//)
public class WebSocketTest {

	// Base url for messaging
	private static String url;
	// URL for testing
	private static String invalidURL;
	// Used to get Messages with a possible delay
	private static CompletableFuture<Message> future;

	/**
	 * Used to start the test since this is not an actual unit test class.
	 *
	 * @param args Possible arguments.
	 * @throws ExecutionException when there is a problem with the used
	 * {@link CompletableFuture}.
	 * @throws InterruptedException when the used {@link CompletableFuture} is
	 * interrupted.
	 * @throws TimeoutException when the used {@link CompletableFuture} is
	 * timed out.
	 */
	public static void main(String[] args)
			throws ExecutionException, InterruptedException, TimeoutException,
			Exception{
		setup();
		testSend();
	}

	/**
	 * Initializes certain attributes.
	 */
//	@BeforeEach
	public static void setup() {
		future = new CompletableFuture<>();
		url = "ws://localhost:8080/connection";
//		url = "ws://193.16.123.46:8081/connection";
		invalidURL = "ws://localhost:8080";
	}

	/**
	 * Execute the actual test. This method creates a messaging client that
	 * subscribes to a topic and sends a message to that topic.
	 *
	 * @throws ExecutionException when there is a problem with the used
	 * {@link CompletableFuture}.
	 * @throws InterruptedException when the used {@link CompletableFuture} is
	 * interrupted.
	 * @throws TimeoutException when the used {@link CompletableFuture} is
	 * timed out.
	 */
//	@Test
	@Transactional(rollbackFor = Exception.class)
	public static void testSend()
			throws ExecutionException, InterruptedException, TimeoutException,
			Exception{
//		User user = new User();
//		user.setUsername("McUsername");
//		user.setPassword("McPassword");
//		user.setRole(Role.STUDENT);
//		uRepo.save(user);
		WebSocketTest.testMessaging(1, "Adam", 1, "Hello, this is a message.",
				ContentType.TEXT);
//		WebSocketTest.testMessaging(2, -1, "oi, mate", ContentType.TEXT);
//		WebSocketTest.testMessaging(1, 1, "/srv/doc/henlo.jpg",
//				ContentType.FILE);
//		WebSocketTest.testMessaging(1, 1, "oi", ContentType.FILE);
//		ArrayList<Transport> transports = new ArrayList<>();
//		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//		// Mockup client for the SockJS library
//		SockJsClient client = new SockJsClient(transports);
//		// Actually used client
//		WebSocketStompClient stomp = new WebSocketStompClient(client);
//		// Used for correctly converting messages
//		stomp.setMessageConverter(new MappingJackson2MessageConverter());
//		// Used to schedule tasks
//		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//		scheduler.initialize();
//		stomp.setTaskScheduler(scheduler);
////		stomp.connectAsync(url,
////				new StompSessionHandlerAdapter() {});
//		/*
//		 * Client connects to the server and creates a session.
//		 */
//		StompSession session = stomp.connectAsync(url,
//				new StompSessionHandlerAdapter() {}).get(30, TimeUnit.SECONDS);
//		log.info(session.getSessionId());
//		session.setAutoReceipt(true);
////		StompSession.Subscription subb =session.subscribe(
////				"topic/topic/1",
////				new DebugSessionHandler());
////		log.info(subb.getSubscriptionId());
//		// Headers used for sending
//		StompHeaders headers = new StompHeaders();
//		headers.add("destination", "/topic/1");
//		headers.add("num", "0");
//		headers.add("mTime", "2023-07-01");
//		headers.add("userID", "1");
//		/*
//		 * The client subscribes to the topic.
//		 */
//		StompSession.Subscription sub =session.subscribe(
////						"/topic/1",
//				headers,
//				new LocalDebugFrameHandler());
//		log.info(sub.getSubscriptionId());
//		// Message to be sent
//		Message msg = new Message();
//		/*
//		 * The Message is a text message
//		 */
////		msg.setContentType(ContentType.TEXT);
//		msg.setContentType(ContentType.FILE);
////		msg.setContentPath("hello, this is a message");
////		msg.setContentPath("henlo");
////		msg.setContentPath("/srv/docs/pics/");
//		msg.setContentPath("/srv/dosc/pics/henlo.jpg");
//		// Author of the Message
//		User user = new User();
//		user.setId(1);
//		user.setUsername("Adam");
//		msg.setAuthorID(user);
//		msg.setTime(new Timestamp(System.currentTimeMillis()));
//		// The Message is sent
//		session.send("/chat/1",
//				msg);
//		Message msg2 = new Message();
//		msg2.setContentType(ContentType.TEXT);
//		msg2.setContentPath("hello, this is a message");
//		User user2 = new User();
//		user2.setId(1);
//		user2.setUsername("Steve");
//		msg2.setAuthorID(user2);
//		msg2.setTime(new Timestamp(System.currentTimeMillis()));
//		future.complete();
//		sub.addReceiptTask(() -> {
//			log.info("sending");
//			session.send("/chat/1", msg2);
//		});
		/*
		 * The assertion fails. That way it is possible to see the Message.
		 */
		assertEquals("oi", future.get(300, TimeUnit.SECONDS).getContentPath());
		throw new Exception("This is for rollback.");
	}

//	private class MockSessionHandler implements StompFrameHandler {
//
//		@Override
//		public Type getPayloadType(StompHeaders headers) {
//			return HelloMessage.class;
//		}
//
//		@Override
//		public void handleFrame(StompHeaders headers, Object payload) {
//			future.complete((HelloMessage) payload);
//		}
//	}

	/**
	 * Used to handle incoming frames ({@link Message}s) so that the
	 * {@link CompletableFuture} may be completed.
	 */
	private static class LocalDebugFrameHandler implements StompFrameHandler {

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
			future.complete(msg);
		}
	}

	/**
	 * Execute the actual test. Should be called by
	 * {@link WebSocketTest#testSend()}.
	 *
	 * @param userID The id of the user the test is done with.
	 * @param username The name of the user,
	 * @param chatID The id of the Chat.
	 * @param content The content of the chat.
	 * @param type The type of the chat.
	 *
	 * @throws ExecutionException when there is a problem with the used
	 * {@link CompletableFuture}.
	 * @throws InterruptedException when the used {@link CompletableFuture} is
	 * interrupted.
	 * @throws TimeoutException when the used {@link CompletableFuture} is
	 * timed out.
	 */
	private static void testMessaging(long userID, String username, long chatID,
			String content, ContentType type)
			throws ExecutionException, InterruptedException, TimeoutException {
		User user = new User();
		user.setId(userID);
		user.setUsername(username);
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
		headers.add("userID", String.valueOf(userID));
		/*
		 * The client subscribes to the topic.
		 */
		StompSession.Subscription sub =session.subscribe(
//						"/topic/1",
				headers,
				new LocalDebugFrameHandler());
		log.info(sub.getSubscriptionId());
		// Message to be sent
		Message msg = new Message();
		/*
		 * The Message is a text message
		 */
//		msg.setContentType(ContentType.TEXT);
		msg.setContentType(type);
//		msg.setContentPath("hello, this is a message");
//		msg.setContentPath("henlo");
//		msg.setContentPath("/srv/docs/pics/");
		msg.setContentPath(content);
		// Author of the Message
		msg.setAuthorID(user);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		// The Message is sent
		session.send("/chat/" + chatID,
				msg);
	}

}
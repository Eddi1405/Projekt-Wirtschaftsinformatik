package thowl.wiprojekt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Idea from https://medium.com/@MelvinBlokhuijzen/spring-websocket-endpoints-integration-testing-180357b4f24c

@Slf4j
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//classes = thowl.wiprojekt.WiProjektApplication.class)
//)
public class WebSocketTest {

	private static String url;
	private static String invalidURL;
	private static CompletableFuture<Message> future;

	public static void main(String[] args)
			throws ExecutionException, InterruptedException, TimeoutException {
		setup();
		testSend();
	}

//	@BeforeEach
	public static void setup() {
		future = new CompletableFuture<>();
		url = "ws://localhost:8080/connection";
		invalidURL = "ws://localhost:8080";
	}

//	@Test
	public static void testSend()
			throws ExecutionException, InterruptedException, TimeoutException {
		ArrayList<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		SockJsClient client = new SockJsClient(transports);
		WebSocketStompClient stomp = new WebSocketStompClient(client);
		stomp.setMessageConverter(new MappingJackson2MessageConverter());
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		stomp.setTaskScheduler(scheduler);
//		stomp.connectAsync(url,
//				new StompSessionHandlerAdapter() {});
		StompSession session = stomp.connectAsync(url,
				new StompSessionHandlerAdapter() {}).get(30, TimeUnit.SECONDS);
		log.info(session.getSessionId());
		session.setAutoReceipt(true);
//		StompSession.Subscription subb =session.subscribe(
//				"topic/topic/1",
//				new DebugSessionHandler());
//		log.info(subb.getSubscriptionId());
		StompHeaders headers = new StompHeaders();
		headers.add("destination", "/topic/1");
		headers.add("num", "0");
		headers.add("mTime", "2023-07-01");
		headers.add("userID", "1");
		StompSession.Subscription sub =session.subscribe(
//						"/topic/1",
				headers,
				new DebugSessionHandler());
		log.info(sub.getSubscriptionId());
		Message msg = new Message();
		msg.setContentType(ContentType.TEXT);
		msg.setContentPath("hello, this is a message");
		User user = new User();
		user.setId(1);
		user.setUsername("Adam");
		msg.setAuthorID(user);
		msg.setTime(new Timestamp(System.currentTimeMillis()));
		session.send("/chat/1",
				msg);
		Message msg2 = new Message();
		msg2.setContentType(ContentType.TEXT);
		msg2.setContentPath("hello, this is a message");
		User user2 = new User();
		user2.setId(1);
		user2.setUsername("Steve");
		msg2.setAuthorID(user2);
		msg2.setTime(new Timestamp(System.currentTimeMillis()));
		// TODO add future completion
//		future.complete();
//		sub.addReceiptTask(() -> {
//			log.info("sending");
//			session.send("/chat/1", msg2);
//		});
		assertEquals("oi", future.get(300, TimeUnit.SECONDS).getContentPath());
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

}
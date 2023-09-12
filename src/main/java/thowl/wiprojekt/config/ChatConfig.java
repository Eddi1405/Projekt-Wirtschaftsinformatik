package thowl.wiprojekt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for chats and messaging.
 *
 * @author Michael Hartmann
 * @version 26.05.2023
 */
@Configuration
@EnableWebSocketMessageBroker
public class ChatConfig implements WebSocketMessageBrokerConfigurer {

	/*
	 * Generally speaking an endpoint is a server or in this case URL handling
	 * communication between clients and servers. The endpoint is responsible
	 * for things like the SockJS handshake for example.
	 *
	 * A broker is a component that sends given messages to its destination.
	 * The annotation @SendTo will be used here to use the broker specified
	 * in this class. Brokers are also responsible for the ability of clients
	 * to subscribe to certain topics.
	 *
	 * A prefix is used in the application layer to filter actions. They are
	 * used by the @MessageMapping annotation.
	 */

	/**
	 * Registers endpoints for the Websocket service and enables SockJS fallback
	 * options.
	 *
	 * @param reg The registry used to register endpoints.
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry reg) {
		reg.addEndpoint("/connection").setAllowedOrigins("*");
		reg.addEndpoint("/connection").setAllowedOrigins("*").withSockJS();
	}

	/**
	 * Used to configure the message broker(s) used by the websocket service
	 * as well as the used prefixes.
	 *
	 * @param reg Used to register the broker(s) and prefixes.
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry reg) {
		/*
		 * Trailing slash is used because it would get added automatically / for
		 * consistency.
		 */
		reg.enableSimpleBroker("/topic");
		/*
		 * Prefixes must not be specified for annotations.
		 */
		reg.setApplicationDestinationPrefixes("/chat", "/topic");
//		reg.setApplicationDestinationPrefixes("/chat");
	}
}

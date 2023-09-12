package thowl.wiprojekt.errors;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * An {@link Aspect} handling {@link Exception}s thrown by methods annotated
 * with a {@link SendsError} annotation. The Exceptions will be converted
 * into ERROR messages of the STOMP protocol and sent to the client.
 *
 * @author Michael Hartmann
 * @version 09.08.2023
 *
 * @see StompCommand#ERROR
 */
@Slf4j
@Component
@Aspect
public class StompErrorSender {

	// The outbound channel used to send messages to the client
	// thanks to https://stackoverflow.com/a/39641687
	@Autowired
	@Qualifier("clientOutboundChannel")
	private MessageChannel channel;


	/**
	 * The {@link Pointcut} used to mark the JoinPoint at which this
	 * {@link Aspect}'s advice may be executed. Affected JoinPoints are
	 * method executions annotated with {@link SendsError}.
	 */
	@Pointcut("execution(* thowl.wiprojekt.*.*.*(..)) && "
			+ "@annotation(SendsError)")
	public void onExecution() {

	}

	/**
	 * Wraps an {@link Exception} thrown by a method at the corresponding
	 * JoinPoint and uses the Exception's message to create a STOMP ERROR
	 * message and send it to the client.
	 *
	 * @param e The thrown {@link Exception}.
	 */
	@AfterThrowing(pointcut = "onExecution()", throwing = "e")
	public void wrapException(Exception e) {
		// Object used for accessing and setting headers
		StompHeaderAccessor accessor =
				StompHeaderAccessor.create(StompCommand.ERROR);
		// The message header should be specified by an error message
		accessor.setMessage(e.getMessage());
		// Session ID used to close the correct session
		String session = SimpAttributesContextHolder.getAttributes()
				.getSessionId();
		log.info(session);
		accessor.setSessionId(session);
		// The message does not have a payload because the error message
		// is part of the header
		GenericMessage<byte[]> msg =
				new GenericMessage<>(new byte[0], accessor.getMessageHeaders());
		// The ERROR message is sent
		channel.send(msg);
	}

}

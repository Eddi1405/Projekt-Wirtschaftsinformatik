package thowl.wiprojekt.errors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link java.lang.annotation.Annotation} to be used on methods that are used
 * for messaging and may throw {@link Exception}s that should be wrapped
 * in a STOMP {@link org.springframework.messaging.simp.stomp.StompCommand#ERROR}
 * message.
 *
 * @version 09.08.2023
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SendsError {

}

package thowl.wiprojekt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Used to enable AspectJ annotations and AOP.
 *
 * @version 17.05.2023
 *
 * @see thowl.wiprojekt.errors.InternalExceptionHandler
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

}

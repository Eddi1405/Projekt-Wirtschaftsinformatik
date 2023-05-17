package thowl.wiprojekt.errors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation signifies that methods of a class can directly be accessed
 * by an external client and should throw {@link InternalException}s instead
 * of other types of {@link Exception}s.
 *
 * @version 17.05.2023
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThrowsInternal {

}

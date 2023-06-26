package thowl.wiprojekt.errors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link java.lang.annotation.Annotation} to be used on methods that may
 * cause a {@link org.springframework.dao.DataIntegrityViolationException}
 * caused by a null value that should not have been null.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UpholdsIntegrity {
}

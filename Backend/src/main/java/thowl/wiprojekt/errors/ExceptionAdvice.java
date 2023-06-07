package thowl.wiprojekt.errors;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 * An {@link Aspect} defining operations to be executed after methods
 * annotated with certain {@link java.lang.annotation.Annotation}s have
 * thrown certain kinds of {@link Exception}s. This Aspect has a slightly
 * higher priority than the {@link InternalExceptionHandler} Aspect.
 *
 * @see ExceptionInterceptor
 */
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Component
@Aspect
public class ExceptionAdvice {

	/**
	 * The {@link Pointcut} used to mark the JoinPoint at which this
	 * {@link Aspect}'s advice may be executed. Affected JoinPoints are
	 * method executions of methods annotated with {@link UpholdsIntegrity}.
	 */
	@Pointcut("execution(* thowl.wiprojekt.*.*.*(..)) && @annotation"
			+ "(UpholdsIntegrity)")
	public void onIntegrityViolation() {
		// empty Pointcut
	}

	/**
	 * This method is executed after a method annotated with {@link UpholdsIntegrity}
	 * has thrown a {@link DataIntegrityViolationException}. If this
	 * Exception was thrown because of a null value that should not have been
	 * null an {@link IllegalEntityException} is thrown instead to cause a
	 * 422 error. Else an {@link InternalException} is thrown.
	 *
	 * @param e The thrown {@link DataIntegrityViolationException}.
	 */
	@AfterThrowing(pointcut = "onIntegrityViolation()", throwing = "e")
	public void giveIntegrityError(DataIntegrityViolationException e) {
		/*
		 * Throwing because of a null value is determined by the Exception's
		 * message.
		 */
		if (e.getMessage().matches(
				".*?not-null property references.*?null.*?")) {
			throw new IllegalEntityException("An attribute that should not be"
					+ " null was null: " + e.getMessage(),e);
		}
		else {
			throw new InternalException(e, e.getMessage());
		}
	}

}

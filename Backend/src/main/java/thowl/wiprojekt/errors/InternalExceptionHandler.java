package thowl.wiprojekt.errors;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

/**
 * An {@link Aspect} used to convert {@link Exception}s thrown by objects
 * whose class is annotated with {@link ThrowsInternal} into Exceptions that
 * can be intercepted by an {@link ExceptionInterceptor}.
 *
 * @version 17.05.2023
 */
@Component
@Aspect
public class InternalExceptionHandler {

	// TODO maybe use non self-defined types

	/**
	 * The {@link Pointcut} used to mark the JoinPoint at which this
	 * {@link Aspect}'s advice may be executed. Affected JoinPoints are
	 * method executions of objects whose class is annotated with
	 * {@link ThrowsInternal}.
	 */
	@Pointcut("execution(* thowl.wiprojekt.*.*.*(..)) && @target(ThrowsInternal)")
	public void onExecution() {
		System.out.println("Aspect method executed");
	}

	/**
	 * Wraps a thrown {@link Exception} into one that can be processed by an
	 * {@link ExceptionInterceptor}.
	 *
	 * @param e The thrown {@link Exception}.
	 */
	@SneakyThrows(Exception.class)
	@AfterThrowing(pointcut = "onExecution()", throwing = "e")
	public void wrapException(Exception e) {
		/*
		 * If the Exception is an Exception that can be intercepted by an
		 * ExceptionInterceptor it is thrown again.
		 */
		if (e instanceof ProjectException ||
				e instanceof HttpRequestMethodNotSupportedException) {
			throw e;
		}
		/*
		 * Else it is converted to a type that can be intercepted.
		 */
		else {
			throw new InternalException(e, e.getMessage());
		}
	}


}

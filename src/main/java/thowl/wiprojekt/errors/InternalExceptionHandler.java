package thowl.wiprojekt.errors;

import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.lang.reflect.Method;

/**
 * An {@link Aspect} used to convert {@link Exception}s thrown by objects
 * whose class is annotated with {@link ThrowsInternal} into Exceptions that
 * can be intercepted by an {@link ExceptionInterceptor}. Note that this
 * Aspect has the lowest possible priority when processed.
 *
 * @author Michael Hartmann
 * @version 17.05.2023
 */
@Order(Ordered.LOWEST_PRECEDENCE)
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

	}

	/**
	 * Wraps a thrown {@link Exception} into one that can be processed by an
	 * {@link ExceptionInterceptor}.
	 *
	 * @param join The {@link JoinPoint} the the {@link Exception} is thrown by.
	 * @param e The thrown {@link Exception}.
	 */
	@SneakyThrows(Exception.class)
	@AfterThrowing(pointcut = "onExecution()", throwing = "e")
	public void wrapException(JoinPoint join, Exception e) {
		// The Method designated by the JoinPoint
		Method meth = ((MethodSignature) join.getSignature()).getMethod();
		/*
		 * The Exception is only processed if it is not processed by another
		 * Aspect beforehand.
		 */
		if (!(meth.isAnnotationPresent(UpholdsIntegrity.class) &&
				e instanceof DataIntegrityViolationException)) {
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

}

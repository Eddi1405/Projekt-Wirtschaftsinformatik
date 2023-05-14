package thowl.wiprojekt.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <quote>Intercepts</quote> thrown {@link Exception}s to return
 * {@link HttpStatus} error codes instead.
 *
 * @author Michael Hartmann
 * @version 06.05.2023
 */
@Slf4j
@ControllerAdvice
public class ExceptionInterceptor {

	/**
	 * Intercepts {@link ResourceAlreadyExistsException}s to return a code
	 * <em>409 (conflict)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public String interceptExistingResource(ResourceAlreadyExistsException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link ResourceNotFoundException}s to return a code
	 * <em>404 (not found)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ResourceNotFoundException.class)
	public String interceptNotFound(ResourceNotFoundException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link RestAuthenticationException}s to return a code
	 * <em>401 (unauthorized)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(RestAuthenticationException.class)
	public String interceptRights(RestAuthenticationException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link InsufficientRightsException}s to return a code
	 * <em>403 (forbidden)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(InsufficientRightsException.class)
	public String interceptRights(InsufficientRightsException e) {
//		var p = new IllegalEntityException(new UserData());
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link MethodNotSupportedException}s to return a code
	 * <em>405 (method not allowed)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(MethodNotSupportedException.class)
	public String interceptRights(MethodNotSupportedException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link IllegalEntityException}s to return a code
	 * <em>422 (unprocessable content)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(IllegalEntityException.class)
	public String interceptIllegalEntity(IllegalEntityException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link InternalException}s to return a code
	 * <em>500 (internal server error)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InternalException.class)
	public String interceptInternal(InternalException e) {
		this.logException(e, true);
		return e.getMessage();
	}

	/**
	 * Intercepts {@link MalformedRequestException}s to return a code
	 * <em>400 (bad request)</em>.
	 *
	 * @param e The intercepted {@link Exception}.
	 * @return The {@link Exception}'s error message.
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MalformedRequestException.class)
	public String interceptMalformedRequest(MalformedRequestException e) {
		this.logException(e, false);
		return e.getMessage();
	}

	/**
	 * Logs the stacktrace of an {@link Exception}.
	 *
	 * @param e The {@link Exception} to be logged.
	 * @param error <code>true</code> if the {@link Exception} should be logged
	 * as an error, <code>false</code> otherwise.
	 */
	private void logException(Exception e, boolean error) {
		// Used to convert stacktrace to String
		// idea from https://www.baeldung.com/java-stacktrace-to-string
		StringWriter stringer = new StringWriter();
		PrintWriter writer = new PrintWriter(stringer);
		e.printStackTrace(writer);
		if (error) {
			log.error(stringer.toString());
		}
		else {
			log.warn(stringer.toString());
		}
	}

}

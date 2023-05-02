package thowl.wiprojekt.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <quote>Intercepts</quote> thrown {@link Exception}s to return
 * {@link HttpStatus} error codes instead.
 *
 * @author Michael Hartmann
 * @version 30.04.2023
 */
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
		return e.getMessage();
	}

}

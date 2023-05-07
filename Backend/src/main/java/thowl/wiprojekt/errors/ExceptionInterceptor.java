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
 * @version 06.05.2023
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
		return e.getMessage();
	}
}

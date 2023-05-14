package thowl.wiprojekt.errors;

/**
 * To be thrown when a client has to be authenticated to access a resource or
 * when authentication failed.
 */
public class RestAuthenticationException extends RuntimeException
		implements ProjectException {

	// TODO boolean?

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public RestAuthenticationException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "RestAuthenticationException: " + this.getMessage();
	}

}

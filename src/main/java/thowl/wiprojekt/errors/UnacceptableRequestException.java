package thowl.wiprojekt.errors;

/**
 * To be thrown when content negotiation
 * (<a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Content_negotiation">see here</a>)
 * fails because of a bad request by the client.
 *
 * @version 26.05.2023
 */
public class UnacceptableRequestException extends RuntimeException
		implements ProjectException {

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public UnacceptableRequestException() {
		super("Content negotiation failed because of a semantically bad "
				+ "request.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public UnacceptableRequestException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "UnacceptableRequestException: " + this.getMessage();
	}

}

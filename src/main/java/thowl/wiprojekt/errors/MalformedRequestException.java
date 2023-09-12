package thowl.wiprojekt.errors;

/**
 * To be thrown when a request does not conform to the expected parameters.
 *
 * @author Michael Hartmann
 * @version 07.05.2023
 */
public class MalformedRequestException extends IllegalArgumentException
		implements ProjectException {

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public MalformedRequestException() {
		super("The request did not conform to expected parameters.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public MalformedRequestException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "MalformedRequestException: " + this.getMessage();
	}

}

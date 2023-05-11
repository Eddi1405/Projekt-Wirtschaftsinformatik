package thowl.wiprojekt.errors;

/**
 * A general Exception akin to the {@link Exception} class. To be used
 * instead of this class when communicating with a client to make sure
 * possible behaviour is clearly defined.
 *
 * @version 07.05.2023
 */
public class InternalException extends RuntimeException implements
		ProjectException{

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public InternalException() {
		super("An internal error occurred.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public InternalException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "InternalException: " + this.getMessage();
	}

}

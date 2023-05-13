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

	private Exception except;

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
	 * Constructor of the class.
	 *
	 * @param wrappedException {@link Exception} that is the reason for this
	 * InternalException to be thrown.
	 * @param msg The message this {@link Exception} should have.
	 */
	public InternalException(Exception wrappedException, String msg) {
		super(msg);
		except = wrappedException;
	}

	/**
	 * Returns the {@link Exception} responsible for the creation of this
	 * InternalException. May be <code>null</code>.
	 *
	 * @return The {@link Exception} responsible for the creation of this
	 * InternalException.
	 */
	public Exception getExcept() {
		return except;
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

	/**
	 * Prints the stack trace of this {@link Exception} and the one that may
	 * be wrapped by it.
	 */
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		if (except != null) {
			System.err.println("InternalException caused by:\n");
			except.printStackTrace();
		}
	}

}

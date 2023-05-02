package thowl.wiprojekt.errors;

/**
 * To be thrown when a given resource already exists.
 *
 * @author Michael Hartmann
 * @version 30.04.2023
 */
public class ResourceAlreadyExistsException extends RuntimeException{

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public ResourceAlreadyExistsException() {
		super("The provided resource already exists.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public ResourceAlreadyExistsException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "ResourceAlreadyExistsException: " + this.getMessage();
	}
}

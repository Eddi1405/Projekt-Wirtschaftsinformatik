package thowl.wiprojekt.errors;

/**
 * To be thrown when a requested resource is not found.
 *
 * @author Michael Hartmann
 * @version 30.04.2023
 */
public class ResourceNotFoundException extends RuntimeException implements
		ProjectException{

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public ResourceNotFoundException() {
		super("The provided resource could not be found.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "ResourceNotFoundException: " + this.getMessage();
	}
}

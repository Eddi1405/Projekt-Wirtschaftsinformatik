package thowl.wiprojekt.errors;

/**
 * To be thrown when the client tries to access a resource that the client is
 * not allowed to under the circumstances. This would for example include a
 * user trying to access resources only allowed to be accessed by users of a
 * specific group the user is not part of.
 *
 * @version 06.05.2023
 *
 * @see thowl.wiprojekt.entity.UserData
 */
public class InsufficientRightsException extends RuntimeException implements
		ProjectException{

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public InsufficientRightsException() {
		super("Insufficient rights to access this resource.");
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public InsufficientRightsException(String msg) {
		super(msg);
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "InsufficientRightsException: " + this.getMessage();
	}
}

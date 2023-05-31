package thowl.wiprojekt.errors;

import jakarta.persistence.Entity;

/**
 * To be thrown when an attribute of an {@link Entity} that should exist does
 * not actually exist. This is for example the case when an Entity has a one-to-many
 * relationship wih another Entity and the former contains instances of the
 * latter that do not exist. Saving the former Entity would therefore result
 * in a constraint violation.
 *
 * @version 31.05.2023
 */
public class AttributeDoesNotExistException extends DatabaseConflictException {

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public AttributeDoesNotExistException(String msg) {
		super(msg);
	}

	/**
	 * Returns this {@link Exception} as a String.
	 *
	 * @return This {@link Exception} in the form of a String.
	 */
	public String toString() {
		return "AttributeDoesNotExistException: " + this.getMessage();
	}

}

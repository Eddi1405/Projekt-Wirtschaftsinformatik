package thowl.wiprojekt.errors;

/**
 * {@link Exception} to be extended by Exceptions that signal a database
 * conflict.
 *
 * @version 31.05.2023
 */
public abstract class DatabaseConflictException extends RuntimeException
		implements ProjectException{

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 */
	public DatabaseConflictException(String msg) {
		super(msg);
	}

}

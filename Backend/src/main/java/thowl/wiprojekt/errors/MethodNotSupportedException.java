package thowl.wiprojekt.errors;

import org.springframework.http.HttpMethod;

/**
 * To be thrown when a client tries to connect to a URL via an unsupported
 * {@link HttpMethod}.
 */
@Deprecated
public class MethodNotSupportedException extends RuntimeException
		implements ProjectException{

	// The unsupported method type
	private HttpMethod method;

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 */
	public MethodNotSupportedException() {
		super("Method not supported.");
	}

	/**
	 * Constructor of the class. Uses the specified {@link HttpMethod} to
	 * generate an error message.
	 *
	 * @param pMethod The unsupported {@link HttpMethod}. <strong>Should not
	 * be null.</strong>
	 */
	public MethodNotSupportedException(HttpMethod pMethod) {
		super("Method " + pMethod.name() + " not supported.");
		// TODO test
		/*
		 * Clone object.
		 */
		method = HttpMethod.valueOf(pMethod.toString());
	}

	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "MethodNotSupportedException: " + this.getMessage();
	}


}

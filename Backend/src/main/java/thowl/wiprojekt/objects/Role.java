package thowl.wiprojekt.objects;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Roles a user might have when using the service. It is advised that only
 * one type of role should be used by a user.
 *
 * @author Michael Hartmann
 * @version 28.04.2023
 */
public enum Role {

	/**
	 * Role for users who are not logged in.
	 */
	ANONYMOUS ("anonymous"),
	/**
	 * Role for students that use the service.
	 */
	STUDENT ("student"),

	/**
	 * Role for teachers (including educators like professors) who use the
	 * service.
	 */
	TEACHER ("teacher"),

	/**
	 * Role for support workers.
	 */
	SUPPORT ("support"),

	/**
	 * Role for admins.
	 */
	ADMIN ("admin");

	// The name of the role as a String
	private String name;

	/**
	 * The class' constructor.
	 *
	 * @param roleName The name the Role should have.
	 */
	private Role(String roleName) {
		name = new String(roleName);
	}

	/**
	 * Returns the name of the Role as a String.
	 *
	 * @return The name of the Role.
	 */
	@JsonValue
	public String getName() {
		return new String(name);
	}

}

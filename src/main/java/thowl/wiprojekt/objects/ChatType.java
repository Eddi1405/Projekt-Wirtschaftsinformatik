package thowl.wiprojekt.objects;

// TODO Link

import com.fasterxml.jackson.annotation.JsonValue;
import thowl.wiprojekt.entity.User;

/**
 * This enum represents types chats can have.
 *
 * @author Michael Hartmann
 * @version 23.05.2023
 */
public enum ChatType {

	/**
	 * A personal chat for which {@link User}s have to be registered before
	 * they can participate.
	 */
	PERSONAL ("personal"),

	/**
	 * An open chat in which everyone can participate without much restriction.
	 */
	ROOM ("room"),

	// Only theoretical for now.
	/**
	 * A channel for sending notifications.
	 */
	NOTIF ("notif");

	// Name of the type
	private String name;

	/**
	 * Constructor of this enum.
	 *
	 * @param typeName The name types are supposed to have.
	 */
	private ChatType(String typeName) {
		name = typeName;
	}

	/**
	 * Returns the name of the type as a String.
	 *
	 * @return The name of the type.
	 */
	@JsonValue
	public String getName() {
		return new String(name);
	}

}

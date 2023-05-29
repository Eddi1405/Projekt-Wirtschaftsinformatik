package thowl.wiprojekt.objects;

// TODO Links

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the different types of content a message can have.
 */
public enum ContentType {

	/**
	 * Used for messages that transport text.
	 */
	TEXT ("text"),

	/**
	 * Used for messages that transport files like pictures.
	 */
	FILE ("file");


	// Name of the type
	private String name;

	private ContentType(String typeName) {
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

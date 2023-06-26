package thowl.wiprojekt.objects;

/**
 * Very simple enum giving access to file separators.
 *
 * @version 07.05.2023
 */
public enum Separator {

	/**
	 * The file separator used by the operating system. Is either a backslash
	 * <qoute>\</qoute> on Windows systems or a slash <qoute>/</qoute> on all
	 * other systems.
	 */
	SEP (System.getProperty("file.separator")),

	/**
	 * The file separator of the OS as used in Java Regex Queries.
	 */
	REGEX ("regex");

	// The enum value
	private String str;

	/**
	 * Constructor of the enum.
	 *
	 * @param val The String value the level should have.
	 */
	private Separator(String val) {
		/*
		 * The Regex level has to be inferred.
		 */
		if (val.equals("regex")) {
			/*
			 * If the OS is Windows the special regex way of displaying the
			 * backslash has to be used. Else a normal slash is used.
			 */
			if (System.getProperty("os.name").contains("Windows") ||
					System.getProperty("os.name").contains("windows")) {
				str = "\\\\";
			}
			else {
				str = "/";
			}
		}
		else {
			str = val;
		}
	}

	/**
	 * Returns the String represented by this enum level.
	 *
	 * @return The String represented by this enum level.
	 */
	public String str() {
		return new String(str);
	}

	/**
	 * Returns a String representation of the enum level.
	 *
	 * @return A String representation of the enum level.
	 */
	@Override
	public String toString() {
		return this.str();
	}

}

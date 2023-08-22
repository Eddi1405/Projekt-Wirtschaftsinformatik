package thowl.wiprojekt.service;

import org.springframework.stereotype.Service;

/**
 * Validates file paths.
 *
 * @version 05.06.2023
 */
@Service
public class FileValidator {

//	private String os = System.getProperty("os.name");

	// Characters that may not be in a file name
//	private final String ILLCHARS = "[^<>\\|\":/\\\\\\?\\*]";
//	private final String DIR = "(/" + ILLCHARS + "+?)+?/?";

	/**
	 * Checks whether a file path is valid.
	 *
	 * @param filePath The file path to be checked.
	 * @return <code>true</code> if the file path is valid, <code>false</code>
	 * otherwise.
	 */
	public boolean isValidPath(String filePath) {
//		String regex = "^/??(?<ill>[^<>|\":/\\\\?*]+?)(/\\k<ill>)+?$";
		String regex = "^(/[^<>|/\":\\\\?*]+?){2,}?$";
		return filePath.matches(regex);
	}

}

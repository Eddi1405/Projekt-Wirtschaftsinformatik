package thowl.wiprojekt.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

/**
 * May be used to encode files to a String in base 64 and decode these into
 * files again.
 *
 * @version 07.05.2023
 */
@Service
@NoArgsConstructor
public class MediaNDcoder {

	// TODO large files
	// TODO robust programming

	/**
	 * Encodes a {@link Blob} to a {@link Base64} String.
	 *
	 * @param blob The {@link Blob} to be encoded.
	 * @return The given {@link Blob} as a String in base 64.
	 *
	 * @throws SQLException when the {@link Blob} value could not be accessed.
	 * @throws IOException when an error occurs reading bytes from the
	 * {@link Blob}.
	 */
	public String blobToBase64(Blob blob) throws SQLException, IOException {
		if (blob == null) {
			throw new IllegalArgumentException("Blob is null");
		}
		byte[] bytes = blob.getBinaryStream().readAllBytes();
		return this.bytesToBase64(bytes);
	}

	/**
	 * Encodes a {@link File} into a {@link Base64} String.
	 *
	 * @param file The {@link File} to be encoded.
	 * @return The {@link File} encoded as a base 64 String.
	 *
	 * @throws IOException when a problem occurs while reading the {@link File}.
	 */
	public String fileToBase64(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File is null");
		}
		byte[] bytes = Files.readAllBytes(file.toPath());
		return this.bytesToBase64(bytes);
	}

	/**
	 * Converts a {@link File} object to a byte array.
	 *
	 * @param file The {@link File} to be converted.
	 * @return The given {@link File} as a byte array.
	 * @throws IOException when a problem occurs while reading the {@link File}.
	 */
	public byte[] fileToBytes(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File is null");
		}
		return Files.readAllBytes(file.toPath());
	}

	/**
	 * Encodes an array of bytes to a {@link Base64} String.
	 *
	 * @param bytes The bytes to be encoded.
	 * @return The bytes as a String encoded in base 64.
	 */
	public String bytesToBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	// TODO no SerialBlob

	/**
	 * Creates a {@link Blob} from the given String encoded in {@link Base64}.
	 *
	 * @param encStr The encoded String to be decoded.
	 * @return A {@link SerialBlob} created from the String.
	 *
	 * @throws SQLException when an error occurs creating the {@link Blob}.
	 */
	public Blob base64ToBlob(String encStr) throws SQLException {
		byte[] bytes = this.base64ToBytes(encStr);
		return new SerialBlob(bytes);
	}

	/**
	 * Creates a {@link File} from the given String encoded in {@link Base64}.
	 *
	 * @param encStr The encoded String to be decoded.
	 * @param path The {@link Path} at which the {@link File} should be created.
	 * @return The {@link File} encoded by the String.
	 *
	 * @throws IOException when a problem occurs writing the {@link File}.
	 */
	public File base64ToFile(String encStr, Path path) throws IOException {
		byte[] bytes = this.base64ToBytes(encStr);
		return Files.write(path, bytes).toFile();
	}

	/**
	 * Decodes a {@link Base64} encoded String to an array of bytes.
	 *
	 * @param encStr The encoded String.
	 * @return The array of bytes encoded by the String.
	 */
	public byte[] base64ToBytes(String encStr) {
		if (encStr == null) {
			throw new IllegalArgumentException("String is null");
		}
		return Base64.getDecoder().decode(encStr);
	}


}

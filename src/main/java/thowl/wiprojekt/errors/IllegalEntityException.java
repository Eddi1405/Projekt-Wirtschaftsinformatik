package thowl.wiprojekt.errors;

import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * To be thrown when an {@link Entity} would have
 * attribute values considered to be illegal.
 * <p></p>
 * <strong>Note that the entity given to this {@link Exception} is not
 * copied. Be careful when using this class. It is advised to only give
 * copies of the entities in question into methods of this class.
 * </strong>
 *
 * @author Michael Hartmann
 * @version 06.05.2023
 */
public class IllegalEntityException extends IllegalArgumentException
		implements ProjectException {

	// The entity that caused the Exception
	@Getter
	private Object entity;

	/**
	 * Constructor of the class. The {@link Exception} will be instantiated
	 * with a generic error message.
	 *
	 * @param failedEntity The entity that cannot be used due to illegal values.
	 *
	 * @throws IllegalArgumentException if the Object is not an {@link Entity}.
	 */
	public IllegalEntityException(Object failedEntity) {
		super("The Entity could not be processed due to illegal values.");
		this.checkEntity(failedEntity);
		entity = failedEntity;
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 * @param failedEntity The entity that cannot be used due to illegal values.
	 *
	 * @throws IllegalArgumentException if the Object is not an {@link Entity}.
	 */
	public IllegalEntityException(String msg, Object failedEntity) {
		super(msg);
		this.checkEntity(failedEntity);
		entity = failedEntity;
	}

	/**
	 * Constructor of the class.
	 *
	 * @param msg The message this {@link Exception} should have.
	 * @param cause The cause of this {@link Exception}.
	 */
	public IllegalEntityException(String msg, Throwable cause) {
		super(msg, cause);
	}


	/**
	 * Returns a {@link String} representation of this {@link Exception}.
	 *
	 * @return This {@link Exception} as a {@link String}.
	 */
	@Override
	public String toString() {
		return "IllegalEntityException: " + this.getMessage();
	}

	/**
	 * Checks whether an Object is annotated with the {@link Entity} annotation.
	 *
	 * @param ent The Object to be checked.
	 * @return <code>true</code> if the Object is an {@link Entity}.
	 *
	 * @throws IllegalArgumentException if the Object is not an {@link Entity}.
	 */
	private boolean checkEntity(Object ent) {
		if (ent.getClass().isAnnotationPresent(Entity.class)) {
			return true;
		}
		throw new IllegalArgumentException(ent.toString() + " is not an "
				+ "Entity as defined by the JPA specification.");
	}

	/**
	 * Changes this {@link Exception}'s {@link Entity} to the one specified.
	 *
	 * @param newEntity The {@link Entity} the {@link Exception}'s entity
	 * field should be changed to.
	 *
	 * @throws IllegalArgumentException if the Object is not an {@link Entity}.
	 */
	// @Setter is not used to throw Exception
	public void setEntity(Object newEntity) {
		this.checkEntity(newEntity);
		entity = newEntity;
	}

}

package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.entity.Thread;

/**
 * Creates {@link EntityModel}s from {@link Thread} objects.
 *
 * @author Michael Hartmann
 * @version 20.05.2023
 */
@Component
public class ThreadModelAssembler implements
		RepresentationModelAssembler<Thread, EntityModel<Thread>> {

	/**
	 * Converts the specified {@link Thread} to an {@link EntityModel}.
	 *
	 * @param entity The {@link Thread} to be converted.
	 * @return The {@link EntityModel} representation of the specified
	 * {@link Thread}.
	 */
	@Override
	public EntityModel<Thread> toModel(Thread entity) {
		EntityModel<Thread> model = EntityModel.of(entity);
		return model;
	}
}

package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.controller.UserController;
import thowl.wiprojekt.entity.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Creates {@link EntityModel}s from {@link User} objects.
 *
 * @author Michael Hartmann
 * @version 13.05.2023
 */
@Component
public class UserModelAssembler implements RepresentationModelAssembler<User,
		EntityModel<User>> {

	// TODO slash or methodOn

	/**
	 * Converts the specified {@link User} to an {@link EntityModel}.
	 *
	 * @param entity The {@link User} to be converted.
	 * @return The {@link EntityModel} representation of the specified
	 * {@link User}.
	 */
	@Override
	public EntityModel<User> toModel(User entity) {
		//EntityModel of the specified User
		EntityModel<User> model = EntityModel.of(entity,
//				linkTo(UserController.class).slash(entity.getId())
//				.withSelfRel(),
				// Object links to all the important methods
				linkTo(methodOn(UserController.class).getUser(entity.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity)).withRel("update"),
				linkTo(methodOn(UserController.class).deleteUser(entity.getId())).withRel("delete"));
		return model;
	}
}

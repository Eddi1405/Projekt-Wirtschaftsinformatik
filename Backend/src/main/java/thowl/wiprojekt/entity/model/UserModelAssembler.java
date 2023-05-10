package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.controller.LoginController;
import thowl.wiprojekt.controller.UserController;
import thowl.wiprojekt.entity.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User,
		EntityModel<User>> {

	// TODO slash or methodOn

	@Override
	public EntityModel<User> toModel(User entity) {
		//EntityModel of the specified User
		EntityModel<User> model = EntityModel.of(entity,
//				linkTo(UserController.class).slash(entity.getId())
//				.withSelfRel(),
				linkTo(methodOn(UserController.class).getUser(entity.getId())).withSelfRel());
		return model;
	}
}

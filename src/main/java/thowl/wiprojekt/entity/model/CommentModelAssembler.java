package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.entity.Comment;

/**
 * Creates {@link EntityModel}s from {@link Comment} objects.
 *
 * @author Michael Hartmann
 * @version 20.05.2023
 */
@Component
public class CommentModelAssembler implements
		RepresentationModelAssembler<Comment, EntityModel<Comment>> {

	/**
	 * Converts the specified {@link Comment} to an {@link EntityModel}.
	 *
	 * @param entity The {@link Comment} to be converted.
	 * @return The {@link EntityModel} representation of the specified
	 * {@link Comment}.
	 */
	@Override
	public EntityModel<Comment> toModel(Comment entity) {
		EntityModel<Comment> model = EntityModel.of(entity);
		return model;
	}
}

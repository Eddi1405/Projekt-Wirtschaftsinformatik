package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.controller.UserController;
import thowl.wiprojekt.entity.Message;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Creates {@link EntityModel}s from {@link Message}s.
 *
 * @version 29.08.2023
 */
@Component
public class MessageModelAssembler implements RepresentationModelAssembler<Message,
		EntityModel<Message>> {

	/**
	 * Converts the given {@link Message} to an {@link EntityModel}.
	 *
	 * @param message The {@link Message} to be converted.
	 *
	 * @return The converted {@link EntityModel}. It may contain a link to the
	 * author.
	 */
	@Override
	public EntityModel<Message> toModel(Message message) {
		// Link to show the author
		Link authorLink = null;
		/*
		 * If the Link to the author cannot be retrieved it is not included.
		 */
		try {
			authorLink = linkTo(methodOn(UserController.class)
					.getUser(message.getAuthorID().getId()).getBody()).withRel("author");
		}
		catch (Exception e) {
			// Do nothing
		}
		/*
		 * The EntityModel is returned with or without the Link.
		 */
		return (authorLink == null) ? EntityModel.of(message)
				: EntityModel.of(message, authorLink);
	}

	/**
	 * Creates a {@link CollectionModel} of the given
	 * {@link java.util.Collection}.
	 *
	 * @param messages The {@link java.util.Collection} of {@link Message}s
	 * to be converted.
	 *
	 * @return The converted {@link CollectionModel}.
	 */
	@Override
	public CollectionModel<EntityModel<Message>> toCollectionModel(
			Iterable<? extends Message> messages) {
		return RepresentationModelAssembler.super.toCollectionModel(messages);
	}
}

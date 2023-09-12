package thowl.wiprojekt.entity.model;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import thowl.wiprojekt.controller.ChatController;
import thowl.wiprojekt.entity.Chat;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Creates {@link EntityModel}s from {@link Chat}s.
 *
 * @author Michael Hartmann
 */
@Component
public class ChatModelAssembler implements RepresentationModelAssembler<Chat,
		EntityModel<Chat>> {

	/**
	 * Converts the given {@link Chat} to an {@link EntityModel}.
	 *
	 * @param chat The {@link Chat} to be converted.
	 *
	 * @return The converted {@link EntityModel}. It contains a self
	 * reference to the {@link Chat}.
	 */
	@Override
	public EntityModel<Chat> toModel(Chat chat) {
		return EntityModel.of(chat,
				linkTo(methodOn(ChatController.class).getChat(chat.getId())).withSelfRel());
	}

	/**
	 * Creates a {@link CollectionModel} of the given
	 * {@link java.util.Collection}.
	 *
	 * @param chats The {@link java.util.Collection} of {@link Chat}s
	 * to be converted.
	 *
	 * @return The converted {@link CollectionModel}.
	 */
	@Override
	public CollectionModel<EntityModel<Chat>> toCollectionModel(
			Iterable<? extends Chat> chats) {
		return RepresentationModelAssembler.super.toCollectionModel(chats);
	}
}

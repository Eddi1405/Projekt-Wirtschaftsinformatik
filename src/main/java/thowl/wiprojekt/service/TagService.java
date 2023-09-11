package thowl.wiprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thowl.wiprojekt.entity.Tag;
import thowl.wiprojekt.repository.TagRepository;

import java.util.List;
import java.util.Optional;
/**
 * Author Florian Laufer
 */
@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        return optionalTag.orElse(null);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag updateTag(long id, Tag updatedTag) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            Tag existingTag = optionalTag.get();
            existingTag.setDescription(updatedTag.getDescription());
            return tagRepository.save(existingTag);
        } else {
            return null;
        }
    }

    public boolean deleteTag(long id) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (optionalTag.isPresent()) {
            tagRepository.delete(optionalTag.get());
            return true;
        } else {
            return false;
        }
    }
}

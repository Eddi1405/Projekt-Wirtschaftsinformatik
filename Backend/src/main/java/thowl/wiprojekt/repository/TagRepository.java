package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.TagData;

/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the entity.
 */
@Repository
public interface TagRepository extends JpaRepository<TagData, Long> {

}

package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.Tag;
//Edward Siebert
/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the entity.
 * @Author Edward Siebert
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}

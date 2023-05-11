package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.Thread;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the entity.
 */
@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

    Optional<Thread> findByauthorID(int authorID);

    List<Thread> findAllByheader(String header);

    List<Thread> findAllBydate(Date date);
}

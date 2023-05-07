package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.ThreadData;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface ThreadJpaRepository extends JpaRepository<ThreadData, Long> {

    Optional<ThreadData> findByauthorID(int authorID);

    List<ThreadData> findAllByheader(String header);

    List<ThreadData> findAllBydate(Date date);
}

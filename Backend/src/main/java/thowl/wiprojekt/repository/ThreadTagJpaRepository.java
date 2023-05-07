package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.ThreadTagData;

/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface ThreadTagJpaRepository extends JpaRepository<ThreadTagData, Long> {

}

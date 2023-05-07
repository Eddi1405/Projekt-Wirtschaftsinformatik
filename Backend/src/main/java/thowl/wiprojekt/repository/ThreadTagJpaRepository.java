package thowl.wiprojekt.repository;

import thowl.wiprojekt.entity.ThreadTagData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface ThreadTagJpaRepository extends JpaRepository<ThreadTagData, Long> {

}

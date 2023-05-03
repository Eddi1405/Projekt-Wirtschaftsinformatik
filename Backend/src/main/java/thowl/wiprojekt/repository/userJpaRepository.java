package thowl.wiprojekt.repository;

import thowl.wiprojekt.entity.userData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface userJpaRepository extends JpaRepository<userData, Long> {
    @Query(value = "SELECT password FROM users WHERE username = ?1", nativeQuery = true)
    List<userData> findByUsername(String param1);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    List<userData> findByEmail(String param1);

    @Query(value = "UPDATE users SET email='?1' WHERE username = ?2", nativeQuery = true)
    List<userData> changeEmail(String param1,String param2);

    @Query(value = "UPDATE users SET password='?1' WHERE username = ?2", nativeQuery = true)
    List<userData> changePassword(String param1,String param2);


}

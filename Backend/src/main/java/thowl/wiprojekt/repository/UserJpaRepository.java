package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.UserData;

import java.util.List;


/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserData, Long> {
    @Query(value = "SELECT password FROM users WHERE username = ?1", nativeQuery = true)
    String findByUsername(String param1);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    String findByEmail(String param1);

    @Query(value = "UPDATE users SET email='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changeEmail(String param1, String param2);

    @Query(value = "UPDATE users SET password='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changePassword(String param1, String param2);

    @Query(value = "UPDATE users SET role='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changeRole(String param1, String param2);

    @Query(value = "UPDATE users SET learningtype='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changeLearningtype(String param1, String param2);


}

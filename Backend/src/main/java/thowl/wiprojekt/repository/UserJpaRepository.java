package thowl.wiprojekt.repository;

import thowl.wiprojekt.entity.UserData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Dies ist eine Schnittstelle, die das JpaRepository-Interface erweitert und spezifische Methoden für die Entität UserDaten bereitstellt.
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserData, Long> {
    @Query(value = "SELECT password FROM users WHERE username = ?1", nativeQuery = true)
    List<UserData> findByUsername(String param1);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    List<UserData> findByEmail(String param1);

    @Query(value = "UPDATE users SET email='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changeEmail(String param1, String param2);

    @Query(value = "UPDATE users SET password='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changePassword(String param1, String param2);

    @Query(value = "UPDATE users SET role='?1' WHERE username = ?2", nativeQuery = true)
    List<UserData> changeRole(String param1, String param2);


}

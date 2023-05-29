package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thowl.wiprojekt.entity.User;


/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT password FROM users WHERE username = ?1 OR email = ?2", nativeQuery = true)
    String findByUsernameOrEmail(String param1,String param2);

    @Query(value = "SELECT ID FROM users WHERE username = ?1", nativeQuery = true)
    long findByUsernameID(String param1);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    String findByEmail(String param1);

    @Query(value = "SELECT password FROM users WHERE email = ?1", nativeQuery = true)
    String findByUsername(String param1);

    @Query(value = "UPDATE users SET email='?1' WHERE username = ?2", nativeQuery = true)
    void changeEmail(String param1, String param2);

    @Query(value = "UPDATE users SET password='?1' WHERE username = ?2", nativeQuery = true)
    void changePassword(String param1, String param2);

    @Query(value = "UPDATE users SET role='?1' WHERE username = ?2", nativeQuery = true)
    void changeRole(String param1, String param2);

    @Query(value = "UPDATE users SET learningtype='?1' WHERE username = ?2", nativeQuery = true)
    void changeLearningtype(String param1, String param2);


}

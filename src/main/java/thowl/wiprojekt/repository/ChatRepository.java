package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thowl.wiprojekt.entity.Chat;

import java.util.Set;

/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the Chat entity.
 * @Author Luca Hüpping
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{

	Set<Chat> findByUsers_id(long id);

}

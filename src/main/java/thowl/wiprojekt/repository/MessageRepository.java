package thowl.wiprojekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thowl.wiprojekt.entity.Message;

/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the Message entity.
 * @Author Luca Hüpping
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
}

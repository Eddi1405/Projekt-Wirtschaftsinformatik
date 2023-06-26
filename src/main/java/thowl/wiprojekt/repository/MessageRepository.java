package thowl.wiprojekt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thowl.wiprojekt.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
}

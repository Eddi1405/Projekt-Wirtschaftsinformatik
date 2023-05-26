package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thowl.wiprojekt.entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
    
}

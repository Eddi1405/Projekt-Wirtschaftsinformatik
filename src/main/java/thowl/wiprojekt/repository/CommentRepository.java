package thowl.wiprojekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import thowl.wiprojekt.entity.Comment;

/**
 * This is an interface that extends the JpaRepository interface and provides specific methods for the Comment entity.
 * @Author Luca Hüpping
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}

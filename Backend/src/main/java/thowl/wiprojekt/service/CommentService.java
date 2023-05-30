package thowl.wiprojekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;
import thowl.wiprojekt.entity.Comment;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.repository.CommentRepository;
import thowl.wiprojekt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    private CommentRepository commentRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    public Comment createComment(Comment comment) {
        // Check if the author exists in the database
        return commentRepository.save(comment);
    }

    public Comment getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new ResourceNotFoundException("Comment not found with id " + id);
        }
    }

    public Comment updateComment(Long id, Comment comment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            comment.setId(id);
            return commentRepository.save(comment);
        } else {
            throw new ResourceNotFoundException("Comment not found with id " + id);
        }
    }

    public void deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Comment not found with id " + id);
        }
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}

package thowl.wiprojekt.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thowl.wiprojekt.entity.Comment;

import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ThrowsInternal;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.CommentService;

import java.util.List;
import java.util.Map;

/**
 * Author Florian Laufer
 */
@Slf4j
@RestController
@ThrowsInternal
@RequestMapping("/comments")
public class CommentController {


     @Autowired
     UserRepository UR;

     @Autowired
     private CommentService commentService;

     @Autowired
     UserController UC;

     @Autowired
     public CommentController(CommentService commentService) {
          this.commentService = commentService;
     }

     @PostMapping("/create")
     public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
               Comment createdComment = commentService.createComment(comment);
               return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

     }

     @GetMapping("/{id}")
     public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
          Comment comment = commentService.getComment(id);
          return new ResponseEntity<>(comment, HttpStatus.OK);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
          Comment updatedComment = commentService.updateComment(id, comment);
          return new ResponseEntity<>(updatedComment, HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
          commentService.deleteComment(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }

     @GetMapping
     public ResponseEntity<List<Comment>> getAllComments() {
          List<Comment> comments = commentService.getAllComments();
          return new ResponseEntity<>(comments, HttpStatus.OK);
     }

     @PatchMapping("/{id}")
     public Comment updateCommentFields(@PathVariable long id,@RequestBody Map<String,Object> fields){
          return commentService.updateCommentByFields(id,fields);
     }

}


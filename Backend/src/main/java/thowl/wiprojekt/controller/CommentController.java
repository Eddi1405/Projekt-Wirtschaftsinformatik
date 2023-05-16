package thowl.wiprojekt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thowl.wiprojekt.entity.Comment;

import thowl.wiprojekt.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

     @Autowired
     private CommentService commentService;

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
}

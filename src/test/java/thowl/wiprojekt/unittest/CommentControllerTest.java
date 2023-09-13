package thowl.wiprojekt.unittest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import thowl.wiprojekt.controller.CommentController;
import thowl.wiprojekt.entity.Comment;
import thowl.wiprojekt.service.CommentService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class CommentControllerTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests whether the 'createComment' method of the 'CommentController' works as expected by creating
     * a comment and returning the correct HTTP status response along with the comment created.
     */
    @Test
    void testCreateComment() {
        // das erstellen eines  'comment' Objekt simuliert, dass ein Benutzer einen Kommentar erstellt
        Comment commentToCreate = new Comment();
        // das erstellen des 'comment' Objekt simuliert, bei erfolgreichen Kommentarerstellung was von 'CommentService' zurückgegeben wird
        Comment createdComment = new Comment();
        // wird erwartet das 'createComment zurückgegeben wird
        when(commentService.createComment(commentToCreate)).thenReturn(createdComment);

        //Testausführung
        // simuliert den HTTP-POST-Request zum Erstellen eines Kommentars
        ResponseEntity<Comment> responseEntity = commentController.createComment(commentToCreate);

        // Überprüft ob der Http-Statuscode 'HttpStatus.CREATE' entspricht
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        //Überprüft ob  der erstellte Kommentar in der Antwort 'responseEntity.getBody()' mit dem erwarteten 'createdComment' übereinstimmt
        assertEquals(createdComment, responseEntity.getBody());
    }

    /**
     * Tests whether the 'getComment' method of the 'CommentController' works as expected
     * by retrieving a comment based on its ID and returning the correct HTTP status
     * response along with the comment retrieved.
     * This ensures that the method works properly and delivers the expected results.
     */
    @Test
    void testGetComment() {
        // Erstellt BeispielId für einen Kommentar
        Long commentId = 1L;
        // Das Erstellte Comment-Objekt soll simulieren was vom 'CommentService' zurückgegeben wird
        Comment comment = new Comment();
        // wird erwartet das 'comment' zurückgegeben wird
        when(commentService.getComment(commentId)).thenReturn(comment);

        // Testausführung
        //  simuliert einen HTTP-GET-Request zum Abrufen eines Kommentars nach seiner ID.
        ResponseEntity<Comment> responseEntity = commentController.getComment(commentId);

        // überprüft, ob der HTTP-Statuscode der Antwort HttpStatus.OK entspricht
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // überprüft, ob 'Comment' Objekt in 'responseEntity.getBody()' mit dem 'comment' Objekt Übereinstimmt
        assertEquals(comment, responseEntity.getBody());
    }

    /**
     * Tests whether the CommentController's 'updateComment' method works as expected
     * by updating a comment by its ID and returning the correct HTTP status response along with the updated comment.
     * This ensures that the method works properly and delivers the expected results.
     */
    @Test
    void testUpdateComment() {
        // Erstellt BeispielId für einen Kommentar
        Long commentId = 1L;
        // Das Erstellte Comment-Objekt soll simulieren was vom 'CommentService' zurückgegeben wird
        Comment updatedComment = new Comment();
        // wird erwartet das 'updatedComment' zurückgegeben wird
        when(commentService.updateComment(commentId, updatedComment)).thenReturn(updatedComment);

        // Dies simuliert einen HTTP-PUT-Request zur Aktualisierung eines Kommentars.
        ResponseEntity<Comment> responseEntity = commentController.updateComment(commentId, updatedComment);

        // überprüft, ob der HTTP-Statuscode der Antwort HttpStatus.OK entspricht
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // überprüft, ob 'Comment' Objekt in 'responseEntity.getBody()' mit dem 'updatedComment' Objekt Übereinstimmt
        assertEquals(updatedComment, responseEntity.getBody());
    }

    /**
     *  Tests that the 'deleteComment' method of 'CommentController' works as expected by
     *  deleting a comment based on its ID and returning the correct HTTP status response ('HttpStatus.NO_CONTENT').
     *  The test also checks if the method triggers deletion at 'CommentService', which means that the comment has been successfully deleted.
     */
    @Test
    void testDeleteComment() {
        // Erstellt BeispielId für einen Kommentar
        Long commentId = 1L;

        // Simuliert einen HTTP-DELETE-Request zur Löschung eines Kommentars.
        ResponseEntity<Void> responseEntity = commentController.deleteComment(commentId);

        // überprüft, ob der HTTP-Statuscode der Antwort 'HttpStatus.NO_CONTENT' entspricht
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        // überprüft, ob 'deleteComment' des 'CommentService' nur einemal mit der entsprechenden 'commentId' aufgerufen wurde
        verify(commentService, times(1)).deleteComment(commentId);
    }

    /**
     * Tests whether the CommentController's getAllComments method works as expected by
     * retrieving all comments and returning the correct HTTP status response (HttpStatus.OK) along with
     * the list of comments retrieved.
     */
    @Test
    void testGetAllComments() {
        //wird eine Liste mit einem einzelnen Beispiel-Kommentar erstellt
        //simuliert, was vom 'CommentService' zurückgegeben wird, wenn alle Kommentare abgerufen werden.
        List<Comment> comments = Collections.singletonList(new Comment());
        // 'getAllComments'- Methode wird aufgerufen und 'comments' Liste zurückgegeben
        when(commentService.getAllComments()).thenReturn(comments);

        //Diese Zeile ruft die getAllComments-Methode auf. simuliert einen HTTP-GET-Request zum Abrufen aller Kommentare.
        ResponseEntity<List<Comment>> responseEntity = commentController.getAllComments();

        // überprüft, ob der HTTP-Statuscode der Antwort HttpStatus.OK entspricht.
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // überprüft, ob die Liste von Kommentaren in der Antwort 'responseEntity.getBody()' mit der vorher erstellten comments-Liste übereinstimmt.
        assertEquals(comments, responseEntity.getBody());
    }

    /**
     * Tests whether the CommentController's 'updateCommentFields' method works as expected by
     * updating a comment using its ID and a map of fields and values and returning the correct updated comment.
     */
    @Test
    void testUpdateCommentFields() {
        // Erstellt BeispielId für einen Kommentar
        Long commentId = 1L;
        //Es wird eine leere HashMap erstellt, die simulieren soll, welche Felder und Werte aktualisiert werden sollen.
        Map<String, Object> fields = new HashMap<>();
        // Ein Eintrag wird zur fields-Map hinzugefügt, der ein Feld mit dem Namen "field1" und dem Wert "value1" enthält.
        fields.put("field1", "value1");
        // Das Erstellte Comment-Objekt soll simulieren was vom 'CommentService' zurückgegeben wird
        Comment updatedComment = new Comment();
        // dass die 'updateCommentByFields-Methode'  aufgerufen wird, wenn 'commentId' und 'fields' übergeben werden, und dass sie 'updatedComment' zurückgibt.
        when(commentService.updateCommentByFields(commentId, fields)).thenReturn(updatedComment);

        // ruft 'updateCommentFields-Methode' mit den Parametern'commentId' und 'fields'auf
        // simuliert einen HTTP-PATCH-Request zur Aktualisierung eines Kommentars anhand bestimmter Felder.
        Comment result = commentController.updateCommentFields(commentId, fields);

        // überprüft, ob das 'Comment'-Objekt 'result', das von der Methode zurückgegeben wird, mit dem erwarteten 'updatedComment'-Objekt übereinstimmt.
        assertEquals(updatedComment, result);
    }
}


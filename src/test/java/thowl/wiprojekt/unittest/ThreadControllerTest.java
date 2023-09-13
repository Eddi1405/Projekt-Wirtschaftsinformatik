package thowl.wiprojekt.unittest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import thowl.wiprojekt.controller.ThreadController;
import thowl.wiprojekt.entity.Thread;
import thowl.wiprojekt.service.ThreadService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ThreadControllerTest {

    @Mock
    private ThreadService threadService;

    private ThreadController threadController;

    @BeforeEach
    public void setUp() {
        // Initialisiere Mock-Objekte
        MockitoAnnotations.initMocks(this);
        // Erstelle den ThreadController mit dem Mock ThreadService
        threadController = new ThreadController(threadService);
    }

    /**
     *  Tests the 'createThread' method in the 'ThreadController' to work as expected by creating a thread
     *  object and returning a response with the correct status code and the created thread object.
     */
    @Test
    public void testCreateThread() {
        // leeres Objekt wird erstellt, welches als Eingabe fpr die tests dient
        Thread thread = new Thread();
        //Definiert das erwartete Verhalten des Mocks (test)
        // wenn 'createThread' im 'ThreadService' mit dem übergebenen Thread-Objekt aufgerufen wird,
        // wird das gleiche Thread-Objekt zurückgegeben.
        when(threadService.createThread(thread)).thenReturn(thread);

        // simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage.
        ResponseEntity<Thread> response = threadController.createThread(thread);

        //  überprüft, ob der HTTP-Statuscode der erhaltenen Antwort dem erwarteten Status "CREATED" (201) entspricht.
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(thread, response.getBody());
    }

    /**
     *  Tests is to make sure that the method works as expected by returning
     *  the expected thread object and returning a correct answer with the status code "OK".
     */
    @Test
    public void testGetThread() {
        // Eine Thread-ID (1L) wird festgelegt
        Long threadId = 1L;
        // Leeres Thread-Objekt wird erstellt, das als erwartetes Ergebnis für die Methode dient.
        Thread thread = new Thread();

        // Erwartetes Verhalten wird definiert
        // Uebergebenen Thread-ID aufgerufen wird, wird das vorbereitete Thread-Objekt zurückgegeben.
        when(threadService.getThread(threadId)).thenReturn(thread);

        // simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage.
        ResponseEntity<Thread> response = threadController.getThread(threadId);

        // Ueberprüft, ob der HTTP-Statuscode der erhaltenen Antwort dem erwarteten Status "OK" (200) entspricht.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Ueberprüft, ob das Thread-Objekt in der Antwort mit dem vorbereiteten Thread-Objekt übereinstimmt.
        assertEquals(thread, response.getBody());
    }

    /**
     *  Testing is to make sure that the updateThread method in the ThreadController works as expected by
     *  returning the expected updated thread object and returning a correct answer with the status code "OK".
     */
    @Test
    public void testUpdateThread() {
        // Eine Thread-ID (1L) wird festgelegt
        Long threadId = 1L;
        // Leeres Thread-Objekt wird erstellt, das als erwartetes Ergebnis für die Methode dient.
        Thread updatedThread = new Thread();

        // Erwartetes Verhalten wird definiert
        // Wenn Methode 'updateThread' mit der Thread-ID übergeben und
        // einem beliebigen Thread-Objekt aufgerufen wird, wird das vorbereitete aktualisierte Thread-Objekt zurückgegeben.
        when(threadService.updateThread(eq(threadId), any(Thread.class))).thenReturn(updatedThread);

        // Simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage
        ResponseEntity<Thread> response = threadController.updateThread(threadId, new Thread());

        // Ueberprüft, ob der HTTP-Statuscode der erhaltenen Antwort dem erwarteten Status "OK" (200) entspricht.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Ueberprüft, ob das aktualisierte Thread-Objekt in der Antwort mit dem vorbereiteten aktualisierten Thread-Objekt übereinstimmt.
        assertEquals(updatedThread, response.getBody());
    }

    /**
     *  Testing is to make sure that the method works as expected by calling
     *  the deleteThread method in the ThreadService and returning the correct answer
     *  with the status code "NO_CONTENT".
     */
    @Test
    public void testDeleteThread() {
        // Eine Thread-ID (1L) wird festgelegt
        Long threadId = 1L;

        // simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage.
        ResponseEntity<Void> response = threadController.deleteThread(threadId);

        // Ueberprüft, ob die Methode deleteThread im ThreadService genau einmal mit der vorbereiteten Thread-ID aufgerufen wurde.
        verify(threadService, times(1)).deleteThread(threadId);
        // Ueberprüft, ob der HTTP-Statuscode der erhaltenen Antwort dem erwarteten Status "NO_CONTENT" (204) entspricht.
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    /**
     *  Testing consists of ensuring that the method works as expected by returning the expected
     *  list of thread objects and the correct answer with the status code "OK".
     */
    @Test
    public void testGetAllThreads() {
        // Eine Liste von Thread-Objekten wird erstellt, die als erwartetes Ergebnis für die Methode dient
        List<Thread> threads = Collections.singletonList(new Thread());

        // Definiere das erwartete Verhalten des Mock ThreadService
        // Wenn die Methode getAllThreads im ThreadService aufgerufen wird, wird die vorbereitete Liste von Thread-Objekten zurückgegeben.
        when(threadService.getAllThreads()).thenReturn(threads);

        // Simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage.
        ResponseEntity<List<Thread>> response = threadController.getAllThreads();

        // Ueberprüft, ob der HTTP-Statuscode der erhaltenen Antwort dem erwarteten Status "OK" (200) entspricht.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Ueberprüft, ob die Liste von Thread-Objekten in der Antwort mit der vorbereiteten Liste übereinstimmt.
        assertEquals(threads, response.getBody());
    }

    /**
     *  Testing is to make sure that the method works as expected,
     *  as expected by returning the expected updated thread object.
     */
    @Test
    public void testUpdateThreadFields() {
        // Thread-ID (1L) wird festgelegt, die als Eingabe für die zu testende Methode dient.
        Long threadId = 1L;
        // Eine leere Map von aktualisierten Feldern (fields) wird vorbereitet.
        Map<String, Object> fields = new HashMap<>();
        // Leeres Thread-Objekt wird erstellt, das als erwartetes Ergebnis für die Methode dient.
        Thread updatedThread = new Thread();

        // Wenn die Methode 'updateThreadsByFields' im 'ThreadService' mit der übergebenen Thread-ID und Feldern aufgerufen wird,
        // wird das vorbereitete aktualisierte Thread-Objekt zurückgegeben.
        when(threadService.updateThreadsByFields(threadId, fields)).thenReturn(updatedThread);

        // RDies simuliert den Aufruf der Methode durch eine tatsächliche HTTP-Anfrage.
        Thread response = threadController.updateThreadFields(threadId, fields);

        // Ueberprüft, ob das aktualisierte Thread-Objekt in der Antwort mit dem vorbereiteten aktualisierten Thread-Objekt übereinstimmt.
        assertEquals(updatedThread, response);
    }
}

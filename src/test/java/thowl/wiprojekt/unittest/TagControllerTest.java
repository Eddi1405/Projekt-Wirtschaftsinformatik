package thowl.wiprojekt.unittest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import thowl.wiprojekt.controller.TagController;
import thowl.wiprojekt.entity.Tag;
import thowl.wiprojekt.service.TagService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringBootTest
class TagControllerTest {
    @Autowired
    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     *  Testing is to ensure that the method in the controller works properly by returning the expected list of tags.
     *  If an error occurs while running the test or the expectations are not met, the test fails. Otherwise,
     *  the test will pass and confirm that the method works as expected.
     */
    @Test
    void testGetAllTags() {
        // Erstellt Zwei Test-Tags 'tag1' und 'tag2' mit eindeutiger ID und Namen
        Tag tag1 = new Tag();
        Tag tag2 = new Tag();
        List<Tag> tags = Arrays.asList(tag1, tag2);

        //Definieren des erwarteten Verhaltens des Mocks
        //wenn die Methode getAllTags() des Service aufgerufen wird, die vorher erstellte Liste von Tags (tags) zurückgegeben.
        when(tagService.getAllTags()).thenReturn(tags);

        //'tagController' wird aufgerufen.Diese Methode wollen wir testen
        ResponseEntity<List<Tag>> response = tagController.getAllTags();

        // Wird eine die Antwort 'HttpStatus.ok' erwartet welche eine erfolgreiche Anfrage bestätigt
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //Der Body der Response wird überprüft, indem wir die erwartete Liste von Tags mit dem tatsächlichen
        // Body der Response vergleichen. Wenn sie übereinstimmen, ist dieser Teil des Tests erfolgreich.
        assertEquals(tags, response.getBody());
    }

    /**
     *  Testing consists of ensuring that the method in the controller responds correctly to
     *  requests and returns the expected HTTP status codes and response bodies. If an error occurs while
     *  running the test or the expectations are not met, the test fails.
     */
    @Test
    void testGetTagById() {
        // Erstellt einen Test-Tag mit eindeutiger ID und Namen
        Tag tag = new Tag();

        // Definieren des erwarteten Verhaltens des Mocks:
        //sagt dem Mock-Service 'tagService', dass er das Tag zurückgeben soll, wenn 'getTagById()' mit der ID 1 aufgerufen wird.
        when(tagService.getTagById(1L)).thenReturn(tag);
        // Sagt dem Mock-Service, dass er null zurückgeben soll, wenn getTagById() mit der ID 99 aufgerufen wird.
        when(tagService.getTagById(99L)).thenReturn(null);

        // Ausführen der zu testenden Methode
        // 'getTagById()' des Controllers  wird zweimal aufgerufen, einmal mit der existierenden ID 1 und
        // einmal mit der nicht existierenden ID 99. Die Rückgaben werden in den Variablen response1 und response2 gespeichert.
        ResponseEntity<Tag> response1 = tagController.getTagById(1L);
        ResponseEntity<Tag> response2 = tagController.getTagById(99L);

        //Ueberprüfen des HTTP-Statuscodes und Response-Bodys für den existierenden Tag und einen Statuscode HttpStatus.ok(200) wird erwartet
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(tag, response1.getBody());

        //Der erwartete HTTP-Statuscode ist HttpStatus.NOT_FOUND (404), da der Tag nicht gefunden wurde.
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    /**
     *  Testing is to make sure that the method in the controller works by returning the created tag and delivering the expected HTTP status code.
     *  If an error occurs while running the test or the expectations are not met, the test fails.
     */
    @Test
    void testCreateTag() {
        // Erstellt einen Test-Tag mit eindeutiger ID und Namen
        Tag tag = new Tag();
        // Definieren des erwarteten Verhaltens des Mocks:
        // wenn die Methode createTag() des Service mit dem tag aufgerufen wird, das gleiche Tag zurückgegeben wird.
        when(tagService.createTag(tag)).thenReturn(tag);

        //der erstellte Test-Tag tag wird als Parameter übergeben. Die Rückgabe wird in der Variable 'response' gespeichert.
        ResponseEntity<Tag> response = tagController.createTag(tag);

        //Der erwartete HTTP-Statuscode ist HttpStatus.OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //Der erwartete Tag tag wird mit dem Body der Response response verglichen
        //stellt sicher, dass der zurückgegebene Tag im Response-Body mit dem erstellten Tag übereinstimmt.
        assertEquals(tag, response.getBody());
    }


    /**
     *  Testing is to ensure that the method in the controller responds to requests and
     *  returns the expected HTTP status codes and response bodies.
     *  If an error occurs while running the test or the expectations are not met, the test fails.
     */
    @Test
    void testUpdateTag() {
        // Erstellt einen Test-Tag mit eindeutiger ID und Namen
        Tag updatedTag = new Tag();

        // Definieren des erwarteten Verhaltens des Mocks
        // sagt dem Mock-Service 'tagService', dass er das aktualisierte Tag zurückgeben soll,
        // wenn 'updateTag()' mit der ID 1 und dem updatedTag als Parameter aufgerufen wird.
        when(tagService.updateTag(1L, updatedTag)).thenReturn(updatedTag);
        //sagt dem Mock-Service, dass er 'null' zurückgeben soll, wenn 'updateTag()' mit der ID 99 und dem 'updatedTag' als Parameter aufgerufen wird.
        when(tagService.updateTag(99L, updatedTag)).thenReturn(null);

        // 'updateTag' wird zweimal ausgeführt einmal mit ID1 und einmal mit ID 99 diese kommen dann in 'response1' und 'response2'
        ResponseEntity<Tag> response1 = tagController.updateTag(1L, updatedTag);
        ResponseEntity<Tag> response2 = tagController.updateTag(99L, updatedTag);

        //erwartete HTTP-Statuscode ist HttpStatus.OK (200), weil erfolgreich
        //erwartete aktualisierte Tag 'updatedTag' wird mit dem Body der Response 'response1' verglichen.
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(updatedTag, response1.getBody());

        // erwartete HTTP-Statuscode ist HttpStatus.NOT_FOUND (404), weil Tag nicht gefunden wurde
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }

    /**
     *  Testing is to make sure that the method in the controller responds to requests and
     *  returns the expected HTTP status codes. If an error occurs while running the test or the expectations are
     *  not met, the test fails. Otherwise, the test will pass and confirm that the method works as expected.
     */
    @Test
    void testDeleteTag() {

        //definiert das erwartete Verhalten des Mocks indem dem Mock-Service 'tagService' sagt, dass er 'true' zurückgeben soll,
        // wenn 'deleteTag()' erfolgreich war
        when(tagService.deleteTag(1L)).thenReturn(true);
        //definiert das erwartete Verhalten des Mocks indem dem Mock-Service 'tagService' sagt, dass er 'false' zurückgeben soll,
        // wenn 'deleteTag()' fehlgeschlagen ist
        when(tagService.deleteTag(99L)).thenReturn(false);

        // 'deleteTag()' wird zweimal ausgeführt einmal mit ID1 und einmal mit ID 99 diese kommen dann in 'response1' und 'response2'
        ResponseEntity<Void> response1 = tagController.deleteTag(1L);
        ResponseEntity<Void> response2 = tagController.deleteTag(99L);

        //erwartete HTTP-Statuscode ist HttpStatus.NO_CONTENT (204), da das Löschen erfolgreich war.
        assertEquals(HttpStatus.NO_CONTENT, response1.getStatusCode());
        //erwartete HTTP-Statuscode ist HttpStatus.NOT_FOUND (404), da das Tag nicht gefunden wurde.
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
    }
}

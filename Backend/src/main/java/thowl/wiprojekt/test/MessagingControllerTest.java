package thowl.wiprojekt.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import thowl.wiprojekt.controller.MessagingController;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.MessageRepository;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.FileValidator;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;

import java.util.Optional;
import java.util.Set;

class MessagingControllerTest {

    @InjectMocks
    private MessagingController messagingController;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FileValidator fileValidator;

    /**
     * Dieser Test simuliert ein Abonnementereignis und überprüft,
     * ob die subscribeTo-Methode wie erwartet funktioniert und die erwarteten Ergebnisse zurückgibt.
     */
    @Test
    void testSubscribeTo_ValidSubscription_ReturnsMessages() {

        long chatID = 1L;
        long num = 0L;
        String mTime = "2023-08-29";
        Long userID = 123L;

        User mockUser = new User();

        Chat mockChat = new Chat();

        // Erwartetes Verhalten wird definiert
        // wenn aufgerufen wird, wird 'mochUser' zurückgegeben
        when(userRepository.findById(userID)).thenReturn(Optional.of(mockUser));
        // wenn aufgerufen wird, wird 'mochChat' zurückgegeben
        when(chatRepository.findById(chatID)).thenReturn(Optional.of(mockChat));

        // subscribeTo-Methode wird mit den passenden Parametern aufgerufen
        Set<Message> result = messagingController.subscribeTo(chatID, num, mTime, userID);

        // stellt sicher, dass das Ergebnis nicht null ist
        assertNotNull(result);

    }

    /**
     * Dieser Test simuliert das Weiterleiten einer Nachricht und überprüft,
     * ob die forwardMessage-Methode wie erwartet funktioniert und die erwartete
     * Nachricht zurückgibt.
     */
    @Test
    void testForwardMessage_ValidMessage_ReturnsSavedMessage() {

        long chatID = 1L;
        // Erstellt Mock-message Objekt
        Message mockMessage = new Message();
        // Erstellt Mock-Chat Objekt
        Chat mockChat = new Chat();

        // Erwartetes Verhalten wird definiert
        // wenn aufgerufen wird, wird 'mockChat' zurückgegeben
        when(chatRepository.findById(chatID)).thenReturn(Optional.of(mockChat));

        // forwardMessage-Methode wird mit angegebenen parametern aufgeurfen
        Message result = messagingController.forwardMessage(chatID, mockMessage);

        // stellt sicher, dass das Ergebnis nicht null ist
        assertNotNull(result);

    }

    /**
     * In diesem Test wird überprüft,
     * ob die Methode saveMessage in der MessagingController-Klasse wie erwartet funktioniert.
     * Diese Verifikation stellt sicher, dass die Nachricht erfolgreich gespeichert
     * und der Chat entsprechend aktualisiert wurde.
     */
    @Test
    void testSaveMessage_ValidMessageAndChat_SavesMessageAndUpdatesChat() {
        // Erstellt Mock-Message Objekt
        Message mockMessage = new Message();
        // Erstellt Mock-Chat Objekt
        Chat mockChat = new Chat();

        // ruft die 'saveMessage' Methode auf mit dem zuvor erstellten Parametern auf
        messagingController.saveMessage(mockMessage, mockChat);

        // Überprüft, ob die 'save' Methode auf dem 'messageRepository' nut einmal aufgerufen wurde
        verify(messageRepository, times(1)).save(mockMessage);
        // Überprüft, ob die 'save' Methode auf dem 'chatRepository' nut einmal aufgerufen wurde
        verify(chatRepository, times(1)).save(mockChat);
    }

}


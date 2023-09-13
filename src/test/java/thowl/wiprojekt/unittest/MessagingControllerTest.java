package thowl.wiprojekt.unittest;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thowl.wiprojekt.controller.MessagingController;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.entity.Message;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.MessageRepository;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.FileValidator;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class MessagingControllerTest {
    @Autowired
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
     *  This test simulates a subscription event and checks whether the
     *  subscribeTo method works as expected and returns the expected results.
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
     *  This test simulates the forwarding of a message and checks
     *  whether the forwardMessage method works as expected and returns the expected message.
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
     *  This test checks whether the saveMessage method in the MessagingController class works as expected.
     *  This verification ensures that the message has been successfully saved and that the chat has been updated accordingly.
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

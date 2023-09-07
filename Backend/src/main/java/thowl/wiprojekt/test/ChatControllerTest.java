package thowl.wiprojekt.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.web.server.ResponseStatusException;

import thowl.wiprojekt.controller.ChatController;
import thowl.wiprojekt.entity.Chat;
import thowl.wiprojekt.errors.*;
import thowl.wiprojekt.repository.ChatRepository;
import thowl.wiprojekt.repository.UserRepository;

public class ChatControllerTest {

    private ChatController chatController;
    private ChatRepository chatRepository;
    private UserRepository userRepository;

    /**
     *	Mock Obejekte für chatRepository und userRepository erstellt
     *	und erstellt Intanz ChatController
     *	dies wird aufgrunt von @BeforeEach vor jedem Test aufgerufen
     */
    @BeforeEach
    void setUp() {
        chatRepository = mock(ChatRepository.class);
        userRepository = mock(UserRepository.class);
        chatController = new ChatController(chatRepository, userRepository);
    }


    /**
     *	Testet die Methode getChat(), vom ChatController das sie wie gewünscht funktioniert
     *	Es wird eine feste chatId zum testen vorgegeben
     */
    @Test
    void testGetChat_ValidChatId_ReturnsChat() {
        //Beispiel chatId
        long chatId = 1L;
        //neues Chat-Objekt
        Chat chat = new Chat();
        //Chat-Objekt obiege Chat-ID geben
        chat.setId(chatId);
        //Verhalten definieren
        //Wenn findById + chatId -> Chat-Objekt zurückgeben
        when(chatRepository.findById(chatId)).thenReturn(Optional.of(chat));

        //In result wird der Chat von der Id "1L" gespeichert
        Chat result = chatController.getChat(chatId);

        //Ergebniss nicht null
        assertNotNull(result);
        //Überpüft ob der zurückgeben Chat die selbe ID wie der voriege hat
        assertEquals(chatId, result.getId());
    }


    /**
     *	Testet die Methode getChat(), vom ChatController auf ungültige Eingabe
     *	Es soll eine ResourceNotFoundException ausgelöst werden
     */
    @Test
    void testGetChat_InvalidChatId_ThrowsResourceNotFoundException() {
        //ungültige ChatId
        long invalidChatId = 999L;
        //Verhalten definieren
        //Wenn findById + invalidChatId -> was leeres zurückgeben
        when(chatRepository.findById(invalidChatId)).thenReturn(Optional.empty());

        //Überpüft ob wirklich die ResourceNotFoundException geworfen wird
        assertThrows(ResourceNotFoundException.class, () -> chatController.getChat(invalidChatId));
    }


    /**
     *	Testet die Metode getChatsOfUser(), vom ChatController das sie wie gewünscht funktioniert,
     *	erwartet Chats werden für einen bestimmten Benutzer korrekt aufgerufen und zurückgeben
     */
    @Test
    void testGetChatsOfUser_ValidUserId_ReturnsChats() {
        //Benutzer ID festlegen
        long userId = 1L;
        //Neues Chat-Objekt "chat1" zum testen
        Chat chat1 = new Chat();
        //"chat1" id "1L" zuweisen
        chat1.setId(1L);
        //Neues Chat-Objekt "chat2" zum testen
        Chat chat2 = new Chat();
        //"chat2" id "2L" zuweisen
        chat2.setId(2L);
        //Liste erstellen, Simuliert Ergebnis einer Datenbank Abfrage
        List<Chat> chatList = Arrays.asList(chat1, chat2);
        //Wenn findByUsers_id + "1L" -> wird Chat-Liste zurückgeben
        when(chatRepository.findByUsers_id(userId)).thenReturn((Set<Chat>) chatList);

        //ruft Chats des angegeben Benutzer auf
        Set<Chat> result = chatController.getChatsOfUser(userId);

        //Erbenis nicht null
        assertNotNull(result);
        //anzahl der zurückgeben Chats übereinstimmt
        assertEquals(2, result.size());
        //Mindestens einer der zurückgeben Chats die ID "1L" hat
        assertTrue(result.stream().anyMatch(chat -> chat.getId() == 1L));
        //Mindestens einer der zurückgeben Chats die ID "2L" hat
        assertTrue(result.stream().anyMatch(chat -> chat.getId() == 2L));
    }

    /**
     *	Testet die Metode addChat(), vom ChatController das sie wie gewünscht funktioniert,
     *	Chat-Objekt hinzufügt und zurückgeben Chat-Objekt prüft
     */
    @Test
    void testAddChat_ValidChat_ReturnsCreatedChat() {
        //erstelt Chat-Objekt
        Chat chat = new Chat();
        //zuweisen von ID
        chat.setId(1L);
        //Verhalten definieren
        when(chatRepository.save(any(Chat.class))).thenReturn(chat);

        //addChat Method des chatController + Chat-Objekt aufrufen
        Chat result = chatController.addChat(chat);

        //Erbenis nicht null
        assertNotNull(result);
        //Vergleicht erwartet ID mit der zurückgeben
        assertEquals(1L, result.getId());
    }


    @Test
    void testAddChat_NullChat_ThrowsMalformedRequestException() {
        //
        assertThrows(MalformedRequestException.class, () -> chatController.addChat(null));
    }

    @Test
    void testDeleteChat_ExistingChatId_DeletesChat() {
        //
        long chatId = 1L;
        Chat chat = new Chat();
        chat.setId(chatId);
        when(chatRepository.findById(chatId)).thenReturn(Optional.of(chat));

        //
        assertDoesNotThrow(() -> chatController.deleteChat(chatId));
    }

    @Test
    void testDeleteChat_NonExistingChatId_ThrowsResourceNotFoundException() {
        //
        long invalidChatId = 999L;
        when(chatRepository.findById(invalidChatId)).thenReturn(Optional.empty());

        //
        assertThrows(ResourceNotFoundException.class, () -> chatController.deleteChat(invalidChatId));
    }

    @Test
    void testUpdateChat_ValidChat_ReturnsUpdatedChat() {
        //
        Chat oldChat = new Chat();
        oldChat.setId(1L);
        when(chatRepository.findById(oldChat.getId())).thenReturn(Optional.of(oldChat));
        Chat updatedChat = new Chat();
        updatedChat.setId(1L);
        when(chatRepository.save(any(Chat.class))).thenReturn(updatedChat);

        //
        Chat result = chatController.updateChat(updatedChat);

        //
        assertNotNull(result);
        assertEquals(updatedChat.getId(), result.getId());
    }

    @Test
    void testUpdateChat_NullChat_ThrowsMalformedRequestException() {
        //
        assertThrows(MalformedRequestException.class, () -> chatController.updateChat(null));
    }


}

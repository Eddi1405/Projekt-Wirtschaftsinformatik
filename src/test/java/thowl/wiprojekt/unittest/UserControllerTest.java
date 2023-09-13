package thowl.wiprojekt.unittest;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import thowl.wiprojekt.controller.UserController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.objects.dto.PatchDto;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
@SpringBootTest
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;


    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     *  Tests whether the getUser method in your controller works properly by retrieving a
     *  user from the repository dependency based on his/her ID and returning the expected response
     */
    @Test
    public void testGetUser() {
        // Testuser mit Userid wird erstellt
        Long userId = 1l;
        User mockUser = new User();
        mockUser.setId(userId);
        //Rückgabe zu simulieren, wenn die Methode 'findById' mit der 'userId' aufgerufen wird
        //erwartet, dass das Mock-Objekt ein 'Optional'-Objekt enthält, das den erstellten 'mockUser' enthält.
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        ResponseEntity<User> response = userController.getUser(userId);

        // überprüft, ob die HTTP-Antwort 'response' den erwarteten HTTP-Statuscode 'HttpStatus.OK' hat.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Überprüft, ob der in der Antwort enthaltene Benutzer 'response.getBody()'mit dem erstellten mockUser übereinstimmt
        assertEquals(mockUser, response.getBody());
    }

    /**
     *  Tests whether the getUser method correctly responds to the absence of a user by triggering
     *  a 'ResourceNotFoundException' if a user with the specified ID is not found in the database.
     */
    @Test
    public void testGetUserNotFound() {
        // userId wird 1L zugewiesen
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // 'getUser' I aufgerufen, indem die 'userId' als Argument übergeben wird. Dies simuliert den Versuch, einen nicht existierenden Benutzer abzurufen.
        //Das erwartete Verhalten wird mit 'assertThrows' überprüft.
        assertThrows(ResourceNotFoundException.class, () -> userController.getUser(userId));
    }

    /**
     *  Tests whether the 'getAllUsers' method in Controller works properly by retrieving
     *  all users from the repository dependency and returning the expected list of users.
     */
    @Test
    public void testGetAllUsers() {
        // erstelt liste mit Mockusern. Diese Liste soll die simulierten Benutzer darstellen, die aus  Repository-Abhängigkeit zurückgegeben werden, wenn 'findAll' aufgerufen wird.
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Ruft 'getAllUsers' Methode des Controllers auf.
        // simuliert den Aufruf dieser Methode durch einen HTTP-Request.
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // überprüft, ob die HTTP-Antwort 'response' den erwarteten HTTP-Statuscode 'HttpStatus.OK' hat.
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // überprüft, ob in der Antowrt enthaltene Benutzer 'response-getBody()' mit der Liste 'mockUsers' übereinstimmt
        assertEquals(mockUsers, response.getBody());
    }

    /**
     *  Tests whether the deleteUser method in the controller works properly by deleting a
     *  user based on his ID and ensuring that the correct user has been deleted.
     */
    @Test
    public void testDeleteUser() {
        // Testuser mit Userid wird erstellt
        // Das Mock- Obejekt wird konfiguriert um 'Optional.of(mockUser)' zurückzugeben, wenn 'findById' mit 'userId' aufgerufen wird.
        // simuliert den Fall, dass Ihr Repository-Objekt den Benutzer mit der angegebenen ID erfolgreich gefunden hat.
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Ruft die 'deleteUser'-Methode aus dem Controller auf und übergibt die 'userId'. Dies simuliert den Versuch, einen Benutzer anhand seiner ID zu löschen.
        ResponseEntity<?> response = userController.deleteUser(userId);

        // überprüft, ob die HTTP-Antwort 'response' den erwarteten HTTP-Statuscode 'HttpStatus.NO_CONTENT' hat.
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // mit Mockito wird sichergestellt dass die Methode 'delete' geanau einmal durch 'times(1)' mit 'mockUser' aufgerufen wurde
        // so wird überprüft, ob die Methode delete aufgerufen wurde und ob sie den richtigen Benutzer gelöscht hat.
        verify(userRepository, times(1)).delete(mockUser);
    }

    /**
     *  Tests whether the 'deleteUser method' in the controller responds correctly to the absence of a user
     *  by triggering a 'ResourceNotFoundException' if a user with the specified ID is not found in the database
     */
    @Test
    public void testDeleteUserNotFound() {
        // löschende Benutzer-ID 'userId' festgelegt
        // Das Mock-Objekt wird konfiguriert, um 'Optional.empty()' zurückzugeben,
        // wenn die Methode findById mit der 'userId' aufgerufen wird.
        //Dies simuliert den Fall, dass in der Datenbank kein Benutzer mit dieser ID gefunden wurde.
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        //  Ruft  die 'deleteUser'-Methode des Controllers auf, indem Sie die userId als Argument übergeben.
        // Dies simuliert den Versuch, einen nicht existierenden Benutzer zu löschen.
        //Es wird erwartet, dass die deleteUser-Methode eine 'ResourceNotFoundException' auslöst,
        // da der Benutzer nicht gefunden wurde. Die 'assertThrows'-Methode überprüft, ob die erwartete Ausnahme tatsächlich ausgelöst wird.
        assertThrows(ResourceNotFoundException.class, () -> userController.deleteUser(userId));
    }


}


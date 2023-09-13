package thowl.wiprojekt.unittest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityModel;
import thowl.wiprojekt.controller.LoginController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.RestAuthenticationException;
import thowl.wiprojekt.errors.ResourceAlreadyExistsException;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginControllerTest {
    @Autowired
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     *  Tests whether the login process is successful, if the login information entered is correct,
     *  and whether the required method calls are performed as expected.
     */
    @Test
    void testLoginSuccessful() {
        // der Mockuser "testuser" wird mit dem Passwort "password" erstellt
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPassword("password");
        System.out.println("test 1 "+ mockUser.getUsername()+ mockUser.getPassword());

        // Es wird abgefragt ob userService.passwordCheck ein true zurückgegeben wird
        when(userService.passwordCheck("password", mockUser.getPassword())).thenReturn(true);

//        System.out.println("test 2 "+ mockUser.getUsername()+ mockUser.getPassword());
        // Es wird abgefragt ob userRepository den gegebenen Benutzernamen zurückgibt
        when(userRepository.findByUsernameOrEmail("testuser", null)).thenReturn(mockUser.getUsername());

//        System.out.println("test 3 "+ mockUser.getUsername()+ mockUser.getPassword());
//      System.out.println("teset "+mockUser.getUsername() + " "+ mockUser.getPassword());
        // Die Login-Methode aufrufen Die Login-Mehtode aus dem logincontroller wird aufgerufen


//        mockUser.setPassword(userService.passwordEncoder(mockUser.getPassword()));

        //System.out.println("test 3.1 "+ mockUser.getPassword());
        EntityModel<User> result = loginController.login(mockUser);


//        System.out.println("test 4 "+ mockUser.getUsername()+ mockUser.getPassword());

        // Überprüfung ob Benutzername mit Mock-Benutzername übereinstimmen
        assertNotNull(result);
        assertEquals(mockUser.getUsername(), result.getContent().getUsername());

        // Prüft ob Methodenaufrufe auf den Mocks erfolgt sind
        verify(userRepository, times(1)).findByUsernameOrEmail("testuser", null);
        verify(userService, times(1)).passwordCheck("password", mockUser.getPassword());


    }

    /**
     *  tests whether the login functionality shows the expected behavior in the case of
     *  a faulty password by triggering an authentication error exception and performing the password check accordingly.
     */
    @Test
    void testLoginFailed() {
        // Der Mockbenutzer "testuser" mit dem Passwort "wrongpassword" wird erstellt. Mock-Benutzer wird verwendet, um den Login-Prozess zu simulieren.
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPassword("wrongpassword");

        // Wenn 'passwordCheck' des Mock-service 'userService ' mit dem Argument 'wrongpassword' und dem Mock-Benutzer 'mockUser' ausgeführt wird, sie false zurückgibt.
        // Dient dazu dass das eingegebene Passwort falsch ist.
        when(userService.passwordCheck("wrongpassword", mockUser.getPassword())).thenReturn(false);

        // Bei Aufruf von 'login' aus 'loginController' mit Mock-Benutzer eine 'RestAuthenticationException' ausgelöst. Da Passwort falsch wird ein Authentifizierungsfehler  auftreten
        assertThrows(RestAuthenticationException.class, () -> loginController.login(mockUser));

        verify(userService, times(1)).passwordCheck("wrongpassword", mockUser.getPassword());
    }


    /**
     *  whether the registration process will be successfully completed if the
     *  selected username does not exist in the database.
     */
    @Test
    void testRegisterSuccessful() {
        // Der Mockbenutzer "newuser" mit dem Passwort "password" wird erstellt. Simuliert Benutzer der sich registrieren möchten
        User mockUser = new User();
        mockUser.setUsername("newuser");
        mockUser.setPassword("password");

        // bei aufruf von 'findByUsernameOrEmail' des Mock-Respositorys 'userRepository' mit dem Argument 'newuser' und 'null' wird 'null' zurückgegeben
        // Simuliert das der Benutzername '"newuser"' noch nicht existiert und daher fortgesetzt werden kann
        when(userRepository.findByUsernameOrEmail("newuser", null)).thenReturn(null);

        // 'register' von 'logincontroller' wird aufgerufen um 'mockUser' zu registrieren und wird dann in'EntityMod1l<User>' instanz gespeichert
        EntityModel<User> result = loginController.register(mockUser);

        // überprüft ob Ergebnis nicht null ist und der Benutzername 'result' mit Benutzername 'mochUSer' übereinstimmen
        assertNotNull(result);
        assertEquals(mockUser.getUsername(), result.getContent().getUsername());

        // Methode 'findByUsernameOrEmail' von Mock-repository 'userRepository' einmal mit Argument '"newuser"' und 'null' aufgerufen wurde
        // stellt sicher, dass die Überprüfenung auf bereits vorhandenen Benutzernamen wie erwartet durchgeführt wurde
        verify(userRepository, times(1)).findByUsernameOrEmail("newuser", null);

    }


    /**
     *  This method tests whether the registration process fails if
     *  the selected username already exists in the database and whether
     *  the corresponding exception is triggered correctly.
     */
    @Test
    void testRegisterExistingUser() {
        // Der Mockbenutzer "existinguser" mit dem Passwort "password" wird erstellt. Simuliert Benutzer 'existinguser' bereits in der Datenbank existiert
        User mockUser = new User();
        mockUser.setUsername("existinguser");
        mockUser.setPassword("password");

        // Wenn Methode 'findByUsernameOrEmail' von 'userRepository' mit Argument '"existinguser"' und 'null' aufgerufen wird. der Benutzer 'mockUser' zurückgegeben wird
        // Simuliert den Fall,dass der Benutzername '"existinguser"' bereits existiert
        when(userRepository.findByUsernameOrEmail("existinguser", null)).thenReturn(mockUser.getUsername());

        // Erwartet 'register' des 'loginController' mit 'mockUser' eine 'ResourceAlreadyExistsException' triggert, weil der Username bereits existiert
        assertThrows(ResourceAlreadyExistsException.class, () -> loginController.register(mockUser));

        // Methode 'findByUsernameOrEmail' von Mock-repository 'userRepository' einmal mit Argument '"newuser"' und 'null' aufgerufen wurde
        // stellt sicher, dass die Überprüfenung auf bereits vorhandenen Benutzernamen wie erwartet durchgeführt wurde
        verify(userRepository, times(1)).findByUsernameOrEmail("existinguser", null);
    }


}

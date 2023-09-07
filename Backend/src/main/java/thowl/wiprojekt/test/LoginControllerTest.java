package thowl.wiprojekt.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import thowl.wiprojekt.controller.LoginController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.IllegalEntityException;
import thowl.wiprojekt.errors.RestAuthenticationException;
import thowl.wiprojekt.errors.ResourceAlreadyExistsException;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        //rename from MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }

    /**
     * testet ob der Login-Prozess erfolgreich ist, wenn die eingegebenen Anmeldeinformationen korrekt sind,
     *	 und ob die erforderlichen Methodenaufrufe erwartungsgemäß erfolgen.
     */
    @Test
    void testLoginSuccessful() {
        // der Mockuser "testuser" wird mit dem Passwort "password" erstellt
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPassword("password");

        // Es wird abgefragt ob userService.passwordCheck ein true zurückgegeben wird
        when(userService.passwordCheck("password", mockUser.getPassword())).thenReturn(true);
        // Es wird abgefragt ob userRepository den gegebenen Benutzernamen zurückgibt
        when(userRepository.findByUsernameOrEmail("testuser", null)).thenReturn(mockUser.getUsername());

        // Die Login-Methode aufrufen Die Login-Mehtode aus dem logincontroller wird aufgerufen
        EntityModel<User> result = loginController.login(mockUser);

        // Überprüfung ob Benutzername mit Mock-Benutzername übereinstimmen
        assertNotNull(result);
        assertEquals(mockUser.getUsername(), result.getContent().getUsername());

        // Prüft ob Methodenaufrufe auf den Mocks erfolgt sind
        verify(userRepository, times(1)).findByUsernameOrEmail("testuser", null);
        verify(userService, times(1)).passwordCheck("password", mockUser.getPassword());
    }

    /**
     * testet ob die Login-Funktionalität im Falle eines fehlerhaften Passworts das erwartete Verhalten zeigt, indem sie eine
     *	Authentifizierungsfehler-Ausnahme auslöst und den Passwortcheck entsprechend durchführt.
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
     * ob der Registrierungsprozess erfolgreich durchgeführt wird, wenn der gewählte Benutzername noch nicht in der Datenbank existiert.
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
     * testet diese Methode, ob der Registrierungsprozess fehlschlägt, wenn der gewählte Benutzername bereits in der Datenbank vorhanden ist,
     *	und ob die entsprechende Ausnahme korrekt ausgelöst wird.
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

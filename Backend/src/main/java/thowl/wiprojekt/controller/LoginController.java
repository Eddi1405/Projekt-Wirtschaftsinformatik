package thowl.wiprojekt.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.entity.model.UserModelAssembler;
import thowl.wiprojekt.errors.IllegalEntityException;
import thowl.wiprojekt.errors.InternalException;
import thowl.wiprojekt.errors.ProjectException;
import thowl.wiprojekt.errors.ResourceAlreadyExistsException;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

import java.io.IOException;

@Slf4j
@RestController
public class LoginController {

    private final UserRepository UR;

    private final UserService US;

    @Autowired
    private UserModelAssembler assembler;

    public LoginController(UserRepository UR, UserService US) {
        this.UR = UR;
        this.US = US;
    }

    /**
     * Logs a {@link User} into the service.
     *
     * @param user The {@link User} to be authenticated.
     * @param response The response given to the client.
     * @return An {@link EntityModel} of the logged in {@link User} object.
     */
    // TODO test IOException
    // IOException should not be thrown, compiler won't stop complaining though
    @SneakyThrows(IOException.class)
    @PostMapping("/login")
    public EntityModel<User> login(@RequestBody User user,
    HttpServletResponse response) {
        /*
         * Certain fields may not be null.
         */
        if (user == null || user.getPassword() == null ||
                (user.getEmail() == null && user.getUsername() == null)) {
            throw new IllegalEntityException("Not sufficient "
                    + "information for login.", user);
        }
        log.info(String.valueOf(US.passwordCheck(user.getPassword(), UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))));
        try {
            if(US.passwordCheck(user.getPassword(),UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))) {
                /*
                 * An EntityModel of a User is created. First the user id
                 * has to be determined to get the User by ID.
                 */
                EntityModel<User> model =
                        assembler.toModel(UR.findById(UR.findByUsernameID(
                                user.getUsername())).orElse(null));
                return model;
            }
            // TODO create Exception
            else {
                /*
                 * If the specified password does not correlate to the one in
                 * the database an error is raised.
                 */
                response.sendError(401, "Authentication failed");
                return null;
            }
        }
        catch (Exception e) {
            /*
             * If the Exception is an Exception that can be intercepted by an
             * ExceptionInterceptor it is thrown again.
             */
            if (e instanceof ProjectException) {
                throw e;
            }
            /*
             * Else it is converted to a type that can be intercepted.
             */
            else {
                e.printStackTrace();
                throw new InternalException(e.getMessage());
            }
        }

    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        /*
         * Certain fields may not be null.
         */
        if (user == null || user.getPassword() == null ||
                (user.getEmail() == null && user.getUsername() == null)) {
            throw new IllegalEntityException("Not sufficient "
                    + "information for register operation.", user);
        }
        if (UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
            US.saveRegisterData(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole(), user.getLearningtype());
            return true;
        }
        else {
            /*
             * If the User already exists a 409 error is given.
             */
            throw new ResourceAlreadyExistsException("User could not be "
                    + "created.");
        }
    }
}
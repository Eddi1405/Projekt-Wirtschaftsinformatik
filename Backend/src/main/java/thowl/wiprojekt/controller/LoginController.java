package thowl.wiprojekt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.entity.model.UserModelAssembler;
import thowl.wiprojekt.errors.*;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

/**
 * Controller for logging in and registering users.
 *
 * @version 13.05.2023
 */
@Slf4j
@ThrowsInternal
@RestController
public class LoginController {

    // Used for database operations on user tables
    private final UserRepository UR;

    // Does the heavy lifting for authentication
    private final UserService US;

    // Used to create EntityModels from Users
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
     * @return An {@link EntityModel} of the logged in {@link User} object.
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<User> login(@RequestBody User user) {
        /*
         * Certain fields may not be null.
         */
        if (user == null || user.getPassword() == null ||
                (user.getEmail() == null && user.getUsername() == null)) {
            throw new IllegalEntityException("Not sufficient "
                    + "information for login.", user);
        }
        log.info(String.valueOf(US.passwordCheck(user.getPassword(),
                UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))));
        if(US.passwordCheck(user.getPassword(),
                UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))) {
            /*
             * An EntityModel of a User is created. First the user id
             * has to be determined to get the User by ID. If the user
             * cannot be found it most certainly means that there was an
             * internal error.
             */
            return assembler.toModel(UR.findById(UR.findByUsernameID(
                    user.getUsername())).orElseThrow(InternalException::new));
        }
        else {
            /*
             * If the specified password does not correlate to the one in
             * the database an error is raised.
             */
            throw new RestAuthenticationException("Authentication failed.");
        }
    }

//    /**
//     * Bundles all illegal {@link HttpMethod}s for the mapping
//     * <quote>/login</quote> and returns throws a
//     * {@link MethodNotSupportedException}.
//     *
//     * @param request The request made by a client.
//     */
//    @RequestMapping(value = "/login", method = {RequestMethod.GET,
//            RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
//    public void noLogin(HttpServletRequest request) {
//        throw new MethodNotSupportedException(
//                HttpMethod.valueOf(request.getMethod()));
//    }

//    /**
//     * Responds to an OPTIONS request from the client with the corresponding
//     * header for the login operation.
//     *
//     * @param response The response to be given.
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.OPTIONS)
//    public void loginMethod(HttpServletResponse response) {
//        response.setHeader("Allow", "POST, OPTIONS");
//    }

    // TODO implement

    /**
     * Registers the specified {@link User} with the service.
     *
     * @param user The {@link User} to be registered.
     * @return An {@link EntityModel} representation of the {@link User}.
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public EntityModel<User> register(@RequestBody User user) {
        /*
         * Certain fields may not be null.
         */
        if (user == null || user.getPassword() == null ||
                (user.getEmail() == null && user.getUsername() == null)) {
            throw new IllegalEntityException("Not sufficient "
                    + "information for register operation.", user);
        }
        if (UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
            // The user is saved in the database
            US.saveRegisterData(user.getUsername(), user.getPassword(),
                    user.getEmail(), user.getRole(), user.getLearningtype());
            /*
             * The EntityModel is created and returned. An error is raised if
             * it is not found after saving.
             */
            return assembler.toModel(UR.findById(UR.findByUsernameID(
                    user.getUsername())).orElseThrow(InternalException::new));
        }
        else {
            /*
             * If the User already exists a 409 error is given.
             */
            throw new ResourceAlreadyExistsException("User could not be "
                    + "created.");
        }
    }

//    /**
//     * Bundles all illegal {@link HttpMethod}s for the mapping
//     * <quote>/register</quote> and returns throws a
//     * {@link MethodNotSupportedException}.
//     *
//     * @param request The request made by a client.
//     */
//    @RequestMapping(value = "/register", method = {RequestMethod.GET,
//            RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
//    public void noRegister(HttpServletRequest request) {
//        throw new MethodNotSupportedException(
//                HttpMethod.valueOf(request.getMethod()));
//    }

//    /**
//     * Responds to an OPTIONS request from the client with the corresponding
//     * header for the register operation.
//     *
//     * @param response The response to be given.
//     */
//    @RequestMapping(value = "/register", method = RequestMethod.OPTIONS)
//    public void registerMethod(HttpServletResponse response) {
//        response.setHeader("Allow", "POST, OPTIONS");
//    }
}
package thowl.wiprojekt.controller;

import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

@RestController
public class UserController {

    private final UserRepository UR;
    private final UserService US;

    public UserController(UserRepository ur, UserService us) {
        UR = ur;
        US = us;
    }

    /**
     * Retrieves a user with the given ID from the database.
     *
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the retrieved user if it exists
     * @throws ResourceNotFoundException if the user does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        // Retrieve user with given id from database
        User user = UR.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create HATEOAS links for the user
        //Link selfLink = linkTo(methodOn(UserController.class).getUser(id)).withSelfRel();

        // Add links to the user response
        //user.add(selfLink);
        return ResponseEntity.ok(user);
    }

}

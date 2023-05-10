package thowl.wiprojekt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.entity.model.UserModelAssembler;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

@Slf4j
@RestController
public class UserController {

    private final UserRepository UR;
    private final UserService US;

    @Autowired
    private UserModelAssembler assembler;

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

        return ResponseEntity.ok(user);
    }

    /**
     * Deletes a user with the given ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with an HTTP status code of 204 (No Content) if the user was deleted successfully
     * @throws ResourceNotFoundException if the user does not exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return UR.findById(id)
                .map(user -> {
                    UR.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }


    //Update Userinfos updaten auf einen bereits bestehenden //PutMapping






}

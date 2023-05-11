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

    @Autowired
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return UR.findById(id)
                .map(user -> {
                    UR.delete(user);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    /**
     * Updates the user with the given ID.
     *
     * @param id the ID of the user to update
     * @param updatedUser the updated user entity
     * @return a ResponseEntity with the updated user entity and an HTTP status code of 200 (OK)
     * @throws ResourceNotFoundException if the user does not exist
     */


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return UR.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    User savedUser = UR.save(user);
                    return ResponseEntity.ok(savedUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

//    @PatchMapping("/patchUpdate/{id}")
//    public ResponseEntity<User> patchUpdateUser(@PathVariable Long id, @RequestBody User updateUser) {
//        User user = UR.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
//
//        if (updateUser.getUsername() != null) {
//            user.setUsername(updateUser.getUsername());
//        }
//        if (updateUser.getEmail() != null) {
//            user.setEmail(updateUser.getEmail());
//        }
//        if (updateUser.getPassword() != null) {
//            user.setPassword(updateUser.getPassword());
//        }
//        User updatedUser = UR.save(user);
//        return ResponseEntity.ok(updatedUser);
//    }
}


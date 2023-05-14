package thowl.wiprojekt.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;


@Service
public class UserService {
    int strength = 10; //strength of the encoder
    private final UserRepository UJR;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository UJR) {
        this.UJR = UJR;
        bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
    }

    public boolean passwordCheck(String password,String usernamePassword){
        return bCryptPasswordEncoder.matches(password,usernamePassword);
    }
    public String passwordEncoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public void saveRegisterData(String username, String password, String email, Role role, String learningtype) {
        User uData = new User();
        uData.setUsername(username);
        uData.setPassword(passwordEncoder(password));
        uData.setEmail(email);
        uData.setRole(role);
        uData.setLearningtype(learningtype);
        UJR.save(uData);
    }
    public boolean partialUpdate(long id, String key, String value) throws ResourceNotFoundException {
        Optional<User> optionalUser = UJR.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Perform the partial update based on the key-value pair
            switch (key) {
                case "username":
                    user.setUsername(value);
                    break;
                case "lastName":
                    user.setPassword(value);
                    break;
                case "email":
                    user.setEmail(value);
                    break;
                case "learningtype":
                    user.setLearningtype(value);
                    break;
                // Add additional cases for other fields as needed
                default:
                    throw new IllegalArgumentException("Invalid key: " + key);
            }

            UJR.save(user);
            return true;
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
}

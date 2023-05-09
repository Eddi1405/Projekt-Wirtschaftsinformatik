package thowl.wiprojekt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.repository.UserRepository;
import thowl.wiprojekt.service.UserService;

@Slf4j
@RestController
public class LoginController {

    private final UserRepository UR;

    private final UserService US;

    public LoginController(UserRepository UR, UserService US) {
        this.UR = UR;
        this.US = US;
    }

    @PutMapping("/login")
    public boolean Login(@RequestBody User user) {
        log.info(String.valueOf(US.passwordCheck(user.getPassword(), UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))));
        if(US.passwordCheck(user.getPassword(),UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()))) {
            return true;
        } else {
            return false;
        }

    }

    @PutMapping("/register")
    public boolean Register(@RequestBody User user) {
        if (UR.findByUsernameOrEmail(user.getUsername(), user.getEmail()) == null) {
            US.saveRegisterData(user.getUsername(), user.getPassword(), user.getEmail(), "test", "test");
            return true;
        } else {
            return false;
        }
    }
}
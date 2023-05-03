package thowl.wiprojekt.service;

import thowl.wiprojekt.entity.userData;
import thowl.wiprojekt.repository.userJpaRepository;

import org.springframework.stereotype.Service;


@Service
public class userService {

    private final userJpaRepository uJR;

    public userService(userJpaRepository uJR) {
        this.uJR = uJR;
    }

    public void saveRegisterData(String username, String password, String email, String roles) {
        userData uData = new userData();
        uData.setUsername(username);
        uData.setPassword(password);
        uData.setEmail(email);
        uData.setRoles(roles);
        uJR.save(uData);
    }
}

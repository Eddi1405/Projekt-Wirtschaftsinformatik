package thowl.wiprojekt.service;

import thowl.wiprojekt.entity.UserData;
import thowl.wiprojekt.repository.UserJpaRepository;

import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserJpaRepository UJR;

    public UserService(UserJpaRepository UJR) {
        this.UJR = UJR;
    }

    public void saveRegisterData(String username, String password, String email, String role, String learningtype) {
        UserData uData = new UserData();
        uData.setUsername(username);
        uData.setPassword(password);
        uData.setEmail(email);
        uData.setRole(role);
        uData.setLearningtype(learningtype);
        UJR.save(uData);
    }
}

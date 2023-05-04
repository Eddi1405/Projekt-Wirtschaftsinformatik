package thowl.wiprojekt.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import thowl.wiprojekt.entity.UserData;
import thowl.wiprojekt.repository.UserJpaRepository;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;


@Service
public class UserService {
    int strength = 10; //strength of the encoder
    private final UserJpaRepository UJR;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserJpaRepository UJR) {
        this.UJR = UJR;
        bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
    }

    public boolean passwordCheck(String password,String usernamePassword){
        return bCryptPasswordEncoder.matches(password,usernamePassword);
    }
    public String passwordEncoder(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public void saveRegisterData(String username, String password, String email, String role, String learningtype) {
        UserData uData = new UserData();
        uData.setUsername(username);
        uData.setPassword(passwordEncoder(password));
        uData.setEmail(email);
        uData.setRole(role);
        uData.setLearningtype(learningtype);
        UJR.save(uData);
    }
}

package thowl.wiprojekt.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ReflectionUtils;
import thowl.wiprojekt.entity.User;
import thowl.wiprojekt.errors.ResourceNotFoundException;
import thowl.wiprojekt.errors.UnacceptableRequestException;
import thowl.wiprojekt.objects.LearningType;
import thowl.wiprojekt.objects.Role;
import thowl.wiprojekt.repository.UserRepository;

import org.springframework.stereotype.Service;

import javax.management.ReflectionException;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.Map;
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

    //TODO: Checken ob es funktioniert
    //Changes: float leanringtype
    public void saveRegisterData(User user) {
//        User uData = new User();
//        uData.setUsername(username);
        user.setPassword(passwordEncoder(user.getPassword()));
//        uData.setEmail(email);
//        uData.setRole(role);
//        uData.setLearningtypeVisual(learningtypeVisual);
//        uData.setLearningtypeAural(learningtypeAural);
//        uData.setLearningtypeReadwrite(learningtypeReadwrite);
//        uData.setLearningtypeKinesthetic(learningtypeKinesthetic);
        UJR.save(user);
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
                case "password":
                    user.setPassword(value);
                    break;
                case "email":
                    user.setEmail(value);
                    break;

                case "dark_mode":
                    user.setDarkmode(Boolean.parseBoolean(value));
                    break;
                case "de_en":
                    user.setDeEn(Boolean.parseBoolean(value));
                    break;
                case "contrast":
                    user.setContrast(Boolean.parseBoolean(value));
                    break;
                case "font_size":
                    user.setFontSize(Integer.parseInt(value));
                    break;
                case "Eye_tracking":
                    user.setEyeTracking(Boolean.parseBoolean(value));
                    break;
                case "color_blindness":
                    user.setColorBlindness(Integer.parseInt(value));
                    break;
                case "sign_language":
                    user.setSignLanguage(Boolean.parseBoolean(value));
                    break;

                case "learningtypeVisual":
                    user.setLearningtypeVisual(Float.parseFloat(value));
                    break;
                case "learningtypeAural":
                    user.setLearningtypeAural(Float.parseFloat(value));
                    break;
                case "learningtypeKinesthetic":
                    user.setLearningtypeKinesthetic(Float.parseFloat(value));
                    break;
                case "learningtypeReadWrite":
                    user.setLearningtypeReadwrite(Float.parseFloat(value));
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

    public User updateUserByFields(long id, Map<String, Object> fields) {
        Optional<User> existingUser = UJR.findById(id);

        if(existingUser.isPresent()){
            fields.forEach((key,value)->{
                Field field = ReflectionUtils.findField(User.class,key);
                if(field != null){
                    field.setAccessible(true);

                    if(key.equals("password")){
                        String hashedPassword = bCryptPasswordEncoder.encode(value.toString());
                        ReflectionUtils.setField(field,existingUser.get(),hashedPassword);
                    }else {

                        ReflectionUtils.setField(field, existingUser.get(), value);
                    }
                }else{
                    throw new UnacceptableRequestException("");
                }

            });
            return UJR.save(existingUser.get());
        }
        return null;
    }

}

package thowl.wiprojekt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thowl.wiprojekt.repository.UserJpaRepository;
import thowl.wiprojekt.service.UserService;

@Slf4j
@Controller
public class LoginController {

    private final UserJpaRepository UJR;

    private final UserService userService;

    public LoginController(UserJpaRepository ujr, UserService userService) {
        this.UJR = ujr;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String ShowLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam("email/name") String email_name,@RequestParam("password") String password){
    log.info(UJR.findByUsername(email_name));
    log.info(String.valueOf(userService.passwordCheck(password,UJR.findByUsername(email_name))));
        if(userService.passwordCheck(password,UJR.findByUsername(email_name))){
            return "redirect:/h2";
        } else if (userService.passwordCheck(password,UJR.findByEmail(email_name))) {
            return "redirect:/h2";
        }else {
            return "login";
        }
    }

    @GetMapping("/register")
    public String ShowRegister() {
        return "register";
    }
    @PostMapping("/register")
    public String Register(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password){
        userService.saveRegisterData(name,password,email,"test","test");
        return "redirect:/login";
    }
}

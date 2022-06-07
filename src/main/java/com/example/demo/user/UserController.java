package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class UserController {

    UserService userService;

    JavaMailSender emailSenderService;

    @Autowired
    public UserController(UserService userService, JavaMailSender emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    //get username and score for ranking the best users
    @GetMapping("/users")
    public List<LoginUserDto> getUsers() {
        return userService.getUsers();
    }

    //add score to user
    @PutMapping("/score/{userScore}")
    public UserDto setScore(@PathVariable long userScore){
        return userService.setScoreOfLoggedUser(userScore);
    }

    String userName = "";
    //save new user to db
    @PostMapping("/register")
    public void addUser(@RequestBody User user) throws MessagingException, UnsupportedEncodingException {
        userService.addUser(user);
        String toAddress = user.getEmail();
        String fromAddress = "pavolhodas4@gmail.com";
        String senderName = "Quiz";
        String subject = "Potvrdte registraciu";
        String content = "Ahoj [[name]],<br>"
                + "Prosím potvrďte registráciu:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Ďakujeme,<br>"
                + "Guiz.";

        userName = user.getUsername();
        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());

        String verifyURL = "http://localhost:4200" + "/verify";

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        emailSenderService.send(message);
    }

    @GetMapping("/support/{name}/{text}")
    public void support(@PathVariable String name, @PathVariable String text)throws MessagingException, UnsupportedEncodingException{
        userService.supportEmail(name, text);
    }

    String email = "";
    @GetMapping("/password/forgot/{email}")
    public void passwordChange(@PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = "pavolhodas4@gmail.com";
        String senderName = "Quiz";
        String subject = "Zmena hesla";
        String content = "Zmente si heslo:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">ZMENIŤ HESLO</a></h3>"
                + "Ďakujeme,<br>"
                + "Guiz.";

        this.email = email;
        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String passwordRecoveryURL = "http://localhost:4200" + "/new-password-set";

        content = content.replace("[[URL]]", passwordRecoveryURL);

        helper.setText(content, true);

        emailSenderService.send(message);
    }
    @GetMapping("/api/auth/verify/{userName}")
    public void sendEmail(@PathVariable String userName){
        userName = this.userName;
        userService.verifyUser(userName);
    }

    @PutMapping("/api/auth/change/password/{password}")
    public void changePassword(@PathVariable String password){
        userService.changePassword(password, this.email);
    }

    @PutMapping("/userSkill/{skill}")
    public void setSkill(@PathVariable int skill){
        userService.setSkill(skill);
    }

    @GetMapping("/userSkill")
    public Integer getSkill(){
        return userService.getSkill();
    }
}

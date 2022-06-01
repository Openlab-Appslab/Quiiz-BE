package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

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

        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());

        String verifyURL = "https://apps-lapp-server.herokuapp.com" + "/api/auth/verify/" + user.getUsername();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        emailSenderService.send(message);
    }

    @GetMapping("/password/change/{email}")
    public void passwordChange(@PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = "pavolhodas4@gmail.com";
        String senderName = "Quiz";
        String subject = "Zmena hesla";
        String content = "Zmente si heslo:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">ZMENIŤ HESLO</a></h3>"
                + "Ďakujeme,<br>"
                + "Guiz.";

        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String passwordRecoveryURL = "https://apps-lapp-server.herokuapp.com" + "/api/auth/password/recovery/" + email;

        content = content.replace("[[URL]]", passwordRecoveryURL);

        helper.setText(content, true);

        emailSenderService.send(message);
    }
    @PutMapping("/api/auth/verify/{userName}")
    public void sendEmail(@PathVariable String userName){
        userService.verifyUser(userName);
    }

//

    @PutMapping("/userSkill/{skill}")
    public void setSkill(@PathVariable int skill){
        userService.setSkill(skill);
    }

    @GetMapping("/userSkill")
    public Integer getSkill(){
        return userService.getSkill();
    }

    private String readEmailTemplate(){
        StringBuilder emailTemplate = new StringBuilder();
        ClassPathResource resource = new ClassPathResource("emailtemplate.html");
        Scanner scanner = null;
        try {
            File email = resource.getFile();
            scanner = new Scanner(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(scanner.hasNextLine()){
            emailTemplate.append(scanner.nextLine());
        }
        scanner.close();
        return emailTemplate.toString();
    }


}

package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class UserController {

    UserService userService;

    EmailSenderService emailSenderService;

    @Autowired
    public UserController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    //get username and score for ranking the best users
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    //add score to user
    @PutMapping("/score/{userScore}")
    public UserDto setScore(@PathVariable long userScore){
        return userService.setScoreOfLoggedUser(userScore);
    }

    //save new user to db
    @PostMapping("/register")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
        String text = readEmailTemplate();

        text = text.replaceFirst("NAME", user.getUsername());

        emailSenderService.sendEmail(user.getEmail(), "Quiz",  text);
    }

    @GetMapping("/email")
    public void sendEmail(@RequestBody User user){
        userService.getUserByUsername(user.getUsername());
    }

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

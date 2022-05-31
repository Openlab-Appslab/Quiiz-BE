package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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

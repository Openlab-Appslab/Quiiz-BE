package com.example.demo.user;

import com.example.demo.security.SecurityConfiguration;
import org.apache.catalina.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultInitialization implements CommandLineRunner {

    private final UserService userService;

    public DefaultInitialization(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(String... args){
        this.createAndPersistUser("palo", "palo123");
    }

    private void createAndPersistUser(String username, String password){
        User user = new User(username, 0);
        this.userService.addUser(user);
    }
}

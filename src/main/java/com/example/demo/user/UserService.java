package com.example.demo.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUserByUsername(String username);
    User updateUser(User user);
    List<UserDto> getUsers();
    UserDto setScoreOfLoggedUser(long score);
}

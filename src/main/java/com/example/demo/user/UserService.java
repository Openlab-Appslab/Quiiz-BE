package com.example.demo.user;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUserByUsername(String username);
    User updateUser(User user);
    List<LoginUserDto> getUsers();
    UserDto setScoreOfLoggedUser(long score);
    void setSkill(int skill);
    Integer getSkill();

    User verifyUser(String userName);

    void changePassword(String password, String email);
}

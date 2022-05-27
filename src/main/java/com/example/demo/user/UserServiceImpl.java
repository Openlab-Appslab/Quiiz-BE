package com.example.demo.user;

import com.example.demo.answer.Answer;
import com.example.demo.answer.Model.AnswerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User addUser(User user) {
        if(!repository.findByUsername(user.getUsername()).isPresent()){

            user.setId(0);
            String encodedPassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return this.repository.save(user);
        }
        return user;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public List<UserDto> getUsers() {
        return repository.getAllUsers().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto setScoreOfLoggedUser(long score) {
        User user = this.getCurrentUser();
        user.setScore(score);

        return convertToDto(repository.save(user));
    }

    @Override
    public void setSkill(int skill) {
        User user = this.getCurrentUser();
        user.setSkill(skill);

        convertToDto(repository.save(user));
    }

//    @Override
//    public User register(User user) throws UserAlreadyExistException {
//        return null;
//    }

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setName(user.getUsername());
        userDto.setScore(user.getScore());
        return userDto;
    }


    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private void createAndPersistUser(String username, String password) {
        String encodedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, 0);
        this.addUser(user);
    }
}

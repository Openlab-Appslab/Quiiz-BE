package com.example.demo.user;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private ModelMapper modelMapper;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User addUser(User user) {
        user.setId(0);
        return this.repository.save(user);
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

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setName(user.getUsername());
        userDto.setScore(user.getScore());
        return userDto;
    }
}

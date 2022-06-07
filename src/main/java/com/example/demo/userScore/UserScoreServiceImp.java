package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import com.example.demo.Quiz.QuizDto;
import com.example.demo.score.ScoreDto;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserScoreServiceImp implements UserScoreService {

    ModelMapper modelMapper;

    UserScoreRepository userScoreRepository;

    UserService userService;

    @Autowired
    public UserScoreServiceImp(ModelMapper modelMapper, UserScoreRepository userScoreRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.userScoreRepository = userScoreRepository;
        this.userService = userService;
    }

    @Override
    public UserScore saveScore(Quiz quiz, Integer score) {
        User user = this.getCurrentUser();
        UserScore userScore = new UserScore();
        userScore.setUser(user);
        userScore.setQuiz(quiz);
        userScore.setScore(score);
        return userScoreRepository.save(userScore);
    }

    @Override
    public Integer getAllScore() {
        User user = getCurrentUser();

        return userScoreRepository.getScoreForUser(user.getId()).stream().reduce(0, Integer::sum);
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private UserScoreDto convertToDto(UserScore userScore){
        UserScoreDto userScoreDto = modelMapper.map(userScore, UserScoreDto.class);
        userScoreDto.setUserName(userScore.getUser().getUsername());
        userScoreDto.setScore(userScore.getScore());
        userScoreDto.setQuizName(userScore.getQuiz().getName());
        return userScoreDto;
    }
}

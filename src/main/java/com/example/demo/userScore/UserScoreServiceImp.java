package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import com.example.demo.score.ScoreDto;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserScoreServiceImp implements UserScoreService {

    UserScoreRepository userScoreRepository;

    UserService userService;

    @Autowired
    public UserScoreServiceImp(UserScoreRepository userScoreRepository, UserService userService) {
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

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}

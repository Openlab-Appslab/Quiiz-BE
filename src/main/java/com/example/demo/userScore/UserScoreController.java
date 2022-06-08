package com.example.demo.userScore;

import com.example.demo.Quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class UserScoreController {

    UserScoreService userScoreService;

    @Autowired
    public UserScoreController(UserScoreService userScoreService) {
        this.userScoreService = userScoreService;
    }

    //POST score to user
    @PostMapping(value = "/score/{quiz}/{score}")
    public void addUser(@PathVariable Quiz quiz, @PathVariable Integer score){
        userScoreService.saveScore(quiz, score);
    }

    //GET all scores for all users and quizzes
    @GetMapping("/score/all")
    public Integer getAllScore(){
        return userScoreService.getAllScore();
    }

    @GetMapping("/score/{quizId}")
    public Integer getAllScoreByQuiz(@PathVariable String quizId){
        return userScoreService.getAllScoreByQuiz(quizId);
    }

    @GetMapping("/score/allUsers")
    public List<UserScoreDto> scoreForUserAndQuiz(){
        return userScoreService.getAllScoreForAllQuiz();
    }
}

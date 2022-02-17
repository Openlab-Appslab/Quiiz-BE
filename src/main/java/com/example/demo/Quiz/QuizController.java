package com.example.demo.Quiz;

import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping
public class QuizController {

    Random rand = new Random();

    @Autowired
    QuizService quizService;
    Quiz quiz;
    @GetMapping("/quiz/add")
    public Quiz writeQuiz(){
        Quiz createdQuiz = new Quiz("Programovanie");
        quizService.saveQuiz(createdQuiz);
        return createdQuiz;
    }
    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

    @RequestMapping("/quizzes/get")
    public List<Quiz> getQuizzes(){
        List<Quiz> quizList;
        quizList = quizService.readQuiz(quiz);
        return quizList;
    }

    @RequestMapping("/quiz/get")
    public Quiz getQuiz(){
        List<Quiz> quizList;
        quizList = quizService.readQuiz(quiz);
        int randQuiz = rand.nextInt(quizList.size());
        return quizList.get(randQuiz);
    }
}

package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Question.QuestionDto;
import com.example.demo.Quiz.Model.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:4200")
public class QuizController {

    QuizService quizService;

    QuestionService questionService;

    ModelMapper modelMapper;

    @Autowired
    private QuizController(QuizService quizService, ModelMapper modelMapper, QuestionService questionService){
        this.quizService = quizService;
        this.modelMapper = modelMapper;
        this.questionService = questionService;
    }

    @PostMapping("/saveQuiz")
    public void saveQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

//    @PostMapping("/postQuizId")
//    public void getQuizId(@RequestBody String quizName){
//        quizService.getQuizName(quizName);
//    }
//
//    @GetMapping("/quiz")
//    @ResponseBody
//    public List<QuizDto> getQuizzes(){
//        return quizService.readQuizzes();
//    }

    @GetMapping("/quiz/names")
    @ResponseBody
    public List<String> getQuizNames(){
        return quizService.getAllQuizNames();
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/quiz/{quizId}")
    @ResponseBody
    public List<QuestionDto> getQuestionForQuiz(@PathVariable String quizId){
        return questionService.getRandomQuestionsForQuiz(quizId);
    }

    //GET quizzes for skill of logged user
    @GetMapping("/quiz/difficulty/{quizId}")
    @ResponseBody
    public List<QuestionDto> getQuestionsByDifficulty(@PathVariable String quizId){
        return questionService.getQuestionsByDifficulty(quizId);
    }

    //GET all quizzes
    @GetMapping("/allQuizzes")
    @ResponseBody
    public List<QuizDto> getAllQuizzes(){
        return quizService.readQuizzes();
    }

    @PutMapping("/setFavourite")
    @ResponseBody
    public void setFavorite(@RequestBody Quiz quiz){
        quizService.setFavorite(quiz);
    }
 }
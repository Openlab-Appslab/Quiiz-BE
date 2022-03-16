package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.Quiz.Model.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
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

    @PostMapping("/postQuizId")
    public void getQuizId(@RequestBody String quizName){
        quizService.getQuizName(quizName);
    }

    @GetMapping("/quiz")
    @ResponseBody
    public List<QuizDto> getQuizzes(){
        return quizService.readQuizzes();
    }

    @GetMapping("/quiz/names")
    @ResponseBody
    public List<String> getQuizNames(){
        return quizService.getAllQuizNames();
    }

    @GetMapping("/quiz/{quizId}")
    @ResponseBody
    public List<QuestionDto> getQuestionForQuiz(@PathVariable String quizId){
        return questionService.getAllQuestionsForQuiz(quizId);
    }
 }
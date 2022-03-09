package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Question.Question;
import com.example.demo.Quiz.Model.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuizController {

    QuizService quizService;

    ModelMapper modelMapper;

    @Autowired
    private QuizController(QuizService quizService, ModelMapper modelMapper){
        this.quizService = quizService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/saveQuiz")
    public void saveQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

//    @PostMapping("/postQuizId")
//    public void getQuizId(@RequestBody Quiz quiz){
//        questionService.questionsByQuizName(quiz.getName());
//    }

    @GetMapping("/getQuizzes")
    @ResponseBody
    public List<QuizDto> getQuizzes(){
        return quizService.readQuizzes();
    }

    @GetMapping("/getQuizzes/names")
    @ResponseBody
    public List<String> getQuizNames(){
        return quizService.getAllQuizNames();
    }
 }
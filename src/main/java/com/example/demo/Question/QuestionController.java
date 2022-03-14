package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    private QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @PostMapping("/saveQuestions")
    public void postQuestions(@RequestBody List<Question> questions){
        questionService.saveQuestions(questions);
    }

    @GetMapping("/getQuestions")
    @ResponseBody
    public List<QuestionDto> getQuestions(){

        return questionService.findAll();
    }

//    @GetMapping("/questionsAnswersById")
//    @ResponseBody
//    public List<QuestionDto> getQuestionById(){
//        return questionService.getQuestionByID();
//    }

    @GetMapping("/questionsByQuizId")
    @ResponseBody
    public List<QuestionDto> getQuestionForQuiz(){
        return questionService.getAllQuestionsForQuiz();
    }

    @GetMapping("/question/{quizId}")
    @ResponseBody
    public String getQuestionsByQizId(@PathVariable String quizId) {
        return quizId;
    }
}

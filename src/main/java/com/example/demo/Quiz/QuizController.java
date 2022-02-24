package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class QuizController {

    //Random rand = new Random();

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    ModelMapper modelMapper;

    private QuizDto convertToDto(Quiz quiz){
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        quizDto.setName(quiz.getName());
        return quizDto;
    }

    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }


    @GetMapping("/get/quizzes")
    @ResponseBody
    public List<QuizDto> getQuizzes(){
        List<Quiz> quizList = new ArrayList<>();
        Iterable<Quiz> quizzes = quizService.findAll();
        quizzes.forEach(quizList::add);

        return quizList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}

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
import java.util.Random;
import java.util.stream.Collectors;

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

    @PostMapping("/quizQuestion")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }


    @GetMapping("/getQuizzes")
    @ResponseBody
    public List<QuizDto> getQuizzes(){
        List<Quiz> quizList = new ArrayList<>();
        Iterable<Quiz> quizzes = quizService.findAll();
        quizzes.forEach(quizList::add);

        return quizList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QuizDto convertToDto(Quiz quiz){
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        quizDto.setName(quiz.getName());
        return quizDto;
    }
}
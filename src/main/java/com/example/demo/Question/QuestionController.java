package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Quiz.QuizDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class QuestionController {

    private final QuestionService questionService;

    private final ModelMapper modelMapper;

    @Autowired
    private QuestionController(QuestionService questionService, ModelMapper modelMapper){
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/getQuestions")
    @ResponseBody
    public List<QuestionDto> getQuestions(){
        List<Question> questionList = new ArrayList<>();
        Iterable<Question> questions = questionService.findAll();
        questions.forEach(questionList::add);

        return questionList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private QuestionDto convertToDto(Question question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setContent(question.getContent());
        return questionDto;
    }
}

package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AnswerContoller {

    @Autowired
    AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

    private AnswerDto convertToDto(Answer answer){
        AnswerDto answerDto = modelMapper.map(answer, AnswerDto.class);
        answerDto.setContent(answer.getContent());
        return answerDto;
    }

    @GetMapping("/get/answers")
    @ResponseBody
    public List<AnswerDto> getAnswers(){

        List<Answer> answerList = new ArrayList<>();
        Iterable<Answer> answers = answerService.findAll();
        answers.forEach(answerList::add);


        return answerList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}

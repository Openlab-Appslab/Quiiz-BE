package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionRepository;
import com.example.demo.Question.Model.QuestionService;
import com.example.demo.answer.Answer;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    ModelMapper modelMapper;

    QuestionRepository questionRepository;

    AnswerService answerService;


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public List<QuestionDto> saveQuestions(List<Question> questions) {
        List<Question> questionList = questionRepository.saveAll(questions);
        return questionList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> findAll() {

        List<Question> questionList = questionRepository.findAll();

        return questionList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getRandomAnsByID() {
        List<Question> questionList = questionRepository.getAllById(1L);

        return questionList.stream().map(q -> {
            QuestionDto question = new QuestionDto();
            question.setContent(q.getContent());
            question.setAnswerList(answerService.getRandom());
            return question;
        }).collect(Collectors.toList());
    }

    private QuestionDto convertToDto(Question question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setContent(question.getContent());
        return questionDto;
    }
}

package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionRepository;
import com.example.demo.Question.Model.QuestionService;
import com.example.demo.answer.Answer;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    Answer answer;

    ModelMapper modelMapper;

    QuestionRepository questionRepository;

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
    public List<Question> getAllByID() {
        List<Question> questionList = questionRepository.getAllById(1L);
        return questionRepository.getAllById(1L);
    }

    private QuestionDto convertToDto(Question question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setContent(question.getContent());
        return questionDto;
    }
}

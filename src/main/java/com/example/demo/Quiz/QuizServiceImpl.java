package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import com.example.demo.Question.QuestionDto;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    QuizRepository quizRepository;

    QuizService quizService;

    ModelMapper modelMapper;

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        quizRepository.save(quiz);
    }

    @Override
    public List<QuizDto> readQuizzes() {

        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllQuizNames() {
        return quizRepository.getAllQuizNames();
    }

    @Override
    public String getQuizName(String quizName) {
        return quizRepository.getQuizName(quizName);
    }

    private QuizDto convertToDto(Quiz quiz){
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        quizDto.setName(quiz.getName());
        return quizDto;
    }
}

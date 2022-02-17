package com.example.demo.answer;

import com.example.demo.Question.Question;
import com.example.demo.Quiz.Quiz;
import com.example.demo.answer.Model.AnswerRepository;
import com.example.demo.answer.Model.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public List<Answer> readAnswers(List<Answer> answerList) {

        List<Answer> answers = new ArrayList<>();
        answerRepository.findAll()
                .forEach(answers::add);
        return  answers;
    }
}

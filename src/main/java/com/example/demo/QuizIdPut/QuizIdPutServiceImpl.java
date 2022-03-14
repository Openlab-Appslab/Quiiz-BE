package com.example.demo.QuizIdPut;

import com.example.demo.Quiz.Model.QuizService;
import com.example.demo.QuizIdPut.Model.QuizIdPutRepository;
import com.example.demo.QuizIdPut.Model.QuizIdPutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizIdPutServiceImpl implements QuizIdPutService {

    QuizIdPutRepository quizIdPutRepository;

    QuizIdPutService quizIdPutService;

    @Autowired
    public void setQuizIdPutRepository(QuizIdPutRepository quizIdPutRepository) {
        this.quizIdPutRepository = quizIdPutRepository;
    }

    @Autowired
    public void setQuizIdPutService(QuizIdPutService quizIdPutService) {
        this.quizIdPutService = quizIdPutService;
    }

    @Override
    public void replaceQuizId(String quizIdPut) {
        quizIdPutRepository.findById(13L).map(quizIdPut1 -> {
            quizIdPut1.setQuizIdToPut(quizIdPut);
            return quizIdPutRepository.save(quizIdPut1);
        });
    }

    @Override
    public void saveQuizId(QuizIdPut quizIdPut) {
        quizIdPutRepository.save(quizIdPut);
    }

    @Override
    public String getQuizId() {
        return quizIdPutRepository.getById(13L).getQuizIdToPut();
    }
}

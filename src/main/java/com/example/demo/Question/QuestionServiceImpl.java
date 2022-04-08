package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionRepository;
import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import com.example.demo.answer.Model.AnswerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    ModelMapper modelMapper;

    QuestionRepository questionRepository;

    AnswerService answerService;

    QuizRepository quizRepository;

    QuizService quizService;


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

    @Autowired
    public void setQuizRepository(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
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
    public List<QuestionDto> getRandomQuestionsForQuiz(String quizId) {
        List<Question> questions = questionRepository.getAllQuestionsById(quizId);
// tu pozriet ci uz su ulozene v db vsetky answers k tejto question
        return questions.stream().map(q -> {
            QuestionDto question = convertToDto(q);
            question.setAnswerList(answerService.getRandom(q.getId()));
            return question;
        }).collect(Collectors.toList());
    }



    private QuestionDto convertToDto(Question question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setContent(question.getContent());
        questionDto.setId(question.getId());
        return questionDto;
    }
}

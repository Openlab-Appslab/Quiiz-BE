package com.example.demo.Question;

import com.example.demo.Question.Model.QuestionRepository;
import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import com.example.demo.answer.Model.AnswerService;
import com.example.demo.user.User;
import com.example.demo.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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

        if(getCurrentUser().getSkill() == 0) {
// tu pozriet ci uz su ulozene v db vsetky answers k tejto question
            return questions.stream().map(q -> {
                QuestionDto question = convertToDto(q);
                question.setAnswerList(answerService.getRandom(q.getId()));
                return question;
            }).collect(Collectors.toList());
        }
        else{
            return questions.stream().map(q -> {
                QuestionDto question = convertToDto(q);
                question.setAnswerList(answerService.getByDifficulty(q.getId()));
                return question;
            }).collect(Collectors.toList());
        }
    }

    @Override
    public List<QuestionDto> getQuestionsByDifficulty(String quizId) {
        List<Question> questions = questionRepository.getAllQuestionsById(quizId);

        return questions.stream().map(q -> {
            QuestionDto question = convertToDto(q);
            question.setAnswerList(answerService.getByDifficulty(q.getId()));
            return question;
        }).collect(Collectors.toList());
    }


    private QuestionDto convertToDto(Question question){
        QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
        questionDto.setContent(question.getContent());
        questionDto.setId(question.getId());
        return questionDto;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.userService.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}

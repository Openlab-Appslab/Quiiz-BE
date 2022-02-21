package com.example.demo.Quiz;

import com.example.demo.Question.Model.QuestionService;
import com.example.demo.Quiz.Model.QuizRepository;
import com.example.demo.Quiz.Model.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quizzes/get")
public class QuizController {

    //Random rand = new Random();

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    QuizRepository quizRepository;



//    @GetMapping("/quiz/add")
//    public Quiz writeQuiz(){
//        Question question = new Question();
//        Answer answer = new Answer();
//        question.setContent("Ktory jazyk je programovaci?");
//        answer.setContent("java");
//        List<Question> questionList = new ArrayList<>();
//        List<Answer> answerList = new ArrayList<>();
//        questionList.add(question);
//
//
//        Quiz createdQuiz = new Quiz("Programovanie", questionList);
//        quizService.saveQuiz(createdQuiz);
//        return createdQuiz;
//    }
    @PostMapping("/quiz/question")
    public void postQuiz(@RequestBody Quiz quiz){
        quizService.saveQuiz(quiz);
    }

    @GetMapping
    public Iterable<Quiz> getQuizzes(){
        return quizRepository.findAll();
    }

//    @RequestMapping("/quizzes/get")
//    public List<Quiz> getQuizzes(){
//        Quiz quiz = new Quiz();
//        List<Quiz> quizList;
//        quizList = quizService.readQuiz(quiz);
//        return quizList;
//    }
//
//    @RequestMapping("/quiz/get")
//    public Quiz getQuiz(){
//        Quiz quiz = new Quiz();
//        List<Quiz> quizList;
//        quizList = quizService.readQuiz(quiz);
//        int randQuiz = rand.nextInt(quizList.size());
//        return quizList.get(randQuiz);
//    }
}

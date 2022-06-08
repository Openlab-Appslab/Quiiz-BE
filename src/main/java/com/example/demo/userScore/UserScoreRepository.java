package com.example.demo.userScore;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    @Query("SELECT u FROM UserScore u ")
    List<UserScore> getAllUserScore();

    @Query("SELECT u FROM UserScore u WHERE u.quiz.name = :quizId")
    List<UserScore> getScoreForUserByQuiz(@Param("quizId") String quizId);

    @Query("SELECT u.score FROM UserScore u WHERE u.user.id = :id")
    List<Integer> getScoreForUser(@Param("id") long id);

    @Query("SELECT u FROM UserScore u WHERE u.user.id = :id")
    List<UserScore> getUserScoreByUserId(@Param("id") long id);

    @Query("SELECT u.quiz.name FROM UserScore u")
    Set<String> getAllName();

    @Query("SELECT u.user FROM UserScore u")
    Set<User> getAllUsers();

    @Query("SELECT u FROM UserScore u WHERE u.quiz.name = :quizId")
    List<UserScore> findFavouriteQuiz(@Param("quizId") String quizId);


    @Query("SELECT u.score FROM UserScore u WHERE u.user.id = :id")
    UserScore findUser(@Param("id") String id);
}

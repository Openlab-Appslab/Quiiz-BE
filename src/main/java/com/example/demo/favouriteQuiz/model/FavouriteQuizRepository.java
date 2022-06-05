package com.example.demo.favouriteQuiz.model;

import com.example.demo.favouriteQuiz.FavouriteQuiz;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouriteQuizRepository extends JpaRepository<FavouriteQuiz, Long> {

    @Query("SELECT f FROM FavouriteQuiz f WHERE f.quiz.name = :id")
    List<FavouriteQuiz> findFavouriteQuiz(@Param("id") String quizId);

    @Query("SELECT f FROM FavouriteQuiz f WHERE f.user = :id")
    FavouriteQuiz findUser(@Param("id") User user);
}

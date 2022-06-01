package com.example.demo.user;

import com.example.demo.answer.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u ")
    List<User> getAllUsers();

    @Query("SELECT u.id FROM User u ")
    long getCurrentUserId(User user);

    @Query("SELECT a.answerSet FROM User a")
    Set<Answer> getSentAnswer();
}

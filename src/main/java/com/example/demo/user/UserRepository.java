package com.example.demo.user;

import com.example.demo.Question.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //List<User> getAllUsers();

    @Query("SELECT u FROM User u ")
    List<User> getAllUsers();
}

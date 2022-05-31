package com.example.demo.user;

import com.example.demo.answer.Answer;
import com.example.demo.userScore.UserScore;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;
    private String password;
    private long score;

    @Column(columnDefinition = "integer default 1")
    private int skill;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "sent_answer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    Set<Answer> answerSet;

//    @OneToMany
//    List<UserScore> userIds;

    public User() {
    }

    public User(String username, String password, long score) {
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}

package com.example.demo.Quiz;

import com.example.demo.Question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Quiz {

    @Id
    private String name;

    private String description;

    private boolean favourite;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "quiz_id")
    private List<Question> questionList = new ArrayList<>();

}

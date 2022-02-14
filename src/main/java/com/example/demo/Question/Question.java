package com.example.demo.Question;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String content;
    //private Set<Answer> answers = new HashSet<Answer>(0);

    public Question(){

    }

    public Question(String content){
        this.content = content;
    }

//    public Question(String content, Set<Answer> answers){
//        this.content = content;
//        this.answers = answers;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Column(name = "content", nullable = false, length = 65535)
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

//    @OneToMany(fetch = FetcType.LAZY, mappedBy = "question")
//    public Set<Answer getAnswers(>{
//        return this ansers;
//    }

//    public void setAnswers(Set<Answer> answers){
//        this.answers = answers;
//    }
}

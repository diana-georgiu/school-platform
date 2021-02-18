package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "homeworks")
@NamedQueries({
        @NamedQuery(name = "Homework.findHomeworksByAssignedId",
                query="select h from Homework h where assigned_id = ?1"
        ),
        @NamedQuery(name = "Homework.findHomeworksByStudentId",
                query="select h from Homework h where student_id = ?1"
        )
})
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Assigned assigned;

    @Column
    public String content;

    @Column
    public String grade;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Assigned getAssigned() {
        return assigned;
    }

    public void setAssigned(Assigned assigned) {
        this.assigned = assigned;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
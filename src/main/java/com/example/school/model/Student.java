package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "Student.findStudentsByGroup",
                    query="select s from Student s where group_id = ?1"
                    ),
        @NamedQuery(name = "Student.findStudentByUsername",
                    query="select s from Student s where username = ?1"
        )
})
public class Student {

    @Id
    public Integer id;
    @Column
    public String name;
    @Column
    public Integer age;
    @Column
    public Integer group_id;
    @Column
    public String username;

    @OneToMany(mappedBy="student", cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Homework> homeworks = new ArrayList<>();

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

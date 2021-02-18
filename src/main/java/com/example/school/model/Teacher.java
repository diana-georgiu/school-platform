package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teachers")
@NamedQueries({
        @NamedQuery(name = "Teacher.findTeacherByUsername",
                query="select t from Teacher t where username = ?1"
        )
})
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public String name;

    @Column
    public String username;

    @OneToMany(mappedBy="teacher", fetch = FetchType.EAGER)
    private List<Enrolled> enrolled = new ArrayList<Enrolled>();

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Enrolled> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Enrolled> enrolled) {
        this.enrolled = enrolled;
    }
}

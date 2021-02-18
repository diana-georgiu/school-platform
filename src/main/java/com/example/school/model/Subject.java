package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String name;

    @OneToMany(mappedBy="subject", fetch = FetchType.EAGER)
    private List<Enrolled> enrolled = new ArrayList<Enrolled>();

    @Column
    private String image;

    public Subject() {
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

    public List<Enrolled> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Enrolled> enrolled) {
        this.enrolled = enrolled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assigned")
@NamedQueries({
        @NamedQuery(name = "Assigned.findAssignedByEnrolledId",
                query="select a from Assigned a where enrolled_id = ?1"
        )
})
public class Assigned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column
    public String title;

    @Column
    public String requirement;

    @Column
    public String content;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Enrolled enrolled;

    @OneToMany(mappedBy="assigned", cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Homework> homeworks = new ArrayList<Homework>();

    @Column
    private String assign_date;

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Enrolled getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(Enrolled enrolled) {
        this.enrolled = enrolled;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Homework> getHomeworks() {
        return homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        this.homeworks = homeworks;
    }
}
package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NamedQueries({
    @NamedQuery(name="Group.FIND_ALL_STUDENTS", query="select s " +
                   "from Student s, Group g where s.group_id = g.id")
})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column
    public String name;
    @Column
    public String description;
    @Column
    public Integer coordinator_id;

    @OneToMany(mappedBy="group", fetch = FetchType.EAGER)
    private List<Enrolled> enrolled = new ArrayList<Enrolled>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCoordinator_id() {
        return coordinator_id;
    }

    public void setCoordinator_id(Integer coordinator_id) {
        this.coordinator_id = coordinator_id;
    }

    public List<Enrolled> getEnrolled() {
        return enrolled;
    }

    public void setEnrolled(List<Enrolled> enrolled) {
        this.enrolled = enrolled;
    }

}

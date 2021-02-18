package com.example.school.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enrolled")
@NamedQueries({
        @NamedQuery(name = "Enrolled.findEnrolledByGroupId",
                query="select e from Enrolled e where group_id = ?1"
        ),
        @NamedQuery(name = "Enrolled.findEnrolledByTeacherId",
                query="select e from Enrolled e where teacher_id = ?1"
        ),
        @NamedQuery(name = "Enrolled.findEnrolledByTeacherIdGroupId",
                query="select e from Enrolled e where teacher_id = ?1 and group_id = ?2"
        ),
        @NamedQuery(name = "Enrolled.findDistinctGroupsByTeacherId",
                query="select distinct g from Group g, Enrolled e where g.id = e.group.id and e.teacher.id = ?1"
        )
})
public class Enrolled {

    @Id
    public Integer id;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Group group;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy="enrolled",  cascade = {CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Assigned> assigned = new ArrayList<Assigned>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Assigned> getAssigned() {
        return assigned;
    }

    public void setAssigned(List<Assigned> assigned) {
        this.assigned = assigned;
    }
}

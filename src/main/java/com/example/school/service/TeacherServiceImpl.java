package com.example.school.service;

import com.example.school.model.*;
import com.example.school.repository.AssignedRepository;
import com.example.school.repository.EnrolledRepository;
import com.example.school.repository.HomeworkRepository;
import com.example.school.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class TeacherServiceImpl implements TeacherService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EnrolledRepository enrolledRepository;

    @Autowired
    private AssignedRepository assignedRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Override
    public Teacher getTeacherByUsername(String username)
    {
        Query q = em.createNamedQuery("Teacher.findTeacherByUsername");
        q.setParameter(1, username);

        return (Teacher)(q.getResultList().get(0));
    }

    @Override
    public List<Enrolled> findEnrolledByTeacherId(Integer teacherId) {

        Query q = em.createNamedQuery("Enrolled.findEnrolledByTeacherId");
        q.setParameter(1, teacherId);

        return q.getResultList();
    }

    @Override
    public List<Assigned> findAssignedByEnrolledId(Integer enrolledId) {

        Query q = em.createNamedQuery("Assigned.findAssignedByEnrolledId");
        q.setParameter(1, enrolledId);

        return q.getResultList();
    }

    @Override
    public Assigned findAssignedById(Integer assignedId) {

        Optional<Assigned> assigned1 = assignedRepository.findById(assignedId);
        Assigned assigned2 = assigned1.get();

        return assigned2;
    }

    @Override
    public List<Homework> findHomeworksByAssignedId(Integer assignedId) {

        Query q = em.createNamedQuery("Homework.findHomeworksByAssignedId");
        q.setParameter(1, assignedId);

        return q.getResultList();
    }

    @Override
    public List<Homework> findHomeworksByStudentId(Integer studentId) {

        Query q = em.createNamedQuery("Homework.findHomeworksByStudentId");
        q.setParameter(1, studentId);

        return q.getResultList();
    }

    @Override
    public Homework findHomeworkById(Integer homeworkId) {

        Optional<Homework> homework1 = homeworkRepository.findById(homeworkId);
        Homework homework2 = homework1.get();

        return homework2;
    }

    @Override
    public void addAssigned(Assigned assigned)
    {
        assignedRepository.save(assigned);
    }

    @Override
    public void gradeHomework(String grade, Integer homeworkId)
    {
        Homework h1 = homeworkRepository.findById(homeworkId).get();
        h1.setGrade(grade);
        homeworkRepository.save(h1);
    }

    @Override
    public List<Student> findStudentsByGroup(Integer grId) {
        Query q = em.createNamedQuery("Student.findStudentsByGroup");
        q.setParameter(1, grId);

        return q.getResultList();
    }

    @Override
    public List<Group> findDistinctGroupsByTeacherId(Integer teacherId)
    {
        Query q = em.createNamedQuery("Enrolled.findDistinctGroupsByTeacherId");
        q.setParameter(1, teacherId);

        return q.getResultList();
    }

    @Override
    public Enrolled findEnrolledById(Integer enrolledId)
    {
        Enrolled enrolled = enrolledRepository.findById(enrolledId).get();

        return enrolled;
    }

    public Map<Student, String> getStudentsGrades(List<Student> listStudents, Integer enrolledId) {

        Map<Student, String> mapGrades = new HashMap<>();
        for (Student st : listStudents) {
            String grades = "";

            List<Homework> listHome = findHomeworksByStudentId(st.getId());

            for (Homework h : listHome)
                if (h.getAssigned().getEnrolled().getId() == enrolledId)
                    if (h.getGrade() != null) {
                        grades = grades + " " + h.getGrade();
                    }
            mapGrades.put(st, grades);
        }
        return mapGrades;
    }

}

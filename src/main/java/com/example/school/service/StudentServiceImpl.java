package com.example.school.service;

import com.example.school.model.*;
import com.example.school.model.Student;
import com.example.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AssignedRepository assignedRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student getStudentByUsername(String username)
    {
        Query q = em.createNamedQuery("Student.findStudentByUsername");
        q.setParameter(1, username);

        return (Student)(q.getResultList().get(0));
    }

    @Override
    public Iterable<Group> listGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Student> findStudentsByGroup(Integer grId) {

        Query q = em.createNamedQuery("Student.findStudentsByGroup");
        q.setParameter(1, grId);

        return q.getResultList();
    }

    @Override
    public List<Enrolled> findEnrolledByGroupId(Integer grId) {

        Query q = em.createNamedQuery("Enrolled.findEnrolledByGroupId");
        q.setParameter(1, grId);

        return q.getResultList();
    }

    @Override
    public List<Assigned> findAssignedByEnrolledId(Integer enrolledId)
    {
        Query q = em.createNamedQuery("Assigned.findAssignedByEnrolledId");
        q.setParameter(1, enrolledId);

        return q.getResultList();
    }

    public void addHomework(Homework hw)
    {
        homeworkRepository.save(hw);
    }

    @Override
    public Assigned findAssignedById(Integer assignedId) {

        Optional<Assigned> assigned1 = assignedRepository.findById(assignedId);
        Assigned assigned2 = assigned1.get();

        return assigned2;
    }
}

package com.example.school.service;
import com.example.school.model.*;
import com.example.school.model.Group;
import java.util.List;

public interface StudentService {

    Student getStudentByUsername(String username);
    Iterable<Group> listGroups();
    List<Student> findStudentsByGroup(Integer grId);
    List<Enrolled> findEnrolledByGroupId(Integer grId);
    void addHomework(Homework hw);
    List<Assigned> findAssignedByEnrolledId(Integer enrolledId);
    Assigned findAssignedById(Integer assignedId);
}

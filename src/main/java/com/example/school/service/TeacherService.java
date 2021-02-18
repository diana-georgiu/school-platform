package com.example.school.service;
import com.example.school.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TeacherService {
    Teacher getTeacherByUsername(String username);
    List<Enrolled> findEnrolledByTeacherId(Integer teacherId);
    List<Assigned> findAssignedByEnrolledId(Integer enrolledId);
    Assigned findAssignedById(Integer assignedId);
    List<Homework> findHomeworksByAssignedId(Integer assignedId);
    List<Homework> findHomeworksByStudentId(Integer studentId);
    void addAssigned(Assigned assigned);
    Homework findHomeworkById(Integer homeworkId);
    void gradeHomework(String grade, Integer homeworkId);
    Enrolled findEnrolledById(Integer enrolledId);
    List<Student> findStudentsByGroup(Integer grId);
    Map<Student, String> getStudentsGrades(List<Student> listStudents, Integer enrolledId);
    List<Group> findDistinctGroupsByTeacherId(Integer teacherId);
}

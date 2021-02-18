package com.example.school.controller;
import com.example.school.model.Assigned;
import com.example.school.model.Homework;
import com.example.school.model.User;
import org.springframework.ui.Model;
import com.example.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.school.model.Student;

import java.util.List;

@Controller
@SessionAttributes("studentInfo")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ModelAttribute("studentInfo")
    public Student studentInfo()
    {
        return new Student();
    }

    @PostMapping("/student")
    public String renderStudentPage(@ModelAttribute("user") User user, Model model){
        Student studentInfo = studentService.getStudentByUsername(user.getUsername());
        model.addAttribute("studentInfo", studentInfo);

        Integer groupId = studentInfo.getGroup_id();
        model.addAttribute("myCourses", studentService.findEnrolledByGroupId(groupId));

        return "studentPage";
    }

    @GetMapping("/student/myClasses/{eid}")
    public String listAssignments(@PathVariable Integer eid, Model model){

        List<Assigned> listAssigned = studentService.findAssignedByEnrolledId(eid);
        model.addAttribute("myAssigned", listAssigned);
        model.addAttribute("eid", eid);

        return "studentAssigned";
    }

    @GetMapping("/student/myClasses/myAssignments/{aid}")
    public String listAssignDetails(@PathVariable Integer aid, Model model){

        Assigned assigned = studentService.findAssignedById(aid);
        model.addAttribute("thisAssignment", assigned);
        model.addAttribute("aid", aid);

        return "studentAssignmentDetails";
    }

    @GetMapping("/student/myGroup")
    public String listMyGroups10(Model model){

        Student st = (Student)(model.getAttribute("studentInfo"));
        Integer gr = st.getGroup_id();

        model.addAttribute("studentMyGroup", studentService.findStudentsByGroup(gr));
        return "studentListMyGroup";
    }

    @ModelAttribute(value = "homework")
    public Homework newEntity()
    {
        return new Homework();
    }

    @PostMapping("/student/listMyAssignments/addHomework")
    public String addHomework(@ModelAttribute("homework") Homework homework, BindingResult bindingResult,
                              Model model)
    {
        model.addAttribute("homework", homework);

        Student st = (Student)(model.getAttribute("studentInfo"));
        homework.setStudent(st);
        studentService.addHomework(homework);

        Assigned assigned = studentService.findAssignedById( homework.getAssigned().getId());
        model.addAttribute("thisAssignment", assigned);

        return "studentAssignmentDetails";
    }

    @GetMapping("/student/listMyAssignments/myHomeworks/{aid}")
    public String listMyHomeworks(@PathVariable Integer aid, Model model)
    {
        Assigned assigned = studentService.findAssignedById(aid);
        List<Homework> listHomework = assigned.getHomeworks();
        Student st = (Student)(model.getAttribute("studentInfo"));
        Integer studId = st.getId();

        for(Homework homework : listHomework)
            if(homework.getStudent().getId() == studId)
                model.addAttribute("thisHomework",homework);

        return "studentHomeworkDetails";
    }
}

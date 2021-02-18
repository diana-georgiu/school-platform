package com.example.school.controller;
import com.example.school.model.*;
import org.springframework.ui.Model;
import com.example.school.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("teacherInfo")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ModelAttribute(value = "assigned")
    public Assigned newEntity()
    {
        return new Assigned();
    }

    @PostMapping("/teacher")
    public String renderTeacherPage(@ModelAttribute("user") User user, Model model){

        Teacher teacherInfo = teacherService.getTeacherByUsername(user.getUsername());
        model.addAttribute("teacherInfo", teacherInfo);

        List<Enrolled> listEnrolled = teacherService.findEnrolledByTeacherId(teacherInfo.getId());
        model.addAttribute("listEnrolled", listEnrolled);

        List<Group> listGroups = teacherService.findDistinctGroupsByTeacherId(teacherInfo.getId());
        model.addAttribute("listGroups", listGroups);

        return "teacherPage";
    }

    @GetMapping("/teacher/myGroups/{eid}")
    public String listGroupsSubjects(@PathVariable Integer eid, Model model){
        Enrolled enrolled = teacherService.findEnrolledById(eid);
        model.addAttribute("myEnrolled", enrolled);
        return "teacherGroupSubject";
    }

    @GetMapping("/teacher/myGroups/mySubjects/{eid}")
    public String listGroupsAssigned(@PathVariable Integer eid, Model model){
        List<Assigned> listAssigned = teacherService.findAssignedByEnrolledId(eid);
        model.addAttribute("myAssigned", listAssigned);
        model.addAttribute("eid", eid);

        return "teacherAssigned";
    }

    @GetMapping("/teacher/myGroups/listGroup/{gid}")
    public String listMyGroupsStudents(@PathVariable Integer gid, Model model){
        List<Student> listStudents = teacherService.findStudentsByGroup(gid);
        model.addAttribute("listStudents", listStudents);

        return "teacherGroupStudents";
    }

    @GetMapping("/teacher/myGroups/catalog/{eid}")
    public String listCatalog(@PathVariable Integer eid, Model model){

        Enrolled enrolled = teacherService.findEnrolledById(eid);
        List<Student> listStudents = teacherService.findStudentsByGroup(enrolled.getGroup().getId());
        model.addAttribute("listStudents", listStudents);
        Map<Student, String> mapGrades = teacherService.getStudentsGrades(listStudents, eid);
        model.addAttribute("myEnrolled", enrolled);
        model.addAttribute("mapGrades", mapGrades);

        return "teacherGroupCatalog";
    }


    @GetMapping("/teacher/myGroups/myAssignments/{aid}")
    public String listAssignments(@PathVariable Integer aid, Model model){

        Assigned assig = teacherService.findAssignedById(aid);
        model.addAttribute("thisAssignment", assig);
        List<Homework> listHomework = teacherService.findHomeworksByAssignedId(aid);
        model.addAttribute("listHomeworks", listHomework);
        return "teacherAssignmentDetails";
    }

    @PostMapping("/teacher/myGroups/myAssignments/addAssig")
    public String addAssignment(@ModelAttribute("assigned") Assigned assigned, BindingResult bindingResult,
                                Model model) {

        model.addAttribute("assigned", assigned);
        teacherService.addAssigned(assigned);
        return "teacherAssigned";
    }

    @GetMapping("/teacher/myGroups/myAssignments/homework/{hid}")
    public String getHomeworkDetails(@PathVariable Integer hid, Model model) {

        Homework homework = teacherService.findHomeworkById(hid);
        model.addAttribute("homework", homework);

        return "teacherHomeworkDetails";
    }

    @PostMapping("/teacher/myGroups/myAssignments/homework/addGrade")
    public String addGrade(@RequestParam("gradeHomework") String grade, @RequestParam("homeworkID") Integer homeworkID, Model model) {

        teacherService.gradeHomework(grade,homeworkID);
        Homework homework = teacherService.findHomeworkById(homeworkID);
        model.addAttribute("homework", homework);
        return "teacherHomeworkDetails";
    }

}
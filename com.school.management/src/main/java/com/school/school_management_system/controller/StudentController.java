package com.school.school_management_system.controller;

import com.school.school_management_system.model.Student;
import com.school.school_management_system.service.ClassroomService;
import com.school.school_management_system.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final ClassroomService classroomService;

    public StudentController(StudentService studentService, ClassroomService classroomService) {
        this.studentService = studentService;
        this.classroomService = classroomService;
    }

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/ShowStudents")
    public String listStudents(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "student-list";
    }
    @GetMapping("/student-details/{id}")
    public String getStudentById(@PathVariable("id") Long id,Model model){
        Student student =  studentService.getStudentById(id);
        model.addAttribute("student",student);
        return "getStudent";
    }

    @GetMapping("/addStudent")
    public String ShowAddForm(Model model ){
        Student student = new Student();
        model.addAttribute("student",student);
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "add-student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/ShowStudents";
    }
    @GetMapping("/editStudent/{id}")
    public String showEditForm(@PathVariable("id") Long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "add-student";
    }
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return "redirect:/ShowStudents";
    }
}

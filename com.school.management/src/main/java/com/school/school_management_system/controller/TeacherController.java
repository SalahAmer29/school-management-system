package com.school.school_management_system.controller;


import com.school.school_management_system.model.Teacher;
import com.school.school_management_system.service.CourseService;
import com.school.school_management_system.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {

    private final TeacherService teacherService;
    private final CourseService courseService;

    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @GetMapping("/showTeacher")
    public String Teacherslist(Model model){
        List<Teacher> teacher = teacherService.getAllTeachers();
        model.addAttribute("teachers",teacher);
        return "teacher-list";
    }
    @GetMapping("/teacher-details/{id}")
    public String getTeacherById(@PathVariable("id") Long id,Model model){
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher",teacher);
        return "getTeacher";
    }
    @GetMapping("/addTeacher")
    public String showAddForm(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher",teacher);
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-teacher";
    }

    @GetMapping("/editTeacher/{id}")
    public String showEditForm(@PathVariable("id") Long id,Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-teacher";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "redirect:/showTeacher";
    }

    @PostMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable("id") Long id,Model model){
        teacherService.deleteTeacher(id);
        return "redirect:/showTeacher";
    }
}

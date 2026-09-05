package com.school.school_management_system.controller;

import com.school.school_management_system.model.Classroom;
import com.school.school_management_system.service.ClassroomService;
import com.school.school_management_system.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClassroomController {

    private final ClassroomService classroomService;
    private final CourseService courseService;

    public ClassroomController(ClassroomService classroomService, CourseService courseService) {
        this.classroomService = classroomService;
        this.courseService = courseService;
    }

    @GetMapping("/showClassrooms")
    public String Classroomlist(Model model){
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        model.addAttribute("classrooms",classrooms);
        return "classroom-list";
    }

    @GetMapping("/classroom-details/{id}")
    public String getClassroomById(@PathVariable("id") Long id, Model model){
        Classroom classroom = classroomService.getClassroomById(id);
        model.addAttribute("classroom",classroom);
        return "getClassroom";
    }

    @GetMapping("/addClassroom")
    public String showAddForm(Model model){
        Classroom classroom = new Classroom();
        model.addAttribute("classroom",classroom);
        model.addAttribute("courses",courseService.getAllCourses());
        return "add-classroom";
    }

    @GetMapping("/editClassroom/{id}")
    public String showEditForm(@PathVariable("id") Long id,Model model){
        Classroom classroom = classroomService.getClassroomById(id);
        model.addAttribute("classroom",classroom);
        model.addAttribute("courses",courseService.getAllCourses());
        return "add-classroom";
    }

    @PostMapping("/saveClassroom")
    public String saveClassroom(@ModelAttribute("classroom") Classroom classroom){
        classroomService.savaClassroom(classroom);
        return "redirect:/showClassrooms";
    }

    @PostMapping("/deleteClassroom/{id}")
    public String deleteClassroom(@PathVariable("id") Long id,Model model){
        classroomService.deleteClassroom(id);
        return "redirect:/showClassrooms";
    }
}

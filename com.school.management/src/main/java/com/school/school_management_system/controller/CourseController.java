package com.school.school_management_system.controller;


import com.school.school_management_system.model.Course;
import com.school.school_management_system.service.ClassroomService;
import com.school.school_management_system.service.CourseService;
import com.school.school_management_system.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final ClassroomService classroomService;

    public CourseController(CourseService courseService, TeacherService teacherService, ClassroomService classroomService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.classroomService = classroomService;
    }

    @GetMapping("/showCourses")
    public String Courselist( Model model){
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses",courses);
        return "course-list";
    }

    @GetMapping("/course-detalis/{id}")
    public String getCourseById(@PathVariable("id") Long id, Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "getCourse";
    }

    @GetMapping("/addCourse")
    public String addCourse(Model model){
        Course course = new Course();
        model.addAttribute("course",course);
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "add-course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){
        courseService.saveCourse(course);
        return "redirect:/showCourses";
    }

    @GetMapping("/editCourse/{id}")
    public String editcourse(@PathVariable("id") Long id,Model model){
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        model.addAttribute("teachers",teacherService.getAllTeachers());
        model.addAttribute("classrooms",classroomService.getAllClassrooms());
        return "add-course";
    }

    @PostMapping("/deleteCourse/{id}")
    public String deletecourse(@PathVariable("id") Long id,Model model){
        courseService.deleteCourse(id);
        return "redirect:/showCourses";
    }
}

package com.school.school_management_system.service;

import com.school.school_management_system.model.Course;
import com.school.school_management_system.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Course not found with id: " + id));
    }
    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }
    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}

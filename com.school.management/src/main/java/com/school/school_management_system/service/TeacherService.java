package com.school.school_management_system.service;

import com.school.school_management_system.model.Teacher;
import com.school.school_management_system.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
    public Teacher getTeacherById(Long id){
        return teacherRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Teacher not found with id: " + id));
    }
    public Teacher saveTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public void deleteTeacher(Long id){
        teacherRepository.deleteById(id);
    }
}

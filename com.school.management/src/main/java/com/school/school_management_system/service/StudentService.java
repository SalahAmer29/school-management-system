package com.school.school_management_system.service;

import com.school.school_management_system.model.Student;
import com.school.school_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Student not found with id: " + id));
    }
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    public void deleteStudent(Long id){
         studentRepository.deleteById(id);
    }
}

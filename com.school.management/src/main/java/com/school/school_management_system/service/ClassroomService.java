package com.school.school_management_system.service;

import com.school.school_management_system.model.Classroom;
import com.school.school_management_system.repository.ClassroomRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;


    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }
    public List<Classroom> getAllClassrooms(){
        return classroomRepository.findAll();
    }
    public Classroom getClassroomById(long id){
        return classroomRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Classroom not found with id: " + id));
    }
    public Classroom savaClassroom(Classroom classroom){
        return classroomRepository.save(classroom);
    }
    public void deleteClassroom(Long id){
        classroomRepository.deleteById(id);
    }
}

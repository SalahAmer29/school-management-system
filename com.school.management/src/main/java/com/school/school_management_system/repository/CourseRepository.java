package com.school.school_management_system.repository;

import com.school.school_management_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByClassroomId(Long classroomId);
}

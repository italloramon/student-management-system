package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.StudentModel;;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    
}

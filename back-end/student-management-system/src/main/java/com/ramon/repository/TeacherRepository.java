package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.TeacherModel;

public interface TeacherRepository extends JpaRepository<TeacherModel, Long>{
    
}

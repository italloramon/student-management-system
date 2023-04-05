package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{
    public boolean existsByCpf(String cpf);

    public Teacher findByCpf(String cpf);
}

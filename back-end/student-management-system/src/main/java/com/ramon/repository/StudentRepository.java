package com.ramon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ramon.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    public boolean existsByCpf(String cpf);

    public Student findByCpf(String cpf);
    
    public Student findByEmail(String email);
}

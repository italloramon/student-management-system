package com.ramon.repository;

import com.ramon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private StudentRepository studentRepository;

    private TeacherRepository teacherRepository;

    private ResponsableRepository responsableRepository;

    public UserRepository(StudentRepository studentRepository, TeacherRepository teacherRepository, ResponsableRepository responsableRepository) {
        this.studentRepository = studentRepository;
        this.responsableRepository = responsableRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users.addAll(studentRepository.findAll());
        users.addAll(teacherRepository.findAll());
        users.addAll(responsableRepository.findAll());
        return users;
    }
}

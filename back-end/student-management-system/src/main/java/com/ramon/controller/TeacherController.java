package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.TeacherRepository;
import java.util.List;
import com.ramon.model.TeacherModel;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class TeacherController {
    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("teachers/")
    public List<TeacherModel> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @GetMapping("teachers/{id}")
    public TeacherModel getTeacher(@PathVariable Long id) {
        return this.teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PostMapping("teachers/")
    public TeacherModel createTeacher(@RequestBody TeacherModel teacher) {
        return this.teacherRepository.save(teacher);
    }

    @PutMapping("teachers/{id}")
    public TeacherModel updateTeacher(@RequestBody TeacherModel newTeacher, @PathVariable Long id) {
        return this.teacherRepository.findById(id).map(teacher -> {
            teacher.setName(newTeacher.getName());
            teacher.setCpf(newTeacher.getCpf());
            teacher.setEmail(newTeacher.getEmail());
            return this.teacherRepository.save(teacher);
        }).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @DeleteMapping("teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        this.teacherRepository.deleteById(id);
    }

}
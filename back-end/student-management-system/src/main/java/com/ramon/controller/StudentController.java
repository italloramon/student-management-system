package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.StudentRepository;
import java.util.List;
import com.ramon.model.StudentModel;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("students/")
    public List<StudentModel> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @GetMapping("students/{id}")
    public StudentModel getStudent(@PathVariable Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PostMapping("students/")
    public StudentModel createStudent(@RequestBody StudentModel student) {
        return this.studentRepository.save(student);
    }

    @PutMapping("students/{id}")
    public StudentModel updateStudent(@RequestBody StudentModel newStudent, @PathVariable Long id) {
        return this.studentRepository.findById(id).map(student -> {
            student.setName(newStudent.getName());
            student.setCpf(newStudent.getCpf());
            student.setEmail(newStudent.getEmail());
            return this.studentRepository.save(student);
        }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        this.studentRepository.deleteById(id);
    }

}

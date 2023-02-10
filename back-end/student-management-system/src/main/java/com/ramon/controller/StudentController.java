package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.*;
import java.util.List;
import com.ramon.model.*;
import com.ramon.exception.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/")
public class StudentController {

    private final StudentRepository studentRepository;
    private final ResponsableRepository responsableRepository;

    public StudentController(StudentRepository studentRepository, ResponsableRepository responsableRepository) {
        this.studentRepository = studentRepository;
        this.responsableRepository = responsableRepository;
    }

    @GetMapping("students/")
    public List<StudentModel> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @GetMapping("students/{id}")
    public StudentModel getStudent(@PathVariable Long id) {
        return this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }
    
    //@PostMapping("students/")
    //public StudentModel createStudent(@RequestBody StudentModel student) {
    //    return this.studentRepository.save(student);
    //}
    
    //@PostMapping(value = {"/search/", "/search"})    
    //public String search(@RequestParam Map<String,String> allRequestParams) {
    //    String test = "";
    //    String name = allRequestParams.get("studentName");
    //    String email = allRequestParams.get("studentEmail");
    //    ModelAndView model = new ModelAndView("Look at this:");
    //    test += ("This is the name: " + name + " And this is the email: " + email);
    //    
    //    return test;
    //}

    @PostMapping("students/{idResponsable}")
    public StudentModel createStudent(@RequestBody StudentModel student, @PathVariable Long idResponsable) {
        ResponsableModel responsable = this.responsableRepository.findById(idResponsable).get();
        String name = student.getName();
        String email = student.getEmail();
        String cpf = student.getEmail();
        StudentModel newStudent = new StudentModel(name, cpf, email, responsable);
        return this.studentRepository.save(newStudent);
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

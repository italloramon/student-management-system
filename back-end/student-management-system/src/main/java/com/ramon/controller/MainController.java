package com.ramon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramon.model.Student;
import com.ramon.model.courses.English;
import com.ramon.model.courses.Geography;
import com.ramon.model.courses.History;
import com.ramon.model.courses.Mathematics;
import com.ramon.model.courses.Portuguese;
import com.ramon.repository.EnglishRepository;
import com.ramon.repository.GeographyRepository;
import com.ramon.repository.HistoryRepository;
import com.ramon.repository.MathematicsRepository;
import com.ramon.repository.PortugueseRepository;
import com.ramon.service.impl.ResponsableServiceImpl;
import com.ramon.service.impl.StudentServiceImpl;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	private final StudentServiceImpl studentService;
	
	private final ResponsableServiceImpl responsableService;
	
    private final EnglishRepository englishRepository;
    
    private final GeographyRepository geographyRepository;
    
    private final HistoryRepository historyRepository;
    
    private final MathematicsRepository mathematicsRepository;
    
    private final PortugueseRepository portugueseRepository;
	
	public MainController(StudentServiceImpl studentService, ResponsableServiceImpl responsableService,
			EnglishRepository englishRepository, GeographyRepository geographyRepository,
			HistoryRepository historyRepository, MathematicsRepository mathematicsRepository,
			PortugueseRepository portugueseRepository) {
		this.studentService = studentService;
		this.responsableService = responsableService;
		this.englishRepository = englishRepository;
		this.geographyRepository = geographyRepository;
		this.historyRepository = historyRepository;
		this.mathematicsRepository = mathematicsRepository;
		this.portugueseRepository = portugueseRepository;
	}
		
	@GetMapping("/students")
	public String home(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/form")
	public String studentForm(Model model) {
		Student student = new Student();
		
        English english = new English();
        this.englishRepository.save(english);
        Geography geography = new Geography();
        geographyRepository.save(geography);
        History history = new History();
        historyRepository.save(history);
        Mathematics mathematics = new Mathematics();
        mathematicsRepository.save(mathematics);
        Portuguese portuguese = new Portuguese();
        portugueseRepository.save(portuguese);
        
        student.setEnglish(english);
        student.setGeography(geography);
        student.setHistory(history);
        student.setMathematics(mathematics);
        student.setPortuguese(portuguese);
        
		
		model.addAttribute("student", student);
		model.addAttribute("responsables", responsableService.getAllResponsables());
		return "student_form";
	}
	
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "student_form";
		}
		student.createId();
		studentService.save(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		model.addAttribute("responsables", responsableService.getAllResponsables());
		return "edit_student_form";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Student olderStudent = studentService.getStudentById(id);
	
		olderStudent.setName(student.getName());
		olderStudent.setCpf(student.getCpf());
		olderStudent.setEmail(student.getEmail());
		olderStudent.setTuition(student.getTuition());
		olderStudent.setResponsable(student.getResponsable());
		
		studentService.updateStudent(olderStudent);
		
		return "redirect:/students";
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
}

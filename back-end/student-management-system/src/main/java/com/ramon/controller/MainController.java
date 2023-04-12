package com.ramon.controller;

import java.util.ArrayList;
import java.util.List;

import com.ramon.model.*;
import com.ramon.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramon.model.courses.English;
import com.ramon.model.courses.Geography;
import com.ramon.model.courses.History;
import com.ramon.model.courses.Mathematics;
import com.ramon.model.courses.Portuguese;
import com.ramon.service.impl.ResponsableServiceImpl;
import com.ramon.service.impl.StudentServiceImpl;

import jakarta.servlet.http.HttpSession;
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
    
    private final NoticeRepository noticeRepository;

	private final StudentRepository studentRepository;

	private final TeacherRepository teacherRepository;

	private final ResponsableRepository responsableRepository;
	
	public MainController(StudentServiceImpl studentService, ResponsableServiceImpl responsableService,
						  EnglishRepository englishRepository, GeographyRepository geographyRepository,
						  HistoryRepository historyRepository, MathematicsRepository mathematicsRepository,
						  PortugueseRepository portugueseRepository, NoticeRepository noticeRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, ResponsableRepository responsableRepository) {
		this.studentService = studentService;
		this.responsableService = responsableService;
		this.englishRepository = englishRepository;
		this.geographyRepository = geographyRepository;
		this.historyRepository = historyRepository;
		this.mathematicsRepository = mathematicsRepository;
		this.portugueseRepository = portugueseRepository;
		this.noticeRepository = noticeRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.responsableRepository = responsableRepository;
	}
		
	@GetMapping("/students")
	public String home(Model model) {
		model.addAttribute("students", studentService.getAll());
		model.addAttribute("method", "student");
		return "students";
	}
	
	@GetMapping("/students/form")
	public String studentForm(Model model) {
		Student student = new Student();

		model.addAttribute("student", student);
		model.addAttribute("responsables", responsableService.getAll());
		model.addAttribute("method", "student");
		return "student_form";
	}
	
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {

		try {
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

			student.createId();
			studentService.save(student);
			return "redirect:/students";
		} catch (Exception ex) {
			Student newStudent = new Student();

			model.addAttribute("student", newStudent);
			model.addAttribute("responsables", responsableService.getAll());
			model.addAttribute("method", "student");
			model.addAttribute("exception", ex.getMessage());
			return "student_form";
		}

	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getById(id));
		model.addAttribute("responsables", responsableService.getAll());
		model.addAttribute("method", "student");
		return "edit_student_form";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
		Student olderStudent = studentService.getById(id);
		try {
			olderStudent.setName(student.getName());
			olderStudent.setCpf(student.getCpf());
			olderStudent.setEmail(student.getEmail());
			olderStudent.setTuition(student.getTuition());
			olderStudent.setResponsable(student.getResponsable());

			studentService.update(olderStudent);

			return "redirect:/students";

		} catch (Exception ex) {
			model.addAttribute("student", olderStudent);
			model.addAttribute("responsables", responsableService.getAll());
			model.addAttribute("method", "student");
			model.addAttribute("exception", ex.getMessage());
			return "edit_student_form";
		}

	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}
	
	@GetMapping("/students/courses/{id}")
	public String getCourses(@PathVariable Long id, Model model, HttpSession session) {
		Student student = studentService.getById(id);
		List<CourseModel> courses = studentService.getStudentCourses(student);
		
		model.addAttribute("student", student);
		model.addAttribute("courses", courses);
		model.addAttribute("method", "student");

	    // Store student object in session
	    session.setAttribute("currentStudent", student);
	    
		return "home-students";
	}

	@GetMapping("/students/courses/wh/{id}")
	public String getCoursesWithoutStudent(@PathVariable Long id, Model model, HttpSession session) {
		Student student = studentService.getById(id);
		List<CourseModel> courses = studentService.getStudentCourses(student);

		model.addAttribute("student", student);
		model.addAttribute("courses", courses);

		// Store student object in session
		session.setAttribute("currentStudent", student);


		Responsable responsable = (Responsable) session.getAttribute("currentResponsable");
		model.addAttribute("responsable", responsable);
		return "home-students";
	}
	
	@GetMapping("/ranking")
	public String ranking(Model model, HttpSession session) {
		Student[] rankingStudents = studentService.getRankingStudents();
		
		// Retrieve student object from session
	    Student currentStudent = (Student) session.getAttribute("currentStudent");
	    
		model.addAttribute("rankingStudents", rankingStudents);
		model.addAttribute("student", currentStudent);
		model.addAttribute("method", "student");

		return "ranking";
	}
	
	@GetMapping("/notices")
	public String notices(Model model, HttpSession session) {
		List<Notice> notices = noticeRepository.findAll();
		Student currentStudent = (Student) session.getAttribute("currentStudent");
		model.addAttribute("student", currentStudent);
		model.addAttribute("notices", notices);
		model.addAttribute("method", "student");
		return "notices";
	}

	@GetMapping("/users")
	public String users(Model model) {
		List<User> users = new ArrayList<>();
		users.addAll(studentRepository.findAll());
		users.addAll(teacherRepository.findAll());
		users.addAll(responsableRepository.findAll());
		model.addAttribute("users", users);
		return "users";
	}
}

package com.ramon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramon.model.Responsable;
import com.ramon.model.Student;
import com.ramon.model.TeacherModel;
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
import com.ramon.service.impl.TeacherServiceImpl;

import jakarta.validation.Valid;

@Controller
public class MainTeacherController {
	
	private final TeacherServiceImpl teacherService;
	
	public MainTeacherController(TeacherServiceImpl teacherService) {
		this.teacherService = teacherService;
	}
		
	@GetMapping("/teachers")
	public String home(Model model) {
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "teachers";
	}
	
	@GetMapping("/teachers/form")
	public String teachersForm(Model model) {
		TeacherModel teacher= new TeacherModel();

		model.addAttribute("teacher", teacher);
		
		return "teacher_form";
	}
	
	@PostMapping("/teachers")
	public String saveTeacher(@Valid @ModelAttribute("teacher") TeacherModel teacher, BindingResult result) {
		if (result.hasErrors()) {
			return "teacher_form";
		}

		teacherService.save(teacher);
		return "redirect:/teachers";
	}
	
	@GetMapping("/teachers/edit/{id}")
	public String editTeacherForm(@PathVariable Long id, Model model) {
		model.addAttribute("teacher", teacherService.getTeacherById(id));
		return "edit_teacher_form";
	}
	
	@PostMapping("/teachers/{id}")
	public String updateTeacher(@PathVariable Long id, @ModelAttribute("teacher") TeacherModel teacher, Model model) {
		TeacherModel olderTeacher= teacherService.getTeacherById(id);
	
		olderTeacher.setName(teacher.getName());
		olderTeacher.setCpf(teacher.getCpf());
		olderTeacher.setEmail(teacher.getEmail());
		olderTeacher.setSalary(teacher.getSalary());
		olderTeacher.setRole(teacher.getRole());
	
		teacherService.updateTeacher(olderTeacher);
		
		return "redirect:/teachers";
	}
	
	@GetMapping("/teachers/{id}")
	public String deleteTeacher(@PathVariable Long id) {
		teacherService.deleteTeacherById(id);
		return "redirect:/teachers";
	}
}
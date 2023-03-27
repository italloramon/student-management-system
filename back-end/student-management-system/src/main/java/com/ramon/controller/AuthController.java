package com.ramon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ramon.model.Student;
import com.ramon.repository.StudentRepository;

@Controller
public class AuthController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/")
	public String homeLoginForm() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role, 
			RedirectAttributes redirectAttributes) {
		
		if (role.equalsIgnoreCase("Admin")) {
			if (username.equals("admin") && password.equals("admin")) {
				return "redirect:/students";
			}
		} else if (role.equalsIgnoreCase("Student")) {
			List<Student> students = studentRepository.findAll();
			for (Student student: students) {
				if (student.getEmail().equals(username) && student.getCpf().endsWith(password)) {
					redirectAttributes.addAttribute("id", student.getId());
					return "redirect:/students/courses/{id}";
				}
			}
		}
		return "redirect:/";
	}
}

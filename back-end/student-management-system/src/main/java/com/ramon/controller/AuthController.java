package com.ramon.controller;

import java.util.ArrayList;
import java.util.List;

import com.ramon.model.*;
import com.ramon.repository.ResponsableRepository;
import com.ramon.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ramon.repository.StudentRepository;

@Controller
public class AuthController {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ResponsableRepository responsableRepository;

	@GetMapping("/")
	public String homeLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, @RequestParam String role,
						RedirectAttributes redirectAttributes) {


		List<User> users = new ArrayList<>();
		users.addAll(studentRepository.findAll());
		users.addAll(teacherRepository.findAll());
		users.addAll(responsableRepository.findAll());

		for (User user: users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				Long id = user.getId();
				if (user.getRole().equals(Role.STUDENT)) {
					redirectAttributes.addAttribute("id", id);
					return "redirect:/students/courses/{id}";
				} else if (user.getRole().equals(Role.TEACHERENGLISH) ||
						user.getRole().equals(Role.TEACHERGEOGRAPHY) ||
						user.getRole().equals(Role.TEACHERHISTORY) ||
						user.getRole().equals(Role.TEACHERMATHEMATICS) ||
						user.getRole().equals(Role.TEACHERENGLISH) ||
						user.getRole().equals(Role.TEACHERPORTUGUESE)) {
					redirectAttributes.addAttribute("id", id);
					return "redirect:/teachers/home/{id}";
				} else if (user.getRole().equals(Role.RESPONSABLE)) {
					redirectAttributes.addAttribute("id", id);
					return "redirect:/home-responsable/{id}";
				}
			} else if (username.equals("admin") && password.equals("admin")) {
				return "redirect:/students";
			}
		}
		return "redirect:/";
	}
}

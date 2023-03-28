package com.ramon.controller;

import com.ramon.model.CourseModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ramon.model.Responsable;
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

import java.util.List;

@Controller
public class MainResponsableController {
	
	private final ResponsableServiceImpl responsableService;
	
	public MainResponsableController(ResponsableServiceImpl responsableService) {
		this.responsableService = responsableService;
	}
		
	@GetMapping("/responsables")
	public String home(Model model) {
		model.addAttribute("responsables", responsableService.getAllResponsables());
		return "responsables";
	}
	
	@GetMapping("/responsables/form")
	public String responsableForm(Model model) {
		Responsable responsable= new Responsable();

		model.addAttribute("responsable", responsable);
		
		return "responsable_form";
	}
	
	@PostMapping("/responsables")
	public String saveResponsable(@Valid @ModelAttribute("responsable") Responsable responsable, BindingResult result) {
		if (result.hasErrors()) {
			return "responsable_form";
		}

		responsableService.save(responsable);
		return "redirect:/responsables";
	}
	
	@GetMapping("/responsables/edit/{id}")
	public String editResponsableForm(@PathVariable Long id, Model model) {
		model.addAttribute("responsable", responsableService.getResponsableById(id));
		return "edit_responsable_form";
	}
	
	@PostMapping("/responsables/{id}")
	public String updateResponsable(@PathVariable Long id, @ModelAttribute("responsable") Responsable responsable, Model model) {
		Responsable olderResponsable = responsableService.getResponsableById(id);
	
		olderResponsable.setNameResponsable(responsable.getNameResponsable());
		olderResponsable.setCpfResponsable(responsable.getCpfResponsable());
		olderResponsable.setEmailResponsable(responsable.getEmailResponsable());

		
		responsableService.updateResponsable(olderResponsable);
		
		return "redirect:/responsables";
	}
	
	@GetMapping("/responsables/{id}")
	public String deleteResponsable(@PathVariable Long id) {
		responsableService.deleteResponsableById(id);
		return "redirect:/responsables";
	}

	@GetMapping("/home-responsable/{id}")
	public String getStudentsOfResponsable(@PathVariable Long id, Model model, HttpSession session) {
		Responsable responsable = responsableService.getResponsableById(id);
		List<Student> students = responsableService.getStudentsOfResponsable(responsable);

		model.addAttribute("responsable", responsable);
		model.addAttribute("students", students);

		// Store student object in session
		session.setAttribute("currentResponsable", responsable);

		return "home-responsable";
	}
}

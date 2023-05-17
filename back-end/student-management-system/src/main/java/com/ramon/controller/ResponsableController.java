package com.ramon.controller;

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
import com.ramon.service.impl.ResponsableServiceImpl;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class ResponsableController {
	
	private final ResponsableServiceImpl responsableService;
	
	public ResponsableController(ResponsableServiceImpl responsableService) {
		this.responsableService = responsableService;
	}
		
	@GetMapping("/responsables")
	public String home(Model model) {
		model.addAttribute("responsables", responsableService.getAll());
		return "responsables";
	}
	
	@GetMapping("/responsables/form")
	public String responsableForm(Model model) {
		Responsable responsable= new Responsable();

		model.addAttribute("responsable", responsable);
		
		return "responsable_form";
	}
	
	@PostMapping("/responsables")
	public String saveResponsable(@Valid @ModelAttribute("responsable") Responsable responsable, BindingResult result,
								  Model model) {
		try {
			responsableService.save(responsable);
			return "redirect:/responsables";
		} catch (Exception ex) {
			model.addAttribute("exception", ex.getMessage());
			return "responsable_form";
		}
	}
	
	@GetMapping("/responsables/edit/{id}")
	public String editResponsableForm(@PathVariable Long id, Model model) {
		model.addAttribute("responsable", responsableService.getById(id));
		return "edit_responsable_form";
	}
	
	@PostMapping("/responsables/{id}")
	public String updateResponsable(@PathVariable Long id, @ModelAttribute("responsable") Responsable responsable, Model model) {
		Responsable olderResponsable = responsableService.getById(id);

		try {
			olderResponsable.setNameResponsable(responsable.getNameResponsable());
			olderResponsable.setCpfResponsable(responsable.getCpfResponsable());
			olderResponsable.setEmailResponsable(responsable.getEmailResponsable());


			responsableService.update(olderResponsable);

			return "redirect:/responsables";
		} catch (Exception ex) {
			model.addAttribute("responsable", olderResponsable);
			model.addAttribute("exception", ex.getMessage());
			return "edit_responsable_form";
		}
	}
	
	@GetMapping("/responsables/{id}")
	public String deleteResponsable(@PathVariable Long id) {
		responsableService.deleteById(id);
		return "redirect:/responsables";
	}

	@GetMapping("/home-responsable/{id}")
	public String getStudentsOfResponsable(@PathVariable Long id, Model model, HttpSession session) {
		Responsable responsable = responsableService.getById(id);
		System.out.println(responsable.printStatus());
		List<Student> students = responsableService.getStudentsOfResponsable(responsable);

		model.addAttribute("responsable", responsable);
		model.addAttribute("students", students);

		// Store student object in session
		session.setAttribute("currentResponsable", responsable);

		return "home-responsable";
	}
}

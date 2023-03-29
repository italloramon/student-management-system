package com.ramon.controller;

import com.ramon.model.Role;
import com.ramon.model.courses.*;
import com.ramon.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ramon.model.Student;
import com.ramon.model.TeacherModel;
import com.ramon.service.impl.StudentServiceImpl;
import com.ramon.service.impl.TeacherServiceImpl;

import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainTeacherController {

	private final TeacherServiceImpl teacherService;
	private final StudentServiceImpl studentService;
	private final EnglishRepository englishRepository;
	private final PortugueseRepository portugueseRepository;
	private final MathematicsRepository mathematicsRepository;
	private final GeographyRepository geographyRepository;
	private final HistoryRepository historyRepository;

	public MainTeacherController(TeacherServiceImpl teacherService, StudentServiceImpl studentService, EnglishRepository englishRepository, PortugueseRepository portugueseRepository, MathematicsRepository mathematicsRepository, GeographyRepository geographyRepository, HistoryRepository historyRepository) {
		this.teacherService = teacherService;
		this.studentService = studentService;
		this.englishRepository = englishRepository;
		this.portugueseRepository = portugueseRepository;
		this.mathematicsRepository = mathematicsRepository;
		this.geographyRepository = geographyRepository;
		this.historyRepository = historyRepository;
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

	@GetMapping("/teachers/home/{id}")
	public String getHomeTeacher(@PathVariable Long id, Model model, HttpSession session) {
		TeacherModel teacher = teacherService.getTeacherById(id);
		List<Student> students = studentService.getAllStudents();

		session.setAttribute("currentTeacher", teacher);

		model.addAttribute("teacher", teacher);
		model.addAttribute("students", students);

		return "home-teachers";
	}

	@GetMapping("/edit-score/{studentId}")
	public String getEditScoreForm(@PathVariable Long studentId, HttpSession session, Model model) {
		Student student = studentService.getStudentById(studentId);
		session.setAttribute("currentStudent", student);
		TeacherModel teacher = (TeacherModel) session.getAttribute("currentTeacher");
		model.addAttribute("teacher", teacher);
		return "edit-score";
	}

	@PostMapping("/update-score")
	public String updateScore(@RequestParam("bimester") Long bimester, @RequestParam("score") Double score,
							HttpSession session, RedirectAttributes redirectAttributes) {
		TeacherModel teacher = (TeacherModel) session.getAttribute("currentTeacher");
		Student student = (Student) session.getAttribute("currentStudent");

		if(teacher.getRole().equals(Role.TEACHERENGLISH)) {
			English english = student.getEnglish();
			if (bimester == 1) english.setScore1(score);
			else if (bimester == 2) english.setScore2(score);
			else if (bimester == 3) english.setScore3(score);
			else if (bimester == 4) english.setScore4(score);
			englishRepository.save(english);
		} else if(teacher.getRole().equals(Role.TEACHERPORTUGUESE)) {
			Portuguese portuguese = student.getPortuguese();
			if (bimester == 1) portuguese.setScore1(score);
			else if (bimester == 2) portuguese.setScore2(score);
			else if (bimester == 3) portuguese.setScore3(score);
			else if (bimester == 4) portuguese.setScore4(score);
			portugueseRepository.save(portuguese);
		} else if(teacher.getRole().equals(Role.TEACHERHISTORY)) {
			History history = student.getHistory();
			if (bimester == 1) history.setScore1(score);
			else if (bimester == 2) history.setScore2(score);
			else if (bimester == 3) history.setScore3(score);
			else if (bimester == 4) history.setScore4(score);
			historyRepository.save(history);
		} else if(teacher.getRole().equals(Role.TEACHERGEOGRAPHY)) {
			Geography geography = student.getGeography();
			if (bimester == 1) geography.setScore1(score);
			else if (bimester == 2) geography.setScore2(score);
			else if (bimester == 3) geography.setScore3(score);
			else if (bimester == 4) geography.setScore4(score);
			geographyRepository.save(geography);
		} else if (teacher.getRole().equals(Role.TEACHERMATHEMATICS)) {
			Mathematics mathematics = student.getMathematics();
			if (bimester == 1) mathematics.setScore1(score);
			else if (bimester == 2) mathematics.setScore2(score);
			else if (bimester == 3) mathematics.setScore3(score);
			else if (bimester == 4) mathematics.setScore4(score);
			mathematicsRepository.save(mathematics);
		}
		redirectAttributes.addAttribute("id", teacher.getId());

		return "redirect:/teachers/home/{id}";
	}
	@GetMapping("/send-notice")
	public String getFormSendNotice(HttpSession session, Model model) {
		TeacherModel teacher = (TeacherModel) session.getAttribute("currentTeacher");
		model.addAttribute("teacher", teacher);
		return "send-notice";
	}

	@PostMapping("/send-notice")
	public String sendNotice(@RequestParam String notice) {
		teacherService.sendNotice(notice);
		return "redirect:/send-notice";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<TeacherModel> teachers = teacherService.getAllTeachers();
		List<Student> students = studentService.getAllStudents();

		double totalPay = 0;
		for (TeacherModel teacher: teachers) {
			totalPay += teacher.getSalary();
		}
		double totalReceive = 0;
		for (Student student: students) {
			totalReceive += student.getTuition();
		}
		double balance = totalReceive - totalPay;
		model.addAttribute("totalPay", totalPay);
		model.addAttribute("teachers", teachers);
		model.addAttribute("students", students);
		model.addAttribute("balance", balance);
		return "dashboard";
	}
}
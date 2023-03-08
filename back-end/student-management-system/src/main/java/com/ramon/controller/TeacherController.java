package com.ramon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ramon.repository.TeacherRepository;
import com.ramon.repository.NoticeRepository;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.ramon.model.TeacherModel;
import com.ramon.model.NoticeModel;
import com.ramon.model.StudentModel;
import com.ramon.exception.*;

@RestController
@RequestMapping("/api/")
public class TeacherController extends MainController<TeacherModel>{
    private final TeacherRepository teacherRepository;
    private final NoticeRepository noticeRepository;

    public TeacherController(TeacherRepository teacherRepository, NoticeRepository noticeRepository) {
        this.teacherRepository = teacherRepository;
        this.noticeRepository = noticeRepository;
    }

	@Override
	@GetMapping("teachers/")
	public List<TeacherModel> getAll() {
		return this.teacherRepository.findAll();
	}

    @GetMapping("teachers/{id}")
    public TeacherModel getTeacher(@PathVariable Long id) {
        return this.teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PostMapping("teachers/")
    public TeacherModel createTeacher(@RequestBody TeacherModel teacher) {
        return this.teacherRepository.save(teacher);
    }

	@Override
	@PutMapping("teachers/{id}")
	public TeacherModel update(@RequestBody TeacherModel newTeacher, @PathVariable Long id) {
        TeacherModel teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        if (newTeacher.getName() != null) {
            teacher.setName(newTeacher.getName());
        }
        if (newTeacher.getCpf() != null) {
            teacher.setCpf(newTeacher.getCpf());
        }
        if (newTeacher.getEmail() != null) {
            teacher.setEmail(newTeacher.getEmail());
        }
        if (newTeacher.getSalary() != null) {
            teacher.setSalary(newTeacher.getSalary());
        }
        if (newTeacher.getRole() != null) {
            teacher.setRole(newTeacher.getRole());
        }
        return teacherRepository.save(teacher);
	}

	@Override
	@DeleteMapping("teachers/{id}")
	public void delete(@PathVariable Long id) {
		this.teacherRepository.deleteById(id);
	}
	
	@Override
	@PostMapping("teachers/login")
	public TeacherModel create(@RequestBody Map<String, String> teacher) {
        String password = teacher.get("password");
        if (teacherRepository.existsByCpf(password)) {
            TeacherModel teacherLogin = teacherRepository.findByCpf(password);
            if (teacherLogin.getEmail().equals(teacher.get("login"))) {
                return teacherLogin;
            }
        }
        throw new TeacherNotFoundException(-1l);
	}

    @PostMapping("teachers/sendNotice")
    public NoticeModel sendNotice(@RequestBody NoticeModel notice) {
        return noticeRepository.save(notice);
    }

    @GetMapping("teachers/payroll")
    public Map<String, Double> payroll() {
        List<TeacherModel> teachers = teacherRepository.findAll();
        Map<String, Double> response = new HashMap<>();
        Double total = 0.0;
        for (TeacherModel teacher: teachers) {
            total += teacher.getSalary();
        }
        response.put("Payroll", total);
        return response;
    }

 

}
package com.ramon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ramon.model.Student;
import com.ramon.repository.StudentRepository;
import com.ramon.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepsitory) {
		this.studentRepository = studentRepsitory;
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}
	
	
}

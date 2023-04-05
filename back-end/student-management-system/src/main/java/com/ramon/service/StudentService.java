package com.ramon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ramon.model.CourseModel;
import com.ramon.model.Student;

public interface StudentService {
	
	List<Student> getAllStudents();
	
	Student save(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteStudentById(Long id);
	
	List<CourseModel> getStudentCourses(Student student);
	
	Student[] getRankingStudents();
}

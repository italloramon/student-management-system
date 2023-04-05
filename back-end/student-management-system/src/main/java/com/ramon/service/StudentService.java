package com.ramon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ramon.model.CourseModel;
import com.ramon.model.Student;

public interface StudentService extends UserService<Student> {
	List<CourseModel> getStudentCourses(Student student);
	
	Student[] getRankingStudents();
}

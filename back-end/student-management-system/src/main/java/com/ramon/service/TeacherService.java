package com.ramon.service;

import java.util.List;

import com.ramon.model.Responsable;
import com.ramon.model.Student;
import com.ramon.model.TeacherModel;


public interface TeacherService {
	
	List<TeacherModel> getAllTeachers();
	
	TeacherModel save(TeacherModel teacher);
	
	TeacherModel getTeacherById(Long id);
	
	TeacherModel updateTeacher(TeacherModel teacher);
	
	void deleteTeacherById(Long id);
}

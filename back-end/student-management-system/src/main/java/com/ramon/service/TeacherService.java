package com.ramon.service;

import java.util.List;

import com.ramon.model.Teacher;


public interface TeacherService {
	
	List<Teacher> getAllTeachers();
	
	Teacher save(Teacher teacher);
	
	Teacher getTeacherById(Long id);
	
	Teacher updateTeacher(Teacher teacher);
	
	void deleteTeacherById(Long id);

	void sendNotice(String textNotice);

}

package com.ramon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ramon.model.TeacherModel;
import com.ramon.repository.TeacherRepository;
import com.ramon.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	private final TeacherRepository teacherRepository;

	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		super();
		this.teacherRepository = teacherRepository;
	}

	@Override
	public List<TeacherModel> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherModel save(TeacherModel teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public TeacherModel getTeacherById(Long id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public TeacherModel updateTeacher(TeacherModel teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacherById(Long id) {
		teacherRepository.deleteById(id);
		
	}

}

package com.ramon.service.impl;

import java.util.List;

import com.ramon.model.NoticeModel;
import com.ramon.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import com.ramon.model.TeacherModel;
import com.ramon.repository.TeacherRepository;
import com.ramon.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	private final TeacherRepository teacherRepository;
	private final NoticeRepository noticeRepository;
	public TeacherServiceImpl(TeacherRepository teacherRepository, NoticeRepository noticeRepository) {
		super();
		this.teacherRepository = teacherRepository;
		this.noticeRepository = noticeRepository;
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

	@Override
	public void sendNotice(String textNotice) {
		NoticeModel notice = new NoticeModel();
		notice.setText(textNotice);
		noticeRepository.save(notice);
	}

}

package com.ramon.service.impl;

import java.util.List;

import com.ramon.model.Notice;
import com.ramon.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import com.ramon.model.Teacher;
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
	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public Teacher updateTeacher(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacherById(Long id) {
		teacherRepository.deleteById(id);
		
	}

	@Override
	public void sendNotice(String textNotice) {
		Notice notice = new Notice();
		notice.setText(textNotice);
		noticeRepository.save(notice);
	}

}

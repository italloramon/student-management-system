package com.ramon.service.impl;

import java.util.List;

import com.ramon.exception.EmptyValuesException;
import com.ramon.exception.InvalidFieldException;
import com.ramon.model.Notice;
import com.ramon.repository.NoticeRepository;
import com.ramon.utils.CheckAllFields;
import com.ramon.utils.CheckCPF;
import com.ramon.utils.CheckEmail;
import com.ramon.utils.CheckName;
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
	public List<Teacher> getAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher save(Teacher teacher) throws EmptyValuesException, InvalidFieldException {
		if (teacher.getEmail().isEmpty() || teacher.getCpf().isEmpty() || teacher.getEmail().isEmpty() ||
		teacher.getSalary() == null) {
			throw new EmptyValuesException();
		}

		try {
			Boolean checkAllFields = CheckAllFields.checkAllFields(teacher.getName(),
					teacher.getCpf(), teacher.getEmail());
		} catch (InvalidFieldException ex) {
			throw ex;
		}

		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher getById(Long id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public Teacher update(Teacher teacher) throws EmptyValuesException, InvalidFieldException {
		return this.save(teacher);
	}

	@Override
	public void deleteById(Long id) {
		teacherRepository.deleteById(id);
		
	}

	@Override
	public void sendNotice(String textNotice) {
		Notice notice = new Notice();
		notice.setText(textNotice);
		noticeRepository.save(notice);
	}

}

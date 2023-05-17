package com.ramon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.ramon.command.Command;
import com.ramon.command.CommandFactory;
import com.ramon.exception.EmptyValuesException;
import com.ramon.exception.InvalidFieldException;
import com.ramon.utils.CheckAllFields;
import com.ramon.utils.CheckCPF;
import com.ramon.utils.CheckEmail;
import com.ramon.utils.CheckName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ramon.model.CourseModel;
import com.ramon.model.Student;
import com.ramon.model.courses.English;
import com.ramon.model.courses.Geography;
import com.ramon.model.courses.History;
import com.ramon.model.courses.Mathematics;
import com.ramon.model.courses.Portuguese;
import com.ramon.repository.EnglishRepository;
import com.ramon.repository.GeographyRepository;
import com.ramon.repository.HistoryRepository;
import com.ramon.repository.MathematicsRepository;
import com.ramon.repository.PortugueseRepository;
import com.ramon.repository.StudentRepository;
import com.ramon.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

	private final CommandFactory commandFactory;

	@Override
	public List<Student> getAll() {
		return (List<Student>) commandFactory.create(Command.GET_STUDENTS).execute();
	}

	@Override
	public Student save(Student element) throws Exception {
		return (Student) commandFactory.create(Command.SAVE_STUDENT, element).executeWithException();
	}

	@Override
	public Student getById(Long id) {
		return (Student) commandFactory.create(Command.GET_STUDENT, id).execute();
	}

	@Override
	public Student update(Student element) throws Exception {
		try {
			return this.save(element);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public void deleteById(Long id) {
		commandFactory.create(Command.DELETE_STUDENT, id).execute();
	}

	@Override
	public List<CourseModel> getStudentCourses(Student student) {
		List<CourseModel> courses = new ArrayList<>();
		English english = student.getEnglish();
		Portuguese portuguese = student.getPortuguese();
		Mathematics mathematics = student.getMathematics();
		History history = student.getHistory();
		Geography geography = student.getGeography();
		courses.add(english);
		courses.add(geography);
		courses.add(history);
		courses.add(portuguese);
		courses.add(mathematics);
		return courses;
	}
	@Override
	public Student[] getRankingStudents() {
		return (Student[]) commandFactory.create(Command.RANKING_STUDENTS).execute();
	}

}

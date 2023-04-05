package com.ramon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
public class StudentServiceImpl implements StudentService{
	
	private final StudentRepository studentRepository;
	
	private final EnglishRepository englishRepository;
	
	private final PortugueseRepository portugueseRepository;
	
	private final MathematicsRepository mathematicsRepository;
	
	private final GeographyRepository geographyRepository;
	
	private final HistoryRepository historyRepository;
	

	
	public StudentServiceImpl(StudentRepository studentRepository, EnglishRepository englishRepository,
			PortugueseRepository portugueseRepository, MathematicsRepository mathematicsRepository,
			GeographyRepository geographyRepository, HistoryRepository historyRepository) {
		super();
		this.studentRepository = studentRepository;
		this.englishRepository = englishRepository;
		this.portugueseRepository = portugueseRepository;
		this.mathematicsRepository = mathematicsRepository;
		this.geographyRepository = geographyRepository;
		this.historyRepository = historyRepository;
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

	public static Student[] getSorterdStudents(Student[] students, Comparator<Student> comparator) {
		Student[] sorted = students.clone();
		Arrays.sort(sorted, comparator);
		return sorted;
	}
	@Override
	public Student[] getRankingStudents() {
        List<Student> studentsList = studentRepository.findAll();
        Student[] studentsArray = new Student[studentsList.size()];

        for (int i = 0; i  < studentsList.size(); i++) {
            studentsArray[i] = studentsList.get(i);
        }

        Student[] studentsArraySorted = this.getSorterdStudents(studentsArray, Comparator.comparing(Student::getRankingStudent).reversed());

        return studentsArraySorted;
	}
	
	
}

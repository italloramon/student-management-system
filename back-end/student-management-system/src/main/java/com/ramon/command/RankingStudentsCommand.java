package com.ramon.command;

import com.ramon.model.Student;
import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class RankingStudentsCommand implements Command {
    private final StudentRepository studentRepository;

    public static Student[] getSorterdStudents(Student[] students, Comparator<Student> comparator) {
        Student[] sorted = students.clone();
        Arrays.sort(sorted, comparator);
        return sorted;
    }
    @Override
    public Object execute() {
        List<Student> studentsList = studentRepository.findAll();
        Student[] studentsArray = new Student[studentsList.size()];

        for (int i = 0; i  < studentsList.size(); i++) {
            studentsArray[i] = studentsList.get(i);
        }

        Student[] studentsArraySorted = this.getSorterdStudents(studentsArray, Comparator.comparing(Student::getRankingStudent).reversed());

        return studentsArraySorted;
    }
}

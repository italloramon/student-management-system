package com.ramon.command;

import com.ramon.model.Student;
import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ramon.command.Command.DELETE_STUDENT;
import static com.ramon.command.Command.RANKING_STUDENTS;

@Component
@RequiredArgsConstructor
public class CommandFactory {

    private final StudentRepository studentRepository;

    public Command create(int commandCode, Object... params) {
        switch (commandCode) {
            case DELETE_STUDENT:
                return new DeleteStudentCommand(studentRepository, (Long) params[0]);
            case RANKING_STUDENTS:
                return new RankingStudentsCommand(studentRepository);
            default:
                return null;
        }
    }
}

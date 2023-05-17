package com.ramon.command;

import com.ramon.model.Student;
import com.ramon.repository.ResponsableRepository;
import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ramon.command.Command.*;

@Component
@RequiredArgsConstructor
public class CommandFactory {

    private final StudentRepository studentRepository;
    private final ResponsableRepository responsableRepository;

    public Command create(int commandCode, Object... params) {
        switch (commandCode) {
            case DELETE_STUDENT:
                return new DeleteStudentCommand(studentRepository, (Long) params[0], responsableRepository);
            case RANKING_STUDENTS:
                return new RankingStudentsCommand(studentRepository);
            case GET_STUDENT:
                return new GetStudentCommand(studentRepository, (Long) params[0]);
            case GET_STUDENTS:
                return new GetStudentsCommand(studentRepository);
            case SAVE_STUDENT:
                return new SaveStudent(studentRepository, (Student) params[0], responsableRepository);
            default:
                return null;
        }
    }
}

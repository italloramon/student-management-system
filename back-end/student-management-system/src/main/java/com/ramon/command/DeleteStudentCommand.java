package com.ramon.command;

import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentCommand implements Command {

    private final StudentRepository studentRepository;
    private final Long id;
    @Override
    public Boolean execute() {
        studentRepository.deleteById(id);
        return true;
    }
}

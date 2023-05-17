package com.ramon.command;

import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStudentCommand implements Command {

    private final StudentRepository studentRepository;
    private final Long id;
    @Override
    public Object execute() {
        return studentRepository.findById(id).get();
    }
}

package com.ramon.command;

import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetStudentsCommand implements Command {

    private final StudentRepository studentRepository;

    @Override
    public Object execute() {
        return studentRepository.findAll();
    }
}

package com.ramon.command;

import com.ramon.model.Responsable;
import com.ramon.repository.ResponsableRepository;
import com.ramon.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteStudentCommand implements Command {

    private final StudentRepository studentRepository;
    private final Long id;
    private final ResponsableRepository responsableRepository;
    @Override
    public Boolean execute() {
        Long idResponsable = studentRepository.findById(id).get().getResponsable().getId();

        studentRepository.deleteById(id);

        Responsable responsable = responsableRepository.findById(idResponsable).get();
        responsable.nextState();
        responsableRepository.save(responsable);

        return true;
    }
}

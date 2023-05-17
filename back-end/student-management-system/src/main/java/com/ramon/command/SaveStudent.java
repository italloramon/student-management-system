package com.ramon.command;

import com.ramon.exception.EmptyValuesException;
import com.ramon.exception.InvalidFieldException;
import com.ramon.model.Responsable;
import com.ramon.model.Student;
import com.ramon.repository.ResponsableRepository;
import com.ramon.repository.StudentRepository;
import com.ramon.utils.CheckAllFields;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveStudent implements Command {

    private final StudentRepository studentRepository;
    private final Student element;
    private final ResponsableRepository responsableRepository;
    @Override
    public Object executeWithException() throws Exception {
        if(element.getName().isEmpty() || element.getCpf().isEmpty() || element.getEmail().isEmpty() ||
                element.getTuition() == null) {
            throw new EmptyValuesException();
        }

        try {
            Boolean checkAllFields = CheckAllFields.checkAllFields(element.getName(), element.getCpf(), element.getEmail());
        } catch (InvalidFieldException ex) {
            throw ex;
        }

        Student student = studentRepository.save(element);

        Responsable responsable = responsableRepository.findById(element.getResponsable().getId()).get();
        responsable.nextState();
        responsableRepository.save(responsable);

        return student;
    }
}

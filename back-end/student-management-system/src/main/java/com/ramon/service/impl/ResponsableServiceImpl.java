package com.ramon.service.impl;

import java.util.List;

import com.ramon.exception.EmptyValuesException;
import com.ramon.exception.InvalidFieldException;
import com.ramon.utils.CheckAllFields;
import com.ramon.utils.CheckCPF;
import com.ramon.utils.CheckEmail;
import com.ramon.utils.CheckName;
import org.springframework.stereotype.Service;

import com.ramon.model.Responsable;
import com.ramon.model.Student;
import com.ramon.repository.ResponsableRepository;
import com.ramon.service.ResponsableService;

@Service
public class ResponsableServiceImpl implements ResponsableService {
	
	private final ResponsableRepository responsableRepository;
	
	
	public ResponsableServiceImpl(ResponsableRepository responsableRepository) {
		super();
		this.responsableRepository = responsableRepository;
	}


	@Override
	public List<Responsable> getAll() {
		return responsableRepository.findAll();
	}


	@Override
	public Responsable save(Responsable responsable) throws EmptyValuesException, InvalidFieldException {
		if (responsable.getNameResponsable().isEmpty() ||
				responsable.getEmailResponsable().isEmpty() ||
				responsable.getCpfResponsable().isEmpty()) {
			throw new EmptyValuesException();
		}

		try {
			Boolean checkAllFields = CheckAllFields.checkAllFields(responsable.getNameResponsable(),
					responsable.getCpfResponsable(), responsable.getEmailResponsable());
		} catch (InvalidFieldException ex) {
			throw ex;
		}

		return responsableRepository.save(responsable);
	}


	@Override
	public Responsable getById(Long id) {
		return responsableRepository.findById(id).get();
	}


	@Override
	public Responsable update(Responsable responsable) throws EmptyValuesException, InvalidFieldException {
		return this.save(responsable);
	}


	@Override
	public void deleteById(Long id) {
		responsableRepository.deleteById(id);
		
	}

	@Override
	public List<Student> getStudentsOfResponsable(Responsable responsable) {
		return responsable.getStudents();
	}

}

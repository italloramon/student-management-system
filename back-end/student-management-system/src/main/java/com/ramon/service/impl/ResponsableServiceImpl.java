package com.ramon.service.impl;

import java.util.List;

import com.ramon.exception.EmptyValuesException;
import com.ramon.exception.InvalidCPFException;
import com.ramon.utils.CheckCPF;
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
	public Responsable save(Responsable responsable) throws EmptyValuesException, InvalidCPFException {
		if (responsable.getNameResponsable().isEmpty() ||
				responsable.getEmailResponsable().isEmpty() ||
				responsable.getCpfResponsable().isEmpty()) {
			throw new EmptyValuesException("Cannot leave empty fields!");
		} else if (!CheckCPF.isCPF(responsable.getCpfResponsable())) {
			throw new InvalidCPFException(responsable.getCpfResponsable());
		}
		return responsableRepository.save(responsable);
	}


	@Override
	public Responsable getById(Long id) {
		return responsableRepository.findById(id).get();
	}


	@Override
	public Responsable update(Responsable responsable) {
		return responsableRepository.save(responsable);
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

package com.ramon.service.impl;

import java.util.List;

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
	public List<Responsable> getAllResponsables() {
		return responsableRepository.findAll();
	}


	@Override
	public Responsable save(Responsable responsable) {
		return responsableRepository.save(responsable);
	}


	@Override
	public Responsable getResponsableById(Long id) {
		return responsableRepository.findById(id).get();
	}


	@Override
	public Responsable updateResponsable(Responsable responsable) {
		return responsableRepository.save(responsable);
	}


	@Override
	public void deleteResponsableById(Long id) {
		responsableRepository.deleteById(id);
		
	}

}

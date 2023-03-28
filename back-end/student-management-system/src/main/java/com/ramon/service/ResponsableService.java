package com.ramon.service;

import java.util.List;

import com.ramon.model.Responsable;
import com.ramon.model.Student;

public interface ResponsableService {
	
	List<Responsable> getAllResponsables();
	
	Responsable save(Responsable responsable);
	
	Responsable getResponsableById(Long id);
	
	Responsable updateResponsable(Responsable responsable);
	
	void deleteResponsableById(Long id);

	List<Student> getStudentsOfResponsable(Responsable responsable);
}

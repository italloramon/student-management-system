package com.ramon.service;

import java.util.List;

import com.ramon.model.Responsable;
import com.ramon.model.Student;

public interface ResponsableService extends UserService<Responsable> {
	List<Student> getStudentsOfResponsable(Responsable responsable);
}

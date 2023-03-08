package com.ramon.controller;

import com.ramon.model.StudentModel;


import java.util.List;
import java.util.Map;

public abstract class MainController<T> {
	public abstract List<T> getAll();
	
	public abstract void delete(Long id);
	
	public abstract T update(T element, Long id);
	
	public abstract T create(Map<String, String> t);

}

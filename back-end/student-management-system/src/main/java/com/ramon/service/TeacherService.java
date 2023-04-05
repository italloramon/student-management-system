package com.ramon.service;

import java.util.List;

import com.ramon.model.Teacher;


public interface TeacherService extends UserService<Teacher>{
	void sendNotice(String textNotice);

}

package com.elon.jdbc.day01.basic.student.model;

import java.util.List;

public interface ManageInterface {

	List<Student> getAllStudent();

	void modifyStudent(String name);

	Student searchOneByName(String name);

	void addStudent(Student student);
	
	void removeStudent(String name);
	

}

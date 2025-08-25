package com.elon.jdbc.day01.student.model;

import java.util.List;

public interface ManageInterface {

	List<Student> getAllStudent();

	void modifyStudent(String name);

	Student searchOneByName(String name);

	void deleteStudent(int index);

//	int searchIndexByName(String name);


	void addStudent(Student student);
	
//	void setStudent(int index, Student student);
	
	void removeStudent(String name);
	

}

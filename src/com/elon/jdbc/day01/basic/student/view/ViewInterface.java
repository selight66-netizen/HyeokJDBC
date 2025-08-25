package com.elon.jdbc.day01.basic.student.view;

import java.util.List;

import com.elon.jdbc.day01.student.model.Student;

public interface ViewInterface {
	
	int printMenu();
	
	void printStudents(List<Student>sList);
	
	void printStudent(Student student);
	
	void displayMsg(String msg);
	
	void testResult(Student student);
	
	String inputName();
	
	Student inputStudent();
}

package com.elon.jdbc.day01.basic.student.controller;

import java.util.ArrayList;
import java.util.List;

import com.elon.jdbc.day01.student.model.ManageStudent;
import com.elon.jdbc.day01.student.model.Student;
import com.elon.jdbc.day01.student.view.ViewStudent;

public class StudentApp {

	
	public static void main(String[] args) {
		ManageStudent manage = new ManageStudent();
		ViewStudent view = new ViewStudent();
		Student student;
		String name;
		int index;
		end:
		while(true) {
			int menu = view.printMenu();
			switch(menu) {
			case 1: 
				student = view.inputStudent();
				manage.addStudent(student);
				break;
			case 2: 
				name = view.inputName();
				student = manage.searchOneByName(name);
				if(student != null) {
					view.printStudent(student);
				}
				else {
					view.displayMsg("회원 정보가 존재하지 않습니다");
				}
				break;
			case 3:
				List<Student> sList = manage.getAllStudent();
				view.printStudents(sList);
				break;
			case 4:		
				name = view.inputName();
				manage.modifyStudent(name);
				break;
			case 5:
				name =view.inputName();
				manage.removeStudent(name);
				view.displayMsg("정보 삭제가 성공적으로 완료되었습니다");
				break;
			case 6:
				name =view.inputName();
				student = manage.searchOneByName(name);
				view.testResult(student);
				break;
			case 0:
				view.displayMsg("프로그램을 종료합니다.");
				break end;
			default:
				view.displayMsg("1~6사이의 숫자를 입력하세요");
				break;
			}
		}
	}

}

package com.elon.jdbc.day01.basic.student.view;

import java.util.List;
import java.util.Scanner;

import com.elon.jdbc.day01.student.model.Student;

public class ViewStudent implements ViewInterface{
	Scanner sc = new Scanner(System.in);

	@Override
	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("=====학생 성적관리 프로그램");
		System.out.println("1. 학생 정보 입력");
		System.out.println("2. 학생 정보 이름 검색");
		System.out.println("3. 학생 정보 전체 출력");
		System.out.println("4. 학생 정보 수정");
		System.out.println("5. 학생 정보 삭제");
		System.out.println("6. 재평가 대상 여부 확인");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 :");
		
		return sc.nextInt();
	}

	@Override
	public void printStudents(List<Student>sList) {
		System.out.println("=======학생 전체 정보 출력 ======");
		for(Student student: sList) {
			System.out.println(student.getName()+"학생의 1차 점수는 "+student.getFirstScore()
			+", 2차 점수는 "+student.getsecondScore()+"점 입니다.");
		}
	}

	@Override
	public void printStudent(Student student) {
		System.out.println("======="+student.getName()+"학생 정보 출력 ======");
		System.out.println(student.getName()+"학생의 1차 점수는 "+student.getFirstScore()
		+", 2차 점수는 "+student.getsecondScore()+"점 입니다.");		
	}

	@Override
	public void displayMsg(String msg) {
		System.out.println(msg);
	}

	@Override
	public void testResult(Student student) {
		int first = student.getFirstScore();
		int second = student.getsecondScore();
		int avg = (first+second)/2;
		System.out.println("1차 점수 :"+first+"점");
		System.out.println("2차 점수 :"+second+"점");
		if(avg>=60) {
			
		}else if(avg <60 ) {
			
		}
	}

	@Override
	public String inputName() {
		System.out.println("이름 입력 :");
		String name = sc.next();
		return name;
	}
	
	@Override
	public Student inputStudent() {
		
		System.out.println("===학생 정보 입력 ===");
		System.out.print("이름 :");
		String name = sc.next();
		System.out.print("1차 점수 :");
		int firstScore = sc.nextInt();
		System.out.print("2차 점수 :");
		int secondScore = sc.nextInt();
		Student student = new Student (name, firstScore,secondScore);
		return student;
	}
}

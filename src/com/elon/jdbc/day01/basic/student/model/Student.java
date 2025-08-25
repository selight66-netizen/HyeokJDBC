package com.elon.jdbc.day01.basic.student.model;

public class Student {
	//필드
	private String name;
	private int firstScore;
	private int secondScore;
	//생성자
	public Student() {}
	public Student(String name,int firstScore,int secondScore) {
		this.name = name;
		this.firstScore = firstScore;
		this.secondScore = secondScore;
	}
	
	//메소드
	
	//setter&getter
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setFirstScore(int firstScore) {
		this.firstScore =firstScore;
	}
	
	public int getFirstScore() {
		return firstScore;
	}
	
	public void setsecondScore(int secondScore) {
		this.secondScore = secondScore;
	}
	
	public int getsecondScore() {
		return secondScore;
	}
	public int getAvgScore() {
		int avg = (secondScore+firstScore)/2;
		return avg;
	}
}

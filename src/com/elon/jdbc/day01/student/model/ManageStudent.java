package com.elon.jdbc.day01.student.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ManageStudent implements ManageInterface {

	private List<Student> sList ;
	Scanner sc = new Scanner(System.in);
	
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER = "ELONJDBC";
	private final static String PW = "ELONJBDC";
	
	public List<Student> getAllStudent(){
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset =null;
		String query = "SELECT * FROM STUDENT_TBL";
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PW);
			stmt = conn.createStatement();	
			rset = stmt.executeQuery(query);
			sList = new ArrayList<Student>();
			while(rset.next()) {
//				System.out.println(rset.getString("STUDENT_NAME")); //위의 코드가 정상적인지 확인용
				String name = rset.getString("STUDENT_NAME"); //이름에 맞는 자료형 대입
				int firstScore = rset.getInt("FIRST_SCORE"); //점수에 맞는 자료형 대입
				int secondScore = rset.getInt("SECOND_SCORE");
				Student student = new Student(name,firstScore,secondScore);//학생 객체 만들어서
				sList.add(student);// sList에 넣기
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
				conn.close();
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
		
		return sList;
	}
	@Override
	public void modifyStudent(String name) {
		Connection conn = null;
		Statement stmt = null;
		int result =-1;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PW);
			stmt = conn.createStatement();	
			System.out.print("1차 점수 입력 ");
			int firstScore = sc.nextInt();
			System.out.print("2차 점수 입력 ");
			int secondScore = sc.nextInt();
			String query = "UPDATE STUDENT_TBL SET FIRST_SCORE ="+firstScore
					+" ,SECOND_SCORE ="+secondScore+" WHERE STUDENT_NAME = '"+name+"'";
			stmt.executeUpdate(query);
			result = stmt.executeUpdate(query);
			conn.setAutoCommit(false);
			if(result >0 ) {
				conn.commit();
				System.out.println("데이터 업데이트 성공");
			}else {
				conn.commit();
				System.out.println("데이터 업데이트 실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}	
//		System.out.print("1차 점수 입력 ");
//		int firstScore = sc.nextInt();
//		System.out.print("2차 점수 입력 ");
//		int secondScore = sc.nextInt();
//		Student student = new Student (name,firstScore,secondScore);
//		return student;//오답노트 > 해당 코드는 Student 생성자를 이용하는건데
//		그러기 위해서 임포트를 해야함. 하지만 해당 코드를 이용하지 않고 List를 사용해서 접근중이므로
//		 setName 같은 메소드를 호출하는 방식으로 만들어야함
	}

	@Override
	public Student searchOneByName(String name) {
		String query = "SELECT * FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PW);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			if(rset.next()) {
				int firstScore = rset.getInt("FIRST_SCORE"); //점수에 맞는 자료형 대입
				int secondScore = rset.getInt("SECOND_SCORE");
				Student student = new Student(name,firstScore,secondScore);
				return student;				
			}else {
				return null;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void deleteStudent(int index) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public int searchIndexByName(String name) {
//		for(int i = 0; i< sList.size();i++) {
//			if(sList.get(i).getName().equals(name)) {
//				return i;
//			}
//		}
//		return -1;// index가 0이면 첫번째 값이 없어지기 때문에
//	}

	@Override
	public void addStudent(Student student) {
//		sList.add(student);
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","ELONJDBC","ELONJBDC");
			Statement stmt = conn.createStatement();
//			String query = "INSERT INTO STUDENT_TBL VALUES('일용자',99,88)";
			String query = "INSERT INTO STUDENT_TBL VALUES('"+student.getName()+"',"
					+student.getFirstScore()+","+student.getsecondScore()+")";
			int result =  stmt.executeUpdate(query);
			conn.setAutoCommit(false);
			if(result >0) {
				conn.commit();
				System.out.println("학생 정보 추가 완료");
			}else {
				conn.commit();
				System.out.println("학생 정보 추가 실패");
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			try {
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
		}	
	}



	@Override
	public void removeStudent(String name) {
		Connection conn = null;
		Statement stmt = null;
		String query = "DELETE FROM STUDENT_TBL WHERE STUDENT_NAME = '"+name+"'";
		int result = -1;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PW);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			conn.setAutoCommit(false);
			if(result >0) {
				conn.commit();
				System.out.println("데이터 삭제 성공");
			}else {
				conn.rollback();
				System.out.println("데이터 삭제 실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	@Override
//	public void removeStudent(int index) {
//		sList.remove(index);
//	}
}

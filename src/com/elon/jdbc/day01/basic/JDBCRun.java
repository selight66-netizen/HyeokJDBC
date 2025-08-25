package com.elon.jdbc.day01.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCRun {
	/*
	 * 1. 드라이버 등록
	 * 2. DBMS 연결
	 * 3. Statement 생성
	 * 4. SQL 전송
	 * 5. 결과받기 - 후처리
	 * 6. 자원 해제
	 */
	//스테틱 메소드는 스테틱에만 접근 가능
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private final static String USER = "KH";
	private final static String PW = "KH";
	
	//여기 스테틱.....
	public static void main(String[] args) {
		String query = "UPDATE EMPLOYEE SET SALARY = 2000000 WHERE EMP_ID ='200'";//쿼리문 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = -1;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PW);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result>0) {
				System.out.println("데이터 업데이트 성공");
			}else {
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
	}
	public static void updateRun() {
		
	}
	
	public void insertRun() {
		String query = "INSERT INTO EMPLOYEE VALUES ('200','선동일','621231-1985634','sun_di@kh.or.kr','01099546325','D9','J1','S1',8000000,0.3,null,to_date('13/02/06','RR/MM/DD'),null,'N')";//쿼리문 
		Connection conn = null;
		Statement stmt = null;
		//INSERT할건데 ResultSet 필요할까?
		int result = -1;
		
		//1.드라이버 등록
		try {
			//스테틱 메소드는 스테틱에만 접근 가능
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL ,USER ,PW);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			if(result>0) {
				System.out.println("데이터 추가 성공");
			}else {
				System.out.println("데이터 추가 실패");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
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
		
	}
	
	public void deleteRun() {
		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = 201";//쿼리문 
		Connection conn = null;
		Statement stmt = null;
		int result = -1;
		
		try {
			//1.드라이버 등록
			Class.forName(DRIVER);
			//2.DBMS 연결
			conn = DriverManager.getConnection(URL ,USER ,PW);
			//3.Statement 생성
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);// ~개의 행이 삭제되었습니다 = 숫자가 출력됨			
			//오토 커밋 해제 방법
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
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}	
		
	}
	
	
	public void selectRun() {
		String query = "SELECT * FROM EMPLOYEE";//쿼리문 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			//1. 드라이버 등록
			Class.forName(DRIVER);//oracle.jdbc.driver.OracleDriver는 상수
			//2. DBMS 연결
			conn = DriverManager.getConnection(URL, USER, PW);
//			Statement stmt = new Statemet();//스테이트먼트 인터페이스 떄문에 new로 생성 불가
			stmt = conn.createStatement();//워크시트 열기
			rset = stmt.executeQuery(query);//DB에서의 컨트롤 + 엔터 역할
			//ResultSet=후처리
			while(rset.next()) {
				System.out.println("사번 :"+rset.getString("EMP_ID")
				+ "\t이름 : "+ rset.getString("EMP_NAME")
				+ "\t월급 : "+ rset.getString("SALARY")
				+ "\t담당 매니저 : " + rset.getString("MANAGER_ID"));
			}
		} catch (ClassNotFoundException e) {
			//드라이버 등록 실패시 작동할 내용
			e.printStackTrace();
		} catch (SQLException e) {
			// DriverManager.getConnection 실패시 작동할 내용
			e.printStackTrace();
		} finally {//try/catch 결과에 관계 없이 실행되는 문장
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

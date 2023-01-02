package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	/*
	 * JDBC 과정 중 반복적으로 씌는 구문들을 각가의 메서드로 정의해둘 곳
	 * "재사용을 목적"으로 공통 템플릿 작업 진행
	 * 
	 * 이 클래스에서의 모든 메서드들은 다 static 메서드로 만들 것
	 * 
	 * 공통적인 부분 뽑아내기
	 * 1. DB와 접속된 Connection 객체를 생성해서 반환시켜 주는 메서드
	 */
	
	public static Connection getConnection() {
		
		// Connection 객체를 담을 그릇 생성
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "JDBC", "JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 2. 전달받은 JDBC용 객체를 반납시켜 주는 메서드(close)
	// 2-1) Connection 객체를 전달받아서 반납
	public static void close(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2-2) Statement 객체를 전달받아서 반납시켜 주는 메서드(다형성 작용)
	//      -> 다형성으로 인해 PreparedStatement 객체를 매개 변수로 받아줄 수 있따.
	public static void close(Statement stmt) {
		
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2-3) ResultSet 객체를 전달받아서 반납시켜 주는 메서드
	public static void close(ResultSet rset) {
		
		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3) 전달받은 Connection 객체로 트랜잭션 처리를 해주는 메서드
	// 3-1) commit 시켜 주는 메서드
	public static void commit(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3-2) rollback 시켜 주는 메서드
	public static void rollback(Connection conn) {
		
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

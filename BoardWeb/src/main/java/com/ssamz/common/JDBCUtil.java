package com.ssamz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;

public class JDBCUtil {

	public static  Connection getConnection() {
		Connection conn = null;
		try {
			// JDBC 1단계: 드라이버 객체 로딩
			DriverManager.registerDriver(new org.h2.Driver());
			// JDBC 2단계: 커넥션 연결
			String jdbcUrl = "jdbc:h2:tcp://localhost/~/test";
			conn = DriverManager.getConnection(jdbcUrl, "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(PreparedStatement stmt, Connection conn) {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}

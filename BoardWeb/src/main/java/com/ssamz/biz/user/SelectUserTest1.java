package com.ssamz.biz.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssamz.common.JDBCUtil;

public class SelectUserTest1 {
	public static void main(String[] args) {
		// JDBC 관련 변수
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = JDBCUtil.getConnection();
			
			// JDBC 3단계: Statement 생성
			String sql = "select * from users";
			stmt = conn.prepareStatement(sql);
			
			// SQL 전송
			rs = stmt.executeQuery();
			
			
		System.out.println("[USER LIST]");
		rs.next();
		System.out.print(rs.getString("USERNAME") + " :");
		System.out.print(rs.getString("PASSWORD") + " :");
		System.out.print(rs.getString("NAME") + " :");
		System.out.println(rs.getString("ROLE"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
	}

}

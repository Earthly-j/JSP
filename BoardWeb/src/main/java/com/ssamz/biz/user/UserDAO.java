package com.ssamz.biz.user;

import com.ssamz.common.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
      private Connection conn = null;
      private PreparedStatement stmt = null;
      private ResultSet rs = null;
      
      private String USER_LIST = "select * from users";
      
      private String USER_INSERT = "insert into users values(?, ?, ?, ?)";
      
      private String USER_UPDATE = "update users set name=?, role=? where USERNAME=?";
      private String USER_DELETE = "delete from users where USERNAME=?";
      
      public void deleteUser(String username) {
		  try {
			  conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(USER_DELETE);
			  stmt.setString(1, username);
			  stmt.executeUpdate();
			 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } finally {
			  JDBCUtil.close(stmt, conn);
		  }
	  }
      
      
      
      public void updateUser(UserVO vo) {
		  try {
			  conn = JDBCUtil.getConnection();
			  stmt = conn.prepareStatement(USER_UPDATE);
			  stmt.setString(1, vo.getName());
			  stmt.setString(2, vo.getRole());
			  stmt.setString(3, vo.getUsername());
			  stmt.executeUpdate();
			 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } finally {
			  JDBCUtil.close(stmt, conn);
		  }
	  }
      
      
      
      public void insertUser (UserVO vo) {
		  try {
			 conn = JDBCUtil.getConnection();
			 stmt = conn.prepareStatement(USER_INSERT);
			 stmt.setString(1, vo.getUsername());
			 stmt.setString(2, vo.getPassword());
			 stmt.setString(3, vo.getName());
			 stmt.setString(4, vo.getRole());
			 
			  
			  int count = stmt.executeUpdate();
			  System.out.println(count + "건 입력되었습니다.");
		  } catch (SQLException e) {
			  e.printStackTrace();
		  } finally {
			  JDBCUtil.close(stmt, conn);
		  }
	  }
      
      
      
      
      public void getUserList() {
			try {
				  conn = JDBCUtil.getConnection();
				  stmt = conn.prepareStatement(USER_LIST);
				  rs = stmt.executeQuery();
				  System.out.println("[USER LIST]");
				  while(rs.next()) {
						System.out.print(rs.getString("USERNAME") + " :");
						System.out.print(rs.getString("PASSWORD") + " :");
						System.out.print(rs.getString("NAME") + " :");
						System.out.println(rs.getString("ROLE"));
				  }
			} catch (SQLException e) {
				  e.printStackTrace();
			} finally {
				  JDBCUtil.close(rs, stmt, conn);
			}
	  }
	}


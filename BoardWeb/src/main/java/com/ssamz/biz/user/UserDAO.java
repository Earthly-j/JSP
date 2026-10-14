package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssamz.common.JDBCUtil;

public class UserDAO {
      private Connection conn = null;
      private PreparedStatement stmt = null;
      private ResultSet rs = null;
      
      private String USER_LIST = "select * from users = ?";
      
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


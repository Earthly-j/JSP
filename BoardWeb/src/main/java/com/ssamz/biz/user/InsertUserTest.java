package com.ssamz.biz.user;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ssamz.common.JDBCUtil;

public class InsertUserTest {

	public static void main(String[] args) {
	
			
		UserDAO dao = new UserDAO(); // create UserDAO object
		
		dao.insertUser("test", "test", "테스트", "USER"); // enroll user
			
		dao.getUserList(); // refer list
		
		
		
	}
}

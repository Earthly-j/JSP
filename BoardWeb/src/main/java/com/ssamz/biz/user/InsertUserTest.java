package com.ssamz.biz.user;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ssamz.common.JDBCUtil;

public class InsertUserTest {

	public static void main(String[] args) {
	
		
			
		UserDAO dao = new UserDAO(); // create UserDAO object
		
		UserVO vo = new UserVO(); // create UserVO object
		
		vo.setUsername("test");
		
		vo.setPassword("1234");
		
		vo.setName("테스트");

		vo.setRole("USER");		
		
		dao.insertUser(vo); // insert user using UserVO object
				
		dao.getUserList(); // refer list
		
		
		
	}
}

package com.ssamz.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssamz.common.JDBCUtil;


public class DeleteUserTest {

	public static void main(String[] args) {
						
		UserDAO dao = new UserDAO();
					
		dao.deleteUser("ssamz");
		
		dao.getUserList();
		
	}
}

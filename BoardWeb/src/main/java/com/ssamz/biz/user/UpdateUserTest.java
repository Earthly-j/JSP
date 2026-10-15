package com.ssamz.biz.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ssamz.common.JDBCUtil;

public class UpdateUserTest {

		public static void main(String[] args) {
			UserDAO dao = new UserDAO();
			
			dao.updateUser("modified", "ADMIN", "ssamz");
			
			dao.getUserList();
			
		}
	}


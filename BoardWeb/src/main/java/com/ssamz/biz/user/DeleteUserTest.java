package com.ssamz.biz.user;


public class DeleteUserTest {

	public static void main(String[] args) {
						
		UserDAO dao = new UserDAO();
					
		dao.deleteUser("ssamz");
		
		dao.getUserList();
		
	}
}

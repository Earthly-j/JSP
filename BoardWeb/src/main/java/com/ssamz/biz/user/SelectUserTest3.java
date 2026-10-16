package com.ssamz.biz.user;

import java.util.List;			

public class SelectUserTest3 {
	
	public static void main(String[] args) {
	  UserDAO dao = new UserDAO();
	  
	  List<UserVO> userList = dao.getUserList(); 
	  
	  System.out.println("whole client amount :" + userList.size());
	  
	  System.out.println("[client authority]");
	  
	  for(UserVO user : userList) {
		 System.out.println(user.getUsername() + " : " + user.getName() + " : " + user.getRole());
	  }
	  dao.getUserList();
		
	}

}

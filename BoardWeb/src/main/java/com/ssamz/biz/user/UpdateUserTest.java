package com.ssamz.biz.user;

public class UpdateUserTest {

		public static void main(String[] args) {
			UserDAO dao = new UserDAO();
			
			UserVO vo = new UserVO();

			vo.setName("modify");
			vo.setRole("USER");
			vo.setUsername("ssamz4");
			dao.updateUser(vo);

			dao.getUserList();
			
			
		}
	}


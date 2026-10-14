package com.ssamz.web.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
    	System.out.println("===> LoginServlet 생성");
    }
    
    
    @Override
    public void init() throws ServletException {
    	System.out.println("--->init() 호출");
    }
    
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
    	System.out.println("--->service() 호출");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> doGet() 호출");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---> doPost() 호출");
	}

	@Override
	public void destroy() {
		System.out.println("---> destroy() 호출");
	}
	
}

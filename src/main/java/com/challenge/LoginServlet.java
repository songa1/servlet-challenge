package com.challenge;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name ="LoginServlet" ,urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		try {
			
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = VolatileDB.fetch(username);
			
			boolean isLogin = user.Login(username, password);
			
			
			if(!isLogin) {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(gson.toJson(new ApiResponse<Object>(404, "user not found", null)));
			}
			else {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(gson.toJson(new ApiResponse<Object>(200, "Successfull login", user)));
			}
			
		} catch (Exception e) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(gson.toJson(new ApiResponse<Object>(404, "Error while loggin in", null)));
		}
		
	}

}

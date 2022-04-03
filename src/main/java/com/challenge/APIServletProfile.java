package com.challenge;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "APIServletProfile", urlPatterns = "/profile")
public class APIServletProfile extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		
		User user = VolatileDB.fetch(id);
		
		Gson gson = new Gson();
		
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 
		 if(user != null) {
		 
		 map.put("username", user.getUsername()); 
		 map.put("password", user.getPassword());
		 map.put("firstname", user.getFirstname());
		 map.put("lastname", user.getLastname());
		 map.put("gender", user.getGender());
		 map.put("phone", user.getPhone());
		 map.put("age", user.getAge());
		 map.put("role", user.getRole());
		 
		 }
		 	 
			resp.setContentType("application/json");
			resp.setContentType("text/html");
	        resp.setHeader("Cache-control", "no-cache, no-store");
	        resp.setHeader("Pragma", "no-cache");
	        resp.setHeader("Expires", "-1");
	 
	        resp.setHeader("Access-Control-Allow-Origin", "*");
	        resp.setHeader("Access-Control-Allow-Methods", "POST");
	        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
	        resp.setHeader("Access-Control-Max-Age", "86400");
			resp.setCharacterEncoding("UTF-8");
		    resp.getWriter().println(gson.toJson(map));
		     
	     	
	     
	}
		 

}

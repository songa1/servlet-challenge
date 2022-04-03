package com.challenge;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSOutput;

import com.google.gson.Gson;


@WebServlet(name = "RegisterServlet" , urlPatterns = "/register" )
public class RegisterServlet extends HttpServlet {
	
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
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String phone = req.getParameter("phone");
			int age =Integer.parseInt(req.getParameter("age"));
			String gender = req.getParameter("gender");
			String account = req.getParameter("role");
			
			if(	username == null || 
				password == null || 
				firstname == null ||
				lastname == null || 
				gender == null || 
				phone == null || 
				account == null || 
				username.isEmpty() || 
				password.isEmpty()	||
				firstname.isEmpty() ||
				lastname.isEmpty() ||
				phone.isEmpty()||		
				gender.isEmpty()||
				account.isEmpty()
				)
			{
				
				resp.setContentType("application/json");
	            resp.setCharacterEncoding("UTF-8");
	            resp.getWriter().println(gson.toJson(new ApiResponse<Object>(400, "all field are required!!", null)));
	            return;
			}
				
			
			
			
			if (account.equals("Admin")) {
					
				Admin admin = new Admin();
				
				admin.setUsername(username);
				admin.setPassword(password);
				admin.setFirstname(firstname);
				admin.setLastname(lastname);
				admin.setAge(age);
				admin.setPhone(phone);
				admin.setGender(gender);
				
				
				String isRegistered = admin.Register();
				
				if(isRegistered =="User Registered") {
					
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().println(gson.toJson(new ApiResponse<Admin>(201, "Successfully registered an account", admin)));
					
				}
				else {
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().println(gson.toJson(new ApiResponse<Object>(400, isRegistered, null)));
					
				}
			}
			else if(account.equals("Guest")) {
				
				Guest guest = new Guest();
				
				guest.setUsername(username);
				guest.setPassword(password);
				guest.setFirstname(firstname);
				guest.setLastname(lastname);
				guest.setAge(age);
				guest.setPhone(phone);
				guest.setGender(gender);
				
				String isRegistered = guest.Register();
				
				if(isRegistered =="User Registered") {
					
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().println(gson.toJson(new ApiResponse<Guest>(201, "Successfully registered an account", guest)));
				}
				else {
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().println(gson.toJson(new ApiResponse<Object>(400, isRegistered, null)));
					
					
				}
				
			}
			else {
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().println(gson.toJson(new ApiResponse<Object>(403, "role not allowed", null)));
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(gson.toJson(new ApiResponse<Exception>(500, "Internal error", e)));
		}
		
		
	}

}

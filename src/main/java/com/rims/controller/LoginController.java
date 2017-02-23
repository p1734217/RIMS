package com.rims.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;






import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rims.Dao.impl.RegisterDaoImpl;
import com.rims.model.Roomy;

public class LoginController extends MultiActionController{
	
	
	public void test(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JSONObject obj= new JSONObject();
		obj.put("pradeep","hello pradeep");
	
		response.getWriter().print(obj);
	}
	
  	
  public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
     JSONObject job= new JSONObject();
     try
     {
        String username= request.getParameter("username");
        String password= request.getParameter("password");
        
        
        Roomy user=new Roomy();
        user.setName(username);
        user.setPassword(password);
        
        RegisterDaoImpl registerDaoImp=new RegisterDaoImpl();
       boolean flag= registerDaoImp.login(user);
        
        if(flag==true)
        	job.put("result", "success");
        else
        	job.put("result", "invalid credentials");
     }
     catch(Exception e){    
    	 System.out.println(e);
    	 job.put("result", "error");
     }
     finally{
    	 response.getWriter().print(job);
     }
  }
  //GET user name for show username in html page
  public void getUserName(HttpServletRequest request, HttpServletResponse response) throws Exception{

		JSONObject result = new JSONObject();
		try{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			result.put("name",auth.getName());//get logged in username
			
			//User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		    //result.put("username",user.getUsername());//get logged in username

		    result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "error");
		} finally {
			response.getWriter().print(result);
			response.flushBuffer();
		}

	}
	
}

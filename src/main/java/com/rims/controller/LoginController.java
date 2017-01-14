package com.rims.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

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
	
}

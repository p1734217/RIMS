/**
 * 
 */
package com.rims.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;



/**
 * @author Administrator
 * @date: 
 */
///http://localhost:8081/RestWS/user?q=getUser
public class UserController extends MultiActionController{

	public void getUser(HttpServletRequest request, HttpServletResponse response){
	
		int a=Integer.valueOf(request.getParameter("a"));
		int b=Integer.valueOf(request.getParameter("b"));
		int c=a+b;
		
		
		
		JSONObject job=new JSONObject();
		try {
			
			job.put("data","its you babau");
			job.put("sum",c);
			response.getWriter().print(job);	
		} catch (Exception e) {
			System.out.println("error"+e);
		} 
		
		finally {
			try {
				response.getWriter().print(job);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}

	public void putUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject job=new JSONObject();
		try {
			job.put("data1","I am a MCA stuent");
			job.put("data2","I am a MCA stuent");
			job.put("data3","I am a MCA stuent");
			job.put("data4","I am a MCA stuent");
			
		} catch (Exception e) {

		} 
		finally {
			response.getWriter().print(job);	
		}
	}

public void netuser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		JSONObject job=new JSONObject();
		try {
			job.put("data","I am a MCA pradeep");
			
		} catch (Exception e) {

		} finally {
			response.getWriter().print(job);	
		}
	}



}

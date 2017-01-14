package com.rims.controller;



import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.Gson;
import com.rims.Dao.RegisterDao;
import com.rims.Dao.impl.RegisterDaoImpl;
import com.rims.model.*;

public class RegisterController extends MultiActionController{
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		JSONObject obj= new JSONObject();
		JSONArray ar=new JSONArray();
		try
		{
			String uname= request.getParameter("username");
			String password= request.getParameter("password");
			String email= request.getParameter("email");
			String country= request.getParameter("country");
			String mobile1= request.getParameter("mobile1");
			String mobile2= request.getParameter("mobile2");
			String sate= request.getParameter("state");
			String pin= request.getParameter("pin");
			String gender=request.getParameter("gender");
			Roomy user=new Roomy();
			
			RegisterDao r=new RegisterDaoImpl();
			
			if(r.register(user))
			  obj.put("result","success");
			
		}catch(Exception e)
		{
			System.out.println(e);
			obj.put("result","error");
		}
	 finally
	 {
		 response.getWriter().print(obj);
	 }
	}
	 
	public void getdata(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		JSONObject obj=new JSONObject();
		
		String email1= request.getParameter("email");
		
		Roomy user= new Roomy();
		RegisterDao p=new RegisterDaoImpl();
		
		user=p.getUser(email1);
		
		obj.put("email",user.getEmail());
		response.getWriter().print(obj);		
		
		
	}
	public void Address(HttpServletRequest request, HttpServletResponse response)throws Exception{
		  JSONObject job=new JSONObject();
		  try{
			  String country=request.getParameter("country");
			  String state=request.getParameter("state");
			  String distt=request.getParameter("distt");
			  int  pincode=Integer.valueOf(request.getParameter("pincode"));
			  String others=request.getParameter("others");
			  Address add=new Address();
			  RegisterDao p=new RegisterDaoImpl();
			  if(p.address(add))
				  job.put("result","success");
				  
				  
		  }
		  catch(Exception e){
			  System.out.println(e);
				job.put("result","error");
		  }
	}
	public void Bathroom(HttpServletRequest request, HttpServletResponse response)throws Exception{
		  JSONObject job=new JSONObject();
		  try{
			  String soap=request.getParameter("soap");
			  String toothpaste=request.getParameter("toothpaste");
			  String waterHolder=request.getParameter("waterHolder");
			  Bathroom bath=new Bathroom();
			  RegisterDao p=new RegisterDaoImpl();
			  if(p.bathroom(bath))
				  job.put("result","success");				  
		  }
		  catch(Exception e){
			  System.out.println(e);
				job.put("result","error");
		  }
	}
	
	public void Room(HttpServletRequest request, HttpServletResponse response)throws Exception{
		  JSONObject job=new JSONObject();
		  try{
			  String noOfRooms=request.getParameter("noOfRooms");
			  String noOfPartner=request.getParameter("noOfPartner");
			  String kich= request.getParameter("kitchen");
			  Room kch=new Room();
			  RegisterDao p=new RegisterDaoImpl();
			  if(p.room(kch))
				  job.put("result","success");				  
		  }
		  catch(Exception e){
			  System.out.println(e);
				job.put("result","error");
		  }
	}
	public void Kitchen(HttpServletRequest request, HttpServletResponse response)throws Exception{
		  JSONObject job=new JSONObject();
		  try{
			  String spices=request.getParameter("spices");
			  String beverage=request.getParameter("beverage");
			  String vagetables=request.getParameter("vagetables");
			  String others=request.getParameter("others");
			  Kitchen kitch=new Kitchen();
			  RegisterDao p=new RegisterDaoImpl();
			  if(p.kitchen(kitch))
				  job.put("result","success");				  
		  }
		  catch(Exception e){
			  System.out.println(e);
				job.put("result","error");
		  }
	}
	public void resgisterRoomy(HttpServletRequest request, HttpServletResponse response)throws Exception{
		JSONObject job = new JSONObject();
		try {
			String name = request.getParameter("name");

			String mobileNo =request.getParameter("mobileNo");

			
			String[] mobileNos=mobileNo.split(",");
			long[] contacts = new long[mobileNos.length];

			int index = 0;
			for (String no : mobileNos) {
				contacts[index++] = Long.valueOf(no);
			}
			for(int i=0;i<contacts.length;i++)
				System.out.println(i+"-th No: "+contacts[i]);
			

/*			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String gender = request.getParameter("gender");
			Roomy romy = new Roomy();
			RegisterDao p = new RegisterDaoImpl();
			if (p.roomy(romy))
				job.put("result", "success");
*/		
			Gson gson=new Gson();
			//Address address=gson.fromJson(json,Address.class);
			
			job.put("result", "success");
		} catch (Exception e) {
			System.out.println(e);
			job.put("result", "error");
		}
		finally{
		 response.getWriter().print(job);	
		}
		}
	
}


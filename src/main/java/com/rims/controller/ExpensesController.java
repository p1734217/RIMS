/**
 * 
 */
package com.rims.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.JsonObject;
import com.rims.Dao.RegisterDao;
import com.rims.Dao.impl.RegisterDaoImpl;
import com.rims.model.ExpensesModel;

/**
 * @author Administrator
 *
 */
public class ExpensesController extends MultiActionController {
	private static final Log logger = LogFactory.getLog(ExpensesController.class);

	//This function have wrote to fetch all row data from db into table(total expenses) 
	public void expenses(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		ArrayList<ExpensesModel> resultList = new ArrayList<ExpensesModel>();
		JSONObject obj;// =new JSONObject();
		RegisterDao user1 = new RegisterDaoImpl();
		ExpensesModel p = new ExpensesModel();
		resultList = user1.expenses();

		/*
		 * JSONArray ja = new JSONArray(); for (ExpensesModel emp : resultList)
		 * { obj=new JSONObject(); obj.put("id", emp.getId()); obj.put("person",
		 * emp.getPerson()); obj.put("itemname", emp.getItemname());
		 * obj.put("price", emp.getPrice()); obj.put("category",
		 * emp.getCategory()); ja.add(obj); }
		 */

		// res.getWriter().print(ja);
		System.out.println("p objects " + resultList);
		JSONArray ja = new JSONArray();
		for (ExpensesModel emp : resultList) {
			obj = new JSONObject();
			obj.put("id", emp.getId());
			obj.put("person", emp.getPerson());
			obj.put("itemname", emp.getItemname());
			obj.put("price", emp.getPrice());
			obj.put("category", emp.getCategory());
			ja.add(obj);

		}
		res.getWriter().print(ja);

	}
// this function for search table in all expenses
	public void search(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String key = req.getParameter("key");
		logger.error("addDialPlan : Unable to add dial plan : ");
		ArrayList<ExpensesModel> resultList = new ArrayList<ExpensesModel>();
		JSONObject obj;
		RegisterDao user1 = new RegisterDaoImpl();
		ExpensesModel p = new ExpensesModel();
		resultList = user1.search(key);

		JSONArray ja = new JSONArray();
		System.out.println("from browser " + key);
		for (ExpensesModel emp : resultList) {
			obj = new JSONObject();
			obj.put("id", emp.getId());
			obj.put("person", emp.getPerson());
			obj.put("itemname", emp.getItemname());
			obj.put("price", emp.getPrice());
			obj.put("category", emp.getCategory());
			ja.add(obj);
		}
		System.out.println("this is search " + ja);
		res.getWriter().print(ja);
	}

	// this function add data into expenses table

	public void adddata(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String rname = req.getParameter("rname");
		String itemname = req.getParameter("iname");
		int price = Integer.valueOf(req.getParameter("price"));
		String category = req.getParameter("category");
		ExpensesModel user = new ExpensesModel();
		user.setCategory(category);
		user.setItemname(itemname);
		user.setPerson(rname);
		user.setPrice(price);
		JSONObject obj = new JSONObject();
		try {
			RegisterDao r = new RegisterDaoImpl();
			if (r.addexpendata(user)) {
				obj.put("result", "success");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			obj.put("result", e.toString());
		}

		finally {
			res.getWriter().print(obj);
		}
	}

	// this function get data from database for update
	public void update(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		/*
		 * int id=Integer.valueOf(req.getParameter("id")); JSONObject job=new
		 * JSONObject(); RegisterDao r = new RegisterDaoImpl(); try{
		 * if(r.update(id)){ job.put("result", "success"); } }catch(Exception
		 * e){ job.put("result", e.toString()); } finally {
		 * res.getWriter().print(job); }
		 */

		int id = Integer.valueOf(req.getParameter("id"));
		System.out.println("kya ho rha  browser " + id);
		ArrayList<ExpensesModel> resultList = new ArrayList<ExpensesModel>();
		JSONObject obj = new JSONObject();
		RegisterDao user1 = new RegisterDaoImpl();
		ExpensesModel p = new ExpensesModel();
		resultList = user1.update(id);

		JSONArray ja = new JSONArray();
		System.out.println("from browser " + id);
		for (ExpensesModel emp : resultList) {
			obj = new JSONObject();
			obj.put("id", emp.getId());
			obj.put("person", emp.getPerson());
			obj.put("itemname", emp.getItemname());
			obj.put("price", emp.getPrice());
			obj.put("category", emp.getCategory());
			ja.add(obj);
		}
	
		res.getWriter().print(obj);
	}

	/* this fuction work updated data to save into database */
	public void updateaddexpen(HttpServletRequest req, HttpServletResponse res)	throws Exception {
		JSONObject obj = new JSONObject();
		int id = Integer.valueOf(req.getParameter("id"));
	
		String rname = req.getParameter("rname");
		String iname = req.getParameter("iname");
		int price = Integer.valueOf(req.getParameter("price"));
		String category = req.getParameter("category");
		ExpensesModel user = new ExpensesModel();
		user.setId(id);
		user.setCategory(category);
		user.setItemname(iname);
		user.setPerson(rname);
		user.setPrice(price);
		
		System.out.println("hii raja"+user);
		try {
			RegisterDao r = new RegisterDaoImpl();
			if (r.updateexpendata(user)) {
				obj.put("result", "success");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			obj.put("result", e.toString());
		}

		finally {
			res.getWriter().print(obj);
		}
	}

	public void delete(HttpServletRequest req, HttpServletResponse res) throws Exception {

		JSONObject obj = new JSONObject();
		try {
			//System.out.println("before split===>"+req.getParameterValues("selected"));
		String id =req.getParameter("selected");
		System.out.println("\n\nids to be deleted:-"+id+"\n\n\n");
		String[] ids=new String[id.length()];
		//for(int i=0;i<id.length();i++){
			ids=id.split(",");
		//}
		
		System.out.println("\n\nAfter split ids to be deleted:-"+ids+"\n\n\n");
		
			RegisterDao rs = new RegisterDaoImpl();
			
			if (rs.delete(ids))
				obj.put("result", "success");

		} catch (Exception e) {
			obj.put("result", e.toString());
			e.printStackTrace();
		
		}finally {
			res.getWriter().print(obj);
		}		
		
		
		
		
	}
}
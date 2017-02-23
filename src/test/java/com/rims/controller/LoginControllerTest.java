package com.rims.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LoginControllerTest {
                 MockHttpServletRequest req=new MockHttpServletRequest();
                 MockHttpServletResponse res=new MockHttpServletResponse();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTest() {
		//fail("Not yet implemented");
	}

	/*@Test
	public void testLogin() throws Exception {
		LoginController  loginController=new LoginController();
		req.setParameter("username", "bijendra");
		req.setParameter("password", "bij");
		loginController.login(req, res);
		System.out.println(res.getContentAsString());
	}*/
	/*@Test
	public void search() throws Exception{
		ExpensesController ex=new ExpensesController();
		req.setParameter("key", "pradeep ");
		ex.search(req, res);
		System.out.println(res.getContentAsString());
	}
	*/
	/*@Test
	public void update1() throws Exception{
		ExpensesController ex=new ExpensesController();
		req.setParameter("id", "4");
		ex.update(req, res);
		System.out.println(res.getContentAsString());
	}*/
	/*@Test
	public void update1() throws Exception{
		ExpensesController ex=new ExpensesController();
		req.setParameter("id", "5");
		req.setParameter("person", "deepu");
		req.setParameter("itemname", "pila");
		req.setParameter("price", "500");
		req.setParameter("category", "loboss");
		ex.updateaddexpen(req, res);
		System.out.println(res.getContentAsString());
	}*/
	
	@Test
	public void deleteTest() throws Exception {

		ExpensesController ex = new ExpensesController();
		req.setParameter("selected", new String[] {"5","6","7"});
		
		ex.delete(req, res);
		
		System.out.println(res.getContentAsString());

	}
}

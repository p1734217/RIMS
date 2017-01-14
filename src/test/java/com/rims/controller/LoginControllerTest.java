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
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() throws Exception {
		LoginController  loginController=new LoginController();
		req.setParameter("username", "bijendra");
		req.setParameter("password", "bij");
		loginController.login(req, res);
		System.out.println(res.getContentAsString());
	}

}

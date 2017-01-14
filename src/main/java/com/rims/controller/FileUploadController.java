/**
 * 
 */
package com.rims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.rims.model.UploadFile;

/**
 * @author Administrator
 *
 */
public class FileUploadController extends AbstractCommandController  {

	UploadFile ufile = new UploadFile();; 
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object arg2, BindException arg3) throws Exception {

		String type = (String) request.getParameter("fileType");
		
		MultipartFile file = null;
		
		return null;
	}

}

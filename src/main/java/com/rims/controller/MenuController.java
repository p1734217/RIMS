package com.rims.controller;

import java.util.Collection;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.rims.model.ModelUser;

public class MenuController  extends MultiActionController {
}

	/*
    public ModelAndView getMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	JSONObject jsonResponse = new JSONObject();
        try {

            	ModelUser user = new ModelUser();

            	JSONObject object = new JSONObject();
            	// Get User information
            	boolean isAdmin = false;
            	boolean isSupervisor = false;
        
            			isAdmin = true;
            		
           
            			isSupervisor = true;
            			isAdmin = true;
            			
            		
            	object.put("isAdminUser", isAdmin);
            	object.put("isSupervisor", isSupervisor);
            	object.put("name", user.getName());
            	if (!isAdmin || !isSupervisor) {
            		object.put("callCenterName", user.getCenterName());
            	}
            	jsonResponse.put("userDetail", object);
            	//response.getWriter().print(new JSONObject().put("userDetail", object));

            	// Get Main menu
            	jsonResponse.put("mainMenu", getMainMenu(user, request));

            	// Sub-menu for admin
            	String subMenu = (String)request.getAttribute("subMenu");
            	if(StringUtils.isNotEmpty(subMenu)) {
        			if(subMenu.equalsIgnoreCase("getAccountSubMenu")) {
        				jsonResponse.put("AccountSubMenu", getAccountSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getRouteAndNumberSubMenu")) {
        				jsonResponse.put("RouteAndNumberSubMenu", getRouteAndNumberSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getNumberSubMenu")) {
        				jsonResponse.put("NumberSubMenu", getNumberSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getServerSubMenu")) {
        				jsonResponse.put("ServerSubMenu", getServerSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getConfigurationSubMenu")) {
        				jsonResponse.put("ConfigurationSubMenu", getConfigurationSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getTicketSubMenu")) {
        				jsonResponse.put("TicketSubMenu", getSupportSubMenu(user, request));
        			}
        			if(subMenu.equalsIgnoreCase("getClientUploadMenu")) {
        				jsonResponse.put("ClientSubMenu", getClientSubMenu(user, request));
        			}
    			}
            

            // Get Footer Settings
            jsonResponse.put("footer", getFooterSettings());

            jsonResponse.put("product" ,getProductSettings());

    		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
				Cookie cookie = new Cookie("lang", deploymentProperties.getProperty("default.language"));
			    cookie.setMaxAge(-1);
			    cookie.setPath("/");
			    response.addCookie(cookie);
			    getMethodNameResolver().setLocale(request, response, new Locale(deploymentProperties.getProperty("default.language")));
			}

            response.getWriter().print(jsonResponse);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error("StackTrace " , e);
            jsonResponse.put("error", "Error");
        }
        return null;
    }

    private JSONObject getMainMenu(ModelUser user, HttpServletRequest request) throws Exception {
    	boolean isAdmin = false;
    	boolean isSupervisor = false;
    	JSONObject jsonObj = new JSONObject();
    	Collection<GrantedAuthority> grantedAuthoritys = user.getAuthorities();
    	for(GrantedAuthority grantedAuthority : grantedAuthoritys) {
    		if ("ROLE_ADMINISTRATOR".equals(grantedAuthority.getAuthority()) || "ROLE_SUPERVISOR".equals(grantedAuthority.getAuthority())) {
    			jsonObj.put("ADMIN_ACCOUNT", "changecenter.html");
    			if ("ROLE_ADMINISTRATOR".equals(grantedAuthority.getAuthority()) || user.isAdministrator()){
    				jsonObj.put("ADMIN_ROUTE_AND_NUMBER", "dial_plan.html");
        			jsonObj.put("ADMIN_SERVER", "servers.html");
        			jsonObj.put("ADMIN_CONFIGURATION", "deployment_config.html");
        			jsonObj.put("ADMIN_EMAIL_CONFIGURATION", "eamil_config.html");
        			jsonObj.put("ADMIN_TICKET", "ticket.html");
        			jsonObj.put("ADMIN_CLIENT_RELEASE","existing_client.html");
    			}
    			isAdmin = true;
    			isSupervisor = true;
    			break;
    		}
    	}
    	return jsonObj;
    }
    public JSONArray getAccountSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
        JSONArray jsonArray = new JSONArray();
        JSONArray menuArray = new JSONArray();
        if (user.isAdministrator()) {

            menuArray = new JSONArray();
            menuArray.add("Agents");
            menuArray.add("submenu.agents");
            menuArray.add("agents.html");
            jsonArray.add(menuArray);

        	menuArray = new JSONArray();
            menuArray.add("Supervisors");
            menuArray.add("submenu.supervisors");
            menuArray.add("cc_supervisors.html");
            jsonArray.add(menuArray);

            menuArray = new JSONArray();
            menuArray.add("Manage Access");
            menuArray.add("submenu.manageaccess");
            menuArray.add("manageaccess.html");
            jsonArray.add(menuArray);

            if (Boolean.valueOf(deploymentProperties.getProperty("installation.email.enabled"))) {
	            menuArray = new JSONArray();
				menuArray.add("Contact Owners");
				menuArray.add("submenuController.conadmin");
				menuArray.add("contact_administrators.html");
				jsonArray.add(menuArray);
            }

            menuArray = new JSONArray();
            menuArray.add("Create Account");
            menuArray.add("submenu.createAcc");
            menuArray.add("admin_register.html");
            jsonArray.add(menuArray);
        }
        if(user.isAdministrator() || user.isSupervisor()){
        	menuArray = new JSONArray();
            menuArray.add("Admin Profile");
            menuArray.add("submenu.adminprofile");
            menuArray.add("adminprofile.html");
            jsonArray.add(menuArray);

            menuArray = new JSONArray();
            menuArray.add("Switch Account");
            menuArray.add("submenu.switchAcc");
            menuArray.add("changecenter.html");
            jsonArray.add(menuArray);
        }

        return jsonArray;
    }
    public JSONArray getRouteAndNumberSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
    	JSONArray jsonArray = new JSONArray();
    	if(user.isAdministrator()){
    		JSONArray menuArray = new JSONArray();

    		menuArray = new JSONArray();
    		menuArray.add("Route Assignment");
    		menuArray.add("submenu.route.assign");
    		menuArray.add("route_assign.html");
    		jsonArray.add(menuArray);

    		menuArray = new JSONArray();
    		menuArray.add("Routes");
    		menuArray.add("submenu.routes");
    		menuArray.add("route.html");
    		jsonArray.add(menuArray);

    		menuArray = new JSONArray();
    		menuArray.add("Numbers");
    		menuArray.add("submenu.numbers");
    		menuArray.add("numbers.html");
    		jsonArray.add(menuArray);

    		menuArray = new JSONArray();
    		menuArray.add("Line Groups");
    		menuArray.add("submenu.linegroups");
    		menuArray.add("line_group.html");
    		jsonArray.add(menuArray);

    		menuArray = new JSONArray();
    		menuArray.add("Lines");
    		menuArray.add("submenu.lines");
    		menuArray.add("line.html");
    		jsonArray.add(menuArray);

    		menuArray = new JSONArray();
    		menuArray.add("Dial Plans");
    		menuArray.add("submenu.dialplans");
    		menuArray.add("dial_plan.html");
    		jsonArray.add(menuArray);
    	}
    	return jsonArray;
    }


    public JSONArray getNumberSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
    	JSONArray jsonArray = new JSONArray();
    	if(user.isAdministrator()){
    		JSONArray menuArray = new JSONArray();

    		menuArray = new JSONArray();
    		menuArray.add("Numbers");
    		menuArray.add("submenu.numbers");
    		menuArray.add("numbers.html");
    		jsonArray.add(menuArray);
    	}
    	return jsonArray;
    }

    public JSONArray getServerSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (user.isAdministrator()) {
            JSONArray menuArray = new JSONArray();

            menuArray = new JSONArray();
            menuArray.add("SerServers");
            menuArray.add("submenu.serservers");
            menuArray.add("ser_servers.html");
            jsonArray.add(menuArray);

            menuArray = new JSONArray();
            menuArray.add("Servers");
            menuArray.add("submenu.servers");
            menuArray.add("servers.html");
            jsonArray.add(menuArray);

        }

        return jsonArray;
    }
    public JSONArray getConfigurationSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (user.isAdministrator()) {
            JSONArray menuArray = new JSONArray();

//            menuArray = new JSONArray();
//            menuArray.add("Default Media Config");
//            menuArray.add("submenu.defaultmediaconfig");
//            menuArray.add("media_config.html");
//            jsonArray.add(menuArray);menuArray = new JSONArray();

            menuArray.add("Email Config");
			menuArray.add("submenu.emailconfig");
			menuArray.add("email_config.html");
			jsonArray.add(menuArray);

            menuArray = new JSONArray();
			menuArray.add("Deployment Config");
			menuArray.add("submenu.deploymentconfig");
			menuArray.add("deployment_config.html");
			jsonArray.add(menuArray);

        }

        return jsonArray;
    }

    public JSONArray getSupportSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
        JSONArray jsonArray = new JSONArray();
        if (user.isAdministrator()) {
            JSONArray menuArray = new JSONArray();

            menuArray = new JSONArray();
            menuArray.add("Language");
            menuArray.add("submenu.language");
            menuArray.add("language.html");
            jsonArray.add(menuArray);

            menuArray = new JSONArray();
            menuArray.add("Diagnostics");
            menuArray.add("submenu.diagnostics");
            menuArray.add("diagnostics.html");
            jsonArray.add(menuArray);


            menuArray = new JSONArray();
            menuArray.add("Orders");
            menuArray.add("submenu.orders");
            menuArray.add("order_list.html");
            jsonArray.add(menuArray);

            menuArray = new JSONArray();
            menuArray.add("Ticket");
            menuArray.add("submenu.ticket");
            menuArray.add("ticket.html");
            jsonArray.add(menuArray);
        }

        return jsonArray;
    }

    public JSONArray getClientSubMenu(ModelUser user, HttpServletRequest request) throws Exception {
    	JSONArray jsonArray = new JSONArray();
    	if(user.isAdministrator()){
    		JSONArray menuArray = new JSONArray();

    		menuArray = new JSONArray();
    		menuArray.add("Existing Client");
    		menuArray.add("submenu.existclient");
    		menuArray.add("existing_client.html");
    		jsonArray.add(menuArray);
    	}
    	return jsonArray;
    }

	 // Get Footer Setting
    private JSONObject getFooterSettings() {
        JSONObject object = new JSONObject();
        object.put("aboutusURL", deploymentProperties.getProperty("installation.aboutus.url"));
        object.put("contactusURL", deploymentProperties.getProperty("installation.contactus.url"));
        object.put("privacyURL", deploymentProperties.getProperty("installation.privacy.url"));
        object.put("termsURL", deploymentProperties.getProperty("installation.terms.url"));
        object.put("copyright", deploymentProperties.getProperty("installation.company.name"));
        return object;
    }
    
    private JSONObject getProductSettings() {

		// Get Product Setting
		JSONObject object = new JSONObject();
		object.put("name", deploymentProperties.getProperty("installation.name"));
		String logo = deploymentProperties.getProperty("installation.logo");
		if(StringUtils.isNotEmpty(logo)) {
			logo = logo.replaceFirst("http://", "").replaceFirst("https://", "");
		}
		object.element("imageName", (Boolean.valueOf(deploymentProperties.getProperty("installation.domain.secured", "false"))?"https://":"http://") + logo);
		object.put("nameCode", StringEscapeUtils.escapeHtml4(deploymentProperties.getProperty("installation.name")));
		object.put("nameUpper", StringUtils.upperCase(deploymentProperties.getProperty("installation.name")));
		object.put("mainServerIp", deploymentProperties.getProperty("installation.domain"));
		object.put("mainServerContext", deploymentProperties.getProperty("installation.context"));
		object.put("path", (Boolean.valueOf(deploymentProperties.getProperty("installation.domain.secured", "false"))?"https://":"http://") + deploymentProperties.getProperty("installation.domain") + deploymentProperties.getProperty("installation.context"));
		object.put("companyName", deploymentProperties.getProperty("installation.company.name"));
		object.put("companyAddress", deploymentProperties.getProperty("installation.company.address"));
		object.put("supportPhone", deploymentProperties.getProperty("installation.company.phone"));
		object.put("companyHome", deploymentProperties.getProperty("installation.company.url"));
		object.put("supportEmail", deploymentProperties.getProperty("installation.company.email"));

		return object;
    }

	private Properties deploymentProperties;
    public void setDeploymentProperties(Properties deploymentProperties) {
		this.deploymentProperties = deploymentProperties;
	}

}
*/
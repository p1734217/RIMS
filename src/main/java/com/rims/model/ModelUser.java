/*
 * Copyright 2006 3CLogic, Inc. All rights reserved.
 * 3CLogic PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.rims.model;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class ModelUser {

    private long id;
    private String name;
    private long centerId = -1;
    private String centerName = "";
    private String language = "";
    private long entityId;
    private String authToken;
	
	private boolean administrator;
	private boolean owner;
	private boolean supervisor;
	
	private String passwordExpire;
	private boolean firstLogin;
		

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerid) {
        this.centerId = centerid;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
    
    public void setLanguage(String language) {
		this.language = language;
	}
    
    public String getLanguage() {
		return language;
	}
    
    public boolean isAdministrator() {
		return administrator;
	}

	public boolean isOwner() {
		return owner;
	}

	public boolean isSupervisor() {
		return supervisor;
	}
	
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}
	
	public String getAuthToken() {
		return authToken;
	}
	
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getPasswordExpire() {
		return passwordExpire;
	}

	public void setPasswordExpire(String passwordExpire) {
		this.passwordExpire = passwordExpire;
	}

	public boolean isFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	
	public JSONObject toJSONObject() {
		
        JSONObject value = new JSONObject();
        
        value.put("id", id);
        value.put("name", name);
        value.put("username"," getUsername()");
        value.put("password", "");
        value.put("enabled", true);
        value.put("centerId", centerId);
        value.put("entityId", entityId);
        value.put("language", language);
        value.put("passwordExpire", passwordExpire);
        value.put("isFirst", firstLogin);
        value.put("centerName", centerName);
        value.put("authorities", "jsonGrantedAuthorityArray");
        value.put("accountNonExpired", "isAccountNonExpired()");
        value.put("credentialsNonExpired", "isCredentialsNonExpired()");
        value.put("accountNonLocked", "isAccountNonLocked()");

        return value;
    }
}

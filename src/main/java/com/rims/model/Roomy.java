/**
 * 
 */
package com.rims.model;

import java.util.Arrays;

/**
 * @author Administrator
 *
 */
public class Roomy {

	private String name;
	private int mobileNo1;
	private int mobileNo2;
	private String password;
	private String email;
	private Address address;
	private String gender;
	
	public Integer getMobileNo1() {
		return mobileNo1;
	}
	public String getName() {
		return name;
	}
	public void setMobileNo1(Integer mobileNo1) {
		this.mobileNo1 = mobileNo1;
	}
	public Integer getMobileNo2() {
		return mobileNo2;
	}
	public void setMobileNo2(Integer mobileNo2) {
		this.mobileNo2 = mobileNo2;
	}
	@Override
	public String toString() {
		return "Roomy [name=" + name + ", mobileNo1=" + mobileNo1
				+ ", mobileNo2=" + mobileNo2 + ", password=" + password
				+ ", email=" + email + ", address=" + address + ", gender="
				+ gender + ", designation=" + designation + "]";
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	private String designation;

}

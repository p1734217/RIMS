/**
 * 
 */
package com.rims.model;

/**
 * @author Administrator
 *
 */
public class Address {

	private String country;
	private String state;
	private String distt;
	private int pincode;
	private String others;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Address [country=" + country + ", state=" + state + ", distt="
				+ distt + ", pincode=" + pincode + ", others=" + others + "]";
	}
	public String getDistt() {
		return distt;
	}
	public void setDistt(String distt) {
		this.distt = distt;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
}

/**
 * 
 */
package com.rims.model;

/**
 * @author Administrator
 *
 */
public class Bathroom {

	private String soap;
	@Override
	public String toString() {
		return "Bathroom [soap=" + soap + ", toothpaste=" + toothpaste
				+ ", waterHolder=" + waterHolder + "]";
	}
	public String getSoap() {
		return soap;
	}
	public void setSoap(String soap) {
		this.soap = soap;
	}
	public String getToothpaste() {
		return toothpaste;
	}
	public void setToothpaste(String toothpaste) {
		this.toothpaste = toothpaste;
	}
	public String getWaterHolder() {
		return waterHolder;
	}
	public void setWaterHolder(String waterHolder) {
		this.waterHolder = waterHolder;
	}
	private String toothpaste;
	private String waterHolder;
	
}

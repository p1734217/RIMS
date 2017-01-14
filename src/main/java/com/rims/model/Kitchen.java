/**
 * 
 */
package com.rims.model;

/**
 * @author Administrator
 *
 */
public class Kitchen {

	private String spices;
	private String beverage;
	private String vagetables;
	private String others;
	public String getSpices() {
		return spices;
	}
	public void setSpices(String spices) {
		this.spices = spices;
	}
	@Override
	public String toString() {
		return "Kitchen [spices=" + spices + ", beverage=" + beverage
				+ ", vagetables=" + vagetables + ", others=" + others + "]";
	}
	public String getBeverage() {
		return beverage;
	}
	public void setBeverage(String beverage) {
		this.beverage = beverage;
	}
	public String getVagetables() {
		return vagetables;
	}
	public void setVagetables(String vagetables) {
		this.vagetables = vagetables;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
	
	
}

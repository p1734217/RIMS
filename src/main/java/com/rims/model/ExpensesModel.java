/**
 * 
 */
package com.rims.model;

/**
 * @author Administrator
 *
 */
public class ExpensesModel {
	
	private String person;
	private String itemname;
	private int price;
	private String category;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ExpensesModel [person=" + person + ", itemname=" + itemname
				+ ", price=" + price + ", category=" + category + ", id=" + id
				+ "]";
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price2) {
		this.price = price2;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}

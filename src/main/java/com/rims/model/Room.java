/**
 * 
 */
package com.rims.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5305424995330126598L;
	
	private int noOfRooms;
	private int noOfPartner;
	private Kitchen kitchen;
	
	@Override
	public String toString() {
		return "Room [noOfRooms=" + noOfRooms + ", noOfPartner=" + noOfPartner
				+ ", kitchen=" + kitchen + ", bathroom=" + bathroom
				+ ", roomNo=" + roomNo + ", address=" + address + "]";
	}
	public int getNoOfRooms() {
		return noOfRooms;
	}
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	public int getNoOfPartner() {
		return noOfPartner;
	}
	public void setNoOfPartner(int noOfPartner) {
		this.noOfPartner = noOfPartner;
	}
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}
	public Bathroom getBathroom() {
		return bathroom;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((bathroom == null) ? 0 : bathroom.hashCode());
		result = prime * result + ((kitchen == null) ? 0 : kitchen.hashCode());
		result = prime * result + noOfPartner;
		result = prime * result + noOfRooms;
		result = prime * result + roomNo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bathroom == null) {
			if (other.bathroom != null)
				return false;
		} else if (!bathroom.equals(other.bathroom))
			return false;
		if (kitchen == null) {
			if (other.kitchen != null)
				return false;
		} else if (!kitchen.equals(other.kitchen))
			return false;
		if (noOfPartner != other.noOfPartner)
			return false;
		if (noOfRooms != other.noOfRooms)
			return false;
		if (roomNo != other.roomNo)
			return false;
		return true;
	}
	public void setBathroom(Bathroom bathroom) {
		this.bathroom = bathroom;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	private Bathroom bathroom;
	private int roomNo;
	private Address address;
}

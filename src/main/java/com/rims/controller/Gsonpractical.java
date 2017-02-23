package com.rims.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.rims.model.Kitchen;
import com.rims.model.Room;

public class Gsonpractical {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		
		Kitchen k=new Kitchen();
		k.setBeverage("jhgjhg");
		k.setOthers("sdkjbkj");
		k.setSpices("sdkhfk");
		k.setVagetables("sdkfgk");
	
		
		Room rm = new Room();
		rm.setNoOfRooms(104);
		rm.setNoOfPartner(52);
		rm.setRoomNo(19);
		rm.setKitchen(k);
		  
		String t=gson.toJson(rm);
		String  a=gson.toJson(rm);

		String x=gson.toJson(rm);
	   Room y=gson.fromJson(x, Room.class);
        System.out.println("hii :  "+x);
        System.out.println("Beverage "+y.getKitchen().getBeverage()+ "Spices :"+y.getKitchen().getSpices()+ "Room No :"+y.getRoomNo());
	}

}

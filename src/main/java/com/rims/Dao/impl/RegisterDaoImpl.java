/**
 * 
 */
package com.rims.Dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;

import com.rims.Dao.RegisterDao;
import com.rims.model.Address;
import com.rims.model.Bathroom;
import com.rims.model.Kitchen;
import com.rims.model.Room;
import com.rims.model.Roomy;

/**
 * @author Administrator
 *
 */
public class RegisterDaoImpl implements RegisterDao{

	

	public Roomy getUser(String email) throws Exception {

				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu", "root", "root");
		
				Statement stmt = con.createStatement();
				String sql="select * from deepu.register where email ='"+ email+"'";
				ResultSet rs = stmt.executeQuery(sql);
				Roomy user = new Roomy();

				while(rs.next()){
					
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					System.out.println("User: "+user);
					
					return user;
				}
		return user;
	}
	public boolean login(Roomy rmy) throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","root","root");
		Statement stmt=con.createStatement();
		String username=rmy.getName();
		String password=rmy.getPassword();
		 ResultSet rs=stmt.executeQuery("select * from register where username='" + username + "' and password='" +password + "'");
		 boolean flag=false;
	        while(rs.next()){
	        	
	        	String s=rs.getString("username");
	        	String str=rs.getString("password");
	        	
	        	if(s.equalsIgnoreCase(username) && str.equalsIgnoreCase(password)){
	        		flag=true;
	        	}
	        } 
		return flag;
		
	}
public boolean register(Roomy user) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","root","root");  
		  
		PreparedStatement ps=con.prepareStatement("insert into register values(?,?,?,?)");  
		ps.setString(1,user.getName());
		ps.setString(2,user.getPassword());  
		ps.setString(3,user.getEmail());
		          
		if(ps.executeUpdate()>0)  
			return true;
		
		return false;
	}

public boolean address(Address addr) throws  Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","rooot","root");
	PreparedStatement ps=con.prepareStatement("insert into address value(?,?,?,?)");
	ps.setString(1, addr.getCountry());
	ps.setString(2, addr.getState());
	ps.setString(3, addr.getDistt());
	ps.setInt(4, addr.getPincode());
	ps.setString(5, addr.getOthers());
	if(ps.executeUpdate()>0)
	return true; 
	return false;
}

public boolean bathroom(Bathroom bath) throws  Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","rooot","root");
	PreparedStatement ps=con.prepareStatement("insert into bathroom value(?,?,?,?)");
	ps.setString(1, bath.getSoap());
	ps.setString(2, bath.getToothpaste());
	ps.setString(3, bath.getWaterHolder());
	if(ps.executeUpdate()>0)
	return true;
	return false;
}
public boolean kitchen(Kitchen kitch) throws  Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","rooot","root");
	PreparedStatement ps=con.prepareStatement("insert into kitchen value(?,?,?,?)");
	ps.setString(1, kitch.getSpices());
	ps.setString(2, kitch.getBeverage());
	ps.setString(3, kitch.getVagetables());
	ps.setString(4, kitch.getOthers());
	if(ps.executeUpdate()>0)
	return true;
	else 
	return 	false;
}
public boolean room(Room rum) throws  Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","root","root");
	PreparedStatement ps=con.prepareStatement("insert into room value(?,?,?,?)");
	ps.setInt(1, rum.getRoomNo());
	ps.setInt(2, rum.getNoOfRooms());
	ps.setInt(3, rum.getNoOfPartner());
	if(ps.executeUpdate()>0)
	return true;
	else 
	return 	false;
	
}
public boolean roomy(Roomy rmy) throws  Exception{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/deepu","root","root");
	PreparedStatement ps=con.prepareStatement("insert into roomy value(?,?,?,?,?,?)");
    ps.setString(1, rmy.getDesignation());
    ps.setString(2, rmy.getEmail());
    ps.setString(3, rmy.getGender());
    ps.setString(4, rmy.getName());
    ps.setString(5, rmy.getMobileNo1().toString());
    ps.setString(6, rmy.getMobileNo2().toString());
    //ps.setString(7, rmy.getAddress());
	
	return true;
}

}

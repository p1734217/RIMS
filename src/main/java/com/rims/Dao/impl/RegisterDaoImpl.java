/**
 * 
 */
package com.rims.Dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.rims.Dao.RegisterDao;
import com.rims.model.Address;
import com.rims.model.Bathroom;
import com.rims.model.ExpensesModel;
import com.rims.model.Kitchen;
import com.rims.model.Room;
import com.rims.model.Roomy;

/**
 * @author Administrator
 *
 */
public class RegisterDaoImpl implements RegisterDao {

	public Roomy getUser(String email) throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "root", "root");

		Statement stmt = con.createStatement();
		String sql = "select * from deepu.register where email ='" + email
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		Roomy user = new Roomy();

		while (rs.next()) {

			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			System.out.println("User: " + user);

			return user;
		}
		return user;
	}
	
	// all expenses show into table

	public ArrayList<ExpensesModel> expenses() throws Exception {

		ArrayList<ExpensesModel> expen = new ArrayList<ExpensesModel>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			Statement stmt = con.createStatement();
			String sql = "select * from rims.expenses";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExpensesModel exm = new ExpensesModel();
				exm.setId(rs.getInt("id"));
				exm.setCategory(rs.getString("category"));
				exm.setItemname(rs.getString("itemname"));
				exm.setPerson(rs.getString("person"));
				exm.setPrice(rs.getInt("price"));
				expen.add(exm);
				exm = null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return expen;

	}
// for searching into expenses table
	public ArrayList<ExpensesModel> search(String key) throws Exception {

		ArrayList<ExpensesModel> expen = new ArrayList<ExpensesModel>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			Statement stmt = con.createStatement();
			String sql = "select * from rims.expenses where person= '" + key
					+ "' OR itemname = '" + key + "' OR  price='" + key
					+ "' OR category='" + key + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExpensesModel exm = new ExpensesModel();
				exm.setId(rs.getInt("id"));
				exm.setCategory(rs.getString("category"));
				exm.setItemname(rs.getString("itemname"));
				exm.setPerson(rs.getString("person"));
				exm.setPrice(rs.getInt("price"));
				expen.add(exm);
				exm = null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return expen;

	}
//add data into expenses table
	public boolean addexpendata(ExpensesModel ex) throws Exception {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			ps = con.prepareStatement("insert into rims.expenses value(default,?,?,?,?)");

			ps.setString(1, ex.getPerson());
			ps.setString(2, ex.getItemname());
			ps.setInt(3, ex.getPrice());
			ps.setString(4, ex.getCategory());
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}
// after updated data save into table
	public boolean updateexpendata(ExpensesModel ex) throws Exception {

		Statement stmt=null;
		String sql=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			 stmt = con.createStatement();
			 sql ="update rims.expenses set person= '" +ex.getPerson()+ "', itemname='" +ex.getItemname()+ "',price='" + ex.getPrice()+ "',category='"
				+ ex.getCategory() + "'where id='" + ex.getId()+"'";


System.out.println(sql);

		} catch (Exception e) {

			e.printStackTrace();
		}
		if (stmt.executeUpdate(sql) > 0)
			return true;
		else
			return false;
	}
	//fetch data for update expense
	public ArrayList<ExpensesModel> update(Integer id) throws Exception {
		ArrayList<ExpensesModel> expen = new ArrayList<ExpensesModel>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			Statement stmt = con.createStatement();
			String sql = "select * from rims.expenses where id= '" + id + "' ";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ExpensesModel exm = new ExpensesModel();
				exm.setId(rs.getInt("id"));
				exm.setCategory(rs.getString("category"));
				exm.setItemname(rs.getString("itemname"));
				exm.setPerson(rs.getString("person"));
				exm.setPrice(rs.getInt("price"));
				expen.add(exm);
				exm = null;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return expen;
	}
	//delete expenses row
	public boolean delete(String[] strArray) throws Exception {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rims", "root", "root");
			Statement stmt = con.createStatement();

			String pat = "(";

			for (int i = 0; i < strArray.length - 1; i++) {
				pat += "'" + strArray[i] + "',";
			}
			pat += "'" + strArray[strArray.length - 1] + "')";

			System.out.println("Query:-\n" + pat);

			String sql = "delete from rims.expenses where id in" + pat;
			System.out.println("sql:-" + sql);
			stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	//login roomie
	public boolean login(Roomy rmy) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "root", "root");
		Statement stmt = con.createStatement();
		String username = rmy.getName();
		String password = rmy.getPassword();
		ResultSet rs = stmt
				.executeQuery("select * from register where username='"
						+ username + "' and password='" + password + "'");
		boolean flag = false;
		while (rs.next()) {

			String s = rs.getString("username");
			String str = rs.getString("password");

			if (s.equalsIgnoreCase(username) && str.equalsIgnoreCase(password)) {
				flag = true;
			}
		}
		return flag;

	}

	/*
	 * public boolean register(Roomy user) throws Exception {
	 * 
	 * Class.forName("com.mysql.jdbc.Driver"); Connection
	 * con=DriverManager.getConnection
	 * ("jdbc:mysql://localhost:3306/deepu","root","root");
	 * 
	 * PreparedStatement
	 * ps=con.prepareStatement("insert into register values(?,?,?,?)");
	 * ps.setString(1,user.getName()); ps.setString(2,user.getPassword());
	 * ps.setString(3,user.getEmail());
	 * 
	 * if(ps.executeUpdate()>0) return true;
	 * 
	 * return false; }
	 */

	public boolean address(Address addr) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "rooot", "root");
		PreparedStatement ps = con
				.prepareStatement("insert into address value(?,?,?,?)");
		ps.setString(1, addr.getCountry());
		ps.setString(2, addr.getState());
		ps.setString(3, addr.getDistt());
		ps.setInt(4, addr.getPincode());
		ps.setString(5, addr.getOthers());
		if (ps.executeUpdate() > 0)
			return true;
		return false;
	}

	public boolean bathroom(Bathroom bath) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "rooot", "root");
		PreparedStatement ps = con
				.prepareStatement("insert into bathroom value(default,?,?,?,?)");
		ps.setString(1, bath.getSoap());
		ps.setString(2, bath.getToothpaste());
		ps.setString(3, bath.getWaterHolder());
		if (ps.executeUpdate() > 0)
			return true;
		return false;
	}

	public boolean kitchen(Kitchen kitch) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "rooot", "root");
		PreparedStatement ps = con
				.prepareStatement("insert into kitchen value(?,?,?,?)");
		ps.setString(1, kitch.getSpices());
		ps.setString(2, kitch.getBeverage());
		ps.setString(3, kitch.getVagetables());
		ps.setString(4, kitch.getOthers());
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}

	public boolean room(Room rum) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/deepu", "root", "root");
		PreparedStatement ps = con
				.prepareStatement("insert into room value(?,?,?,?)");
		ps.setInt(1, rum.getRoomNo());
		ps.setInt(2, rum.getNoOfRooms());
		ps.setInt(3, rum.getNoOfPartner());
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;

	}
// function for registration roomie
	public boolean registerRoomy(Roomy roomy) throws Exception {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/deepu", "root", "root");

			ps = con.prepareStatement("insert into register value(?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, roomy.getName());
			ps.setString(2, roomy.getPassword());
			ps.setString(3, roomy.getEmail());
			ps.setString(4, roomy.getAddress().getCountry());
			ps.setString(5, roomy.getDesignation());
			ps.setString(6, roomy.getGender());
			ps.setInt(7, roomy.getMobileNo1());
			ps.setInt(8, roomy.getMobileNo2());
			ps.setString(9, roomy.getAddress().getDistt());
			ps.setString(10, roomy.getAddress().getState());
			ps.setString(11, roomy.getAddress().getOthers());

		} catch (Exception e) {

			e.printStackTrace();
		}
		if (ps.executeUpdate() > 0)
			return true;
		else
			return false;
	}

}

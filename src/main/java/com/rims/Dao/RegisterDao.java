/**us
 * 
 */
package com.rims.Dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public interface RegisterDao {

	//public boolean register(Roomy user)throws Exception;
	public Roomy getUser(String email)throws Exception;
	public boolean address(Address address)throws Exception;
	public boolean bathroom(Bathroom bath)throws Exception;
	public boolean kitchen(Kitchen bath)throws Exception;
	public boolean room(Room bath)throws Exception;
	public boolean registerRoomy(Roomy bath)throws Exception;
	public ArrayList<ExpensesModel> expenses()throws Exception;
	public ArrayList<ExpensesModel> search(String k)throws Exception;
	public boolean addexpendata(ExpensesModel ex)throws Exception;
	public ArrayList<ExpensesModel> update(Integer id)throws Exception;
	public boolean updateexpendata(ExpensesModel ex)throws Exception;
	public boolean delete(String []strArray)throws Exception;
	
}

/**us
 * 
 */
package com.rims.Dao;

import com.rims.model.Address;
import com.rims.model.Bathroom;
import com.rims.model.Kitchen;
import com.rims.model.Room;
import com.rims.model.Roomy;

/**
 * @author Administrator
 *
 */
public interface RegisterDao {

	public boolean register(Roomy user)throws Exception;
	public Roomy getUser(String email)throws Exception;
	public boolean address(Address address)throws Exception;
	public boolean bathroom(Bathroom bath)throws Exception;
	public boolean kitchen(Kitchen bath)throws Exception;
	public boolean room(Room bath)throws Exception;
	public boolean roomy(Roomy bath)throws Exception;
}

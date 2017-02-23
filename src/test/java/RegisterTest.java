import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.rims.Dao.RegisterDao;
import com.rims.Dao.impl.RegisterDaoImpl;
import com.rims.controller.ExpensesController;
import com.rims.controller.RegisterController;
import com.rims.model.ExpensesModel;

/**
 * 
 */

/**
 * @author Administrator
 *
 */
public class RegisterTest {

	MockHttpServletRequest request=new MockHttpServletRequest();
    MockHttpServletResponse response=new MockHttpServletResponse();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception{
		RegisterController r=new RegisterController();
		request.setParameter("username", "bijendra");
		request.setParameter("password", "bij");
	

		r.register(request, response);
		
		System.out.println(response.getContentAsString());
	}
	@Test
	public void addd()throws Exception{
		ExpensesController es =new ExpensesController();
		
		request.setParameter("person", "value");
		request.setParameter("itemname", "value");
		request.setParameter("price", "123");
		request.setParameter("category", "value");
		es.adddata(request, response);
		System.out.println(response.getContentAsString());
		/*RegisterDaoImpl rs=new RegisterDaoImpl();
		ExpensesModel em=new ExpensesModel();
		em.setPerson("ram");
		em.setItemname("ram");
		em.setPrice(4);
		em.setCategory("dfsfs");
	
		 assertNotNull(em);
		 System.out.println(em.toString());*/
	}
	

}

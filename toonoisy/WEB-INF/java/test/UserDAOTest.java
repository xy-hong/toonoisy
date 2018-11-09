package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UserDAOTest {

	//@Test
	void testInsert() {
		User u = new User();
		u.setId("1015683970@qq.com");
		u.setPassword("12345abc");
		
		UserDAO dao = new UserDAOImpl();
		dao.insert(u);
		
	}

	//@Test
	void testDelete() {
		UserDAO dao = new UserDAOImpl();
		dao.delete("1015683970@qq.com");
	}

	//@Test
	void testUpdate() {
		User u = new User();
		u.setId("1015683970@qq.com");
		u.setPassword("123abc");
		
		UserDAO dao = new UserDAOImpl();
		dao.update(u);
	}

	@Test
	void testGetDO() {
		UserDAO dao = new UserDAOImpl();
		User u = dao.getDO("1015683970@qq.com");
		assertEquals("1015683970@qq.com", u.getId());
	//	System.out.println(u.getId());
	//	System.out.println(u.getPassword());
		
	}

}

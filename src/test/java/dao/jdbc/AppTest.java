package dao.jdbc;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import dao.jdbc.demo.UserDao;
import app.model.User;


public class AppTest extends TestCase {
	
	UserDao userDao = null;
	
	@Override
	protected void setUp() throws Exception {
		userDao = new UserDao();
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setName("测试123");
		user.setAge(100);
		user.setSex('0');
		user.setAddress("不知道");
		user.setPhoto("d:/pic");
		user.setBirthday(new Date());
		user.setRegDate(new Date());
		
		userDao.save(user);
	}
	
	@Test
	public void testUpdate() {
		User user = new User();
		user.setName("测试2");
		user.setAge(10);
		user.setSex('1');
		user.setAddress("知道");
		user.setPhoto("e:/pic");
		user.setBirthday(null);
		user.setRegDate(new Date());
		user.setId(1);
		
		userDao.update(user);
	}
	
	@Test
	public void testDelete() {
		int id = 2;
		userDao.delete(id);
	}
	
	@Test
	public void testGet() {
		int id = 1;
		User user = userDao.get(id);
		System.out.println(user);
	}
	
	@Test
	public void testQuery() {
		String name = "张";
		List<User> users = userDao.query(name);
		for(User user : users) {
			System.out.println(user);
		}
	}
	
}

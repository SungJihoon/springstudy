package chapter02.exercise.e05;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e05/daoContext.xml");

		UserDao userDao = context.getBean("userDao", UserDao.class);
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));

		User user = new User();
		user.setId("Yuna");
		user.setName("김연아");
		user.setPassword("승냥이");

		userDao.add(user);
		assertThat(userDao.getCount(), is(1));

		User user2 = userDao.get(user.getId());

		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e05/daoContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		User user1 = new User("lena", "박정현", "park");
		User user2 = new User("jlim", "임정희", "j-lim");
		User user3 = new User("jiyoung", "백지영", "baek");
		
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));
		
		userDao.add(user1);
		assertThat(userDao.getCount(), is(1));
		
		userDao.add(user2);
		assertThat(userDao.getCount(), is(2));
		
		userDao.add(user3);
		assertThat(userDao.getCount(), is(3));
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e05/daoContext.xml");
		
		UserDao userDao = context.getBean("userDao", UserDao.class);
		userDao.deleteAll();
		
		userDao.get("unknown_id");
	}
	
}

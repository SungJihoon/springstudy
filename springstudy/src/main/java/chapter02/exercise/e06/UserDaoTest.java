package chapter02.exercise.e06;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UserDaoTest {
	
	UserDao userDao;
	
	User user1;
	User user2;
	User user3;
	
	@Before
	public void setUp() {
		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e06/daoContext.xml");
		
		userDao = context.getBean("userDao", UserDao.class);
		user1 = new User("lena", "박정현", "park");
		user2 = new User("jlim", "임정희", "j-lim");
		user3 = new User("jiyoung", "백지영", "baek");
	}

	@Test
	public void addAndGet() throws SQLException {
		userDao.deleteAll();
		assertThat(userDao.getCount(), is(0));

		userDao.add(user1);
		assertThat(userDao.getCount(), is(1));

		User user2 = userDao.get(user1.getId());

		assertThat(user2.getName(), is(user1.getName()));
		assertThat(user2.getPassword(), is(user1.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {

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
		userDao.deleteAll();
		
		userDao.get("unknown_id");
	}
	
}

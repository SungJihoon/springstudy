package chapter02.exercise.e03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	@Test
	public void addAndGet() throws SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e03/daoContext.xml");

		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("Yuna");
		user.setName("김연아");
		user.setPassword("승냥이");

		userDao.add(user);

		User user2 = userDao.get(user.getId());

		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
	}
}

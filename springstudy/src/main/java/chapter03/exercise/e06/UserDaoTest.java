package chapter03.exercise.e06;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/java/chapter03/exercise/e06/daoContext.xml")
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setUp() {
		this.user1 = new User("lena", "박정현", "park");
		this.user2 = new User("jlim", "임정희", "j-lim");
		this.user3 = new User("jiyoung", "백지영", "baek");
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

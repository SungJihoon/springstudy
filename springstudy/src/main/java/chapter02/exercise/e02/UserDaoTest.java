package chapter02.exercise.e02;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new GenericXmlApplicationContext("chapter02/exercise/e02/daoContext.xml");

		UserDao userDao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("Yuna");
		user.setName("김연아");
		user.setPassword("승냥이");

		userDao.add(user);

		User user2 = userDao.get(user.getId());

		if (!user.getName().equals(user2.getName())) {
			System.out.println("테스트 실패 (name)");
		} else if (!user.getPassword().equals(user2.getPassword())) {
			System.out.println("테스트 실패 (password)");
		} else {
			System.out.println("조회 테스트 성공");
		}
	}
}

package chapter01.exercise.ex10;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		ApplicationContext context = new GenericXmlApplicationContext("chapter01/exercise/ex10/daoContext.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("Yuna");
		user.setName("김연아");
		user.setPassword("승냥이");
		
		userDao.add(user);
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = userDao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + " 조회 성공");
	}
}

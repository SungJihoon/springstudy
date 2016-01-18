package chapter01.exercise.ex07;

public class DaoFactory {
	
	public UserDao userDao() {
		ConnectionMaker connectionMaker = new BConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		return userDao;
	}

}

package chapter01.exercise.ex08;

public class DaoFactory {
	
	public UserDao userDao() {
		UserDao userDao = new UserDao(connectionMaker());
		return userDao;
	}

	public GroupDao groupDao() {
		GroupDao groupDao = new GroupDao(connectionMaker());
		return groupDao;
	}
	
	public CompanyDao companyDao() {
		CompanyDao companyDao = new CompanyDao(connectionMaker());
		return companyDao;
	}

	public ConnectionMaker connectionMaker() {
		return new BConnectionMaker();
	}

}

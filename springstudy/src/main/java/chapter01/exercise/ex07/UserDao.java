package chapter01.exercise.ex07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	
	ConnectionMaker connectionMaker;
	
	UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}

	public void add(User user) throws ClassNotFoundException, SQLException {
		Connection connection = connectionMaker.makeConnection();
		PreparedStatement statement = connection.prepareStatement("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)");
		statement.setString(1, user.getId());
		statement.setString(2, user.getName());
		statement.setString(3, user.getPassword());
		
		statement.executeUpdate();
		
		statement.close();
		connection.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException {
		Connection connection = connectionMaker.makeConnection();
		PreparedStatement statement = connection.prepareStatement("select * from USERS where ID = ?");
		statement.setString(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		
		User user = new User();
		user.setId(resultSet.getString("ID"));
		user.setName(resultSet.getString("NAME"));
		user.setPassword(resultSet.getString("PASSWORD"));

		resultSet.close();
		statement.close();
		connection.close();
		
		return user;
	}

}

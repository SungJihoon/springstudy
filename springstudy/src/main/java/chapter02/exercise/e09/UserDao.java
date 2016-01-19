package chapter02.exercise.e09;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {
	
	DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(User user) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)");
		statement.setString(1, user.getId());
		statement.setString(2, user.getName());
		statement.setString(3, user.getPassword());
		
		statement.executeUpdate();
		
		statement.close();
		connection.close();
	}
	
	public User get(String id) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from USERS where ID = ?");
		statement.setString(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			user = new User();
			user.setId(resultSet.getString("ID"));
			user.setName(resultSet.getString("NAME"));
			user.setPassword(resultSet.getString("PASSWORD"));
		}

		resultSet.close();
		statement.close();
		connection.close();

		if (user == null) throw new EmptyResultDataAccessException(1);
		
		return user;
	}
	
	public void deleteAll() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("delete from USERS");
		statement.executeUpdate();
		
		statement.close();
		connection.close();
	}

	public int getCount() throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement("select count(*) from USERS");
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		int count = resultSet.getInt(1);
		
		resultSet.close();
		statement.close();
		connection.close();
		
		return count;
	}
}

package chapter03.exercise.e07;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy {

	User user;
	
	public AddStatement(User user) {
		this.user = user;
	}
	
	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)");
		statement.setString(1, user.getId());
		statement.setString(2, user.getName());
		statement.setString(3, user.getPassword());
		return statement;
	}

}

package chapter03.exercise.e06;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStatement implements StatementStrategy {

	public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
		return connection.prepareStatement("delete from USERS");
	}

}

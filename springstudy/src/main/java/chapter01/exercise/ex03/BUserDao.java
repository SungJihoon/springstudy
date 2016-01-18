package chapter01.exercise.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BUserDao extends UserDao {

	@Override
	Connection getConnection() throws ClassNotFoundException, SQLException  {
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "bbada");
		return connection;
	}

}

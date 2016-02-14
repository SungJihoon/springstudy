package chapter03.exercise.e17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;		
	}
	
	public void workWithStatementStrategy(StatementStrategy strategy) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = strategy.makePreparedStatement(connection);
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void executeSql(final String query) throws SQLException {
		workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				return connection.prepareStatement(query);
			}
		});
	}
}

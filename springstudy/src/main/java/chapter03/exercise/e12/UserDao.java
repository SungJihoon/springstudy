package chapter03.exercise.e12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {
	
	DataSource dataSource;
	private JdbcContext jdbcContext;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcContext = new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
		this.dataSource = dataSource;
	}

	public void deleteAll() throws SQLException {
		this.jdbcContext.executeSql("delete from USERS");
	}

	public void add(final User user) throws SQLException {
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
				PreparedStatement statement = connection
						.prepareStatement("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)");
				statement.setString(1, user.getId());
				statement.setString(2, user.getName());
				statement.setString(3, user.getPassword());
				return statement;
			}
		});
	}

	public User get(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select * from USERS where ID = ?");
			statement.setString(1, id);
			
			resultSet = statement.executeQuery();
			User user = null;
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getString("ID"));
				user.setName(resultSet.getString("NAME"));
				user.setPassword(resultSet.getString("PASSWORD"));
			}
			if (user == null) throw new EmptyResultDataAccessException(1);	
			return user;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
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
	
	public int getCount() throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement("select count(*) from USERS");
			
			resultSet = statement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			
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
}

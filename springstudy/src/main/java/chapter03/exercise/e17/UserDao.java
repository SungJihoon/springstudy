package chapter03.exercise.e17;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	
	DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public void deleteAll() {
		/*this.jdbcTemplate.update(
			new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					return con.prepareStatement("delete from USERS");
				}
			}
		);*/
		this.jdbcTemplate.update("delete from USERS");
	}

	public void add(final User user) {
		this.jdbcTemplate.update("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)",
				user.getId(), user.getName(), user.getPassword());
	}

	public int getCount() {
		/*return this.jdbcTemplate.query(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("select count(*) from USERS");
			}
		}, new ResultSetExtractor<Integer>() {
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}
		});*/
		return this.jdbcTemplate.queryForObject("select count(*) from USERS", Integer.class);
	}

	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from USERS where ID = ?",
				new Object[] {id},
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
		});
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from USERS order by ID",
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
		});
	}
}

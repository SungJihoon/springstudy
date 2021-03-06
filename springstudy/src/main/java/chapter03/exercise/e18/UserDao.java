package chapter03.exercise.e18;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = new RowMapper<User>() {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};

	public void add(final User user) {
		this.jdbcTemplate.update("insert into USERS (ID, NAME, PASSWORD) values (?, ?, ?)",
				user.getId(), user.getName(), user.getPassword());
	}

	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from USERS where ID = ?",
				new Object[] {id}, this.userMapper);
	}

	public void deleteAll() {
		this.jdbcTemplate.update("delete from USERS");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from USERS", Integer.class);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from USERS order by ID",
				this.userMapper);
	}
}

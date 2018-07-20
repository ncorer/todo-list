package sk.elct.java.todo_list_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;



public class MysqlActivityDao implements ActivityDao {

	private JdbcTemplate jdbctemplate;

/*	public MysqlActivityDao() {
		MysqlDataSource dataSource = new MysqlDataSource();

		dataSource.setUrl("jdbc:mysql://localhost/todolist?"
				+ "serverTimezone=Europe/Bratislava&nullNamePatternMatchesAll=true\"");
		dataSource.setDatabaseName("todolist");
		dataSource.setUser("admin");
		dataSource.setPassword("elct");
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}
*/
	public MysqlActivityDao(JdbcTemplate jdbcTemplate) {
		this.jdbctemplate = jdbcTemplate;
	}
	
	
/*	public MysqlActivityDao(MysqlDataSource dataSource) {
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}*/


	public void add(Activity activity) {
		
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbctemplate);
		simpleJdbcInsert.withTableName("activity");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
		simpleJdbcInsert.usingColumns("title", "priority", "dueDate", "body","progress","category_id");
		
		Map<String, Object> hodnoty = new HashMap<>();
		hodnoty.put("title", activity.getTitle());
		hodnoty.put("priority", activity.getPriority());
		hodnoty.put("dueDate", activity.getDueDate());
		hodnoty.put("body", activity.getBody());
		hodnoty.put("progress", activity.getProgress());
		hodnoty.put("category_id", activity.getCategory().getId());
		
		long noveId = simpleJdbcInsert.executeAndReturnKey(hodnoty).longValue();
		activity.setId(noveId);
		

	}


	public List<Activity> getAll() {
		String sql = "Select activity.id,title,priority,dueDate,body,progress,category_id,`name` from activity JOIN category on (category_id=category.id)" ;
		ActivityRowMapper rowMapper = new ActivityRowMapper(); //ekvivalent   RowMapper<Activity> rowMapper = new BeanPropertyRowMapper<Activity>(Activity.class);
		return jdbctemplate.query(sql, rowMapper);
	}


	public void update(Activity activity) {
		String sql = ("UPDATE ACTIVITY set title = ? ,priority = ? ,dueDate = ? ,body = ?, progress = ?, category_id= ? where ID = ?" ); 
		jdbctemplate.update(sql, activity.getTitle(), activity.getPriority(),activity.getDueDate(), activity.getBody(), activity.getProgress(), activity.getCategory().getId(), activity.getId());		}


	public void delete(long id) {
		jdbctemplate.update("DELETE FROM ACTIVITY WHERE ID = " + id);
		
	}


	@Override
	public Activity getById(long id) {
		
			String sql = "Select activity.id,title,priority,dueDate,body,progress,category_id,`name` from activity JOIN category on (category_id=category.id) where id= " + id;
			RowMapper<Activity> rowMapper = new ActivityRowMapper();
			return jdbctemplate.queryForObject(sql, rowMapper);
		
	}

}

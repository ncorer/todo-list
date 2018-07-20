package sk.elct.java.todo_list_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlCategoryDao implements CategoryDao {

	private JdbcTemplate jdbctemplate;
	
	
	public MysqlCategoryDao(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbctemplate = jdbcTemplate;
		}
	
	@Override
	public List<Category> getAll() {
		String sql = "SELECT * FROM `category`";
		RowMapper<Category> rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
		return jdbctemplate.query(sql, rowMapper);
	}

	@Override
	public void add(Category category) {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbctemplate);
		simpleJdbcInsert.withTableName("category");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
		simpleJdbcInsert.usingColumns("name");
		
		Map<String, Object> hodnoty = new HashMap<>();
		hodnoty.put("name", category.getName());

		
		long noveId = simpleJdbcInsert.executeAndReturnKey(hodnoty).longValue();
		category.setId(noveId);
		
	}

	
	public void delete(long id) {
		if(id == 1) {
			throw new DefaultCategoryException("Defaultna kategoria sa neda zmazat") ;		
		}
		jdbctemplate.update("UPDATE activity SET category_id = 1 WHERE category_id= " + id);
		jdbctemplate.update("DELETE FROM category WHERE ID = " + id);
				
	}


	public void update(Category category) {
		String sql = ("UPDATE category set name = ? where ID = ?" ); 
		jdbctemplate.update(sql, category.getName(), category.getId());		
		
	}


	public Category getById(long id) {
		String sql = "SELECT * FROM `category` where id = " + id;
		RowMapper<Category> rowMapper = new BeanPropertyRowMapper<Category>(Category.class);
		return jdbctemplate.queryForObject(sql, rowMapper);
		
	}
	
	
}

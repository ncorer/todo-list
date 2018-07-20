package sk.elct.java.todo_list_project;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;



public enum DaoFactory {
	INSTANCE;
	
	private ActivityDao activityDao;
	
	private MysqlDataSource getDataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();

		dataSource.setUrl("jdbc:mysql://localhost/todolist?"
				+ "serverTimezone=Europe/Bratislava&nullNamePatternMatchesAll=true\"");
		dataSource.setDatabaseName("todolist");
		dataSource.setUser("admin");
		dataSource.setPassword("elct");
		return dataSource;
	}
	
	public ActivityDao getActivityDao() {
		if(activityDao == null) {
			activityDao = new MysqlActivityDao(new JdbcTemplate(this.getDataSource()));
		}
	return activityDao;	
	}
	
	public CategoryDao getCategoryDao() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return new MysqlCategoryDao(jdbcTemplate);
	
	}
}

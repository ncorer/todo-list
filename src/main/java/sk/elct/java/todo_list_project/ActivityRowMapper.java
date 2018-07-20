package sk.elct.java.todo_list_project;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import com.mysql.cj.result.LocalDateValueFactory;

	public class ActivityRowMapper implements RowMapper<Activity> {


		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity a = new Activity();
			a.setId(rs.getLong("id"));
			a.setTitle(rs.getString("title"));
			a.setPriority(rs.getInt("priority"));
			if(rs.getTimestamp("dueDate")==null){
				a.setDueDate(new LocalDateValueFactory().createFromDate(2018, 01, 05));
			}else {
			a.setDueDate(rs.getTimestamp("dueDate").toLocalDateTime().toLocalDate());}
			Category catTemp = new Category();
			catTemp.setId(rs.getLong("category_id"));
			catTemp.setName(rs.getString("name"));
			a.setCategory(catTemp);
			a.setBody(rs.getString("body"));
			a.setProgress(rs.getInt("progress"));
			return a;
		}

	}
	


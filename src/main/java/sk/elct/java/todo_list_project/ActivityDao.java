package sk.elct.java.todo_list_project;

import java.util.List;

public interface ActivityDao {

	//CREATE
	void add(Activity activity);

	//READ
	List<Activity> getAll();

	//UPDATE	
	void update(Activity activity);

	//DELETE
	void delete(long id);
	
	Activity getById(long id);
	
	
}

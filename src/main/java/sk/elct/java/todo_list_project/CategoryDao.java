package sk.elct.java.todo_list_project;

import java.util.List;

public interface CategoryDao {

	List<Category> getAll();
	
	void add(Category category);
	
	void delete(long id);
	
	void update(Category category);
	
	Category getById(long id);
}

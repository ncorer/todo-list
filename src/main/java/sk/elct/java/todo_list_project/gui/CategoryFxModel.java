package sk.elct.java.todo_list_project.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.elct.java.todo_list_project.Category;



public class CategoryFxModel {


		private StringProperty name = new SimpleStringProperty();
		private Long id;

		
		public Category getCategory() {
			Category category = new Category();
			category.setId(getId());
			category.setName(getName());
			return category;
						
		}
				
		public void setCategory(Category category) {
			setId(category.getId());
			setName(category.getName());
			
		}
		
		public String getName() {
			return name.get();
		}
		
		
		public void setName(String name) {
			this.name.set(name);
		}
		
		
		public StringProperty nameProperty() {
			return name;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}
		
		
		
	}

	


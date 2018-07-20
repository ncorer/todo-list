package sk.elct.java.todo_list_project.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import sk.elct.java.todo_list_project.Activity;
import sk.elct.java.todo_list_project.Category;




public class ActivityFxModel {

	private StringProperty title = new SimpleStringProperty();
	private IntegerProperty priority = new SimpleIntegerProperty();
	private ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
	private ObjectProperty<Category> category = new SimpleObjectProperty<>();
	private Long id;
	private IntegerProperty progress = new SimpleIntegerProperty();
	private StringProperty body = new SimpleStringProperty();
	private ObservableList<Category> allCategories;
	



	public Activity getActivity() {
		Activity activity = new Activity();
		activity.setId(getId());
		activity.setTitle(getTitle());
		activity.setPriority(getPriority());
		activity.setDueDate(getDueDate());
		activity.setCategory(getCategory());
		activity.setProgress(getProgress());
		activity.setBody(getBody());
		return activity;
				
	}
	
	public void setActivity(Activity activity) {
		setId(activity.getId());
		setTitle(activity.getTitle());
		setPriority(activity.getPriority());
		setDueDate(activity.getDueDate());
		setCategory(activity.getCategory());
		setProgress(activity.getProgress());
		setBody(activity.getBody());
		
	}
	
	
	public ObservableList<Category> getAllCategories() {
		return allCategories;
	}


	public void setAllCategories(ObservableList<Category> allCategories) {
		this.allCategories = allCategories;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public StringProperty titleProperty() {
		return title;
	}
	
	public Integer getPriority() {
		return priority.get();
	}
	
	public void setPriority(Integer priority) {
		this.priority.set(priority);
	}
	
	public IntegerProperty priorityProperty() {
		return priority;
	}
	
	public LocalDate getDueDate() {
		return dueDate.get();
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate.set(dueDate);
	}
	
	public ObjectProperty<LocalDate> dueDateProperty(){
		return dueDate;
	}
	
	public Category getCategory() {
		return category.get();
	}
	
	public void setCategory(Category category) {
		this.category.set(category);
	}
	
	public ObjectProperty<Category> categoryProperty(){
		return category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getProgress() {
		return progress.get();
	}
	
	public void setProgress(Integer progress) {
		this.progress.set(progress);
	}
	
	public IntegerProperty progressProperty() {
		return progress;
	}
	
	public String getBody() {
		return body.get();
	}
	
	public void setBody(String body) {
		this.body.set(body);
	}
	
	public StringProperty bodyProperty() {
		return body;
	}
}

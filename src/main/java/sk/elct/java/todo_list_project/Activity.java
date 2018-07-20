package sk.elct.java.todo_list_project;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activity {

	private Long id;
	private String title;
	private int priority;
	private LocalDate dueDate;
	private String body;
	private Integer progress;
	private Category category;
	
	public String getCategoryName() {
		return category.getName();
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", priority=" + priority + ", dueDate=" + dueDate + ", body="
				+ body + ", progress=" + progress + ", category=" + category + "]";
	}
	

	
}

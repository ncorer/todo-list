package sk.elct.java.todo_list_project.gui;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sk.elct.java.todo_list_project.Category;
import sk.elct.java.todo_list_project.CategoryDao;
import sk.elct.java.todo_list_project.DaoFactory;

public class EditCategoryController {

private CategoryFxModel categoryModel = new CategoryFxModel();
	
private boolean editing = true;


	public EditCategoryController(Category category) {
		this.categoryModel.setCategory(category);
	}

	public EditCategoryController() {
		editing = false;
	}

	private CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();
	
    @FXML
    private TextField editCategoryTextField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {

    	editCategoryTextField.textProperty().bindBidirectional(categoryModel.nameProperty());
    	
      	saveButton.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {

    				if (editing) {
    				categoryDao.update(categoryModel.getCategory());
    				}else {
    					categoryDao.add(categoryModel.getCategory());
    				}
    				saveButton.getScene().getWindow().hide();
   
 				
    			}
    		});

    }
	
	
}

package sk.elct.java.todo_list_project.gui;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.NumberStringConverter;
import sk.elct.java.todo_list_project.Activity;
import sk.elct.java.todo_list_project.ActivityDao;
import sk.elct.java.todo_list_project.Category;
import sk.elct.java.todo_list_project.CategoryDao;
import sk.elct.java.todo_list_project.DaoFactory;



public class DruhyController {

	
	private ObservableList<Activity> observableActivities;
	
	private ActivityDao activityDao = DaoFactory.INSTANCE.getActivityDao();
	
	private CategoryDao categoryDao = DaoFactory.INSTANCE.getCategoryDao();
	
	private ActivityFxModel selectedActivity = new ActivityFxModel();
	
    @FXML
    private TableView<Activity> activityTableView;
		
//    @FXML
//    private ListView<Activity> activityListView;

    @FXML
    private ImageView imageView;
    
    
    @FXML
    private TextArea bodyTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField priorityTextField;

    @FXML
    private TextField dueDateTextField;

    @FXML
    private TextField progressTextField;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<Category> categoryComboBox;
  
    @FXML
    private Button editActivityButton;

    @FXML
    private Button clearFieldsButton;

    @FXML
    private Button deleteActivityButton;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    void initialize() {
    	
    	List<Activity> activities = activityDao.getAll();
    	observableActivities = FXCollections.observableArrayList(activities);

       	titleTextField.textProperty().bindBidirectional(selectedActivity.titleProperty());
    	priorityTextField.textProperty().bindBidirectional(selectedActivity.priorityProperty(),new NumberStringConverter());
    	datePicker.editorProperty().getValue().textProperty().bindBidirectional(selectedActivity.dueDateProperty(), new LocalDateStringConverter());
//    	dueDateTextField.textProperty().bindBidirectional(selectedActivity.dueDateProperty(), new LocalDateTimeStringConverter());
    	progressTextField.textProperty().bindBidirectional(selectedActivity.progressProperty(), new NumberStringConverter());
    	bodyTextField.textProperty().bindBidirectional(selectedActivity.bodyProperty());
    	
    	//combobox
    	
    	selectedActivity.setAllCategories(FXCollections.observableArrayList(categoryDao.getAll()));
    	
    	categoryComboBox.setItems(selectedActivity.getAllCategories());
    	
    	categoryComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Category>() {

			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
				if (newValue != null) {
					selectedActivity.setCategory(newValue);
				} 
				
			}
		});
    	
    	selectedActivity.categoryProperty().addListener(new ChangeListener<Category>() {

			@Override
			public void changed(ObservableValue<? extends Category> observable, Category oldValue, Category newValue) {
				categoryComboBox.getSelectionModel().select(newValue);
				
			}
		});
    	
    	categoryComboBox.getSelectionModel().select(selectedActivity.getAllCategories().get(0));
    	selectedActivity.setDueDate(LocalDate.now());
    	
       	addButton.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {

    				activityDao.add(selectedActivity.getActivity());
    				observableActivities.setAll(activityDao.getAll());
    				//vyprazdnenie textfieldu
    				selectedActivity.setTitle(null);
    				selectedActivity.setPriority(0);
    				selectedActivity.setProgress(0);
    				selectedActivity.setDueDate(LocalDate.now());
    				categoryComboBox.getSelectionModel().select(selectedActivity.getAllCategories().get(0));
    				selectedActivity.setBody(null);
    				
    			}
    		});
       	
      	clearFieldsButton.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {

 //   				activityDao.add(selectedActivity.getActivity());
  //  				observableActivities.setAll(activityDao.getAll());
    				//vyprazdnenie textfieldu
    				selectedActivity.setTitle(null);
    				selectedActivity.setPriority(0);
    				selectedActivity.setProgress(0);
    				selectedActivity.setDueDate(LocalDate.now());
    				categoryComboBox.getSelectionModel().select(selectedActivity.getAllCategories().get(0));
    				selectedActivity.setBody(null);
    				
    			}
    		});
   
       	deleteActivityButton.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {

    				activityDao.delete(selectedActivity.getId());
    				observableActivities.setAll(activityDao.getAll());
    				//vyprazdnenie textfieldu
    				selectedActivity.setTitle(null);
    				selectedActivity.setPriority(0);
    				selectedActivity.setProgress(0);
    				selectedActivity.setDueDate(LocalDate.now());
    				categoryComboBox.getSelectionModel().select(selectedActivity.getAllCategories().get(0));
    				selectedActivity.setBody(null);
    				
    			}
    		});
       	
       	editActivityButton.setOnAction(new EventHandler<ActionEvent>() {

    			@Override
    			public void handle(ActionEvent event) {

    				activityDao.update(selectedActivity.getActivity());
    				observableActivities.setAll(activityDao.getAll());
    				//vyprazdnenie textfieldu
    				selectedActivity.setTitle(null);
    				selectedActivity.setPriority(0);
    				selectedActivity.setProgress(0);
    				selectedActivity.setDueDate(LocalDate.now());
    				categoryComboBox.getSelectionModel().select(selectedActivity.getAllCategories().get(0));
    				selectedActivity.setBody(null);
    				
    			}
    		});
      	
      	
       	activityTableView.setItems(observableActivities);	
       	activityTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {

			@Override
			public void changed(ObservableValue<? extends Activity> observable, Activity oldValue, Activity newValue) {
				if(newValue != null)
				selectedActivity.setActivity(newValue);
				
			}
		});
    	
    	
    	TableColumn<Activity, String> titleColumn = new TableColumn<>("Title");
    	titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
    	activityTableView.getColumns().add(titleColumn);
    	
    	TableColumn<Activity, String> priorityColumn = new TableColumn<>("Priority");
    	priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
    	activityTableView.getColumns().add(priorityColumn);
    	
    	TableColumn<Activity, String> dueDateColumn = new TableColumn<>("Due Date");
    	dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    	activityTableView.getColumns().add(dueDateColumn);
    	
       	TableColumn<Activity, String> bodyColumn = new TableColumn<>("Body");
    	bodyColumn.setCellValueFactory(new PropertyValueFactory<>("body"));
    	activityTableView.getColumns().add(bodyColumn);
    	
    	TableColumn<Activity, String> progressColumn = new TableColumn<>("Progress");
    	progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));
    	activityTableView.getColumns().add(progressColumn);
    	
    	TableColumn<Activity, String> categoryColumn = new TableColumn<>("Category");
    	categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
    	activityTableView.getColumns().add(categoryColumn);
    	
    	
    	activityTableView.setItems(observableActivities);
    	
    	
    	
    	
    }
    
	
    	
    
    
}

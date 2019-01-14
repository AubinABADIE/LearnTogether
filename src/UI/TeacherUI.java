package UI;

import Types.DepartmentType;
import Types.TeacherType;
import Types.UserType;
import client.CoreClient;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Types.RoomType;

import java.util.List;

/**
 * 
 */
public class TeacherUI extends UI {

	private Scene principalTeacherScene;
	private Scene principalAdminScene;
	private TabPane tabPane;
	private Tab tabProfile, tabSchedule, tabRecords, tabDiary, tabChat, tabCourse, tabRoom;
	private ObservableList<RoomType> roomNames;
	
    /**
     * Default constructor
     */
    public TeacherUI(Stage primaryStage, String login, int id, CoreClient client) {
		this.primaryStage = primaryStage;
		this.login = login;
		this.userID = id;
		this.client = client;
    }

	public Scene getPrincpalTeacherScene() {
		return principalTeacherScene;
	}

	public void setPrincpalTeacherScene(Scene princpalTeacherScene) {
		this.principalTeacherScene = princpalTeacherScene;
	}

	public Scene createPrincipalTeacherScene(){
		this.primaryStage.setTitle("LearnTogether for Teachers");
        BorderPane teacherScene = new BorderPane();
        //Create the top bar
        VBox topBar = new VBox();
        HBox titleBar = new HBox();
        Text title = new Text("Learn Together -- Teacher side");
        Text user = new Text("Connected as: " + login);
        title.setFont(Font.font("Cambria", 20));
        titleBar.getChildren().addAll(title, user);

        //Create the Tabs
        TabPane tabPane = new TabPane();
        
        Tab tabProfile = createProfileTab();
        
        Tab tabSchedule = new Tab();
        tabSchedule.setText("Schedule");
        tabSchedule.setClosable(false);

        Tab tabRecords = new Tab();
        tabRecords.setText("Record");
        tabRecords.setClosable(false);

        Tab tabDiary = new Tab();
        tabDiary.setText("Diary");
        tabDiary.setClosable(false);

        Tab tabChat = createChatTab();
        
        tabCourse = createTabCourse();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabCourse);
        

        topBar.getChildren().addAll(titleBar, tabPane);
        teacherScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalTeacherScene = new Scene(teacherScene, 900, 700);
        return principalTeacherScene;
	}
	
	protected Tab createTabCourse(){
    	Tab tabCourse = new Tab();
        tabCourse.setText("Course");
        tabCourse.setClosable(false);

        // labels
        Label nameLabel = new Label("Name of course : ");
        Label descriptionLabel = new Label("Description : ");
        Label totalHoursLabel = new Label("Total hours : ");
        Label promotionLabel = new Label ("Promotion : ");
        Label referingTeacherLabel = new Label ("Refering teacher : ");

        // Add text Field
        TextField nameField = new TextField();
        TextField totalHoursField = new TextField();
        TextField promotionField = new TextField();
        TextField referingTeacherField = new TextField();
        
        //Add text Area
        TextArea descriptionField = new TextArea();

        //grid pane
        GridPane gridCourse = new GridPane();
        gridCourse.setHgap(10);
        gridCourse.setVgap(10);
        gridCourse.setPadding(new Insets(10,10,10,10));

        //Hbox
        HBox nameCourse = new HBox();
        HBox descriptionCourse = new HBox();
        HBox totalHoursCourse = new HBox();
        HBox promotionCourse = new HBox();
        HBox referingTeacherCourse = new HBox();
        

         // add form in hbox
        nameCourse.getChildren().addAll(nameLabel, nameField);
        descriptionCourse.getChildren().addAll(descriptionLabel, descriptionField) ;
        totalHoursCourse.getChildren().addAll(totalHoursLabel, totalHoursField) ;
        promotionCourse.getChildren().addAll(promotionLabel, promotionField) ;
        referingTeacherCourse.getChildren().addAll(referingTeacherLabel, referingTeacherField) ;

        //add gridpane in tab
        tabCourse.setContent(gridCourse);
        
      //add hbox in gridpane
        gridCourse.add(nameCourse, 1, 1);
        gridCourse.add(descriptionCourse, 1, 2);
        gridCourse.add(totalHoursCourse, 1, 3);
        gridCourse.add(promotionCourse, 1, 4);
        gridCourse.add(referingTeacherCourse, 1, 5);

        //add button
        Button okCreate = new Button("Validate");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        
        gridCourse.add(okCreate, 0, 6, 1, 1);
        gridCourse.setHalignment(okCreate, HPos.RIGHT);
        gridCourse.setMargin(okCreate, new Insets(20, 0,20,0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        
        gridCourse.add(cancelCreate, 2, 6, 1, 1);
        gridCourse.setHalignment(cancelCreate, HPos.RIGHT);
        gridCourse.setMargin(cancelCreate, new Insets(20, 0,20,0));
        
        
        return tabCourse;
    }

	public void addUIControls(BorderPane borderPane){

	}

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setState(String cmd) {

	}

	@Override
	public void getRooms(List<RoomType> rooms) {

	}

    @Override
    public void getDepartment(List<DepartmentType> dep) {

    }

    @Override
    public void getTeacher(List<TeacherType> teacher) {

    }

    @Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
package UI;

import client.CoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import Types.RoomType;
import Types.CourseType;

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
	private ObservableList<CourseType> courseNames;
	
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
	
	public void setCourses(List<CourseType> courseList) {
        courseNames.setAll(courseList);
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
        
        tabCourse = tabCourse();

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
	
	protected Tab tabCourse(){

        Tab tabCourse = new Tab();
        tabCourse.setText("Course");
        tabCourse.setClosable(false);


        tabCourse.setContent(courseRead(tabCourse));
        return tabCourse;
	}

	private GridPane courseRead(Tab tabCourse){

        /*add list of Course*/
        client.getCourses();
        ListView<CourseType> list = new ListView<>();
        courseNames = FXCollections.observableArrayList();
        courseNames.addListener((ListChangeListener<CourseType>) c -> {
            list.setItems(courseNames);
        });

        Image addCourse = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addCourseView = new ImageView(addCourse);
        addCourseView.setFitHeight(15);
        addCourseView.setFitWidth(15);

        //create button add
        Button btnAddCourse = new Button("Add");
        btnAddCourse.setGraphic(addCourseView);//setting icon to button

        //delete button
        Image deleteCourse = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteCourseView = new ImageView(deleteCourse);
        deleteCourseView.setFitHeight(12);
        deleteCourseView.setFitWidth(12);

        //create button delete
        Button btnDeleteCourse = new Button("Delete");
        btnDeleteCourse.setGraphic(deleteCourseView);//setting icon to button

        // add in hbox buttons and title
        HBox hboxButtonCourse = new HBox();

        Text title = new Text("Course : ");
        title.setFont(Font.font(20));
        hboxButtonCourse.getChildren().add(title);
        hboxButtonCourse.getChildren().add(btnAddCourse);
        hboxButtonCourse.getChildren().add(btnDeleteCourse);
        hboxButtonCourse.setSpacing(5);

        list.setItems(courseNames);
        System.out.println(courseNames);
        list.setPrefWidth(350);
        list.setPrefHeight(500);

        // left vbox
        VBox vboxListCourse = new VBox();
        vboxListCourse.getChildren().add(list);

        //grid pane
        GridPane gridCourseVisu = new GridPane();
        gridCourseVisu.setHgap(10);
        gridCourseVisu.setVgap(10);
        gridCourseVisu.setPadding(new Insets(10,10,10,10));

        gridCourseVisu.add(hboxButtonCourse, 1, 0);
        gridCourseVisu.add(vboxListCourse, 1, 2);

        /*creation of the info vbox of one course*/
        VBox vboxInfoCourse = new VBox();

        //title of column
        HBox hboxCourseInfo = new HBox();
        Text titleInfo = new Text("Course information : ");
        titleInfo.setFont(Font.font(20));
        hboxCourseInfo.getChildren().add(titleInfo);
        hboxCourseInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxnameCourseInfo = new HBox();
        HBox hboxdescriptionCourseInfo = new HBox();
        HBox hboxnbHourTotalCourseInfo = new HBox();
        HBox hboxidTeacherCourseInfo = new HBox();

        Label nameLabel = new Label("Name of course : ");
        Label descriptionCourseLabel = new Label("Course description : ");
        Label nbHourTotalLabel = new Label( "Number Total Hour course : ");
        Label idTeacherLabel = new Label("Course idTeacher number : ");

        Text name = new Text(" ");
        Text descriptionCourse = new Text(" ");
        Text nbHourTotal = new Text(" ");
        Text idTeacher = new Text(" ");


        hboxnameCourseInfo.getChildren().add(nameLabel);
        hboxdescriptionCourseInfo.getChildren().add(descriptionCourseLabel);
        hboxnbHourTotalCourseInfo.getChildren().add(nbHourTotalLabel);
        hboxidTeacherCourseInfo.getChildren().add(idTeacherLabel);

        hboxnameCourseInfo.getChildren().add(name);
        hboxdescriptionCourseInfo.getChildren().add(descriptionCourse);
        hboxnbHourTotalCourseInfo.getChildren().add(nbHourTotal);
        hboxidTeacherCourseInfo.getChildren().add(idTeacher);

        hboxnameCourseInfo.setAlignment(Pos.CENTER);
        hboxdescriptionCourseInfo.setAlignment(Pos.CENTER);
        hboxnbHourTotalCourseInfo.setAlignment(Pos.CENTER);
        hboxidTeacherCourseInfo.setAlignment(Pos.CENTER);

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateCourse = new Button("Update");
        hboxupdateButton.getChildren().add(btnUpdateCourse);
        hboxupdateButton.setAlignment(Pos.CENTER);

        vboxInfoCourse.getChildren().addAll(hboxCourseInfo, hboxnameCourseInfo,hboxdescriptionCourseInfo,hboxnbHourTotalCourseInfo,hboxidTeacherCourseInfo, hboxupdateButton);
        vboxInfoCourse.setSpacing(10);
        vboxInfoCourse.setPadding( new Insets(100, 0, 0, 75));


        btnAddCourse.setOnAction(event -> {
            createTabCourse(tabCourse);
        });

        btnUpdateCourse.setOnAction(event ->{
            SelectionModel<CourseType> selectedDeleteCourse = list.getSelectionModel();
            if (selectedDeleteCourse.getSelectedItem() != null) {
                updateTabCourse(tabCourse, selectedDeleteCourse.getSelectedItem().getName(),selectedDeleteCourse.getSelectedItem().getDescription(),selectedDeleteCourse.getSelectedItem().getNbTotalHour(),selectedDeleteCourse.getSelectedItem().getIdTeacher(), selectedDeleteCourse.getSelectedItem().getId());
            }
        });

        btnDeleteCourse.setOnAction(event -> {
            SelectionModel<CourseType> selectedDeleteCourse = list.getSelectionModel();
            if (selectedDeleteCourse.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You are sure to delete a course", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridCourseVisu.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeleteCourse(selectedDeleteCourse.getSelectedItem().getId());
                }
            }

        });

        list.setOnMouseClicked(event -> {
            gridCourseVisu.getChildren().remove(vboxInfoCourse);
            gridCourseVisu.add(vboxInfoCourse, 2, 2);
            System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
            SelectionModel<CourseType> selectedCourse = list.getSelectionModel();
            name.setText(selectedCourse.getSelectedItem().getName());
            descriptionCourse.setText(selectedCourse.getSelectedItem().getDescription());
            nbHourTotal.setText(Integer.toString(selectedCourse.getSelectedItem().getNbTotalHour()));
            idTeacher.setText(Integer.toString(selectedCourse.getSelectedItem().getIdTeacher()));
        });



        return gridCourseVisu;
}

	protected Tab createTabCourse(Tab TabCourse){
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
	
	protected void setCourseTab(){
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabCourse());


    }
	
	private void updateTabCourse(Tab tabCourse2, String name, String description, int nbTotalHour, int idTeacher,
			int id) {
		// TODO Auto-generated method stub
		
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
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getCourses(List<CourseType> courses) {
		
	}
}
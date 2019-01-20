package UI;

import Types.*;
import client.CoreClient;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Types.UserType;

import java.util.List;


/**
 * This UI is for when a Student wants to use this application.
 * @author Yvan Sanson
 * @author Marie Salelles
 * @author Solene Serafin
 * @author Aubin Abadie
 */
public class StudentUI extends UI  {
    private Scene principalStudentScene;
    private Tab recordTab;

    public StudentUI(Stage primaryStage, String login, int id, CoreClient client){
        this.primaryStage = primaryStage;
        this.login = login;
        this.userID = id;
        this.client=client;
    }

    public Scene getPrincipalStudentScene() {
        return principalStudentScene;
    }

    public void setPrincipalStudentScene(Scene principalStudentScene) {
        this.principalStudentScene = principalStudentScene;
    }

    /**
     * This function creates the principal scene (view) for the student.
     * It is called by the startUI when a student logs in.
     * @return the principal scene
     */
    public Scene createPrincipalStudentScene(){
        this.primaryStage.setTitle("LearnTogether for Students");
        BorderPane studentScene = new BorderPane();
        //Create the top bar
        VBox topBar = new VBox();
        HBox titleBar = new HBox();
        Text title = new Text("Learn Together -- Student side");
        Text user = new Text("Connected as: " + login);
        title.setFont(Font.font("Cambria", 20));
        titleBar.getChildren().addAll(title, user);

        //Create the Tabs
        TabPane tabPane = new TabPane();
        
        Tab tabProfile = createProfileTab();
        
        Tab tabSchedule = new Tab();
        tabSchedule.setText("Schedule");
        tabSchedule.setClosable(false);

        Tab tabRecords = createRecordTab();


        Tab tabDiary = new Tab();
        tabDiary.setText("Diary");
        tabDiary.setClosable(false);

        Tab tabChat = createChatTab();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);

        

        topBar.getChildren().addAll(titleBar, tabPane);
        studentScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalStudentScene = new Scene(studentScene, 900, 700);
        return principalStudentScene;
    }


    @Override
    public void getRooms(List<RoomType> rooms) {

    }

	@Override
	public void getCourses(List<CourseType> courses) {
		
	}
	
	@Override
	public void setUser(UserType user) {
		this.user = user;
		
	}

    @Override
    public void getDepartment(List<DepartmentType> dep) {

    }
    @Override
    public void getTeacher(List<TeacherType> teacher) {

    }

    @Override
    public void getPromo(List<PromotionType> promo) {

    }

    @Override
    public void getClasses(List<ClassType> classes) {

    }

	@Override
	public void getUsers(List<UserType> users) {
		
	}

    @Override
    public void getAdmin(List<AdminType> adm) {

    }
}

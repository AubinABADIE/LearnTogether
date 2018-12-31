package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 */
public class TeacherUI extends UI {

	private Scene princpalTeacherScene;
    /**
     * Default constructor
     */
    public TeacherUI(Stage primaryStage, String login, int id) {
		this.primaryStage = primaryStage;
		this.login = login;
		this.userID = id;
    }

	public Scene getPrincpalTeacherScene() {
		return princpalTeacherScene;
	}

	public void setPrincpalTeacherScene(Scene princpalTeacherScene) {
		this.princpalTeacherScene = princpalTeacherScene;
	}

	public Scene createPrincipalTeacherScene(){
		BorderPane root = new BorderPane();
		HBox titleBar = new HBox();
		Text title = new Text("Learn Together -- Teacher side");
		title.setFont(Font.font("Cambria", 20));
		root.setTop(titleBar);
		titleBar.setSpacing(20);
		titleBar.setPadding(new Insets(15, 12, 15, 12));

		princpalTeacherScene = new Scene(root, 900, 700);
		return princpalTeacherScene;
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
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
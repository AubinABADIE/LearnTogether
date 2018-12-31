package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author Yvan Sanson
 * @author Marie Salelles
 * @author Solene Serafin
 * @author Aubin Abadie
 */
public class StudentUI extends UI  {
	private Scene principalStudentScene;

	public StudentUI(Stage primaryStage, String login, int id){
		this.primaryStage = primaryStage;
		this.login = login;
		this.userID = id;
	}

	public Scene getPrincipalStudentScene() {
		return principalStudentScene;
	}

	public void setPrincipalStudentScene(Scene principalStudentScene) {
		this.principalStudentScene = principalStudentScene;
	}

	public Scene createPrincipalStudentScene(){
		this.primaryStage.setTitle("LearnTogether for Students");
		BorderPane studentScene = new BorderPane();
		HBox titleBar = new HBox();
		Text title = new Text("Learn Together -- Student side");
		Text user = new Text("Connecté en tant que : " + login);
		title.setFont(Font.font("Cambria", 20));
		titleBar.getChildren().addAll(title, user);
		studentScene.setTop(titleBar);
		titleBar.setSpacing(20);
		titleBar.setPadding(new Insets(15, 12, 15, 12));

		principalStudentScene = new Scene(studentScene, 900, 700);
		return principalStudentScene;
	}

	public void addUIControls(BorderPane borderPane){

	}
}
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
public class AdminUI extends TeacherUI {

    private Scene principalAdminScene;
    /**
     * Default constructor
     */
    public AdminUI(Stage primaryStage, String login, int id) {
        super(primaryStage, login, id);
    }

    public Scene getPrincipalAdminScene() {
        return principalAdminScene;
    }

    public void setPrincipalAdminScene(Scene principalAdminScene) {
        this.principalAdminScene = principalAdminScene;
    }

    public Scene createPrincipalAdminScene(){
        BorderPane root = new BorderPane();
        HBox titleBar = new HBox();
        Text title = new Text("Learn Together -- Admin side");
        title.setFont(Font.font("Cambria", 20));
        root.setTop(titleBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalAdminScene = new Scene(root, 900, 700);
        return principalAdminScene;
    }

    public void addUIControls(BorderPane borderPane){

    }

}
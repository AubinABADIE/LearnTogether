package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

/**
 * 
 */
public class SuperAdminUI extends AdminUI {
    private Scene principalSuperAdminScene;
    /**
     * Default constructor
     */
    public SuperAdminUI(Stage primaryStage, String login, int id) {
        super(primaryStage, login, id);
    }

    public Scene getPrincipalSuperAdminScene() {
        return principalSuperAdminScene;
    }

    public void setPrincipalSuperAdminScene(Scene principalSuperAdminScene) {
        this.principalSuperAdminScene = principalSuperAdminScene;
    }

    public Scene createPrincipalSuperAdminScene(){
        BorderPane root = new BorderPane();
        HBox titleBar = new HBox();
        Text title = new Text("Learn Together -- Super admin side");
        title.setFont(Font.font("Cambria", 20));
        root.setTop(titleBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalSuperAdminScene = new Scene(root, 900, 700);
        return principalSuperAdminScene;
    }

    public void addUIControls(BorderPane borderPane){

    }

}
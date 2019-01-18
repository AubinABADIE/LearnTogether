package UI;

import client.CoreClient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This UI is when a Super Admin wants to use this application.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class SuperAdminUI extends AdminUI {
    private Scene principalSuperAdminScene;
    /**
     * Default constructor
     */
    public SuperAdminUI(Stage primaryStage, String login, int id, CoreClient client) {
        super(primaryStage, login, id, client);
    }

    public Scene getPrincipalSuperAdminScene() {
        return principalSuperAdminScene;
    }

    public void setPrincipalSuperAdminScene(Scene principalSuperAdminScene) {
        this.principalSuperAdminScene = principalSuperAdminScene;
    }

    public Scene createPrincipalSuperAdminScene(){
    	this.primaryStage.setTitle("LearnTogether for Admins");
        BorderPane adminScene = new BorderPane();
        //Create the top bar
        VBox topBar = new VBox();
        HBox titleBar = new HBox();
        Text title = new Text("Learn Together -- Admin side");
        Text user = new Text("Connected as: " + login);
        title.setFont(Font.font("Cambria", 20));
        titleBar.getChildren().addAll(title, user);

        //Create the Tabs
        tabPane = new TabPane();

        tabProfile = createProfileTab();

        tabSchedule = new Tab();
        tabSchedule.setText("Schedule");
        tabSchedule.setClosable(false);

        tabRecords = createRecordTab();
        tabRecords.setText("Record");
        tabRecords.setClosable(false);

        tabDiary = new Tab();
        tabDiary.setText("Diary");
        tabDiary.setClosable(false);

        tabChat = createChatTab();

        tabRoom= tabRoom();
        
        tabCourse = tabCourse();

        tabDepartment =  tabDepartment();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabRoom);
        tabPane.getTabs().add(tabCourse);
        tabPane.getTabs().add(tabDepartment);

        topBar.getChildren().addAll(titleBar, tabPane);
        adminScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalSuperAdminScene = new Scene(adminScene, 900, 700);
        return principalSuperAdminScene;
    }

    public void addUIControls(BorderPane borderPane){

    }

}
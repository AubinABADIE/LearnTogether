package UI;

import client.CoreClient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
     * This funciton creates the principal scene (view) for the student.
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

        Tab tabProfile= new Tab();
        tabProfile.setText("Profile");
        tabProfile.setClosable(false);

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

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);

        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Tab" ));
        hbox.setAlignment(Pos.CENTER);
        tabProfile.setContent(hbox);

        topBar.getChildren().addAll(titleBar, tabPane);
        studentScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalStudentScene = new Scene(studentScene, 900, 700);
        return principalStudentScene;
    }

    private Tab createChatTab(){
        Tab chatTab = new Tab();
        chatTab.setText("Chat");
        chatTab.setClosable(false);
        BorderPane chatPane = new BorderPane();
        HBox newConv = new HBox();
        Label startConvLabel = new Label("Email for a new conversation: ");
        TextField startConvEmail = new TextField();
        newConv.getChildren().addAll(startConvLabel, startConvEmail);

        ScrollPane conversations = new ScrollPane();
        conversations.setFitToWidth(true);
        conversations.setFitToHeight(true);
        TextArea convo = new TextArea();
        convo.setEditable(false);
        convo.setPrefWidth(conversations.getHvalue());
        convo.setWrapText(true);
        conversations.setContent(convo);

        VBox conversationList = new VBox();
        Label conversationListLabel = new Label("All conversations");

        conversationList.getChildren().addAll(conversationListLabel);

        chatPane.setTop(newConv);
        chatPane.setCenter(conversations);
        chatPane.setLeft(conversationList);


        return chatTab;
    }

}

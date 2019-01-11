package UI;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    /**
     * This method create the principal admin scene
     */

    public Scene createPrincipalAdminScene(){
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

        Tab tabChat = new Tab();
        tabChat.setText("Chat");
        tabChat.setClosable(false);

        Tab tabRoom= createTabRoom();


        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabRoom);

        HBox hbox = new HBox();
        hbox.getChildren().add(new Label("Tab" ));
        hbox.setAlignment(Pos.CENTER);
        tabProfile.setContent(hbox);

        topBar.getChildren().addAll(titleBar, tabPane);
        adminScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalAdminScene = new Scene(adminScene, 900, 700);
        return principalAdminScene;
    }

    /**
     * This method create the room tab in the principal admin scene
     */
    private Tab createTabRoom(){

        Tab tabRoom = new Tab();
        tabRoom.setText("Room");
        tabRoom.setClosable(false);

        // labels
        Label nameLabel = new Label("Name of room : ");
        Label capacityLabel = new Label("Capacity : ");
        Label buildingLabel = new Label("Building : ");
        Label projLabel = new Label("There is a projector : ");
        Label compLabel = new Label("There are computers : ");
        Label descLabel = new Label("Room description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextField capacityField = new TextField();
        capacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                capacityField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        TextField buildingField = new TextField();
        buildingField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                buildingField.setText(newValue.replaceAll("[^\\d]", ""));
        });

        RadioButton py = new RadioButton();
        py.setText("Yes");
        RadioButton pn = new RadioButton();
        pn.setText("No");

        ToggleGroup tp = new ToggleGroup();

        py.setToggleGroup(tp);
        pn.setToggleGroup(tp);
        tp.selectToggle(py);

        RadioButton cy = new RadioButton();
        cy.setText("Yes");
        RadioButton cn = new RadioButton();
        cn.setText("No");

        ToggleGroup tp2 = new ToggleGroup();

        cy.setToggleGroup(tp2);
        cn.setToggleGroup(tp2);
        tp2.selectToggle(cn);

        TextArea descriptionField = new TextArea();

        //grid pane
        GridPane gridRoom = new GridPane();
        gridRoom.setHgap(10);
        gridRoom.setVgap(10);
        gridRoom.setPadding(new Insets(10,10,10,10));

        //Hbox
        HBox nameRoom = new HBox();
        HBox capacityRoom = new HBox();
        HBox buildingRoom = new HBox();
        HBox projectorRoom = new HBox();
        HBox computerRoom = new HBox();
        HBox descriptionRoom = new HBox();

        // add form in hbox
        nameRoom.getChildren().addAll(nameLabel, nameField);
        capacityRoom.getChildren().addAll(capacityLabel, capacityField) ;
        buildingRoom.getChildren().addAll(buildingLabel, buildingField) ;
        projectorRoom.getChildren().addAll(projLabel, py, pn) ;
        computerRoom.getChildren().addAll(compLabel, cy, cn) ;
        descriptionRoom.getChildren().addAll(descLabel, descriptionField) ;

        //add hbox in gridpane
        gridRoom.add(nameRoom, 1, 0);
        gridRoom.add(capacityRoom, 1, 2);
        gridRoom.add(buildingRoom, 1, 5);
        gridRoom.add(projectorRoom, 1, 7);
        gridRoom.add(computerRoom, 1, 9);
        gridRoom.add(descriptionRoom, 1, 11);

        //add gridpane in tab
        tabRoom.setContent(gridRoom);

        //add button

        Button okCreate = new Button("Validate");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridRoom.add(okCreate, 0, 13, 1, 1);
        gridRoom.setHalignment(okCreate, HPos.RIGHT);
        gridRoom.setMargin(okCreate, new Insets(20, 0,20,0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridRoom.add(cancelCreate, 2, 13, 1, 1);
        gridRoom.setHalignment(cancelCreate, HPos.RIGHT);
        gridRoom.setMargin(cancelCreate, new Insets(20, 0,20,0));

        okCreate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridRoom.getScene().getWindow(), "Form Error!", "Please enter room name");
                return;
            }
            if (capacityField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridRoom.getScene().getWindow(), "Form Error!", "Please enter room capacity");
                return;
            }
            if (buildingField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridRoom.getScene().getWindow(), "Form Error!", "Please enter room building");
                return;
            }
            boolean hasProjector;
            boolean hasComputer;
            if(((RadioButton) tp.getSelectedToggle()).getText().equals("Yes"))
                hasProjector = true;
            else
                hasProjector = false;

            if(((RadioButton) tp2.getSelectedToggle()).getText().equals("Yes"))
                hasComputer = true;
            else
                hasComputer = false;

            client.handleCreateRoom(nameField.getText(), Integer.parseInt(capacityField.getText()),Integer.parseInt(buildingField.getText()), hasProjector, hasComputer, descriptionField.getText());
        });

        cancelCreate.setOnAction(event -> {
            nameField.setText("");
            capacityField.setText("");
            buildingField.setText("");
            descriptionField.setText("");
        });

        return tabRoom;
    }

    public void addUIControls(BorderPane borderPane){

    }

}
package UI;

import Types.*;
import client.CoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;

//StaffAdmin@umontpellier.fr
//AdmStaff

/**
 * This UI is for when an Admin connects to this application.
 *
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class AdminUI extends TeacherUI {

    private Scene principalAdminScene;
    protected TabPane tabPane;
    protected Tab tabProfile;
    protected Tab tabSchedule;
    protected Tab tabRecords;
    protected Tab tabDiary;
    protected Tab tabChat;
    protected Tab tabCourse;
    protected Tab tabRoom;
    protected Tab tabDepartment;
	protected Tab tabUser;
    protected Tab tabAdmManagement;
	protected ObservableList<RoomType> roomNames;
    protected ObservableList<DepartmentType> depNames;
    protected ObservableList<PromotionType> promoNames;
    protected ObservableList<ClassType> classNames;
    protected ObservableList<UserType> userNames;
    protected ObservableList<TeacherType> teacherNames;
    protected ObservableList<AdminType> admNames;

    /**
     * Default constructor
     */
    public AdminUI(Stage primaryStage, String login, int id, CoreClient client) {
        super(primaryStage, login, id, client);
    }

    public Tab getTabRoom() {
        return tabRoom;
    }

    public Scene getPrincipalAdminScene() {
        return principalAdminScene;
    }

    public void setPrincipalAdminScene(Scene principalAdminScene) {
        this.principalAdminScene = principalAdminScene;
    }

    public void setRooms(List<RoomType> roomList) {
        roomNames.setAll(roomList);
    }

    public void setDepartment(List<DepartmentType> depList) {
        depNames.setAll(depList);
    }

    public void setPromo(List<PromotionType> promoList) {
        promoNames.setAll(promoList);
    }

    public void setClasses(List<ClassType> classes) {
        classNames.setAll(classes);
    }

    public void setTeacher(List<TeacherType> teacherList) {
        teacherNames.setAll(teacherList);
    }
    
    public void setUsers(List<UserType> users) {
    	userNames.setAll(users);
    }
    public void setAdmin(List<AdminType> adm) {
        admNames.setAll(adm);
    }

    /**
     * This method create the principal admin scene
     */

    public Scene createPrincipalAdminScene() {
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
        tabDepartment = tabDepartment();
        tabUser = createTabUser();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabRoom);
        tabPane.getTabs().add(tabCourse);
        tabPane.getTabs().add(tabDepartment);
        tabPane.getTabs().add(tabUser);

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


    protected Tab tabRoom() {

        Tab tabRoom = new Tab();
        tabRoom.setText("Room");
        tabRoom.setClosable(false);


        tabRoom.setContent(roomRead(tabRoom));
        return tabRoom;
    }

    protected GridPane roomRead(Tab tabRoom) {

        /*add list of room*/
        client.getRooms();
        ListView<RoomType> list = new ListView<>();
        roomNames = FXCollections.observableArrayList();
        roomNames.addListener((ListChangeListener<RoomType>) c -> {
            list.setItems(roomNames);
        });

        Image addRoom = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addRoomView = new ImageView(addRoom);
        addRoomView.setFitHeight(15);
        addRoomView.setFitWidth(15);

        //create button add
        Button btnAddRoom = new Button("Add");
        btnAddRoom.setGraphic(addRoomView);//setting icon to button

        //delete button
        Image deleteRoom = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteRoomView = new ImageView(deleteRoom);
        deleteRoomView.setFitHeight(12);
        deleteRoomView.setFitWidth(12);

        //create button delete
        Button btnDeleteRoom = new Button("Delete");
        btnDeleteRoom.setGraphic(deleteRoomView);//setting icon to button

        // add in hbox buttons and title
        HBox hboxButtonRoom = new HBox();

        Text title = new Text("Room : ");
        title.setFont(Font.font(20));
        hboxButtonRoom.getChildren().add(title);
        hboxButtonRoom.getChildren().add(btnAddRoom);
        hboxButtonRoom.getChildren().add(btnDeleteRoom);
        hboxButtonRoom.setSpacing(5);

        list.setItems(roomNames);
        System.out.println(roomNames);
        list.setPrefWidth(350);
        list.setPrefHeight(500);

        // left vbox
        VBox vboxListRoom = new VBox();
        vboxListRoom.getChildren().add(list);

        //grid pane
        GridPane gridRoomVisu = new GridPane();
        gridRoomVisu.setHgap(10);
        gridRoomVisu.setVgap(10);
        gridRoomVisu.setPadding(new Insets(10, 10, 10, 10));

        gridRoomVisu.add(hboxButtonRoom, 1, 0);
        gridRoomVisu.add(vboxListRoom, 1, 2);

        /*creation of the info vbox of one room*/
        VBox vboxInfoRoom = new VBox();

        //title of column
        HBox hboxRoomInfo = new HBox();
        Text titleInfo = new Text("Room information : ");
        titleInfo.setFont(Font.font(20));
        hboxRoomInfo.getChildren().add(titleInfo);
        hboxRoomInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxnameRoomInfo = new HBox();
        HBox hboxcapacityRoomInfo = new HBox();
        HBox hboxbuildingRoomInfo = new HBox();
        HBox hboxprojectorRoomInfo = new HBox();
        HBox hboxcomputerRoomInfo = new HBox();
        HBox hboxdescRoomInfo = new HBox();
        Label nameLabel = new Label("Name of room : ");
        Label capacityLabel = new Label("Capacity room : ");
        Label buildingLabel = new Label("Room building number : ");
        Label hasComputerLabel = new Label(" There are computers : ");
        Label hasProjectorLabel = new Label(" There is projector : ");
        Label descriptionLabel = new Label("Room description : ");
        Text name = new Text(" ");
        Text capacity = new Text(" ");
        Text building = new Text(" ");
        Text projector = new Text(" ");
        Text computer = new Text(" ");
        Text description = new Text(" ");


        hboxnameRoomInfo.getChildren().add(nameLabel);
        hboxcapacityRoomInfo.getChildren().add(capacityLabel);
        hboxbuildingRoomInfo.getChildren().add(buildingLabel);
        hboxcomputerRoomInfo.getChildren().add(hasComputerLabel);
        hboxprojectorRoomInfo.getChildren().add(hasProjectorLabel);
        hboxdescRoomInfo.getChildren().add(descriptionLabel);
        hboxnameRoomInfo.getChildren().add(name);
        hboxcapacityRoomInfo.getChildren().add(capacity);
        hboxbuildingRoomInfo.getChildren().add(building);
        hboxprojectorRoomInfo.getChildren().add(projector);
        hboxcomputerRoomInfo.getChildren().add(computer);
        hboxdescRoomInfo.getChildren().add(description);

        hboxnameRoomInfo.setAlignment(Pos.CENTER);
        hboxcapacityRoomInfo.setAlignment(Pos.CENTER);
        hboxbuildingRoomInfo.setAlignment(Pos.CENTER);
        hboxcomputerRoomInfo.setAlignment(Pos.CENTER);
        hboxprojectorRoomInfo.setAlignment(Pos.CENTER);
        hboxdescRoomInfo.setAlignment(Pos.CENTER);

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateRoom = new Button("Update");
        hboxupdateButton.getChildren().add(btnUpdateRoom);
        hboxupdateButton.setAlignment(Pos.CENTER);

        vboxInfoRoom.getChildren().addAll(hboxRoomInfo, hboxnameRoomInfo, hboxcapacityRoomInfo, hboxbuildingRoomInfo, hboxprojectorRoomInfo, hboxcomputerRoomInfo, hboxdescRoomInfo, hboxupdateButton);
        vboxInfoRoom.setSpacing(10);
        vboxInfoRoom.setPadding(new Insets(100, 0, 0, 75));


        btnAddRoom.setOnAction(event -> {
            createTabRoom(tabRoom);
        });

        btnUpdateRoom.setOnAction(event -> {
            SelectionModel<RoomType> selectedDeleteRoom = list.getSelectionModel();
            if (selectedDeleteRoom.getSelectedItem() != null) {
                updateTabRoom(tabRoom, selectedDeleteRoom.getSelectedItem().getName(), selectedDeleteRoom.getSelectedItem().getCapacity(), selectedDeleteRoom.getSelectedItem().getBuilding(), selectedDeleteRoom.getSelectedItem().isHasProjector(), selectedDeleteRoom.getSelectedItem().isHasComputer(), selectedDeleteRoom.getSelectedItem().getDescription(), selectedDeleteRoom.getSelectedItem().getId());
            }
        });

        btnDeleteRoom.setOnAction(event -> {
            SelectionModel<RoomType> selectedDeleteRoom = list.getSelectionModel();
            if (selectedDeleteRoom.getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the room?", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridRoomVisu.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeleteRoom(selectedDeleteRoom.getSelectedItem().getId());
                }
            }

        });

        list.setOnMouseClicked(event -> {
            gridRoomVisu.getChildren().remove(vboxInfoRoom);
            gridRoomVisu.add(vboxInfoRoom, 2, 2);
            System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
            SelectionModel<RoomType> selectedRoom = list.getSelectionModel();
            name.setText(selectedRoom.getSelectedItem().getName());
            capacity.setText(Integer.toString(selectedRoom.getSelectedItem().getCapacity()));
            building.setText(Integer.toString(selectedRoom.getSelectedItem().getBuilding()));
            if (selectedRoom.getSelectedItem().isHasProjector()) {
                projector.setText("Yes");
            } else {
                projector.setText("No");
            }
            if (selectedRoom.getSelectedItem().isHasComputer()) {
                computer.setText("Yes");
            } else {
                computer.setText("No");
            }
            description.setText(selectedRoom.getSelectedItem().getDescription());

        });


        return gridRoomVisu;
    }

    protected GridPane createTabRoom(Tab tabRoom) {
        //return button
        Image returnRoom = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnRoomView = new ImageView(returnRoom);
        returnRoomView.setFitHeight(15);
        returnRoomView.setFitWidth(15);

        //create return button
        Button btnReturnRoom = new Button();
        btnReturnRoom.setGraphic(returnRoomView);//setting icon to button

        HBox returnBox = new HBox();

        returnBox.getChildren().add(btnReturnRoom);
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
            if (!newValue.matches("\\d*"))
                capacityField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        TextField buildingField = new TextField();
        buildingField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
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
        gridRoom.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameRoom = new HBox();
        HBox capacityRoom = new HBox();
        HBox buildingRoom = new HBox();
        HBox projectorRoom = new HBox();
        HBox computerRoom = new HBox();
        HBox descriptionRoom = new HBox();

        // add form in hbox
        nameRoom.getChildren().addAll(nameLabel, nameField);
        capacityRoom.getChildren().addAll(capacityLabel, capacityField);
        buildingRoom.getChildren().addAll(buildingLabel, buildingField);
        projectorRoom.getChildren().addAll(projLabel, py, pn);
        computerRoom.getChildren().addAll(compLabel, cy, cn);
        descriptionRoom.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridRoom.add(returnBox, 2, 0);

        gridRoom.add(nameRoom, 1, 0);
        gridRoom.add(capacityRoom, 1, 2);
        gridRoom.add(buildingRoom, 1, 5);
        gridRoom.add(projectorRoom, 1, 7);
        gridRoom.add(computerRoom, 1, 9);
        gridRoom.add(descriptionRoom, 1, 11);

        //add gridpane in tab
        tabRoom.setContent(gridRoom);

        //add button

        Button okCreate = new Button("Create");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridRoom.add(okCreate, 0, 13, 1, 1);
        gridRoom.setHalignment(okCreate, HPos.RIGHT);
        gridRoom.setMargin(okCreate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridRoom.add(cancelCreate, 2, 13, 1, 1);
        gridRoom.setHalignment(cancelCreate, HPos.RIGHT);
        gridRoom.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        btnReturnRoom.setOnAction(event -> {
            tabRoom.setContent(roomRead(tabRoom));
        });

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
            if (((RadioButton) tp.getSelectedToggle()).getText().equals("Yes"))
                hasProjector = true;
            else
                hasProjector = false;

            if (((RadioButton) tp2.getSelectedToggle()).getText().equals("Yes"))
                hasComputer = true;
            else
                hasComputer = false;

            client.handleCreateRoom(nameField.getText(), Integer.parseInt(capacityField.getText()), Integer.parseInt(buildingField.getText()), hasProjector, hasComputer, descriptionField.getText());
            nameField.setText("");
            capacityField.setText("");
            buildingField.setText("");
            descriptionField.setText("");

        });

        cancelCreate.setOnAction(event -> {
            tabRoom.setContent(roomRead(tabRoom));
        });


        return gridRoom;
    }

    protected GridPane updateTabRoom(Tab tabRoom, String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description, int id) {

        //return button
        Image returnRoom = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnRoomView = new ImageView(returnRoom);
        returnRoomView.setFitHeight(15);
        returnRoomView.setFitWidth(15);

        //create return button
        Button btnReturnRoom = new Button();
        btnReturnRoom.setGraphic(returnRoomView);//setting icon to button

        HBox returnBox = new HBox();

        returnBox.getChildren().add(btnReturnRoom);

        // labels
        Label nameLabel = new Label("Name of room: ");
        Label capacityLabel = new Label("Capacity: ");
        Label buildingLabel = new Label("Building: ");
        Label projLabel = new Label("There is a projector: ");
        Label compLabel = new Label("There are computers: ");
        Label descLabel = new Label("Room description: ");

        // Add text Field
        TextField nameField = new TextField();
        nameField.setText(name);
        TextField capacityField = new TextField();
        capacityField.setText(Integer.toString(capacity));
        capacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                capacityField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        TextField buildingField = new TextField();
        buildingField.setText(Integer.toString(building));
        buildingField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                buildingField.setText(newValue.replaceAll("[^\\d]", ""));
        });

        RadioButton py = new RadioButton();
        py.setText("Yes");
        RadioButton pn = new RadioButton();
        pn.setText("No");

        ToggleGroup tp = new ToggleGroup();

        py.setToggleGroup(tp);
        pn.setToggleGroup(tp);
        if (hasProjector) {
            tp.selectToggle(py);
        } else {
            tp.selectToggle(pn);
        }


        RadioButton cy = new RadioButton();
        cy.setText("Yes");
        RadioButton cn = new RadioButton();
        cn.setText("No");

        ToggleGroup tp2 = new ToggleGroup();

        cy.setToggleGroup(tp2);
        cn.setToggleGroup(tp2);

        if (hasComputer) {
            tp2.selectToggle(cy);
        } else {
            tp2.selectToggle(cn);
        }


        TextArea descriptionField = new TextArea();
        descriptionField.setText(description);

        //grid pane
        GridPane gridUpadateRoom = new GridPane();
        gridUpadateRoom.setHgap(10);
        gridUpadateRoom.setVgap(10);
        gridUpadateRoom.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameRoom = new HBox();
        HBox capacityRoom = new HBox();
        HBox buildingRoom = new HBox();
        HBox projectorRoom = new HBox();
        HBox computerRoom = new HBox();
        HBox descriptionRoom = new HBox();

        // add form in hbox
        nameRoom.getChildren().addAll(nameLabel, nameField);
        capacityRoom.getChildren().addAll(capacityLabel, capacityField);
        buildingRoom.getChildren().addAll(buildingLabel, buildingField);
        projectorRoom.getChildren().addAll(projLabel, py, pn);
        computerRoom.getChildren().addAll(compLabel, cy, cn);
        descriptionRoom.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridUpadateRoom.add(returnBox, 2, 0);

        gridUpadateRoom.add(nameRoom, 1, 1);
        gridUpadateRoom.add(capacityRoom, 1, 2);
        gridUpadateRoom.add(buildingRoom, 1, 5);
        gridUpadateRoom.add(projectorRoom, 1, 7);
        gridUpadateRoom.add(computerRoom, 1, 9);
        gridUpadateRoom.add(descriptionRoom, 1, 11);

        //add gridpane in tab
        tabRoom.setContent(gridUpadateRoom);

        //add button

        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridUpadateRoom.add(okUpdate, 0, 13, 1, 1);
        gridUpadateRoom.setHalignment(okUpdate, HPos.RIGHT);
        gridUpadateRoom.setMargin(okUpdate, new Insets(20, 0, 20, 0));

        Button cancelUpdate = new Button("Cancel");
        cancelUpdate.setPrefHeight(40);
        cancelUpdate.setDefaultButton(false);
        cancelUpdate.setPrefWidth(100);
        gridUpadateRoom.add(cancelUpdate, 2, 13, 1, 1);
        gridUpadateRoom.setHalignment(cancelUpdate, HPos.RIGHT);
        gridUpadateRoom.setMargin(cancelUpdate, new Insets(20, 0, 20, 0));

        btnReturnRoom.setOnAction(event -> {
            tabRoom.setContent(roomRead(tabRoom));
        });

        okUpdate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpadateRoom.getScene().getWindow(), "Form Error!", "Please enter room name");
                return;
            }
            if (capacityField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpadateRoom.getScene().getWindow(), "Form Error!", "Please enter room capacity");
                return;
            }
            if (buildingField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpadateRoom.getScene().getWindow(), "Form Error!", "Please enter room building");
                return;
            }
            boolean hasProjectorUp;
            boolean hasComputerUp;
            if (((RadioButton) tp.getSelectedToggle()).getText().equals("Yes"))
                hasProjectorUp = true;
            else
                hasProjectorUp = false;

            if (((RadioButton) tp2.getSelectedToggle()).getText().equals("Yes"))
                hasComputerUp = true;
            else
                hasComputerUp = false;

            client.handleUpdateRoom(id, nameField.getText(), Integer.parseInt(capacityField.getText()), Integer.parseInt(buildingField.getText()), hasProjectorUp, hasComputerUp, descriptionField.getText());
            nameField.setText("");
            capacityField.setText("");
            buildingField.setText("");
            descriptionField.setText("");

        });

        cancelUpdate.setOnAction(event -> {
            tabRoom.setContent(roomRead(tabRoom));
        });

        return gridUpadateRoom;
    }

    public GridPane setRoomTab() {
        return roomRead(tabRoom);
    }


    public void addUIControls(BorderPane borderPane) {

    }


    /**
     * This method create the department tab in the principal admin scene
     */

    protected Tab tabDepartment() {

        Tab tabDepartment = new Tab();
        tabDepartment.setText("Students Groups");
        tabDepartment.setClosable(false);


        tabDepartment.setContent(departmentRead(tabDepartment));
        return tabDepartment;
    }

    protected GridPane departmentRead(Tab tabDepartment) {

        /*add list of DepartmentServices*/
        client.getDepartment();
        ListView<DepartmentType> list = new ListView<>();
        depNames = FXCollections.observableArrayList();
        depNames.addListener((ListChangeListener<DepartmentType>) c -> {
            list.setItems(depNames);
        });



        Image addDepartment = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addDepView = new ImageView(addDepartment);
        addDepView.setFitHeight(15);
        addDepView.setFitWidth(15);

        //create button add
        Button btnAddDep = new Button("Add-Dep");
        btnAddDep.setGraphic(addDepView);//setting icon to button

        Image deleteDep = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteDepView = new ImageView(deleteDep);
        deleteDepView.setFitHeight(12);
        deleteDepView.setFitWidth(12);

        //create button delete
        Button btnDeleteDep = new Button("Delete-Dep");
        btnDeleteDep.setGraphic(deleteDepView);//setting icon to button

        //create update button
        Button btnUpdateDe=new Button("Update-Dep");

        // add in hbox buttons and title
        HBox hboxButtonDep = new HBox();
        Text title = new Text("Department : ");
        hboxButtonDep.getChildren().addAll(title, btnAddDep, btnDeleteDep,btnUpdateDe);
        hboxButtonDep.setSpacing(5);

        list.setItems(depNames);
        System.out.println(depNames);
        list.setPrefWidth(300);
        list.setPrefHeight(300);

        // left vbox
        Text labelHeader = new Text("Departments");
        VBox vboxListDep = new VBox();
        vboxListDep.getChildren().addAll(labelHeader,list);

        //title of column
        HBox hboxDepInfo = new HBox();
        Text titleInfo = new Text("Department information : ");
        titleInfo.setFont(Font.font(20));
        hboxDepInfo.getChildren().add(titleInfo);
        hboxDepInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxnameDepInfo = new HBox();
        HBox hboxteacherDepInfo = new HBox();
        HBox hboxdescDepInfo = new HBox();
        Label nameLabel = new Label("Name of Departement : ");
        Label teacherLabel = new Label("Referent teacher: ");
        Label descriptionLabel = new Label("Department description : ");
        Text name = new Text(" ");
        Text teacher = new Text(" ");
        Text description = new Text(" ");


        hboxnameDepInfo.getChildren().addAll(nameLabel, name);
        hboxteacherDepInfo.getChildren().addAll(teacherLabel, teacher);
        hboxdescDepInfo.getChildren().addAll(descriptionLabel, description);
        hboxnameDepInfo.setAlignment(Pos.CENTER);
        hboxteacherDepInfo.setAlignment(Pos.CENTER);
        hboxdescDepInfo.setAlignment(Pos.CENTER);


        //PROMOTION
        /*add list of PromotionServices*/
        client.getPromo();
        ListView<PromotionType> listPromo = new ListView<>();
        promoNames = FXCollections.observableArrayList();
        promoNames.addListener((ListChangeListener<PromotionType>) c -> {
            listPromo.setItems(promoNames);
        });


        Image addPromo = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addPromoView = new ImageView(addPromo);
        addPromoView.setFitHeight(15);
        addPromoView.setFitWidth(15);

        //create button add
        Button btnAddPromo = new Button("Add-Promo");
        btnAddPromo.setGraphic(addPromoView);//setting icon to button

        //delete button
        Image deletePromo = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deletePromoView = new ImageView(deletePromo);
        deletePromoView.setFitHeight(12);
        deletePromoView.setFitWidth(12);

        //create button delete
        Button btnDeletePromo = new Button("Delete-Promo");
        btnDeletePromo.setGraphic(deletePromoView);//setting icon to button

        //create update button
        Button btnUpdatePromo = new Button("Update-Promo");

        // add in hbox buttons and title
        HBox hboxButtonPromo = new HBox();
        Text titlePromo = new Text("Promotion : ");
        hboxButtonPromo.getChildren().addAll(titlePromo, btnAddPromo, btnDeletePromo, btnUpdatePromo);
        hboxButtonPromo.setSpacing(5);


        listPromo.setItems(promoNames);
        System.out.println(promoNames);
        listPromo.setPrefWidth(300);
        listPromo.setPrefHeight(300);

        // left vbox
        Text labelHeaderPromo = new Text("Promotions");
        VBox vboxListPromo = new VBox();
        vboxListPromo.getChildren().addAll(labelHeaderPromo,listPromo);


        //title of column
        HBox hboxPromoInfo = new HBox();
        Text titleInfoPromo = new Text("Promotion information : ");
        titleInfoPromo.setFont(Font.font(20));
        hboxPromoInfo.getChildren().add(titleInfoPromo);
        hboxPromoInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxdepPromoInfo = new HBox();
        HBox hboxnamePromoInfo = new HBox();
        HBox hboxgraduationPromoInfo = new HBox();
        HBox hboxdescPromoInfo = new HBox();
        Label depLabelPromo = new Label("Referent Department : ");
        Label nameLabelPromo = new Label("Name of Promotion : ");
        Label graduationLabelPromo = new Label("Graduation: ");
        Label descriptionLabelPromo = new Label("Promotion description : ");
        Text depPromo = new Text(" ");
        Text namePromo = new Text(" ");
        Text graduationPromo = new Text(" ");
        Text descriptionPromo = new Text(" ");

        hboxdepPromoInfo.getChildren().addAll(depLabelPromo,depPromo);
        hboxnamePromoInfo.getChildren().addAll(nameLabelPromo,namePromo);
        hboxgraduationPromoInfo.getChildren().addAll(graduationLabelPromo,graduationPromo);
        hboxdescPromoInfo.getChildren().addAll(descriptionLabelPromo,descriptionPromo);
        hboxdepPromoInfo.setAlignment(Pos.CENTER);
        hboxnamePromoInfo.setAlignment(Pos.CENTER);
        hboxgraduationPromoInfo.setAlignment(Pos.CENTER);
        hboxdescPromoInfo.setAlignment(Pos.CENTER);
        //

        /*add list of Classes*/
        client.getClasses();
        ListView<ClassType> listClass = new ListView<>();
        classNames = FXCollections.observableArrayList();
        classNames.addListener((ListChangeListener<ClassType>) c -> {
            listClass.setItems(classNames);
        });

        Image addClass = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addClassView = new ImageView(addClass);
        addClassView.setFitHeight(15);
        addClassView.setFitWidth(15);

        //create button add
        Button btnAddClass = new Button("Add-Class");
        btnAddClass.setGraphic(addClassView);//setting icon to button

        //delete button
        Image deleteClass = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteClassView = new ImageView(deleteClass);
        deleteClassView.setFitHeight(12);
        deleteClassView.setFitWidth(12);

        //create button delete
        Button btnDeleteClass = new Button("Delete-Class");
        btnDeleteClass.setGraphic(deleteClassView);//setting icon to button


        //create button update
        Button btnUpdateClass = new Button("Update-Class");


        listClass.setItems(classNames);
        System.out.println(classNames);
        listClass.setPrefWidth(300);
        listClass.setPrefHeight(300);

        // left vbox
        Text labelHeaderCl = new Text("Classes");
        VBox vboxListClass = new VBox();
        vboxListClass.getChildren().addAll(labelHeaderCl,listClass);


        // add in hbox buttons and title
        HBox hboxButtonClass = new HBox();
        Text titleClass = new Text("ClassServices : ");
        hboxButtonClass.getChildren().addAll(titleClass, btnAddClass, btnDeleteClass,btnUpdateClass);
        hboxButtonClass.setSpacing(5);

        // add in hbox list
        HBox hboxListDep = new HBox();
        hboxListDep.getChildren().addAll(vboxListDep, vboxListPromo, vboxListClass);

        //title of column
        HBox hboxClassInfo = new HBox();
        Text titleInfoClass = new Text("Class information : ");
        titleInfoClass.setFont(Font.font(20));
        hboxClassInfo.getChildren().add(titleInfoClass);
        hboxClassInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxdepClassInfo = new HBox();
        HBox hboxnameClassInfo = new HBox();
        HBox hboxdescClassInfo = new HBox();
        Label depLabelClass = new Label("Referent Promotion : ");
        Label nameLabelClass = new Label("Name of Class : ");
        Label descriptionLabelClass = new Label("Class description : ");
        Text depClass = new Text(" ");
        Text nameClass = new Text(" ");
        Text descriptionClass = new Text(" ");

        hboxdepClassInfo.getChildren().addAll(depLabelClass,depClass);
        hboxnameClassInfo.getChildren().addAll(nameLabelClass,nameClass);
        hboxdescClassInfo.getChildren().addAll(descriptionLabelClass,descriptionClass);
        hboxdepClassInfo.setAlignment(Pos.CENTER);
        hboxnameClassInfo.setAlignment(Pos.CENTER);
        hboxdescClassInfo.setAlignment(Pos.CENTER);
        //

        //grid pane
        GridPane gridDepV = new GridPane();
        gridDepV.setHgap(10);
        gridDepV.setVgap(10);
        gridDepV.setPadding(new Insets(10, 10, 10, 10));
        gridDepV.add(hboxButtonDep, 1, 2);
        gridDepV.add(hboxButtonPromo, 1, 4);
        gridDepV.add(hboxButtonClass, 1, 6);
        gridDepV.add(hboxListDep, 1, 9);



        /*creation of the info vbox of one department*/
        VBox vboxInfoDep = new VBox();
        vboxInfoDep.getChildren().addAll(hboxDepInfo, hboxnameDepInfo, hboxteacherDepInfo, hboxdescDepInfo);
        vboxInfoDep.setSpacing(10);
        vboxInfoDep.setPadding(new Insets(100, 0, 0, 75));
        HBox hboxID = new HBox();
        hboxID.getChildren().add(vboxInfoDep);

        /*creation of the info vbox of one promotion*/
        VBox vboxInfoPromo = new VBox();
        vboxInfoPromo.getChildren().addAll(hboxPromoInfo, hboxnamePromoInfo, hboxgraduationPromoInfo, hboxdescPromoInfo);
        vboxInfoPromo.setSpacing(10);
        vboxInfoPromo.setPadding(new Insets(100, 0, 0, 75));
        HBox hboxIP = new HBox();
        hboxIP.getChildren().add(vboxInfoPromo);

        /*creation of the info vbox of one class*/
        VBox vboxInfoClass = new VBox();
        vboxInfoClass.getChildren().addAll(hboxClassInfo, hboxnameClassInfo, hboxdepClassInfo, hboxdescClassInfo);
        vboxInfoClass.setSpacing(10);
        vboxInfoClass.setPadding(new Insets(100, 0, 0, 75));
        HBox hboxIC = new HBox();
        hboxIC.getChildren().add(vboxInfoClass);

        //Dep
        btnAddDep.setOnAction(event -> {
            createTabDepartment(tabDepartment);
        });


        btnDeleteDep.setOnAction(event -> {
            SelectionModel<DepartmentType> selectedDeleteDepartment = list.getSelectionModel();
            if (selectedDeleteDepartment.getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are sure to delete this department", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeleteDepartment(selectedDeleteDepartment.getSelectedItem().getIdDepartment());
                    ;
                }
            }

        });

        btnUpdateDe.setOnAction(event -> {
            SelectionModel<DepartmentType> selectedDeleteDep = list.getSelectionModel();
            if (selectedDeleteDep.getSelectedItem() != null) {
                updateTabDep(tabDepartment, selectedDeleteDep.getSelectedItem().getNameDep(), selectedDeleteDep.getSelectedItem().getRefTeacher(), selectedDeleteDep.getSelectedItem().getDescriptionDep(), selectedDeleteDep.getSelectedItem().getIdDepartment());
            }
        });

        list.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(hboxID);
            gridDepV.getChildren().remove(hboxIP);
            gridDepV.getChildren().remove(hboxIC);
            gridDepV.add(hboxID, 1, 7);
            listClass.getSelectionModel().clearSelection();
            listPromo.getSelectionModel().clearSelection();
            System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
            SelectionModel<DepartmentType> selectedDep = list.getSelectionModel();
            name.setText(selectedDep.getSelectedItem().getNameDep());
            teacher.setText(Integer.toString(selectedDep.getSelectedItem().getRefTeacher()));
            description.setText(selectedDep.getSelectedItem().getDescriptionDep());

        });

        //Promo
        btnAddPromo.setOnAction(event -> {
            createTabPromotion(tabDepartment);
        });


        btnDeletePromo.setOnAction(event -> {
            SelectionModel<PromotionType> selectedDeletePromo = listPromo.getSelectionModel();
            if (selectedDeletePromo.getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are sure to delete a promo", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeletePromotion(selectedDeletePromo.getSelectedItem().getIdPromo());
                }
            }

        });

        btnUpdatePromo.setOnAction(event -> {
            SelectionModel<PromotionType> selectedPromo = listPromo.getSelectionModel();
            if (selectedPromo.getSelectedItem() != null) {
                updateTabPromo(tabDepartment, selectedPromo.getSelectedItem().getNamePromo(), selectedPromo.getSelectedItem().getDescriptionPromo(), selectedPromo.getSelectedItem().getGraduationPromo(), selectedPromo.getSelectedItem().getRefDep(),selectedPromo.getSelectedItem().getIdPromo());
            }
        });


        listPromo.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(hboxID);
            gridDepV.getChildren().remove(hboxIP);
            gridDepV.getChildren().remove(hboxIC);
            listClass.getSelectionModel().clearSelection();
            list.getSelectionModel().clearSelection();
            gridDepV.add(hboxIP, 1, 7);
            System.out.println("clicked on " + listPromo.getSelectionModel().getSelectedItem());
            SelectionModel<PromotionType> selectedPromo = listPromo.getSelectionModel();
            namePromo.setText(selectedPromo.getSelectedItem().getNamePromo());
            graduationPromo.setText(Integer.toString(selectedPromo.getSelectedItem().getGraduationPromo()));
            descriptionPromo.setText(selectedPromo.getSelectedItem().getDescriptionPromo());
            depPromo.setText(Integer.toString(selectedPromo.getSelectedItem().getRefDep()));
            System.out.println(selectedPromo.getSelectedItem().getDescriptionPromo());
        });

        //ClassServices
        btnAddClass.setOnAction(event -> {
            createTabClass(tabDepartment);
        });

        btnUpdateClass.setOnAction(event -> {
            SelectionModel<ClassType> selectedClass = listClass.getSelectionModel();
            if (selectedClass.getSelectedItem() != null) {
                updateTabClass(tabDepartment, selectedClass.getSelectedItem().getClassName(), selectedClass.getSelectedItem().getClassDescription(), selectedClass.getSelectedItem().getIdPromotion(), selectedClass.getSelectedItem().getIdClass());
            }
        });

        btnDeleteClass.setOnAction(event -> {
            SelectionModel<ClassType> selectedDeleteClass = listClass.getSelectionModel();
            if (selectedDeleteClass.getSelectedItem() != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are sure to delete a class", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeleteClass(selectedDeleteClass.getSelectedItem().getIdClass());
                }
            }

        });

        listClass.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(hboxID);
            gridDepV.getChildren().remove(hboxIP);
            gridDepV.getChildren().remove(hboxIC);
            list.getSelectionModel().clearSelection();
            listPromo.getSelectionModel().clearSelection();
            gridDepV.add(hboxIC, 1, 7);
            System.out.println("clicked on " + listClass.getSelectionModel().getSelectedItem());
            SelectionModel<ClassType> selectedClass = listClass.getSelectionModel();
            nameClass.setText(selectedClass.getSelectedItem().getClassName());
            descriptionClass.setText(selectedClass.getSelectedItem().getClassDescription());
            depClass.setText(Integer.toString(selectedClass.getSelectedItem().getIdPromotion()));
        });

        return gridDepV;
    }

    protected GridPane createTabDepartment(Tab tabDepartment) {
        // labels
        Label nameLabel = new Label("Name of departement : ");
        Label teacherLabel = new Label("Referent teacher : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextArea descriptionField = new TextArea();

        client.getTeacher();
        ListView<TeacherType> listT = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
            listT.setItems(teacherNames);
        });

        ComboBox teacherComboBox = new ComboBox();
        teacherComboBox.setItems(teacherNames);
        teacherComboBox.getSelectionModel().select(1);


        //grid pane
        GridPane gridDep = new GridPane();
        gridDep.setHgap(10);
        gridDep.setVgap(10);
        gridDep.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameDep = new HBox();
        HBox teacherDep = new HBox();
        HBox descriptionDep = new HBox();

        // add form in hbox
        nameDep.getChildren().addAll(nameLabel, nameField);
        teacherDep.getChildren().addAll(teacherLabel, teacherComboBox);
        descriptionDep.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridDep.add(nameDep, 1, 0);
        gridDep.add(teacherDep, 1, 2);
        gridDep.add(descriptionDep, 1, 5);

        //add gridpane in tab
        tabDepartment.setContent(gridDep);

        //add button

        Button okCreate = new Button("Create");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridDep.add(okCreate, 0, 13, 1, 1);
        gridDep.setHalignment(okCreate, HPos.RIGHT);
        gridDep.setMargin(okCreate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridDep.add(cancelCreate, 2, 13, 1, 1);
        gridDep.setHalignment(cancelCreate, HPos.RIGHT);
        gridDep.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okCreate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter department name");
                return;
            }
            if (teacherComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter referent teacher");
                return;
            }

            TeacherType teach = (TeacherType) teacherComboBox.getSelectionModel().getSelectedItem();
            client.handleCreateDepartment(nameField.getText(), teach.getId(), descriptionField.getText());
            nameField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelCreate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridDep;
    }

    protected GridPane updateTabDep(Tab tabDepartment, String nameDep, int refTeacher, String descDep, int idDep) {
        // labels
        Label nameLabel = new Label("Name of departement : ");
        Label teacherLabel = new Label("Referent teacher : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextArea descriptionField = new TextArea();
        nameField.setText(nameDep);
        descriptionField.setText(descDep);

        client.getTeacher();
        ListView<TeacherType> listT = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
            listT.setItems(teacherNames);
        });

        ComboBox teacherComboBox = new ComboBox();
        teacherComboBox.setItems(teacherNames);
        teacherComboBox.getSelectionModel().select(1);

        //return button
        Image returnDep = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnDepView = new ImageView(returnDep);
        returnDepView.setFitHeight(15);
        returnDepView.setFitWidth(15);

        //create return button
        Button btnReturnDep = new Button();
        btnReturnDep.setGraphic(returnDepView);//setting icon to button

        HBox returnBox = new HBox();
        returnBox.getChildren().add(btnReturnDep);

        //grid pane
        GridPane gridDep = new GridPane();
        gridDep.setHgap(10);
        gridDep.setVgap(10);
        gridDep.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameDepUp = new HBox();
        HBox teacherDepUp = new HBox();
        HBox descriptionDepUp = new HBox();

        // add form in hbox
        nameDepUp.getChildren().addAll(nameLabel, nameField);
        teacherDepUp.getChildren().addAll(teacherLabel, teacherComboBox);
        descriptionDepUp.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridDep.add(nameDepUp, 1, 0);
        gridDep.add(teacherDepUp, 1, 2);
        gridDep.add(descriptionDepUp, 1, 5);
        gridDep.add(returnBox, 2, 0);

        //add gridpane in tab
        tabDepartment.setContent(gridDep);

        //add button
        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridDep.add(okUpdate, 0, 13, 1, 1);
        gridDep.setHalignment(okUpdate, HPos.RIGHT);
        gridDep.setMargin(okUpdate, new Insets(20, 0, 20, 0));

        Button cancelUpdate = new Button("Cancel");
        cancelUpdate.setPrefHeight(40);
        cancelUpdate.setDefaultButton(false);
        cancelUpdate.setPrefWidth(100);
        gridDep.add(cancelUpdate, 2, 13, 1, 1);
        gridDep.setHalignment(cancelUpdate, HPos.RIGHT);
        gridDep.setMargin(cancelUpdate, new Insets(20, 0, 20, 0));

        btnReturnDep.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });

        okUpdate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter department name");
                return;
            }
            if (teacherComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter referent teacher");
                return;
            }
            if (descriptionField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter department description");
                return;
            }
            TeacherType teach = (TeacherType) teacherComboBox.getSelectionModel().getSelectedItem();
            client.handleUpdateDepartment(idDep, nameField.getText(), teach.getId(), descriptionField.getText());
            nameField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelUpdate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridDep;
    }

    protected GridPane createTabPromotion(Tab tabDepartment) {

        // labels
        Label nameLabel = new Label("Name of promotion : ");
        Label depLabel = new Label("Referent DepartmentServices : ");
        Label gradLabel = new Label("Promo's graduation : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextField gradField = new TextField();
        TextArea descriptionField = new TextArea();

        client.getDepartment();
        ListView<DepartmentType> listT = new ListView<>();
        depNames = FXCollections.observableArrayList();
        depNames.addListener((ListChangeListener<DepartmentType>) c -> {
            listT.setItems(depNames);
        });

        ComboBox depComboBox = new ComboBox();
        depComboBox.setItems(depNames);
        depComboBox.getSelectionModel().select(1);


        //grid pane
        GridPane gridPromo = new GridPane();
        gridPromo.setHgap(10);
        gridPromo.setVgap(10);
        gridPromo.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox namePromo = new HBox();
        HBox refDep = new HBox();
        HBox gradPromo = new HBox();
        HBox descriptionPromo = new HBox();

        // add form in hbox
        namePromo.getChildren().addAll(nameLabel, nameField);
        refDep.getChildren().addAll(depLabel, depComboBox);
        gradPromo.getChildren().addAll(gradLabel, gradField);
        descriptionPromo.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridPromo.add(namePromo, 1, 0);
        gridPromo.add(refDep, 1, 2);
        gridPromo.add(gradPromo, 1, 5);
        gridPromo.add(descriptionPromo, 1, 7);

        //add gridpane in tab
        tabDepartment.setContent(gridPromo);

        //add button

        Button okCreate = new Button("Create");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridPromo.add(okCreate, 0, 13, 1, 1);
        gridPromo.setHalignment(okCreate, HPos.RIGHT);
        gridPromo.setMargin(okCreate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridPromo.add(cancelCreate, 2, 13, 1, 1);
        gridPromo.setHalignment(cancelCreate, HPos.RIGHT);
        gridPromo.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okCreate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromo.getScene().getWindow(), "Form Error!", "Please enter promotion name");
                return;
            }
            if (gradField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromo.getScene().getWindow(), "Form Error!", "Please enter graduation");
                return;
            }
            if (descriptionField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromo.getScene().getWindow(), "Form Error!", "Please enter descritpion");
                return;
            }
            if (depComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromo.getScene().getWindow(), "Form Error!", "Please enter referent department");
                return;
            }

            DepartmentType dep = (DepartmentType) depComboBox.getSelectionModel().getSelectedItem();
            client.handleCreatePromotion(nameField.getText(), descriptionField.getText(), Integer.parseInt(gradField.getText()), dep.getIdDepartment());
            nameField.setText("");
            gradField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelCreate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridPromo;
    }

    protected GridPane updateTabPromo(Tab tabDepartment, String namePromo, String descPromo, int graduationPromo, int refDep, int idPromo){

        // labels
        Label nameLabel = new Label("Name of promotion : ");
        Label depLabel = new Label("Referent DepartmentServices : ");
        Label gradLabel = new Label("Promo's graduation : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextField gradField = new TextField();
        TextArea descriptionField = new TextArea();
        nameField.setText(namePromo);
        gradField.setText(Integer.toString(graduationPromo));
        descriptionField.setText(descPromo);


        client.getDepartment();
        ListView<DepartmentType> listT = new ListView<>();
        depNames = FXCollections.observableArrayList();
        depNames.addListener((ListChangeListener<DepartmentType>) c -> {
            listT.setItems(depNames);
        });

        ComboBox depComboBox = new ComboBox();
        depComboBox.setItems(depNames);
        depComboBox.getSelectionModel().select(refDep);

        //return button
        Image returnPromo = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnDepView = new ImageView(returnPromo);
        returnDepView.setFitHeight(15);
        returnDepView.setFitWidth(15);
        //create return button
        Button btnReturnPromo = new Button();
        btnReturnPromo.setGraphic(returnDepView);//setting icon to button

        HBox returnBox = new HBox();
        returnBox.getChildren().add(btnReturnPromo);
        //grid pane
        GridPane gridPromoUp = new GridPane();
        gridPromoUp.setHgap(10);
        gridPromoUp.setVgap(10);
        gridPromoUp.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox namePromoUp = new HBox();
        HBox refDepUp = new HBox();
        HBox gradPromoUp = new HBox();
        HBox descriptionPromoUp = new HBox();

        // add form in hbox
        namePromoUp.getChildren().addAll(nameLabel, nameField);
        refDepUp.getChildren().addAll(depLabel,depComboBox);
        gradPromoUp.getChildren().addAll(gradLabel,gradField);
        descriptionPromoUp.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridPromoUp.add(namePromoUp, 1, 0);
        gridPromoUp.add(refDepUp, 1, 2);
        gridPromoUp.add(gradPromoUp, 1, 5);
        gridPromoUp.add(descriptionPromoUp, 1, 7);
        gridPromoUp.add(returnBox, 2, 0);

        //add gridpane in tab
        tabDepartment.setContent(gridPromoUp);

        //add button
        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridPromoUp.add(okUpdate, 0, 13, 1, 1);
        gridPromoUp.setHalignment(okUpdate, HPos.RIGHT);
        gridPromoUp.setMargin(okUpdate, new Insets(20, 0, 20, 0));

        Button cancelUpdate = new Button("Cancel");
        cancelUpdate.setPrefHeight(40);
        cancelUpdate.setDefaultButton(false);
        cancelUpdate.setPrefWidth(100);
        gridPromoUp.add(cancelUpdate, 2, 13, 1, 1);
        gridPromoUp.setHalignment(cancelUpdate, HPos.RIGHT);
        gridPromoUp.setMargin(cancelUpdate, new Insets(20, 0, 20, 0));

        okUpdate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromoUp.getScene().getWindow(), "Form Error!", "Please enter promotion name");
                return;
            }
            if (gradField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromoUp.getScene().getWindow(), "Form Error!", "Please enter graduation");
                return;
            }
            if (descriptionField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromoUp.getScene().getWindow(), "Form Error!", "Please enter description");
                return;
            }
            if (depComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPromoUp.getScene().getWindow(), "Form Error!", "Please enter referent department");
                return;
            }

            DepartmentType dep= (DepartmentType) depComboBox.getSelectionModel().getSelectedItem();
            client.handleUpdatePromotion(idPromo,nameField.getText(),descriptionField.getText(),Integer.parseInt(gradField.getText()),dep.getIdDepartment());
            nameField.setText("");
            gradField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelUpdate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });

        btnReturnPromo.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridPromoUp;
    }

    protected GridPane createTabClass(Tab tabDepartment) {

        // labels
        Label nameLabel = new Label("Name of Class : ");
        Label depLabel = new Label("Referent Promotion : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextArea descriptionField = new TextArea();

        client.getPromo();
        ListView<PromotionType> listT = new ListView<>();
        promoNames = FXCollections.observableArrayList();
        promoNames.addListener((ListChangeListener<PromotionType>) c -> {
            listT.setItems(promoNames);
        });

        ComboBox proComboBox = new ComboBox();
        proComboBox.setItems(promoNames);
        proComboBox.getSelectionModel().select(1);


        //grid pane
        GridPane gridClass = new GridPane();
        gridClass.setHgap(10);
        gridClass.setVgap(10);
        gridClass.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameClass = new HBox();
        HBox refPromo = new HBox();
        HBox descriptionClass = new HBox();

        // add form in hbox
        nameClass.getChildren().addAll(nameLabel, nameField);
        refPromo.getChildren().addAll(depLabel, proComboBox);
        descriptionClass.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridClass.add(nameClass, 1, 0);
        gridClass.add(refPromo, 1, 2);
        gridClass.add(descriptionClass, 1, 5);

        //add gridpane in tab
        tabDepartment.setContent(gridClass);

        //add button

        Button okCreate = new Button("Create");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridClass.add(okCreate, 0, 13, 1, 1);
        gridClass.setHalignment(okCreate, HPos.RIGHT);
        gridClass.setMargin(okCreate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridClass.add(cancelCreate, 2, 13, 1, 1);
        gridClass.setHalignment(cancelCreate, HPos.RIGHT);
        gridClass.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okCreate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClass.getScene().getWindow(), "Form Error!", "Please enter class name");
                return;
            }

            if (descriptionField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClass.getScene().getWindow(), "Form Error!", "Please enter description");
                return;
            }
            if (proComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClass.getScene().getWindow(), "Form Error!", "Please enter referent promotion");
                return;
            }

            PromotionType pt = (PromotionType) proComboBox.getSelectionModel().getSelectedItem();
            client.handleCreateClass(nameField.getText(), descriptionField.getText(), pt.getIdPromo());
            nameField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelCreate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridClass;
    }

    protected GridPane updateTabClass(Tab tabDepartment, String nameClass, String descClass, int refPromo, int idClass) {

        // labels
        Label nameLabel = new Label("Name of Class : ");
        Label depLabel = new Label("Referent Promotion : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextArea descriptionField = new TextArea();
        nameField.setText(nameClass);
        descriptionField.setText(descClass);

        client.getPromo();
        ListView<PromotionType> listT = new ListView<>();
        promoNames = FXCollections.observableArrayList();
        promoNames.addListener((ListChangeListener<PromotionType>) c -> {
            listT.setItems(promoNames);
        });

        ComboBox proComboBox = new ComboBox();
        proComboBox.setItems(promoNames);
        proComboBox.getSelectionModel().select(refPromo);

        //return button
        Image returnPromo = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnDepView = new ImageView(returnPromo);
        returnDepView.setFitHeight(15);
        returnDepView.setFitWidth(15);
        //create return button
        Button btnReturnClass = new Button();
        btnReturnClass.setGraphic(returnDepView);//setting icon to button

        HBox returnBox = new HBox();
        returnBox.getChildren().add(btnReturnClass);

        //grid pane
        GridPane gridClassUp = new GridPane();
        gridClassUp.setHgap(10);
        gridClassUp.setVgap(10);
        gridClassUp.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox nameClassUp = new HBox();
        HBox refPromoUp = new HBox();
        HBox descriptionClassUP = new HBox();

        // add form in hbox
        nameClassUp.getChildren().addAll(nameLabel, nameField);
        refPromoUp.getChildren().addAll(depLabel, proComboBox);
        descriptionClassUP.getChildren().addAll(descLabel, descriptionField);

        //add hbox in gridpane
        gridClassUp.add(nameClassUp, 1, 0);
        gridClassUp.add(refPromoUp, 1, 2);
        gridClassUp.add(descriptionClassUP, 1, 5);
        gridClassUp.add(returnBox,2,0);

        //add gridpane in tab
        tabDepartment.setContent(gridClassUp);

        //add button

        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridClassUp.add(okUpdate, 0, 13, 1, 1);
        gridClassUp.setHalignment(okUpdate, HPos.RIGHT);
        gridClassUp.setMargin(okUpdate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridClassUp.add(cancelCreate, 2, 13, 1, 1);
        gridClassUp.setHalignment(cancelCreate, HPos.RIGHT);
        gridClassUp.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okUpdate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClassUp.getScene().getWindow(), "Form Error!", "Please enter class name");
                return;
            }

            if (descriptionField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClassUp.getScene().getWindow(), "Form Error!", "Please enter description");
                return;
            }
            if (proComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridClassUp.getScene().getWindow(), "Form Error!", "Please enter referent promotion");
                return;
            }

            PromotionType pt = (PromotionType) proComboBox.getSelectionModel().getSelectedItem();
            client.handleUpdateClass(idClass,nameField.getText(), descriptionField.getText(), pt.getIdPromo());
            nameField.setText("");
            descriptionField.setText("");
            tabDepartment.setContent(departmentRead(tabDepartment));

        });

        cancelCreate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });
        btnReturnClass.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridClassUp;
    }
    
    /**
     * Create the user management tab for admin.
     * 
     * @return Tab user
     */
    protected Tab createTabUser(){

        Tab tabUser = new Tab();
        tabUser.setText("User");
        tabUser.setClosable(false);

        tabUser.setContent(readUser(tabUser));
        return tabUser;
    }

    /**
     * Create the content of the user management tab.
     * 
     * @param tabUser
     * @return A GridPane which is the tab content.
     */
    protected GridPane readUser(Tab tabUser){

        /*add list of users*/
        client.getUsers();
        
        ListView<UserType> userList = new ListView<>();
        userNames = FXCollections.observableArrayList();
        userNames.addListener((ListChangeListener<UserType>) c -> {
        	userList.setItems(userNames);
        });

        Image addUser = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addUserView = new ImageView(addUser);
        addUserView.setFitHeight(15);
        addUserView.setFitWidth(15);

        //create button add
        Button btnAddUser = new Button("Add");
        btnAddUser.setGraphic(addUserView);//setting icon to button

        //delete button
        Image deleteUser = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteUserView = new ImageView(deleteUser);
        deleteUserView.setFitHeight(12);
        deleteUserView.setFitWidth(12);

        //create button delete
        Button btnDeleteUser = new Button("Delete");
        btnDeleteUser.setGraphic(deleteUserView);//setting icon to button

        // add in hbox buttons and title
        HBox hboxButtonUser = new HBox();

        Text title = new Text("User : ");
        title.setFont(Font.font(20));
        hboxButtonUser.getChildren().add(title);
        hboxButtonUser.getChildren().add(btnAddUser);
        hboxButtonUser.getChildren().add(btnDeleteUser);
        hboxButtonUser.setSpacing(5);

        userList.setItems(userNames);
        userList.setPrefWidth(350);
        userList.setPrefHeight(500);

        // left vbox
        VBox vboxListUser = new VBox();
        vboxListUser.getChildren().add(userList);

        //grid pane
        GridPane gridUserVisu = new GridPane();
        gridUserVisu.setHgap(10);
        gridUserVisu.setVgap(10);
        gridUserVisu.setPadding(new Insets(10,10,10,10));

        gridUserVisu.add(hboxButtonUser, 1, 0);
        gridUserVisu.add(vboxListUser, 1, 2);

        /*creation of the info vbox of one user*/
        VBox vboxInfoUser = new VBox();

        //title of column
        HBox hboxUserInfo = new HBox();
        Text titleInfo = new Text("User information : ");
        titleInfo.setFont(Font.font(20));
        hboxUserInfo.getChildren().add(titleInfo);
        hboxUserInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxnameUserInfo = new HBox();
        HBox hboxbirthdateUserInfo = new HBox();
        HBox hboxemailUserInfo = new HBox();
        HBox hboxidUserInfo = new HBox();
        HBox hboxroleUserInfo = new HBox();
        Label nameLabel = new Label("Name: ");
        Label birthdateLabel = new Label( "Birthdate: ");
        Label emailLabel = new Label("Email: ");
        Label idLabel = new Label("ID: ");
        Label roleLabel = new Label("Role: ");
        Text name = new Text(" ");
        Text birthdate = new Text(" ");
        Text email = new Text(" ");
        Text id = new Text(" ");
        Text role = new Text(" ");


        hboxnameUserInfo.getChildren().add(nameLabel);
        hboxnameUserInfo.getChildren().add(name);
        hboxbirthdateUserInfo.getChildren().add(birthdateLabel);
        hboxbirthdateUserInfo.getChildren().add(birthdate);
        hboxemailUserInfo.getChildren().add(emailLabel);
        hboxemailUserInfo.getChildren().add(email);
        hboxidUserInfo.getChildren().add(idLabel);
        hboxidUserInfo.getChildren().add(id);
        hboxroleUserInfo.getChildren().add(roleLabel);
        hboxroleUserInfo.getChildren().add(role);

        hboxnameUserInfo.setAlignment(Pos.CENTER);
        hboxbirthdateUserInfo.setAlignment(Pos.CENTER);
        hboxemailUserInfo.setAlignment(Pos.CENTER);
        hboxidUserInfo.setAlignment(Pos.CENTER);
        hboxroleUserInfo.setAlignment(Pos.CENTER);

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateUser = new Button("Update");
        hboxupdateButton.getChildren().add(btnUpdateUser);
        hboxupdateButton.setAlignment(Pos.CENTER);

        vboxInfoUser.getChildren().addAll(hboxnameUserInfo,hboxbirthdateUserInfo,hboxemailUserInfo, hboxidUserInfo, hboxroleUserInfo, hboxupdateButton);
        vboxInfoUser.setSpacing(10);
        vboxInfoUser.setPadding( new Insets(100, 0, 0, 75));


        btnAddUser.setOnAction(event -> {
            createUser(tabUser);
        });

        btnUpdateUser.setOnAction(event ->{
            SelectionModel<UserType> selectedDeleteUser = userList.getSelectionModel();
            if (selectedDeleteUser.getSelectedItem() != null) {
                updateUser(tabUser, selectedDeleteUser.getSelectedItem().getId(), selectedDeleteUser.getSelectedItem().getName(), selectedDeleteUser.getSelectedItem().getFirstName(), selectedDeleteUser.getSelectedItem().getEmail(), selectedDeleteUser.getSelectedItem().getBirthDate(), selectedDeleteUser.getSelectedItem().getRole());
            }
        });

        btnDeleteUser.setOnAction(event -> {
            SelectionModel<UserType> selectedDeleteUser = userList.getSelectionModel();
            if (selectedDeleteUser.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete the user?", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridUserVisu.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.handleDeleteUser(selectedDeleteUser.getSelectedItem().getId(), selectedDeleteUser.getSelectedItem().getRole());
                }
                tabUser.setContent(readUser(tabUser));
            }
        });

        userList.setOnMouseClicked(event -> {
            gridUserVisu.getChildren().remove(vboxInfoUser);
            gridUserVisu.add(vboxInfoUser, 2, 2);
            System.out.println("clicked on " + userList.getSelectionModel().getSelectedItem());
            SelectionModel<UserType> selectedUser = userList.getSelectionModel();
            name.setText(selectedUser.getSelectedItem().getName() + " " + selectedUser.getSelectedItem().getFirstName());
            birthdate.setText(selectedUser.getSelectedItem().getBirthDate());
            email.setText(selectedUser.getSelectedItem().getEmail());
            id.setText(Integer.toString(selectedUser.getSelectedItem().getId()));
            role.setText(selectedUser.getSelectedItem().getRole());
        });

        return gridUserVisu;
    }
    
    /**
     * Create the content of the user create tab.
     * 
     * @param tabUser
     * @return A GridPane which is the tab content.
     */
    private GridPane createUser(Tab tabUser) {
        
        // labels
        Label nameLabel = new Label("Name: ");
        Label firstNameLabel = new Label("Fisrtname: ");
        Label birthDateLabel = new Label("Birthdate: ");
        Label emailLabel = new Label("Email: ");
        Label roleLabel = new Label("Type: ");
        Label jobTypeLabel = new Label("Job type: (fill if STAFF) ");

        // Add fields
        TextField nameField = new TextField();
        TextField firstNameField = new TextField();
        TextField birthDateField = new TextField();
        TextField emailField = new TextField();
        ComboBox roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("STUDENT", "STUDENT", "STUDENT");
        roleComboBox.getSelectionModel().select("STUDENT");
        TextField jobTypeField = new TextField();

        //grid pane
        GridPane gridUser = new GridPane();
        gridUser.setHgap(10);
        gridUser.setVgap(10);
        gridUser.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox hboxnameUserInfo = new HBox();
        HBox hboxfirstNameUserInfo = new HBox();
        HBox hboxbirthdateUserInfo = new HBox();
        HBox hboxemailUserInfo = new HBox();
        HBox hboxroleUserInfo = new HBox();
        HBox hboxjobTypeUserInfo = new HBox();

        // add form in hbox
        hboxnameUserInfo.getChildren().addAll(nameLabel, nameField);
        hboxfirstNameUserInfo.getChildren().addAll(firstNameLabel, firstNameField);
        hboxbirthdateUserInfo.getChildren().addAll(birthDateLabel, birthDateField);
        hboxemailUserInfo.getChildren().addAll(emailLabel, emailField);
        hboxroleUserInfo.getChildren().addAll(roleLabel, roleComboBox);
        hboxjobTypeUserInfo.getChildren().addAll(jobTypeLabel, jobTypeField);

        //add hbox in gridpane
        gridUser.add(hboxnameUserInfo, 1, 0);
        gridUser.add(hboxfirstNameUserInfo, 1, 2);
        gridUser.add(hboxbirthdateUserInfo, 1, 5);
        gridUser.add(hboxemailUserInfo, 1, 7);
        gridUser.add(hboxroleUserInfo, 1, 9);
        gridUser.add(hboxjobTypeUserInfo, 1, 11);

        //add gridpane in tab
        tabUser.setContent(gridUser);

        //add button

        Button okCreate = new Button("Create");
        okCreate.setPrefHeight(40);
        okCreate.setDefaultButton(true);
        okCreate.setPrefWidth(100);
        gridUser.add(okCreate, 0, 13, 1, 1);
        gridUser.setHalignment(okCreate, HPos.RIGHT);
        gridUser.setMargin(okCreate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridUser.add(cancelCreate, 2, 13, 1, 1);
        gridUser.setHalignment(cancelCreate, HPos.RIGHT);
        gridUser.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okCreate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user name");
                return;
            }
            if (firstNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user firstname");
                return;
            }
            if (birthDateField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user birthdate");
                return;
            }
            if (emailField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user email");
                return;
            }
            
            if(roleComboBox.getValue().equals("STAFF")) {
            	if (jobTypeField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user a job type");
                    return;
                }
            	client.handleCreateUser(nameField.getText(), firstNameField.getText(), birthDateField.getText(), emailField.getText(), roleComboBox.getValue().toString(), "null", jobTypeField.getText());
            } else {
            	client.handleCreateUser(nameField.getText(), firstNameField.getText(), birthDateField.getText(), emailField.getText(), roleComboBox.getValue().toString(), "null", "null");
            }
            
            nameField.setText("");
            firstNameField.setText("");
            birthDateField.setText("");
            emailField.setText("");
            
            tabUser.setContent(readUser(tabUser));

        });

        cancelCreate.setOnAction(event -> {
        	tabUser.setContent(readUser(tabUser));
        });


        return gridUser;
	}

    /**
     * Create the content of the user update tab.
     * 
     * @param tabUser
     * @param name
     * @param birthDate
     * @param email
     * @param id
     * @param role
     * @return A GridPane which is the tab content.
     */
	private GridPane updateUser(Tab tabUser, int id, String name, String firstName, String email, String birthDate, String role) {
		
		// labels
        Label nameLabel = new Label("Name: ");
        Label firstNameLabel = new Label("Fisrtname: ");
        Label birthDateLabel = new Label("Birthdate: ");
        Label emailLabel = new Label("Email: ");
        Label roleLabel = new Label("Role: ");

        
        // Add fields
        TextField nameField = new TextField(name);
        TextField firstNameField = new TextField(firstName);
        TextField birthDateField = new TextField(birthDate);
        TextField emailField = new TextField(email);
        ComboBox roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("STUDENT", "TEACHER");
        roleComboBox.getSelectionModel().select(role);

        //grid pane
        GridPane gridUser = new GridPane();
        gridUser.setHgap(10);
        gridUser.setVgap(10);
        gridUser.setPadding(new Insets(10, 10, 10, 10));

        //Hbox
        HBox hboxnameUserInfo = new HBox();
        HBox hboxfirstNameUserInfo = new HBox();
        HBox hboxbirthdateUserInfo = new HBox();
        HBox hboxemailUserInfo = new HBox();
        HBox hboxroleUserInfo = new HBox();
        HBox hboxpasswordUserInfo = new HBox();

        // add form in hbox
        hboxnameUserInfo.getChildren().addAll(nameLabel, nameField);
        hboxfirstNameUserInfo.getChildren().addAll(firstNameLabel, firstNameField);
        hboxbirthdateUserInfo.getChildren().addAll(birthDateLabel, birthDateField);
        hboxemailUserInfo.getChildren().addAll(emailLabel, emailField);
        hboxroleUserInfo.getChildren().addAll(roleLabel, roleComboBox);

        //add hbox in gridpane
        gridUser.add(hboxnameUserInfo, 1, 0);
        gridUser.add(hboxfirstNameUserInfo, 1, 2);
        gridUser.add(hboxbirthdateUserInfo, 1, 5);
        gridUser.add(hboxemailUserInfo, 1, 7);
        gridUser.add(hboxroleUserInfo, 1, 9);
        gridUser.add(hboxpasswordUserInfo, 1, 11);

        //add gridpane in tab
        tabUser.setContent(gridUser);

        //add button

        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridUser.add(okUpdate, 0, 13, 1, 1);
        gridUser.setHalignment(okUpdate, HPos.RIGHT);
        gridUser.setMargin(okUpdate, new Insets(20, 0, 20, 0));

        Button cancelCreate = new Button("Cancel");
        cancelCreate.setPrefHeight(40);
        cancelCreate.setDefaultButton(false);
        cancelCreate.setPrefWidth(100);
        gridUser.add(cancelCreate, 2, 13, 1, 1);
        gridUser.setHalignment(cancelCreate, HPos.RIGHT);
        gridUser.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

        okUpdate.setOnAction(event -> {
            if (nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user name");
                return;
            }
            if (firstNameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user firstname");
                return;
            }
            if (birthDateField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user birthdate");
                return;
            }
            if (emailField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUser.getScene().getWindow(), "Form Error!", "Please enter user email");
                return;
            }
            
            client.handleUpdateUser(id, nameField.getText(), firstNameField.getText(), emailField.getText(), birthDateField.getText(), roleComboBox.getValue().toString());
            
            nameField.setText("");
            firstNameField.setText("");
            birthDateField.setText("");
            emailField.setText("");
            
            tabUser.setContent(readUser(tabUser));

        });

        cancelCreate.setOnAction(event -> {
        	tabUser.setContent(readUser(tabUser));
        });


        return gridUser;
		
	}
    
    private GridPane createTabCourse(Tab tabCourse){
	      
	       // labels
	        Label nameCourseLabel = new Label("Name of course : ");
	        Label descriptionCourseLabel = new Label("Description : ");
	        Label nbTotalHourLabel = new Label("Total hours : ");
	        Label referentTeacherLabel = new Label ("Referent teacher : ");
	        Label promoLabel = new Label ("Promotion : ");

	        // Add text Field
	        TextField nameCourseField = new TextField();
	        TextArea descriptionCourseField = new TextArea();
	        TextField nbTotalHourField = new TextField();
	        nbTotalHourField.textProperty().addListener((observable, oldValue, newValue) -> {
	            if(!newValue.matches("\\d*"))
	                nbTotalHourField.setText(newValue.replaceAll("[^\\d]", ""));
	        });
	        
	        client.getTeacher();
	        ListView<TeacherType> listT = new ListView<>();
	        teacherNames = FXCollections.observableArrayList();
	        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
	            listT.setItems(teacherNames);
	        });

	        ComboBox<TeacherType> teacherComboBox = new ComboBox<TeacherType>();
	        teacherComboBox.setItems(teacherNames);
	        teacherComboBox.getSelectionModel().select(1);
	        
	        ComboBox<PromotionType> promoComboBox = new ComboBox<PromotionType>();
	        promoComboBox.setItems(promoNames);
	        promoComboBox.getSelectionModel().select(1);

	        //grid pane
	        GridPane gridCourse = new GridPane();
	        gridCourse.setHgap(10);
	        gridCourse.setVgap(10);
	        gridCourse.setPadding(new Insets(10,10,10,10));

	        //Hbox
	        HBox nameCourse = new HBox();
	        HBox descriptionCourse = new HBox();
	        HBox nbTotalHourCourse = new HBox();
	        HBox idReferentTeacherCourse = new HBox();
	        HBox promoCourse = new HBox();

	        // add form in hbox
	        nameCourse.getChildren().addAll(nameCourseLabel, nameCourseField);
	        descriptionCourse.getChildren().addAll(descriptionCourseLabel, descriptionCourseField) ;
	        nbTotalHourCourse.getChildren().addAll(nbTotalHourLabel, nbTotalHourField) ;
	        idReferentTeacherCourse.getChildren().addAll(referentTeacherLabel, teacherComboBox) ;
	        promoCourse.getChildren().addAll(promoLabel, promoComboBox) ;
	        
	        //add hbox in gridpane
	        gridCourse.add(nameCourse, 1, 1);
	        gridCourse.add(descriptionCourse, 1, 2);
	        gridCourse.add(nbTotalHourCourse, 1, 3);
	        gridCourse.add(idReferentTeacherCourse, 1, 4);
	        gridCourse.add(promoCourse, 1, 5);

	        //add gridpane in tab
	        tabCourse.setContent(gridCourse);

	        //add button

	        Button okCreate = new Button("Create");
	        okCreate.setPrefHeight(40);
	        okCreate.setDefaultButton(true);
	        okCreate.setPrefWidth(100);
	        gridCourse.add(okCreate, 0, 13, 1, 1);
	        GridPane.setHalignment(okCreate, HPos.RIGHT);
	        gridCourse.setMargin(okCreate, new Insets(20, 0, 20, 0));

	        Button cancelCreate = new Button("Cancel");
	        cancelCreate.setPrefHeight(40);
	        cancelCreate.setDefaultButton(false);
	        cancelCreate.setPrefWidth(100);
	        gridCourse.add(cancelCreate, 2, 13, 1, 1);
	        gridCourse.setHalignment(cancelCreate, HPos.RIGHT);
	        gridCourse.setMargin(cancelCreate, new Insets(20, 0, 20, 0));

	        okCreate.setOnAction(event -> {
	            if (nameCourseField.getText().isEmpty()) {
	                showAlert(Alert.AlertType.ERROR, gridCourse.getScene().getWindow(), "Form Error!", "Please enter name for this course");
	                return;
	            }
	            if (descriptionCourseField.getText().isEmpty()) {
	                showAlert(Alert.AlertType.ERROR, gridCourse.getScene().getWindow(), "Form Error!", "Please enter description for this course");
	                return;
	            }
	            if (nbTotalHourField.getText().isEmpty()) {
	                showAlert(Alert.AlertType.ERROR, gridCourse.getScene().getWindow(), "Form Error!", "Please enter a total number of hour for this course");
	                return;
	            }
	            if (teacherComboBox.getSelectionModel().isEmpty()) {
	                showAlert(Alert.AlertType.ERROR, gridCourse.getScene().getWindow(), "Form Error!", "Please enter referent teacher for this course");
	                return;
	            }if (promoComboBox.getSelectionModel().isEmpty()) {
	                showAlert(Alert.AlertType.ERROR, gridCourse.getScene().getWindow(), "Form Error!", "Please enter a promotion for this course");
	                return;
	            }
	            TeacherType teach = (TeacherType) teacherComboBox.getSelectionModel().getSelectedItem();
	            PromotionType promo = (PromotionType) promoComboBox.getSelectionModel().getSelectedItem();
	            client.handleCreateCourse(nameCourseField.getText(), descriptionCourseField.getText(), Integer.parseInt(nbTotalHourField.getText()),teach.getId(), promo.getIdPromo());
	            nameCourseField.setText("");
	            descriptionCourseField.setText("");
	            nbTotalHourField.setText("");

	        });

	        cancelCreate.setOnAction(event -> {
	            tabCourse.setContent(courseRead(tabCourse));
	        });


	        return gridCourse;
	        }
    
    
    protected GridPane courseRead(Tab tabCourse){
        client.getCourses();
        ListView<CourseType> list = new ListView<>();
        CourseType ct = new CourseType(1,"course1","communication course",1,2, 5);
        courseNames = FXCollections.observableArrayList();
        courseNames.add(ct);
        courseNames.addListener((ListChangeListener<CourseType>) c -> list.setItems(courseNames));

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
        HBox hbPromo = new HBox();

        Label nameLabel = new Label("Name of course : ");
        Label descriptionCourseLabel = new Label("Course description : ");
        Label nbHourTotalLabel = new Label( "Number Total Hour course : ");
        Label idTeacherLabel = new Label(" Identifiant du referent Teacher : ");
        Label promoLabel = new Label(" Promotion identifiant : ");

        Text name = new Text(" ");
        Text descriptionCourse = new Text(" ");
        Text nbHourTotal = new Text(" ");
        Text idTeacher = new Text(" ");
        Text idPromo = new Text ("");


        hboxnameCourseInfo.getChildren().add(nameLabel);
        hboxdescriptionCourseInfo.getChildren().add(descriptionCourseLabel);
        hboxnbHourTotalCourseInfo.getChildren().add(nbHourTotalLabel);
        hboxidTeacherCourseInfo.getChildren().add(idTeacherLabel);
        hbPromo.getChildren().add(promoLabel);

        hboxnameCourseInfo.getChildren().add(name);
        hboxdescriptionCourseInfo.getChildren().add(descriptionCourse);
        hboxnbHourTotalCourseInfo.getChildren().add(nbHourTotal);
        hboxidTeacherCourseInfo.getChildren().add(idTeacher);
        hbPromo.getChildren().add(idPromo);

        hboxnameCourseInfo.setAlignment(Pos.CENTER);
        hboxdescriptionCourseInfo.setAlignment(Pos.CENTER);
        hboxnbHourTotalCourseInfo.setAlignment(Pos.CENTER);
        hboxidTeacherCourseInfo.setAlignment(Pos.CENTER);
        hbPromo.setAlignment(Pos.CENTER);

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateCourse = new Button("Update");
        hboxupdateButton.getChildren().add(btnUpdateCourse);
        hboxupdateButton.setAlignment(Pos.CENTER);

        vboxInfoCourse.getChildren().addAll(hboxCourseInfo, hboxnameCourseInfo,hboxdescriptionCourseInfo,hboxnbHourTotalCourseInfo,hboxidTeacherCourseInfo, hbPromo, hboxupdateButton);
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
            idPromo.setText(Integer.toString(selectedCourse.getSelectedItem().getIdPromo()));
        });



        return gridCourseVisu;
    }
    
    protected GridPane updateTabCourse(Tab tabCourse, String nameCourse, String descriptionCourse, int nbTotalHourCourse, int referentTeacherCourse, int idCourse){
        // labels
        Label nameCourseLabel = new Label("Name of course : ");
        Label descriptionCourseLabel = new Label("Description : ");
        Label nbTotalHourLabel = new Label("Total hours : ");
        Label referentTeacherLabel = new Label ("Referent teacher : ");
        Label promoLabel = new Label ("Promotion : ");

        // Add text Field
        TextField nameCourseField = new TextField();
        nameCourseField.setText(nameCourse);
        TextArea descriptionCourseField = new TextArea();
        descriptionCourseField.setText(descriptionCourse);
        TextField capacityField = new TextField();
        TextField nbTotalHourField = new TextField();
        nbTotalHourField.setText(Integer.toString(nbTotalHourCourse));
        nbTotalHourField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                nbTotalHourField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        
        client.getTeacher();
        ListView<TeacherType> listT = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
            listT.setItems(teacherNames);
        });

        ComboBox teacherComboBox = new ComboBox();
        teacherComboBox.setItems(teacherNames);
        teacherComboBox.getSelectionModel().select(1);
        teacherComboBox.setValue(referentTeacherCourse);
        
        ComboBox<PromotionType> promoComboBox = new ComboBox<PromotionType>();
        promoComboBox.setItems(promoNames);
        promoComboBox.getSelectionModel().select(1);
        
        //grid pane
        GridPane gridUpdateCourse = new GridPane();
        gridUpdateCourse.setHgap(10);
        gridUpdateCourse.setVgap(10);
        gridUpdateCourse.setPadding(new Insets(10,10,10,10));

        //Hbox
        HBox nameCourseHb = new HBox();
        HBox descriptionCourseHb = new HBox();
        HBox nbTotalHourCourseHb = new HBox();
        HBox referentTeacherCourseHb = new HBox();
        HBox promoHb = new HBox();

        // add form in hbox
        nameCourseHb.getChildren().addAll(nameCourseLabel, nameCourseField);
        descriptionCourseHb.getChildren().addAll(descriptionCourseLabel, descriptionCourseField);
        nbTotalHourCourseHb.getChildren().addAll(nbTotalHourLabel, nbTotalHourField);
        referentTeacherCourseHb.getChildren().addAll(referentTeacherLabel, teacherComboBox);
        promoHb.getChildren().addAll(promoLabel, promoComboBox);

         //add hbox in gridpane
        gridUpdateCourse.add(nameCourseHb, 1, 1);
        gridUpdateCourse.add(descriptionCourseHb, 1, 2);
        gridUpdateCourse.add(nbTotalHourCourseHb, 1, 3);
        gridUpdateCourse.add(referentTeacherCourseHb, 1, 4);
        gridUpdateCourse.add(promoHb, 1, 5);

        //add gridpane in tab
        tabCourse.setContent(gridUpdateCourse);

        //add button

        Button okUpdate = new Button("Update");
        okUpdate.setPrefHeight(40);
        okUpdate.setDefaultButton(true);
        okUpdate.setPrefWidth(100);
        gridUpdateCourse.add(okUpdate, 0, 13, 1, 1);
        gridUpdateCourse.setHalignment(okUpdate, HPos.RIGHT);
        gridUpdateCourse.setMargin(okUpdate, new Insets(20, 0,20,0));

        Button cancelUpdate = new Button("Cancel");
        cancelUpdate.setPrefHeight(40);
        cancelUpdate.setDefaultButton(false);
        cancelUpdate.setPrefWidth(100);
        gridUpdateCourse.add(cancelUpdate, 2, 13, 1, 1);
        gridUpdateCourse.setHalignment(cancelUpdate, HPos.RIGHT);
        gridUpdateCourse.setMargin(cancelUpdate, new Insets(20, 0,20,0));

        okUpdate.setOnAction(event -> {
            if (nameCourseField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpdateCourse.getScene().getWindow(), "Form Error!", "Please enter a name for this course");
                return;
            }
            if (descriptionCourseField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpdateCourse.getScene().getWindow(), "Form Error!", "Please enter a description for this course");
                return;
            }
            if (nbTotalHourField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpdateCourse.getScene().getWindow(), "Form Error!", "Please enter a number of total hour for this course");
                return;
            }if (teacherComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpdateCourse.getScene().getWindow(), "Form Error!", "Please enter referent teacher for this course");
                return;
            }if (promoComboBox.getSelectionModel().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridUpdateCourse.getScene().getWindow(), "Form Error!", "Please enter a Promotion");
                return;
            }
            PromotionType promo = (PromotionType) promoComboBox.getSelectionModel().getSelectedItem();
            TeacherType teach= (TeacherType) teacherComboBox.getSelectionModel().getSelectedItem();
            client.handleUpdateCourse(idCourse, nameCourseField.getText(), descriptionCourseField.getText(), Integer.parseInt(nbTotalHourField.getText()),teach.getId(), promo.getIdPromo());
            nameCourseField.setText("");
            descriptionCourseField.setText("");
            nbTotalHourField.setText("");

        });

        cancelUpdate.setOnAction(event -> {
            tabCourse.setContent(courseRead(tabCourse));
        });


        return gridUpdateCourse;
    }


 }

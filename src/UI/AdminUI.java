package UI;

import Types.*;
import client.CoreClient;
import client.Groups.Department;
import client.Groups.Promotion;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

//StaffAdmin@umontpellier.fr
//AdmStaff

/**
 * 
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
	protected ObservableList<RoomType> roomNames;
    protected ObservableList<DepartmentType> depNames;
    protected ObservableList<PromotionType> promoNames;
    protected ObservableList<ClassType> classNames;
    protected ObservableList<UserType> teacherNames;

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
        tabPane = new TabPane();

        tabProfile = createProfileTab();

        tabSchedule = new Tab();
        tabSchedule.setText("Schedule");
        tabSchedule.setClosable(false);

        tabRecords = new Tab();
        tabRecords.setText("Record");
        tabRecords.setClosable(false);

        tabDiary = new Tab();
        tabDiary.setText("Diary");
        tabDiary.setClosable(false);

        tabChat = createChatTab();

        tabRoom= tabRoom();
        
        tabCourse = tabCourse();

        tabDepartment = tabDepartment();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabRoom);
        tabPane.getTabs().add(tabCourse);
        tabPane.getTabs().add(tabDepartment);

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
    
    
    protected Tab tabRoom(){

        Tab tabRoom = new Tab();
        tabRoom.setText("Room");
        tabRoom.setClosable(false);


        tabRoom.setContent(roomRead(tabRoom));
        return tabRoom;
    }

    private GridPane roomRead(Tab tabRoom){

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
        gridRoomVisu.setPadding(new Insets(10,10,10,10));

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
        Label capacityLabel = new Label( "Capacity room : ");
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

        vboxInfoRoom.getChildren().addAll(hboxRoomInfo, hboxnameRoomInfo,hboxcapacityRoomInfo,hboxbuildingRoomInfo,hboxprojectorRoomInfo, hboxcomputerRoomInfo, hboxdescRoomInfo, hboxupdateButton);
        vboxInfoRoom.setSpacing(10);
        vboxInfoRoom.setPadding( new Insets(100, 0, 0, 75));


        btnAddRoom.setOnAction(event -> {
            createTabRoom(tabRoom);
        });

        btnUpdateRoom.setOnAction(event ->{
            SelectionModel<RoomType> selectedDeleteRoom = list.getSelectionModel();
            if (selectedDeleteRoom.getSelectedItem() != null) {
                updateTabRoom(tabRoom, selectedDeleteRoom.getSelectedItem().getName(), selectedDeleteRoom.getSelectedItem().getCapacity(),selectedDeleteRoom.getSelectedItem().getBuilding(), selectedDeleteRoom.getSelectedItem().isHasProjector(), selectedDeleteRoom.getSelectedItem().isHasComputer(),selectedDeleteRoom.getSelectedItem().getDescription(), selectedDeleteRoom.getSelectedItem().getId());
            }
        });

        btnDeleteRoom.setOnAction(event -> {
            SelectionModel<RoomType> selectedDeleteRoom = list.getSelectionModel();
            if (selectedDeleteRoom.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You are sure to delete a room", ButtonType.YES, ButtonType.NO);
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
            if ( selectedRoom.getSelectedItem().isHasProjector()){
                projector.setText("Yes");
            } else {
                projector.setText("No");
            }
            if ( selectedRoom.getSelectedItem().isHasComputer()){
                computer.setText("Yes");
            } else {
                computer.setText("No");
            }
            description.setText(selectedRoom.getSelectedItem().getDescription());

        });



        return gridRoomVisu;
    }

    private GridPane createTabRoom(Tab tabRoom){
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

        Button okCreate = new Button("Create");
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

    private GridPane updateTabRoom(Tab tabRoom, String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description, int id){

        // labels
        Label nameLabel = new Label("Name of room : ");
        Label capacityLabel = new Label("Capacity : ");
        Label buildingLabel = new Label("Building : ");
        Label projLabel = new Label("There is a projector : ");
        Label compLabel = new Label("There are computers : ");
        Label descLabel = new Label("Room description : ");

        // Add text Field
        TextField nameField = new TextField();
        nameField.setText(name);
        TextField capacityField = new TextField();
        capacityField.setText(Integer.toString(capacity));
        capacityField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*"))
                capacityField.setText(newValue.replaceAll("[^\\d]", ""));
        });
        TextField buildingField = new TextField();
        buildingField.setText(Integer.toString(building));
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
        if (hasProjector){
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

        if (hasComputer){
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
        gridUpadateRoom.setPadding(new Insets(10,10,10,10));

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
        gridUpadateRoom.add(nameRoom, 1, 0);
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
        gridUpadateRoom.setMargin(okUpdate, new Insets(20, 0,20,0));

        Button cancelUpdate = new Button("Cancel");
        cancelUpdate.setPrefHeight(40);
        cancelUpdate.setDefaultButton(false);
        cancelUpdate.setPrefWidth(100);
        gridUpadateRoom.add(cancelUpdate, 2, 13, 1, 1);
        gridUpadateRoom.setHalignment(cancelUpdate, HPos.RIGHT);
        gridUpadateRoom.setMargin(cancelUpdate, new Insets(20, 0,20,0));

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
            if(((RadioButton) tp.getSelectedToggle()).getText().equals("Yes"))
                hasProjectorUp = true;
            else
                hasProjectorUp = false;

            if(((RadioButton) tp2.getSelectedToggle()).getText().equals("Yes"))
                hasComputerUp = true;
            else
                hasComputerUp = false;

            client.handleUpdateRoom(id, nameField.getText(), Integer.parseInt(capacityField.getText()),Integer.parseInt(buildingField.getText()), hasProjectorUp, hasComputerUp, descriptionField.getText());
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

    protected void setRoomTab(){
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabRoom());


    }


    public void addUIControls(BorderPane borderPane){

    }


    /**
     * This method create the department tab in the principal admin scene
     */

    protected Tab tabDepartment(){

        Tab tabDepartment = new Tab();
        tabDepartment.setText("Students Groups");
        tabDepartment.setClosable(false);


        tabDepartment.setContent(departmentRead(tabDepartment));
        return tabDepartment;
    }

    private GridPane departmentRead(Tab tabDepartment) {

        /*add list of Department*/
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

        // add in hbox buttons and title
        HBox hboxButtonDep = new HBox();

        Text title = new Text("Department : ");
        hboxButtonDep.getChildren().addAll(title,btnAddDep,btnDeleteDep);
        hboxButtonDep.setSpacing(5);

        list.setItems(depNames);
        System.out.println(depNames);
        list.setPrefWidth(300);
        list.setPrefHeight(500);

        // left vbox
        VBox vboxListDep = new VBox();
        vboxListDep.getChildren().add(list);

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
        Label teacherLabel = new Label( "Referent teacher: ");
        Label descriptionLabel = new Label("Department description : ");
        Text name = new Text(" ");
        Text teacher = new Text(" ");
        Text description = new Text(" ");


        hboxnameDepInfo.getChildren().addAll(nameLabel,name);
        hboxteacherDepInfo.getChildren().addAll(teacherLabel,teacher);
        hboxdescDepInfo.getChildren().addAll(descriptionLabel,description);

        hboxnameDepInfo.setAlignment(Pos.CENTER);
        hboxteacherDepInfo.setAlignment(Pos.CENTER);
        hboxdescDepInfo.setAlignment(Pos.CENTER);

        VBox VBoxAffichageDep = new VBox();
        VBoxAffichageDep.getChildren().addAll(hboxnameDepInfo,hboxteacherDepInfo,hboxdescDepInfo);
        VBoxAffichageDep.setAlignment(Pos.CENTER_RIGHT);
        //

        /*add list of Promotion*/
        //client.getPromo();
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
        Button btnAddPromo= new Button("Add-Promo");
        btnAddPromo.setGraphic(addPromoView);//setting icon to button

        //delete button
        Image deletePromo = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deletePromoView = new ImageView(deletePromo);
        deletePromoView.setFitHeight(12);
        deletePromoView.setFitWidth(12);

        //create button delete
        Button btnDeletePromo = new Button("Delete-Promo");
        btnDeletePromo.setGraphic(deletePromoView);//setting icon to button

        // add in hbox buttons and title
        HBox hboxButtonPromo = new HBox();
        Text titlePromo = new Text("Promotion : ");
        hboxButtonPromo.getChildren().addAll(titlePromo,btnAddPromo,btnDeletePromo);
        hboxButtonPromo.setSpacing(5);


        listPromo.setItems(promoNames);
        System.out.println(promoNames);
        listPromo.setPrefWidth(300);
        listPromo.setPrefHeight(500);

        // left vbox
        VBox vboxListPromo = new VBox();
        vboxListPromo.getChildren().add(listPromo);


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
        Label graduationLabelPromo = new Label( "Graduation: ");
        Label descriptionLabelPromo = new Label("Promotion description : ");
        Text DepPromo = new Text(" ");
        Text namePromo = new Text(" ");
        Text graduationPromo = new Text(" ");
        Text descriptionPromo = new Text(" ");

        hboxdepPromoInfo.getChildren().add(depLabelPromo);
        hboxnamePromoInfo.getChildren().add(nameLabelPromo);
        hboxgraduationPromoInfo.getChildren().add(graduationLabelPromo);
        hboxdescPromoInfo.getChildren().add(descriptionLabelPromo);

        hboxdepPromoInfo.setAlignment(Pos.CENTER);
        hboxnamePromoInfo.setAlignment(Pos.CENTER);
        hboxgraduationPromoInfo.setAlignment(Pos.CENTER);
        hboxdescPromoInfo.setAlignment(Pos.CENTER);
        //

        /*add list of Classes*/
        //client.getClass();
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
        Button btnAddClass= new Button("Add-Class");
        btnAddClass.setGraphic(addClassView);//setting icon to button

        //delete button
        Image deleteClass = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteClassView = new ImageView(deleteClass);
        deleteClassView.setFitHeight(12);
        deleteClassView.setFitWidth(12);

        //create button delete
        Button btnDeleteClass = new Button("Delete-Class");
        btnDeleteClass.setGraphic(deleteClassView);//setting icon to button


        listClass.setItems(classNames);
        System.out.println(classNames);
        listClass.setPrefWidth(300);
        listClass.setPrefHeight(500);

        // left vbox
        VBox vboxListClass = new VBox();
        vboxListClass.getChildren().add(listClass);


        // add in hbox buttons and title
        HBox hboxButtonClass = new HBox();
        Text titleClass = new Text("Class : ");
        hboxButtonClass.getChildren().addAll(titleClass,btnAddClass,btnDeleteClass);
        hboxButtonClass.setSpacing(5);

        // add in hbox list
        HBox hboxListDep = new HBox();
        hboxListDep.getChildren().addAll(vboxListDep,vboxListPromo,vboxListClass);


        //title of column
        HBox hboxClassInfo = new HBox();
        Text titleInfoClass = new Text("Class information : ");
        titleInfoClass.setFont(Font.font(20));
        hboxClassInfo.getChildren().add(titleInfoClass);
        hboxClassInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxdepClassInfo = new HBox();
        HBox hboxnameClassInfo = new HBox();
        HBox hboxgraduationClassInfo = new HBox();
        HBox hboxdescClassInfo = new HBox();
        Label depLabelClass = new Label("Referent Promotion : ");
        Label nameLabelClass = new Label("Name of Class : ");
        Label descriptionLabelClass = new Label("Class description : ");
        Text DepClass = new Text(" ");
        Text nameClass = new Text(" ");
        Text graduationClass = new Text(" ");
        Text descriptionClass = new Text(" ");

        hboxdepClassInfo.getChildren().add(depLabelClass);
        hboxnameClassInfo.getChildren().add(nameLabelClass);
        hboxdescClassInfo.getChildren().add(descriptionLabelClass);

        hboxdepClassInfo.setAlignment(Pos.CENTER);
        hboxnameClassInfo.setAlignment(Pos.CENTER);
        hboxgraduationClassInfo.setAlignment(Pos.CENTER);
        hboxdescClassInfo.setAlignment(Pos.CENTER);
        //

        //grid pane
        GridPane gridDepV = new GridPane();
        gridDepV.setHgap(10);
        gridDepV.setVgap(10);
        gridDepV.setPadding(new Insets(10,10,10,10));

        gridDepV.add(hboxButtonDep, 1, 0);
        gridDepV.add(hboxButtonPromo, 1, 2);
        gridDepV.add(hboxButtonClass, 1, 3);
        gridDepV.add(hboxListDep, 1, 4);


        /*creation of the info vbox of one department*/
        VBox vboxInfoDep = new VBox();

        vboxInfoDep.getChildren().addAll(hboxDepInfo, hboxnameDepInfo,hboxteacherDepInfo,hboxdescDepInfo);
        vboxInfoDep.setSpacing(10);
        vboxInfoDep.setPadding( new Insets(100, 0, 0, 75));

        //Dep
        btnAddDep.setOnAction(event -> {
            createTabDepartment(tabDepartment);
        });


        btnDeleteDep.setOnAction(event -> {
            SelectionModel<DepartmentType> selectedDeleteDepartment = list.getSelectionModel();
            if (selectedDeleteDepartment.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You are sure to delete a department", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    return;
                }
            }

        });

        list.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(vboxInfoDep);
            gridDepV.add(vboxInfoDep, 2, 2);
            System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
            SelectionModel<DepartmentType> selectedDep = list.getSelectionModel();
            name.setText(selectedDep.getSelectedItem().getNameDep());
            teacher.setText(Integer.toString(selectedDep.getSelectedItem().getRefTeacher()));
            description.setText(selectedDep.getSelectedItem().getDescriptionDep());

        });

        //Promo
        btnAddPromo.setOnAction(event -> {
            createTabDepartment(tabDepartment);
        });


        btnDeletePromo.setOnAction(event -> {
            SelectionModel<PromotionType> selectedDeletePromo = listPromo.getSelectionModel();
            if (selectedDeletePromo.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You are sure to delete a promo", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    return;
                }
            }

        });

        listClass.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(vboxInfoDep);
            gridDepV.add(vboxInfoDep, 2, 2);
            System.out.println("clicked on " + listPromo.getSelectionModel().getSelectedItem());
            SelectionModel<PromotionType> selectedClass = listPromo.getSelectionModel();
            namePromo.setText(selectedClass.getSelectedItem().getNamePromo());
            descriptionPromo.setText(selectedClass.getSelectedItem().getDescriptionPromo());

        });

        //Class
        btnAddClass.setOnAction(event -> {
            createTabDepartment(tabDepartment);
        });


        btnDeleteDep.setOnAction(event -> {
            SelectionModel<ClassType> selectedDeleteDepartment = listClass.getSelectionModel();
            if (selectedDeleteDepartment.getSelectedItem() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You are sure to delete a department", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = gridDepV.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    return;
                }
            }

        });

        listClass.setOnMouseClicked(event -> {
            gridDepV.getChildren().remove(vboxInfoDep);
            gridDepV.add(vboxInfoDep, 2, 2);
            System.out.println("clicked on " + listClass.getSelectionModel().getSelectedItem());
            SelectionModel<ClassType> selectedClass = listClass.getSelectionModel();
            namePromo.setText(selectedClass.getSelectedItem().getNameClass());
            descriptionPromo.setText(selectedClass.getSelectedItem().getDescriptionClass());

        });

        return gridDepV;
    }

    private GridPane createTabDepartment(Tab tabDepartment) {
        // labels
        Label nameLabel = new Label("Name of departement : ");
        Label teacherLabel = new Label("Referent teacher : ");
        Label descLabel = new Label("Description : ");

        // Add text Field
        TextField nameField = new TextField();
        TextField teacherField = new TextField();
        TextArea descriptionField = new TextArea();

        //client.getTeacher();
        ListView<UserType> listT = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        depNames.addListener((ListChangeListener<DepartmentType>) c -> {
            listT.setItems(teacherNames);
        });
       // ObservableList<String> options =
          //      FXCollections.observableArrayList(listT
            //    );
        //final ComboBox comboBox = new ComboBox(options);


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
        teacherDep.getChildren().addAll(teacherLabel, teacherField);
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
            if (teacherField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridDep.getScene().getWindow(), "Form Error!", "Please enter referent teacher");
                return;
            }

            client.handleCreateDepartment(nameField.getText(), Integer.parseInt(teacherField.getText()), descriptionField.getText());
            nameField.setText("");
            teacherField.setText("");
            descriptionField.setText("");

        });

        cancelCreate.setOnAction(event -> {
            tabDepartment.setContent(departmentRead(tabDepartment));
        });


        return gridDep;
    }

    }
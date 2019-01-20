package UI;

import Types.*;
import client.CoreClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Types.RoomType;
import Types.UserType;
import Types.CourseType;

import java.io.IOException;
import java.util.List;

/**
 * UI used to start the client application.
 * It contains everything related to this stage.
 * It the starts UIs according to the information received from the server.
 *
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class StartUI extends UI {
    private AdminUI adminUI;
    private StudentUI studentUI;
    private SuperAdminUI superAdminUI;
    private TeacherUI teacherUI;

    public static void main(String[] args) {
        Application.launch(StartUI.class, args);
    }

    /**
     * This method sets the principal scene for a student with the StudentUI class.
     */
    private void setPrincipalSceneAsStudent(){
        studentUI = new StudentUI(primaryStage, login, userID, client);
        primaryStage.setScene(studentUI.createPrincipalStudentScene());
    }

    /**
     * This method sets the principal scene for a teacher, with the TeacherUI class.
     */
    private void setPrincipalSceneAsTeacher(){
        teacherUI = new TeacherUI(primaryStage, login, userID, client);
        primaryStage.setScene(teacherUI.createPrincipalTeacherScene());
    }

    /**
     * This method sets the principal scene for an admin, with the AdminUI class.
     */
    private void setPrincipalSceneAsAdmin(){
        adminUI = new AdminUI(primaryStage, login, userID, client);
        primaryStage.setScene(adminUI.createPrincipalAdminScene());
    }

    /**
     * This method sets the principal scene for a super admin, with the SuperAdminUI class.
     */
    private void setPrincipalSceneAsSuperAdmin(){
        superAdminUI = new SuperAdminUI(primaryStage, login, userID, client);
        primaryStage.setScene(superAdminUI.createPrincipalSuperAdminScene());
    }

    /**
     * This method return the current instanciated UI.
     * Since only one UI at a time is used, the others are null.
     * @return the current UI.
     */
    private UI getCurrentUI(){
        if(adminUI != null)
            return adminUI;
        else if(studentUI != null)
            return studentUI;
        else if(superAdminUI != null)
            return superAdminUI;
        else if(teacherUI != null)
            return teacherUI;
        else
            return null;
    }

    /**
     * Creates the instance of the User, for the business logic relative to it
     * @throws IOException
     */
    private void createCoreClient() throws IOException{
        if(client == null){
            String host = "localhost";
            int port = 5555;
            client = new CoreClient(host, port, this);
        }
    }

    @Override
    public void start(Stage primaryStage){
        setupListeners();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Learn Together - Connection");
        primaryStage.setAlwaysOnTop(true);
        // Scene 2
        // Create the registration form grid pane
        root = createPrincipalPane();
        // Create a scene with registration form grid pane as the root node
        // Scene 1
        // Create the registration form grid pane
        GridPane gridPaneConnection = createConnectionFormPane();
        // Add UI controls to the registration form grid pane
        addUIControlsConnectionPane(gridPaneConnection);
        // Create a scene with registration form grid pane as the root node
        this.connectionScene = new Scene(gridPaneConnection, 800, 500);
        // Set the scene in primary stage
        this.primaryStage.setScene(connectionScene);
        // Set the stage visible
        this.primaryStage.show();

        GridPane gridPaneFirstConnection = createConnectionFormPane();
        addUIControlsFirstConnectionPane(gridPaneFirstConnection);
        firstConnectionScene = new Scene(gridPaneFirstConnection, 800, 500);

        BorderPane borderPaneWaiting = createWaitingPane();
        addUIControlsWaitingPane(borderPaneWaiting);
        waitingScene = new Scene(borderPaneWaiting, 800, 500);

        primaryStage.setOnCloseRequest(event ->{
            if(client != null){
                try {
                    client.getConnection().closeConnection();
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * This method setups the different listeners used in the UI.
     * It mainly setups the connection status and the current state.
     */
    @Override
    protected void setupListeners(){
        connectionStatus.addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("STUDENT")){
                showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Connected.");
                Platform.runLater(this::setPrincipalSceneAsStudent);
            }
            else if(newValue.equalsIgnoreCase("TEACHER")){
                Platform.runLater(this::setPrincipalSceneAsTeacher);
            }
            else if(newValue.equalsIgnoreCase("ADMIN")){
                Platform.runLater(this::setPrincipalSceneAsAdmin);
            }
            else if(newValue.equalsIgnoreCase("SUPERADMIN")){
                Platform.runLater(this::setPrincipalSceneAsSuperAdmin);
            }
            else if(newValue.equalsIgnoreCase("CONNECTION ERROR")){
                Platform.runLater(()->primaryStage.setScene(connectionScene));
                showAlert(Alert.AlertType.ERROR, null, "Failure", "Error: impossible to connect.");
            }
            else if(newValue.equalsIgnoreCase("WAITING")){
                Platform.runLater(() -> primaryStage.setScene(waitingScene));
            }
        });

        currentState.addListener((observable, oldValue, newValue) -> {
            if (newValue.equalsIgnoreCase("FC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "This account has been activated. You can now log in properly."));
                Platform.runLater(() -> primaryStage.setScene(connectionScene));
            } else if (newValue.equalsIgnoreCase("FC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "Error: impossible to activate your account. Is it already activated? Otherwise, please check later."));
                Platform.runLater(() -> primaryStage.setScene(connectionScene));
            } else if (newValue.equalsIgnoreCase("RC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have created the room."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.tabRoom.setContent(adminUI.setRoomTab()));
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.tabRoom.setContent(superAdminUI.setRoomTab()));
                }
            } else if (newValue.equalsIgnoreCase("RC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Room hasn't been created."));
            } else if (newValue.equalsIgnoreCase("RD SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have deleted the room."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getRooms());
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getRooms());
                }
            } else if (newValue.equalsIgnoreCase("RD FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Room hasn't been deleted."));
            } else if (newValue.equalsIgnoreCase("RU SUCCESS")) {
                showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have update the room.");
                if (adminUI != null) {
                    Platform.runLater(() -> Platform.runLater(() -> adminUI.client.getRooms()));
                    Platform.runLater(() -> adminUI.tabRoom.setContent(adminUI.setRoomTab()));
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> Platform.runLater(() -> superAdminUI.client.getRooms()));
                    Platform.runLater(() -> superAdminUI.tabRoom.setContent(superAdminUI.setRoomTab()));
                }
            } else if (newValue.equalsIgnoreCase("RU FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Room hasn't been updated."));
            } else if (newValue.equalsIgnoreCase("CC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have created the course."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.tabCourse.setContent(adminUI.setCourseTab()));
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.tabCourse.setContent(superAdminUI.setCourseTab()));
                } else if (teacherUI != null) {
                    Platform.runLater(() -> teacherUI.tabCourse.setContent(teacherUI.setCourseTab()));
                }
            } else if (newValue.equalsIgnoreCase("CC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Course hasn't been created."));
            } else if (newValue.equalsIgnoreCase("CD SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have deleted the course."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getCourses());
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getCourses());
                } else if (teacherUI != null) {
                    Platform.runLater(() -> teacherUI.client.getCourses(userID));
                }
            } else if (newValue.equalsIgnoreCase("CD FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Course hasn't been deleted."));
            } else if (newValue.equalsIgnoreCase("CU SUCCESS")) {
                showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have update the course.");
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getCourses());
                    Platform.runLater(() -> adminUI.tabCourse.setContent(adminUI.setCourseTab()));
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getCourses());
                    Platform.runLater(() -> superAdminUI.tabCourse.setContent(superAdminUI.setCourseTab()));
                } else if (teacherUI != null) {
                    Platform.runLater(() -> Platform.runLater(() -> teacherUI.client.getCourses(userID)));
                    Platform.runLater(() -> teacherUI.tabCourse.setContent(teacherUI.setCourseTab()));
                }
            } else if (newValue.equalsIgnoreCase("CU FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "Error: Course hasn't been updated."));
            } else if (newValue.equalsIgnoreCase("MD SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "The conversation has been deleted."));
            } else if (newValue.equalsIgnoreCase("MD FAILURE.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The conversation cannot be deleted at this time."));
            } else if (newValue.equalsIgnoreCase("CUS SUCCESS.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Success", "The user has been created."));
            } else if (newValue.equalsIgnoreCase("CUS FAILURE.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The user cannot be created at this time."));
            } else if (newValue.equalsIgnoreCase("UU SUCCESS.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Success", "The user has been updated."));
            } else if (newValue.equalsIgnoreCase("UU FAILURE.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The user cannot be updated at this time."));
            } else if (newValue.equalsIgnoreCase("DU SUCCESS.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Success", "The user has been deleted."));
            } else if (newValue.equalsIgnoreCase("DU FAILURE.")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The user cannot be deleted at this time."));
            } else if (newValue.equalsIgnoreCase("REC UPLOAD SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "The record has been added."));
            } else if (newValue.equalsIgnoreCase("REC UPLOAD FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The record cannot be uploaded at this time. Try another name?"));
            } else if (newValue.equalsIgnoreCase("RECORD DOWNLOADED")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "The record has been downloaded in the Downloads folder."));
            } else if (newValue.equalsIgnoreCase("RECORD NOT DOWNLOADED")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "The record cannot be downloaded at this time. Try again later?"));
            }
            else if (newValue.equalsIgnoreCase("DC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Department hasn't been created."));
            } else if (newValue.equalsIgnoreCase("DC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "You have created the department."));
            } else if (newValue.equalsIgnoreCase("DEU FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Department hasn't been updated."));
            } else if (newValue.equalsIgnoreCase("DEU SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Department has been updated."));
            } else if (newValue.equalsIgnoreCase("DD FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Department hasn't been deleted."));
            } else if (newValue.equalsIgnoreCase("DD SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Department has been deleted."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getDepartment());
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getDepartment());
                }
            } else if (newValue.equalsIgnoreCase("PC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Promotion hasn't been created."));
            } else if (newValue.equalsIgnoreCase("PC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Promotion has been created."));
            } else if (newValue.equalsIgnoreCase("PU FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Promotion hasn't been updated."));
            } else if (newValue.equalsIgnoreCase("PU SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Promotion has been updated."));
            } else if (newValue.equalsIgnoreCase("DP FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Promotion hasn't been deleted."));
            } else if (newValue.equalsIgnoreCase("DP SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Promotion has been deleted."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getPromo());
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getPromo());
                }
            } else if (newValue.equalsIgnoreCase("CLC FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Class hasn't been created."));
            } else if (newValue.equalsIgnoreCase("CLC SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Class has been created."));
            }else if (newValue.equalsIgnoreCase("CLU FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Class hasn't been updated."));
            } else if (newValue.equalsIgnoreCase("CLU SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Class has been updated."));
            }else if (newValue.equalsIgnoreCase("CLP FAILURE")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Failure", "Error: Class hasn't been deleted."));
            } else if (newValue.equalsIgnoreCase("CLP SUCCESS")) {
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Class has been deleted."));
                if (adminUI != null) {
                    Platform.runLater(() -> adminUI.client.getClasses());
                } else if (superAdminUI != null) {
                    Platform.runLater(() -> superAdminUI.client.getClasses());
                }
            }

        });
    }

    /**
     * Create an instance of GridPane and apply format on it.
     *
     * @return GridPane Login pane.
     */
    private GridPane createConnectionFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }


    /**
     * Design the primary scene with the connection interface and handle the events.
     *
     * @param gridPane Login pane.
     */
    private void addUIControlsConnectionPane(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Connection");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Email address: ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField("StaffAdmin@umontpellier.fr");
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Password Text Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        passwordField.setText("AdmStaff");
        gridPane.add(passwordField, 1, 2);

        // Add Login Button
        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(loginButton, 0, 4, 1, 1);
        GridPane.setHalignment(loginButton, HPos.RIGHT);
        GridPane.setMargin(loginButton, new Insets(20, 0,20,0));
        //Add FirstConnection Button
        Button firstConnectionButton = new Button("First connection?");
        firstConnectionButton.setDefaultButton(false);
        firstConnectionButton.setPrefHeight(40);
        firstConnectionButton.setPrefWidth(200);
        gridPane.add(firstConnectionButton, 1, 4, 1, 1);
        GridPane.setHalignment(firstConnectionButton, HPos.RIGHT);
        GridPane.setMargin(firstConnectionButton, new Insets(20, 0, 20, 0));

        loginButton.setOnAction(event -> {
            if(nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                return;
            }
            if(passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your password");
                return;
            }

            login=nameField.getText();
            password = passwordField.getText();
            connectionStatus.setValue("WAITING");
            try {
                createCoreClient();
                client.handleLogin(login, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        firstConnectionButton.setOnAction(event -> {
            Platform.runLater(()->primaryStage.setScene(firstConnectionScene));
        });
    }

    private void addUIControlsFirstConnectionPane(GridPane pane){
        // Add Header
        Label headerLabel = new Label("First connection");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        pane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        Text howto = new Text("If it is your first connection, enter your email address and your new password in the form below. \nThis only work once, on the first connection.");
        pane.add(howto, 0, 1, 2, 2);
        GridPane.setHalignment(howto, HPos.CENTER);
        GridPane.setMargin(howto,  new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Enter you email address: ");
        pane.add(nameLabel, 0,3);

        // Add Name Text Field
        TextField nameField = new TextField("yvan.sanson@etu.umontpellier.fr");
        nameField.setPrefHeight(40);
        pane.add(nameField, 1,3);

        // Add Password Label
        Label passwordLabel = new Label("New password: ");
        pane.add(passwordLabel, 0, 4);

        // Add Password Text Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        pane.add(passwordField, 1, 4);

        //Add Button
        Button loginButton = new Button("Connect");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        pane.add(loginButton, 0, 5, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20, 0,20,0));

        loginButton.setOnAction(event -> {
            if(nameField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter your name");
                return;
            }
            if(passwordField.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), "Form Error!", "Please enter your password");
                return;
            }

            login=nameField.getText();
            password = passwordField.getText();
            connectionStatus.setValue("WAITING");
            try {
                createCoreClient();
                client.setFirstPassword(login, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void showLogin(boolean isConnected, int id, String role){
        if(isConnected){
            userID = id;
            switch (role){
                case "STUDENT":
                    connectionStatus.setValue("STUDENT");
                    break;
                case "TEACHER":
                    connectionStatus.setValue("TEACHER");
                    break;
                case "ADMIN":
                    connectionStatus.setValue("ADMIN");
                    break;
                case "SUPERADMIN":
                    connectionStatus.setValue("SUPERADMIN");
                    break;
                default:
                    connectionStatus.setValue("CONNECTION ERROR");
                    break;
            }
        }
        else{
            connectionStatus.setValue("CONNECTION ERROR");
        }
    }

    @Override
    public void display(String message){

    }
    @Override
    public void getRooms(List<RoomType> rooms){
    	Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setRooms(rooms);
            else if(superAdminUI != null)
                superAdminUI.setRooms(rooms);
        });
    }

    @Override
    public void getRecords(List<RecordType> records){
        Platform.runLater(() -> {
            getCurrentUI().setRecordNames(records);

        });
    }

    @Override
    public void getAdmin(List<AdminType> adm) {
        Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setAdmin(adm);
            else if(superAdminUI != null)
                superAdminUI.setAdmin(adm);
        });
    }
    /**
     * This method put the record user in the list recordsNames
     * @param records : list of user record
     */
    @Override
    public void getRecordByUser(List<RecordType> records){
        Platform.runLater(()->{
            records.remove(records.get(0));
            getCurrentUI().setRecordNames(records);
        });
    }


    @Override
    public void getCourses(List<CourseType> courses){
    	Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setCourses(courses);
            else if(superAdminUI != null)
                superAdminUI.setCourses(courses);
            else if(teacherUI != null)
                teacherUI.setCourses(courses);
        });
    }

    @Override
    public void setConversationMessages(List<MessageType> conversationMessages){
        if(getCurrentUI().getConvo() != null){
            StringBuilder sb = new StringBuilder();
            for(MessageType message : conversationMessages){
                sb.append(message.toString());
                sb.append("\n");
            }
            getCurrentUI().getConvo().setText(sb.toString());
        }

    }
    
    

    @Override
    public void setConversationEmails(List<String> emails) {
        emails.remove(0);
        if (emails.size() != 0) {
            Platform.runLater(() ->  getCurrentUI().setReceiversEmail(emails));
        }
    }

    @Override
    public void getDepartment(List<DepartmentType> dep)
        {
            Platform.runLater(() -> {
                if (adminUI != null)
                    adminUI.setDepartment(dep);
                else if (superAdminUI != null)
                    superAdminUI.setDepartment(dep);
            });

        }
    
    /**
     * Set the user for the current UI.
     */
    @Override
	public void setUser(UserType user) {
    	Platform.runLater(() -> {
    		getCurrentUI().setUser(user);
    		getCurrentUI().setHasClient(true);
    	});
	}

    @Override
    public void getTeacher(List<TeacherType> teacher) {
        Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setTeacher(teacher);
            else if(superAdminUI != null)
                superAdminUI.setTeacher(teacher);
        });

    }

    @Override
    public void getPromo(List<PromotionType> promo) {
        Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setPromo(promo);
            else if(superAdminUI != null)
                superAdminUI.setPromo(promo);
        });
    }

    @Override
    public void getClasses(List<ClassType> classes) {
        Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setClasses(classes);
            else if(superAdminUI != null)
                superAdminUI.setClasses(classes);
        });
    }

    /**
     * Get all users in DB and set them in a list for the user management tab.
     */
	@Override
	public void getUsers(List<UserType> users) {
		Platform.runLater(() -> {
            if(adminUI != null)
                adminUI.setUsers(users);
            else if(superAdminUI != null)
                superAdminUI.setUsers(users);
        });
	}
}


package UI;

import java.io.IOException;
import java.util.Optional;

import Users.User;
import common.DisplayIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public abstract class UI extends Application implements DisplayIF {

    //Scenes and other UI elements
    private StackPane root;
	Stage primaryStage;
    Scene connectionScene;
    Scene waitingScene;
    Scene firstConnectionScene;
    StringProperty connectionStatus = new SimpleStringProperty("NOT CONNECTED");
    StringProperty currentState = new SimpleStringProperty("UNDEFINED");

    //Business logic
    protected User user;

    //Information about the user and the general environment
    protected String login;
    protected String password;
    protected int userID;


    /**
     * Creates the instance of the User, for the business logic relative to it
     * @throws IOException
     */
    private void createUser() throws IOException{
        if(user==null){
            String host = "localhost";
            int port = 5555;
            user = new User(host, port, this);
        }
    }

	/**
	 * Create the window with the two scene and set the first scene as main.
	 * 
	 * @param primaryStage Frame window.
	 * @throws Exception
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
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

    }

    protected void setupListeners(){

    }
    
    /**
     * Create an instance of GridPane and apply format on it.
     *
     * @return GridPane Login pane.
     */
    protected GridPane createConnectionFormPane() {
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
    protected void addUIControlsConnectionPane(GridPane gridPane) {
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
        TextField nameField = new TextField("yvan.sanson@etu.umontpellier.fr");
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        // Add Password Label
        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Password Text Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
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
                createUser();
                user.handleLogin(login, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        firstConnectionButton.setOnAction(event -> {
            Platform.runLater(()->primaryStage.setScene(firstConnectionScene));
        });
    }

    protected void addUIControlsFirstConnectionPane(GridPane pane){
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
                createUser();
                user.setFirstPassword(login, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    protected BorderPane createWaitingPane(){
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(40, 40, 40, 40));
        return borderPane;
    }

    protected void addUIControlsWaitingPane(BorderPane pane){
        Text waitingText = new Text("Connexion en cours... Veuillez patienter.");
        pane.setCenter(waitingText);
    }



    /**
     *  Create an instance of StackPane and apply format on it.
     *
     * @return StackPane Chat pane.
     */
    protected StackPane createPrincipalPane() {
        // Instantiate a new VBox
    	StackPane root = new StackPane();

        return root;
    }

    /**
     * Show personalized alert to user.
     *
     * @param alertType Format of the alert.
     * @param owner Window on which alert will be displayed.
     * @param title Title to be displayed.
     * @param message Message to be displayed.
     */
    protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        if(owner != null)
            alert.initOwner(owner);
        else
            alert.initOwner(primaryStage);
        alert.initStyle(StageStyle.DECORATED);
        alert.setResizable(false);
        alert.show();
    }
    @Override
    public void display(String message){

    }
    @Override
    public void setState(String cmd){
        currentState.setValue(cmd);
    }
    public void showLogin(boolean isConnected, int id, String role){

    }

}

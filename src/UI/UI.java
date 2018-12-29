package UI;

import java.io.IOException;
import java.util.Optional;

import Users.User;
import common.DisplayIF;
import javafx.application.Application;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class UI extends Application implements DisplayIF {

    private StackPane root;
	private Stage primaryStage;
	private Scene connectionScene;
	private Scene principalScene;
	private User user;
    private String login;
    private String password;

    private void createUser() throws IOException{
        String host = "localhost";
        int port = 5555;
        user = new User(host, port, this);
    }

	/**
	 * Create the window with the two scene and set the first scene as main.
	 * 
	 * @param primaryStage Frame window.
	 * @throws Exception
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Connection");
        
        // Scene 2
        // Create the registration form grid pane
        root = createPrincipalPane();
        // Add UI controls to the registration form grid pane
        //addUIControls2(root);
        // Create a scene with registration form grid pane as the root node
        principalScene = new Scene(root, 800, 500);
        
        // Scene 1
        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        this.connectionScene = new Scene(gridPane, 800, 500);


        // Set the scene in primary stage	
        this.primaryStage.setScene(connectionScene);
        // Set the stage visible
        this.primaryStage.show();
    }
    
    /**
     * Create an instance of GridPane and apply format on it.
     * 
     * @return GridPane Login pane.
     */
    private GridPane createRegistrationFormPane() {
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
    private void addUIControls(GridPane gridPane) {
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

        // Add Host Label
        Label passwordLabel = new Label("Password: ");
        gridPane.add(passwordLabel, 0, 2);

        // Add Host Text Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 2);

        // Add Login Button
        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(loginButton, 0, 4, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20, 0,20,0));
        
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
            try {
                createUser();
                user.handleLogin(login, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setScene(principalScene);
        });
    }


    
    /**
     *  Create an instance of StackPane and apply format on it.
     * 
     * @return StackPane Chat pane.
     */
    private StackPane createPrincipalPane() {
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
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.setResizable(false);
        alert.showAndWait();
    }

    @Override
    public void showLogin(boolean isConnected, int id, String role){
        if(isConnected){
            showAlert(Alert.AlertType.CONFIRMATION, root.getScene().getWindow(),"Succès", "ID " + id + "role " + role);
        }
        else{
            showAlert(Alert.AlertType.ERROR, root.getScene().getWindow(), "Echec", "Nous n'avons pas pu effectuer la connexion.");
        }
    }

    /**
     * This method overrides the method in the ChatIF interface. 
     * It displays a message onto the screen.
     *
     * @param message The string to be displayed.
     */
	@Override
	public void display(String message) {
		
	}


    @Override
    public void displayCommand(String cmd){

    }
}

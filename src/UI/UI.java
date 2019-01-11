package UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import client.CoreClient;
import common.DisplayIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public abstract class UI extends Application implements DisplayIF {

    //Scenes and other UI elements
    StackPane root;
	Stage primaryStage;
    Scene connectionScene;
    Scene waitingScene;
    Scene firstConnectionScene;
    StringProperty connectionStatus = new SimpleStringProperty("NOT CONNECTED");
    StringProperty currentState = new SimpleStringProperty("UNDEFINED");

    //Business logic
    CoreClient client;

    //Information about the user and the general environment
    protected String login;
    protected String password;
    protected int userID;



	/**
	 * Create the window with the two scene and set the first scene as main.
	 * 
	 * @param primaryStage Frame window.
	 * @throws Exception
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    protected void setupListeners(){

    }


    protected BorderPane createWaitingPane(){
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(40, 40, 40, 40));
        return borderPane;
    }

    protected void addUIControlsWaitingPane(BorderPane pane){
        Text waitingText = new Text("Connecting... Please wait.");
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
        Platform.runLater(() -> {
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
        });

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
    
public Tab createTabProfile() {
    	
    	Tab tabProfile = new Tab();
    	tabProfile.setText("Profile");
    	tabProfile.setClosable(false);
    	
    	// Labels
    	Label name = new Label(login);
    	Label email = new Label("Email: ");
    	Label birthdate = new Label("Birthdate: ");
    	Label id = new Label("ID: ");
    	Label nameDB = new Label();
    	Label emailDB = new Label();
    	Label birthdateDB = new Label();
    	Label idDB = new Label();
    	
    	// Buttons
    	Button changePhotoButton = new Button("Change...");
    	Button changePwdButton = new Button("Change password");
    	changePwdButton.setPrefHeight(40);
    	changePwdButton.setDefaultButton(true);
    	
    	// Photo
    	Image image = null;
		try {
			image = new Image(new FileInputStream("C:\\Users\\Aubin\\Pictures\\avatar.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Setting the image view 
        ImageView imageView = new ImageView(image); 
        //Setting the position of the image 
        imageView.setX(50); 
        imageView.setY(25); 
        //setting the fit height and width of the image view 
        imageView.setFitHeight(150); 
        imageView.setFitWidth(150); 
        //Setting the preserve ratio of the image view 
        imageView.setPreserveRatio(true);  
        //Creating a Group object  
        Group photo = new Group(imageView);  
        
        // GridPane
        GridPane gridProfile = new GridPane();
        gridProfile.setHgap(10);
        gridProfile.setVgap(10);
        gridProfile.setPadding(new Insets(10,10,10,10));
        
        // Box
        VBox photoBox = new VBox();
        HBox nameBox = new HBox();
        HBox emailBox = new HBox();
        HBox birthdateBox = new HBox();
        HBox idBox = new HBox();
        
        // Fill box
        photoBox.getChildren().addAll(photo,changePhotoButton);
        photoBox.setAlignment(Pos.CENTER);
        nameBox.getChildren().addAll(name, nameDB);
        emailBox.getChildren().addAll(email, emailDB);
        birthdateBox.getChildren().addAll(birthdate, birthdateDB);
        idBox.getChildren().addAll(id, idDB);
        
        // Fill gridpane
        gridProfile.add(photoBox, 1, 0);
        gridProfile.add(nameBox, 2, 0);
        gridProfile.add(emailBox, 2, 4);
        gridProfile.add(birthdateBox, 2, 6);
        gridProfile.add(idBox, 2, 8);
        gridProfile.add(changePwdButton, 2, 10);

        // Set tab content
        tabProfile.setContent(gridProfile);
        
        // Event
        changePhotoButton.setOnAction(event -> {
        });
        
        changePwdButton.setOnAction(event -> {
        });
        
        return tabProfile;
    }

}

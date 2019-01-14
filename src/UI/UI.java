package UI;

import java.util.List;

import Types.MessageType;
import client.CoreClient;
import common.DisplayIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
    ObservableList<String> receiversEmail;
    TextArea convo;

    //Business logic
    CoreClient client;

    //Information about the user and the general environment
    protected String login;
    protected String password;
    protected int userID;

    public String getConnectionStatus() {
        return connectionStatus.get();
    }

    public StringProperty connectionStatusProperty() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus.set(connectionStatus);
    }

    public String getCurrentState() {
        return currentState.get();
    }

    public StringProperty currentStateProperty() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState.set(currentState);
    }

    public ObservableList<String> getReceiversEmail() {
        return receiversEmail;
    }

    public void setReceiversEmail(List<String> receiversEmail) {
        this.receiversEmail.setAll(receiversEmail);
    }

    public TextArea getConvo() {
        return convo;
    }

    public void setConvo(TextArea convo) {
        this.convo = convo;
    }

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
    
    /**
     * Create the profile tab for all user type
     * 
     * @return Profile tab
     */
    public Tab createProfileTab() {
    	
    	Tab tabProfile = new Tab();
    	tabProfile.setText("Profile");
    	tabProfile.setClosable(false);
    	
        tabProfile.setContent(readProfile(tabProfile));
        
        return tabProfile;
    }
    
    public GridPane readProfile(Tab tabProfile) {
        
    	// Labels
    	Label name = new Label(login);
    	name.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
    	name.setAlignment(Pos.CENTER_LEFT);
    	Label email = new Label("Email: ");
    	Label birthdate = new Label("Birthdate: ");
    	Label id = new Label("ID: ");
    	Label emailDB = new Label(login);
    	Label birthdateDB = new Label();
    	Label idDB = new Label();
    	
    	// Buttons
    	Button changePhotoButton = new Button("Change...");
    	Button changePwdButton = new Button("Change password");
    	changePwdButton.setPrefHeight(40);
    	changePwdButton.setDefaultButton(true);
    	
    	// Photo
    	Image image = null; //new Image(getClass().getResourceAsStream("images/profilePhoto.png"));
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
        nameBox.getChildren().addAll(name);
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

        // Event
        changePhotoButton.setOnAction(event -> {
        });
        
        changePwdButton.setOnAction(event -> {
        	tabProfile.setContent(updateProfile(tabProfile));
        });
        
        return gridProfile;
    }
    
    public GridPane updateProfile(Tab tabProfile) {
    	// Labels
    	Label old = new Label("Old password: ");
    	Label newPwd = new Label("New password: ");
    	Label newPwdConfirm = new Label("Confirm new password: ");
    	
    	// Password fields
    	PasswordField oldTF = new PasswordField();
    	PasswordField newPwdTF = new PasswordField();
    	PasswordField newPwdConfirmTF = new PasswordField();
    	
    	// Buttons
    	Button updateButton = new Button("Update");
    	Button cancelButton = new Button("Cancel");
    	updateButton.setPrefHeight(40);
    	updateButton.setDefaultButton(true);
    	cancelButton.setPrefHeight(40); 
    	
        // GridPane
        GridPane gridProfile = new GridPane();
        gridProfile.setHgap(10);
        gridProfile.setVgap(10);
        gridProfile.setPadding(new Insets(10,10,10,10));
        
        // Box
        HBox oldPwdBox = new HBox();
        HBox newPwdBox = new HBox();
        HBox cNewPwdBox = new HBox();
        HBox buttonBox = new HBox();
        
        // Fill box
        oldPwdBox.getChildren().addAll(old, oldTF);
        newPwdBox.getChildren().addAll(newPwd, newPwdTF);
        cNewPwdBox.getChildren().addAll(newPwdConfirm, newPwdConfirmTF);
        buttonBox.getChildren().addAll(updateButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Fill gridpane
        gridProfile.add(oldPwdBox, 0, 0);
        gridProfile.add(newPwdBox, 0, 1);
        gridProfile.add(cNewPwdBox, 0, 2);
        gridProfile.add(buttonBox, 0, 3);

        // Event
        cancelButton.setOnAction(event -> {
        	tabProfile.setContent(readProfile(tabProfile));
        });
        
        updateButton.setOnAction(event -> {
        	if(oldTF.getText()!=null && newPwdTF.getText()!=null && newPwdConfirmTF.getText()!=null) {
        		if(newPwdTF.getText().equals(newPwdConfirmTF.getText())) {
        			client.handleUpdatePwd(login, newPwdTF.getText());
                	tabProfile.setContent(readProfile(tabProfile));
        		} else {
        			showAlert(Alert.AlertType.ERROR, gridProfile.getScene().getWindow(), "Form Error!", "Password don't correspond");
        		}
        	} else {
        		showAlert(Alert.AlertType.ERROR, gridProfile.getScene().getWindow(), "Form Error!", "Please enter all fields.");
        	}
        });
        
        return gridProfile;
    }

    Tab createChatTab(){
        client.getConversationEmail(userID);
        Tab chatTab = new Tab();
        chatTab.setText("Chat");
        chatTab.setClosable(false);
        BorderPane chatPane = new BorderPane();
        HBox newConv = new HBox();
        Label startConvLabel = new Label("Email for a new conversation: ");
        TextField startConvEmail = new TextField();
        Button startConvButton = new Button("New conversation");
        startConvButton.setOnAction(event -> {
            if(startConvEmail.getText()!=null){
                receiversEmail.add(startConvEmail.getText());
                if(convo!=null)
                    convo.setText("");
            }

        });
        newConv.setSpacing(20);
        newConv.setPadding(new Insets(15,12,15,12));
        newConv.getChildren().addAll(startConvLabel, startConvEmail, startConvButton);

        ScrollPane conversations = new ScrollPane();
        conversations.setFitToWidth(true);
        conversations.setFitToHeight(true);
        convo = new TextArea();
        convo.setEditable(false);
        convo.setPrefWidth(conversations.getHvalue());
        convo.setWrapText(true);
        conversations.setContent(convo);
        conversations.setPrefHeight(500);

        VBox conversationList = new VBox();
        Label conversationListLabel = new Label("All conversations");
        conversationList.setSpacing(10);
        conversationList.setPadding(new Insets(10,10,10,10));
        ListView<String> convoNameList = new ListView<>();
        receiversEmail = FXCollections.observableArrayList("");
        receiversEmail.addListener((ListChangeListener<String>) c -> convoNameList.setItems(receiversEmail));
        convoNameList.setOnMouseClicked(event -> client.readConversation(userID, convoNameList.getSelectionModel().getSelectedItem()));
        conversationList.getChildren().addAll(conversationListLabel, convoNameList);

        HBox sendMsgBar = new HBox();
        sendMsgBar.setSpacing(20);
        sendMsgBar.setPadding(new Insets(15, 12, 15, 12));
        Text enterMsg = new Text("Enter your message: ");
        enterMsg.setFont(Font.font("Cambria", FontPosture.ITALIC, 15));
        TextField msgInput = new TextField();
        msgInput.setPrefWidth(600);
        Button sendBtn = new Button("Send");
        msgInput.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.ENTER)){
                if(client!=null){
                    client.sendMsgToClient(userID, convoNameList.getSelectionModel().getSelectedItem(),msgInput.getText());
                    msgInput.setText("");
                    client.readConversation(userID, convoNameList.getSelectionModel().getSelectedItem());
                }
            }
        });
        sendBtn.setOnAction(event -> {
            if(client!=null){
                client.sendMsgToClient(userID, convoNameList.getSelectionModel().getSelectedItem(),msgInput.getText());
                msgInput.setText("");
                client.readConversation(userID, convoNameList.getSelectionModel().getSelectedItem());
            }
        });

        sendMsgBar.getChildren().addAll(enterMsg, msgInput, sendBtn);

        chatPane.setTop(newConv);
        chatPane.setCenter(conversations);
        chatPane.setLeft(conversationList);
        chatPane.setBottom(sendMsgBar);
        chatPane.setPadding(new Insets(10,10,10,10));

        chatTab.setContent(chatPane);
        return chatTab;
    }

    @Override
    public void setConversationMessages(List<MessageType> conversationMessages){
        if(convo != null){
            StringBuilder sb = new StringBuilder();
            for(MessageType message : conversationMessages){
                sb.append(message.toString());
                sb.append("\n");
            }
            convo.setText(sb.toString());
        }
    }

    @Override
    public void setConversationEmails(List<String> emails) {
        emails.remove(0);
        if (emails.size() != 0) {
            Platform.runLater(() -> receiversEmail.setAll(emails));
        }
    }

}

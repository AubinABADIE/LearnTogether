package UI;

import java.io.File;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import Types.*;
import client.CoreClient;
import common.DisplayIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * Core UI from which inherits all the other.
 * It contains the all the shared information and common methods.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
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
    BooleanProperty hasClient = new SimpleBooleanProperty(false);
    TextArea convo;
    private ObservableList<CourseType> courseNames;
    protected ObservableList<RecordType> recordNames;


    //Business logic
    CoreClient client;

    //Information about the user and the general environment
    protected String login;
    protected String password;
    protected int userID;
    protected UserType user;
    protected File recordFile;




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
    
    public BooleanProperty getHasClient() {
		return hasClient;
	}

	public void setHasClient(boolean hasClient) {
		this.hasClient.setValue(hasClient);
	}

    public ObservableList<CourseType> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<CourseType> courseNames) {
        if(this.courseNames != null)
            this.courseNames.setAll(courseNames);
    }

    public File getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public ObservableList<RecordType> getRecordNames() {
        return recordNames;
    }

    public void setRecordNames(List<RecordType> recordNames) {
        this.recordNames.setAll(recordNames);
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
    	
        client.handleReadUser(userID);
    	
    	// Labels
    	Label name = new Label();
    	name.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
    	name.setAlignment(Pos.CENTER_LEFT);
    	Label email = new Label("Email: ");
    	Label birthdate = new Label("Birthdate: ");
    	Label id = new Label("ID: ");
    	Label emailDB = new Label();
    	Label birthdateDB = new Label();
    	Label idDB = new Label();
    	
    	if(hasClient.getValue()) {
    		name.setText(user.getName() + " " + user.getFirstName());
			emailDB.setText(user.getEmail());
			birthdateDB.setText(user.getBirthDate());
			idDB.setText(Integer.toString(user.getId()));
    	}
    	
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
        
        hasClient.addListener((observable, oldValue, newValue)->{
    		if(newValue) {
    			name.setText(user.getName() + " " + user.getFirstName());
    			emailDB.setText(user.getEmail());
    			birthdateDB.setText(user.getBirthDate());
    			idDB.setText(Integer.toString(user.getId()));
    		}
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
        startConvEmail.setPrefWidth(200);
        Button startConvButton = new Button("New conversation");
        startConvButton.setOnAction(event -> {
            if(startConvEmail.getText()!=null){
                receiversEmail.add(startConvEmail.getText());
                if(convo!=null)
                    convo.setText("");
            }

        });
        Image delConv = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView delConvView = new ImageView(delConv);
        delConvView.setFitHeight(12);
        delConvView.setFitWidth(12);
        Button btnDelConv = new Button("Delete this conversation...");
        btnDelConv.setGraphic(delConvView);//setting icon to button


        newConv.setSpacing(20);
        newConv.setPadding(new Insets(15,12,15,12));
        newConv.getChildren().addAll(startConvLabel, startConvEmail, startConvButton, btnDelConv);

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
                    String convEmail = convoNameList.getSelectionModel().getSelectedItem();
                    if(convEmail != null) {
                        client.sendMsgToClient(userID, convoNameList.getSelectionModel().getSelectedItem(), msgInput.getText());
                        msgInput.setText("");
                        client.readConversation(userID, convoNameList.getSelectionModel().getSelectedItem());
                    }
                }
            }
        });
        sendBtn.setOnAction(event -> {
            String convEmail = convoNameList.getSelectionModel().getSelectedItem();
            if(convEmail != null) {
                client.sendMsgToClient(userID, convoNameList.getSelectionModel().getSelectedItem(), msgInput.getText());
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

        btnDelConv.setOnAction(event -> {
            String emailConv = convoNameList.getSelectionModel().getSelectedItem();
            if(emailConv != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this conversation with " + emailConv + "?", ButtonType.YES, ButtonType.NO);
                alert.setHeaderText("Confirmation delete");
                Window win = chatPane.getScene().getWindow();
                alert.initOwner(win);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.NO) {
                    return;
                }
                if (alert.getResult() == ButtonType.YES) {
                    client.deleteConversation(userID, emailConv);
                    client.getConversationEmail(userID);
                }
            }
        });

        chatTab.setContent(chatPane);
        return chatTab;
    }

    /**
     * Create the record tab
     * @return Tab which is the record tab
     */
    protected Tab createRecordTab (){

        Tab tabRecords = new Tab();
        tabRecords.setText("Record");
        tabRecords.setClosable(false);

        tabRecords.setContent(readRecords(tabRecords));

        return tabRecords;

    }

    /**
     * This method build the default record tab
     * @param tabRecords : tab record
     * @return : the gridPane which are displaying
     */
    protected GridPane readRecords(Tab tabRecords){
        //add  records
        client.getAllRecords();
        ListView<RecordType> list = new ListView<>();
        recordNames = FXCollections.observableArrayList();
        recordNames.addListener((ListChangeListener<RecordType>) c -> {
            list.setItems(recordNames);
        });


        list.setItems(recordNames);

        Image fileImg = new Image(getClass().getResourceAsStream("images/icons8-document-480.png"));
        Image fileImgDown = new Image(getClass().getResourceAsStream("images/icons8-download-filled-100.png"));
        ImageView downView = new ImageView(fileImgDown);

        //add images on the items in the recordList
        list.setCellFactory(param -> new ListCell<RecordType>() {
            private ImageView imageView = new ImageView();
            Button btnDown = new Button();
            @Override
            public void updateItem(RecordType nameR, boolean empty) {
                super.updateItem(nameR, empty);
                imageView.setImage(fileImg);
                setGraphic(imageView);
                btnDown.setGraphic(downView);//setting icon to button
            }
            //event download button




        });

        list.setPrefWidth(350);
        list.setPrefHeight(500);

        Image addRec = new Image(getClass().getResourceAsStream("images/icons8-plus-208.png"));
        ImageView addRecView = new ImageView(addRec);
        addRecView.setFitHeight(15);
        addRecView.setFitWidth(15);

        //create button add
        Button btnAddRec = new Button("Add");
        btnAddRec.setGraphic(addRecView);//setting icon to button
        btnAddRec.setOnAction(event -> {
            tabRecords.setContent(addRecord(tabRecords));
        });


        //delete button
        Image deleteRec = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));
        ImageView deleteRecView = new ImageView(deleteRec);
        deleteRecView.setFitHeight(12);
        deleteRecView.setFitWidth(12);

        //create button delete
        Button btnDeleteRec = new Button("Delete");
        btnDeleteRec.setGraphic(deleteRecView);//setting icon to button
        btnDeleteRec.setOnAction(event -> {
            tabRecords.setContent(deleteRecord(tabRecords));
                });


        // add in hbox buttons and title
        HBox hboxButtonRec = new HBox();
        hboxButtonRec.setPadding(new Insets(5, 0, 0, 5));

        Text title = new Text("Record : ");
        title.setFont(Font.font(20));
        hboxButtonRec.getChildren().add(title);
        hboxButtonRec.getChildren().add(btnAddRec);
        hboxButtonRec.getChildren().add(btnDeleteRec);
        hboxButtonRec.setSpacing(5);


        GridPane gridRecord = new GridPane();
        gridRecord.setHgap(10);
        gridRecord.setVgap(10);

        gridRecord.add(hboxButtonRec,1,0);
        gridRecord.add(list, 1, 1);


        return gridRecord;
    }

    /**
     * This method buils pane of add record.
     * @param tabRecords : tab record
     * @return return the gridPane with the form
     */
    protected GridPane addRecord(Tab tabRecords){

        //return button
        Image returnRec = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnRecView = new ImageView(returnRec);
        returnRecView.setFitHeight(15);
        returnRecView.setFitWidth(15);

        //create return button
        Button btnReturnRec = new Button();
        btnReturnRec.setGraphic(returnRecView);//setting icon to button

        btnReturnRec.setOnAction(event -> {
            tabRecords.setContent(readRecords(tabRecords));
        });

        //form
        Text titleRecord = new Text("Add a Record :");
        titleRecord.setFont(Font.font(20));

        HBox returnTitleBox = new HBox();

        returnTitleBox.getChildren().addAll(titleRecord, btnReturnRec);
        returnTitleBox.setSpacing(600);

        Label courseLab = new Label("Course : ");
        Label dateLab = new Label("Exam date : ");

        Image imageUp = new Image(getClass().getResourceAsStream("images/icons8-upload.png"), 15,15,true, false);
        Label labelUp = new Label("Upload");
        labelUp.setGraphic(new ImageView(imageUp));

        client.getCourses();
        ListView<CourseType> listC = new ListView<>();
        courseNames = FXCollections.observableArrayList();
        courseNames.addListener((ListChangeListener<CourseType>) c -> listC.setItems(courseNames));

        //list of courses
        ComboBox courseComboBox = new ComboBox();
        courseComboBox.setItems(courseNames);
        courseComboBox.getSelectionModel().select(1);

        //date selector
        final Spinner<Integer> examDate = new Spinner<Integer>();

        SpinnerValueFactory<Integer> examDateFact = new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2018);
        examDate.setValueFactory(examDateFact);

        //upload file
        Button uploadButton = new Button("Select Records");

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        HBox hboxCourses = new HBox();
        HBox hboxDate = new HBox();
        HBox hboxUpload= new HBox();
        HBox hboxButtons = new HBox();
        hboxCourses.setAlignment(Pos.CENTER);
        hboxDate.setAlignment(Pos.CENTER);
        hboxUpload.setAlignment(Pos.CENTER);
        hboxButtons.setAlignment(Pos.CENTER);

       final Text textFile = new Text("");
       

        //AtomicReference<File> record = null;

        //event to choose file and seethe result
        uploadButton.setOnAction(event -> {
            File record = fileChooser.showOpenDialog(primaryStage);
            
            if (record != null) {
                textFile.setText(" Charged file");
                setRecordFile(record);
            } else {
                textFile.setText(" You don't have a file charged currently");
            }
            hboxUpload.getChildren().add(textFile);
        });

        // buttons
        Button createB = new Button("Add");
        Button cancelB = new Button("Cancel");

        //add in hbox
        hboxCourses.getChildren().addAll(courseLab, courseComboBox);
        hboxDate.getChildren().addAll(dateLab,examDate);
        hboxUpload.getChildren().addAll(labelUp, uploadButton);
        hboxButtons.getChildren().addAll(createB, cancelB);

        hboxCourses.setSpacing(10);
        hboxDate.setSpacing(10);
        hboxUpload.setSpacing(10);
        hboxButtons.setSpacing(10);
        hboxCourses.setPadding( new Insets(10, 0, 0, 175));
        hboxDate.setPadding( new Insets(10, 0, 0, 175));
        hboxUpload.setPadding( new Insets(10, 0, 0, 175));
        hboxButtons.setPadding( new Insets(10, 0, 0, 175));

        GridPane gridAddRec = new GridPane();
        gridAddRec.setHgap(10);
        gridAddRec.setVgap(10);
        gridAddRec.setPadding(new Insets(10,10,10,10));

        gridAddRec.add(returnTitleBox, 1, 1);
        gridAddRec.add(hboxCourses, 1, 2);
        gridAddRec.add(hboxDate, 1, 3);
        gridAddRec.add(hboxUpload, 1, 4);
        gridAddRec.add(hboxButtons, 1, 5);

        createB.setOnAction(event -> {
            if (courseComboBox.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, gridAddRec.getScene().getWindow(), "Form Error!", "Please select a course name");
                return;
            }
            if (examDate.getValue() == null) {
                showAlert(Alert.AlertType.ERROR, gridAddRec.getScene().getWindow(), "Form Error!", "Please enter an exam date");
                return;
            }
            if(recordFile == null){
                showAlert(Alert.AlertType.ERROR, gridAddRec.getScene().getWindow(), "Form Error!", "Please upload a pdf file");
                return;
            }
            client.createRecord(((CourseType)courseComboBox.getValue()).getId(), examDate.getValue(), recordFile, userID);
            courseComboBox.setValue(null);

        });

        cancelB.setOnAction(event -> {
            tabRecords.setContent(readRecords(tabRecords));
        });

        return gridAddRec;
    }

    /**
     * This method build the pane to delete a record
     * @param tabRecords : the record tab
     * @return the gridPane with the delete interface
     */
    protected GridPane deleteRecord(Tab tabRecords){

        //return button
        Image returnRec = new Image(getClass().getResourceAsStream("images/icons8-return.png"));
        ImageView returnRecView = new ImageView(returnRec);
        returnRecView.setFitHeight(15);
        returnRecView.setFitWidth(15);

        //create return button
        Button btnReturnRecD = new Button();
        btnReturnRecD.setGraphic(returnRecView);//setting icon to button

        btnReturnRecD.setOnAction(event -> {
            tabRecords.setContent(readRecords(tabRecords));
        });

        //title
        Text titleDeleteRecord = new Text("Delete a Record :");
        titleDeleteRecord.setFont(Font.font(20));

        //record list

        //client.getRecordsByUser();
        ListView<RecordType> list = new ListView<>();
        recordNames = FXCollections.observableArrayList();
        recordNames.addListener((ListChangeListener<RecordType>) c -> {
            list.setItems(recordNames);
        });

        list.setItems(recordNames);
        list.setPrefWidth(350);
        list.setPrefHeight(500);

        Image deleteR = new Image(getClass().getResourceAsStream("images/icons8-annuler-208.png"));

        //add images on the items in the recordList
        list.setCellFactory(param -> new ListCell<RecordType>() {
            private ImageView imageView = new ImageView();
            Button deleteRB = new Button();
            @Override
            public void updateItem(RecordType nameR, boolean empty) {
                super.updateItem(nameR, empty);
                imageView.setImage(deleteR);
                deleteRB.setGraphic(imageView);
            }
            //event delete




        });


        HBox returnTitleBox = new HBox();

        returnTitleBox.getChildren().addAll(titleDeleteRecord, btnReturnRecD);
        returnTitleBox.setSpacing(600);


        GridPane deleteGrid = new GridPane();
        deleteGrid.add(returnTitleBox, 1,0);
        deleteGrid.add(list, 1,1);

        return deleteGrid;
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

    @Override
    public void getRecords(List<RecordType>records){

    }
}

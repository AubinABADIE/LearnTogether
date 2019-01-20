package UI;

import Types.AdminType;
import Types.TeacherType;
import client.CoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
 * This UI is when a Super Admin wants to use this application.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class SuperAdminUI extends AdminUI {
    private Scene principalSuperAdminScene;
    /**
     * Default constructor
     */
    public SuperAdminUI(Stage primaryStage, String login, int id, CoreClient client) {
        super(primaryStage, login, id, client);
    }

    public Scene getPrincipalSuperAdminScene() {
        return principalSuperAdminScene;
    }

    public void setPrincipalSuperAdminScene(Scene principalSuperAdminScene) {
        this.principalSuperAdminScene = principalSuperAdminScene;
    }

    public Scene createPrincipalSuperAdminScene(){
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

        tabDepartment =  tabDepartment();

        tabAdmManagement = createTabAdmManagement();

        tabPane.getTabs().add(tabProfile);
        tabPane.getTabs().add(tabSchedule);
        tabPane.getTabs().add(tabRecords);
        tabPane.getTabs().add(tabDiary);
        tabPane.getTabs().add(tabChat);
        tabPane.getTabs().add(tabRoom);
        tabPane.getTabs().add(tabCourse);
        tabPane.getTabs().add(tabDepartment);
        tabPane.getTabs().add(tabAdmManagement);


        topBar.getChildren().addAll(titleBar, tabPane);
        adminScene.setTop(topBar);
        titleBar.setSpacing(20);
        titleBar.setPadding(new Insets(15, 12, 15, 12));

        principalSuperAdminScene = new Scene(adminScene, 900, 700);
        return principalSuperAdminScene;
    }

    public void addUIControls(BorderPane borderPane){

    }

    protected Tab createTabAdmManagement() {

        Tab tabAdmManagement= new Tab();
        tabAdmManagement.setText("Admin Management");
        tabAdmManagement.setClosable(false);


        tabAdmManagement.setContent(AdmManaRead(tabAdmManagement));
        return tabAdmManagement;
    }

    /**
     *
     * @param tabAdmManagement
     * @return
     */
    protected GridPane AdmManaRead(Tab tabAdmManagement) {
        /*add list of users*/

        client.getTeacher();
        ListView<TeacherType> userList = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
            userList.setItems(teacherNames);
        });
        Text titlePAdm = new Text("Possible Admins ");

        client.getAdmin();
        ListView<AdminType> admList = new ListView<>();
        admNames = FXCollections.observableArrayList();
        admNames.addListener((ListChangeListener<AdminType>) c -> {
            admList.setItems(admNames);
        });
        Text titleAdm = new Text("Actual Admins ");

        // add in hbox buttons and title
        HBox hboxButtonUser = new HBox();

        Text title = new Text("Admin Managment : ");
        title.setFont(Font.font(20));
        hboxButtonUser.getChildren().add(title);
        hboxButtonUser.setSpacing(5);

        // left vbox
        VBox vboxListPAdm= new VBox();
        vboxListPAdm.getChildren().addAll(titlePAdm,userList);


        // right vbox
        VBox vboxListAdm = new VBox();
        vboxListAdm.getChildren().addAll(titleAdm,admList);

        //grid pane
        GridPane gridUserVisu = new GridPane();
        gridUserVisu.setHgap(10);
        gridUserVisu.setVgap(10);
        gridUserVisu.setPadding(new Insets(10,10,10,10));
        gridUserVisu.add(hboxButtonUser, 1, 0);
        gridUserVisu.add(vboxListPAdm, 1, 2);
        gridUserVisu.add(vboxListAdm, 3, 2);

        /*creation of the info vbox of one user*/
        VBox vboxInfoUser = new VBox();

        //title of column
        HBox hboxUserInfo = new HBox();
        Text titleInfo = new Text("Person information : ");
        titleInfo.setFont(Font.font(20));
        hboxUserInfo.getChildren().add(titleInfo);
        hboxUserInfo.setAlignment(Pos.CENTER);

        // initialisation label and input
        HBox hboxnameUserInfo = new HBox();
        HBox hboxbirthdateUserInfo = new HBox();
        HBox hboxemailUserInfo = new HBox();
        HBox hboxAdminInfo = new HBox();
        HBox hboxroleUserInfo = new HBox();
        Label nameLabel = new Label("Name: ");
        Label emailLabel = new Label("Email: ");
        Label adminLabel = new Label("Admin: ");
        Label roleLabel = new Label("Role: ");
        Text name = new Text(" ");
        Text email = new Text(" ");
        Text admin = new Text(" ");
        Text role = new Text(" ");


        hboxnameUserInfo.getChildren().addAll(nameLabel,name);
        hboxemailUserInfo.getChildren().addAll(emailLabel,email);
        hboxroleUserInfo.getChildren().addAll(roleLabel,role);
        hboxAdminInfo.getChildren().addAll(adminLabel,admin);

        hboxnameUserInfo.setAlignment(Pos.CENTER);
        hboxemailUserInfo.setAlignment(Pos.CENTER);
        hboxroleUserInfo.setAlignment(Pos.CENTER);
        hboxAdminInfo.setAlignment(Pos.CENTER);

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateUser = new Button("Become Admin");
        hboxupdateButton.getChildren().add(btnUpdateUser);
        hboxupdateButton.setAlignment(Pos.CENTER);

        vboxInfoUser.getChildren().addAll(hboxnameUserInfo,hboxbirthdateUserInfo,hboxemailUserInfo, hboxroleUserInfo, hboxAdminInfo, hboxupdateButton);
        vboxInfoUser.setSpacing(10);
        vboxInfoUser.setPadding( new Insets(100, 0, 0, 75));


        userList.setOnMouseClicked(event -> {
            gridUserVisu.getChildren().remove(vboxInfoUser);
            gridUserVisu.add(vboxInfoUser, 2, 2);
            System.out.println("clicked on " + userList.getSelectionModel().getSelectedItem());
            SelectionModel<TeacherType> selectedUser = userList.getSelectionModel();
            name.setText(selectedUser.getSelectedItem().getName() + " " + selectedUser.getSelectedItem().getFirstName());
            email.setText(selectedUser.getSelectedItem().getEmail());
            role.setText(selectedUser.getSelectedItem().getRole());
            admin.setText(selectedUser.getSelectedItem().isAdmin());

        });

        return gridUserVisu;
    }

}
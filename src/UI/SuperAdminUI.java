package UI;

import Types.AdminType;
import Types.StaffType;
import Types.TeacherType;
import Types.UserType;
import client.CoreClient;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
     * Useful constructor.
     * @param primaryStage the primary stage.
     * @param login the client login.
     * @param id the client ID.
     * @param client the business logic.
     */
    public SuperAdminUI(Stage primaryStage, String login, int id, CoreClient client) {
        super(primaryStage, login, id, client);
    }

    /**
     * This method creates the scene (view) for the Super Admin.
     * It is called by the startUI when a student logs in.
     * @return the principal scene
     */
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

    /**
     * This method creates the tab for admin management.
     * @return the tab.
     */
    protected Tab createTabAdmManagement() {

        Tab tabAdmManagement= new Tab();
        tabAdmManagement.setText("Admin Management");
        tabAdmManagement.setClosable(false);


        tabAdmManagement.setContent(admManaRead(tabAdmManagement));
        return tabAdmManagement;
    }

    /**
     * This method creates and sets up the admin management pane.
     * @param tabAdmManagement the admin management tab
     * @return the populated grid pane
     */
    protected GridPane admManaRead(Tab tabAdmManagement) {
        /*add list of users*/

        client.getTeacherNotAdmin();
        ListView<TeacherType> userList = new ListView<>();
        teacherNames = FXCollections.observableArrayList();
        teacherNames.addListener((ListChangeListener<TeacherType>) c -> {
            userList.setItems(teacherNames);
        });
        Text titlePAdm = new Text("Teachers Not Admin ");

        client.getAdmin();
        ListView<AdminType> admList = new ListView<>();
        admNames = FXCollections.observableArrayList();
        admNames.addListener((ListChangeListener<AdminType>) c -> {
            admList.setItems(admNames);
        });
        Text titleAdm = new Text("Admins ");

        client.getStaffNotAdmin();
        ListView<StaffType> staffList = new ListView<>();
        staffNames = FXCollections.observableArrayList();
        staffNames.addListener((ListChangeListener<StaffType>) c -> {
            staffList.setItems(staffNames);
        });
        Text titleStaff = new Text("Staffs Not Admin ");


        // hbox title
        HBox hboxButtonUser = new HBox();
        Text title = new Text("Admin Managment : ");
        title.setFont(Font.font(20));
        hboxButtonUser.getChildren().add(title);
        hboxButtonUser.setSpacing(5);

        // left vbox
        VBox vboxListPAdm= new VBox();
        vboxListPAdm.getChildren().addAll(titlePAdm,userList);
        // middle vbox
        VBox vboxListStaff = new VBox();
        vboxListStaff.getChildren().addAll(titleStaff,staffList);
        // right vbox
        VBox vboxListAdm = new VBox();
        vboxListAdm.getChildren().addAll(titleAdm,admList);

        //grid pane
        GridPane gridUserVisu = new GridPane();
        gridUserVisu.setHgap(10);
        gridUserVisu.setVgap(10);
        gridUserVisu.setPadding(new Insets(10,10,10,10));
        gridUserVisu.add(hboxButtonUser, 1, 0);
        gridUserVisu.add(vboxListPAdm, 1, 3);
        gridUserVisu.add(vboxListStaff, 2, 3);
        gridUserVisu.add(vboxListAdm, 6, 3);

        /*creation of the info vbox of one user*/
        VBox vboxInfoUser = new VBox();

        /*creation of the info vbox of one admin*/
        VBox vboxInfoAdmin = new VBox();

        //create update button
        HBox hboxupdateButton = new HBox();
        Button btnUpdateUser = new Button("Add Admin");
        hboxupdateButton.getChildren().add(btnUpdateUser);
        hboxupdateButton.setAlignment(Pos.CENTER);


        //create update button
        HBox hboxupdateInvButton = new HBox();
        Button btnUpdateAdm = new Button("Remove Admin");
        hboxupdateInvButton.getChildren().add(btnUpdateAdm);
        hboxupdateInvButton.setAlignment(Pos.CENTER);

        vboxInfoUser.getChildren().addAll(hboxupdateButton);
        vboxInfoUser.setSpacing(10);
        vboxInfoUser.setPadding( new Insets(100, 0, 0, 75));

        vboxInfoAdmin.getChildren().addAll(hboxupdateInvButton);
        vboxInfoAdmin.setSpacing(10);
        vboxInfoAdmin.setPadding( new Insets(100, 0, 0, 75));


        userList.setOnMouseClicked(event -> {
            admList.getSelectionModel().clearSelection();
            gridUserVisu.getChildren().remove(vboxInfoUser);
            gridUserVisu.getChildren().remove(vboxInfoAdmin);
            gridUserVisu.add(vboxInfoUser, 2, 1);
            System.out.println("clicked on " + userList.getSelectionModel().getSelectedItem());

        });

        admList.setOnMouseClicked(event -> {
            userList.getSelectionModel().clearSelection();
            gridUserVisu.getChildren().remove(vboxInfoUser);
            gridUserVisu.getChildren().remove(vboxInfoAdmin);
            gridUserVisu.add(vboxInfoAdmin, 2, 1);
            System.out.println("clicked on " + admList.getSelectionModel().getSelectedItem());
        });

        btnUpdateUser.setOnAction(event -> {
            SelectionModel<TeacherType> selectedUser = userList.getSelectionModel();
            if (selectedUser.getSelectedItem() != null) {
                client.handleUpdateAdminUser(selectedUser.getSelectedItem().getId(), selectedUser.getSelectedItem().getName(), selectedUser.getSelectedItem().getFirstName(), selectedUser.getSelectedItem().getEmail(), selectedUser.getSelectedItem().getBirthDate(),selectedUser.getSelectedItem().getRole(),1);
                tabAdmManagement.setContent(admManaRead(tabAdmManagement));

            }
        });

        btnUpdateAdm.setOnAction(event -> {
            SelectionModel<AdminType> selectedUserA = admList.getSelectionModel();
            if (selectedUserA.getSelectedItem() != null) {
                client.handleUpdateAdminUser(selectedUserA.getSelectedItem().getId(), selectedUserA.getSelectedItem().getName(), selectedUserA.getSelectedItem().getFirstName(), selectedUserA.getSelectedItem().getEmail(), selectedUserA.getSelectedItem().getBirthDate(),selectedUserA.getSelectedItem().getRole(),0);
                tabAdmManagement.setContent(admManaRead(tabAdmManagement));            }
        });



        return gridUserVisu;
    }


}
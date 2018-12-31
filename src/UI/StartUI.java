package UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class StartUI extends UI {
    private AdminUI adminUI;
    private StudentUI studentUI;
    private SuperAdminUI superAdminUI;
    private TeacherUI teacherUI;
    private StringProperty connectionStatus = new SimpleStringProperty("NOT CONNECTED");

    public static void main(String[] args) {
        Application.launch(StartUI.class, args);
    }

    @Override
    public void displayCommand(String cmd){
    }

    public void setPrincipalSceneAsStudent(){
        studentUI = new StudentUI(primaryStage, login, userID);
        primaryStage.setScene(studentUI.createPrincipalStudentScene());
    }

    public void setPrincipalSceneAsTeacher(){
        teacherUI = new TeacherUI(primaryStage, login, userID);
        primaryStage.setScene(teacherUI.createPrincipalTeacherScene());
    }

    public void setPrincipalSceneAsAdmin(){
        adminUI = new AdminUI(primaryStage, login, userID);
        primaryStage.setScene(adminUI.createPrincipalAdminScene());
    }

    public void setPrincipalSceneAsSuperAdmin(){
        superAdminUI = new SuperAdminUI(primaryStage, login, userID);
        primaryStage.setScene(superAdminUI.createPrincipalSuperAdminScene());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);

    }

    @Override
    protected void setupListeners(){
        connectionStatus.addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("STUDENT")){
                Platform.runLater(this::setPrincipalSceneAsStudent);
            }
            else if(newValue.equalsIgnoreCase("TEACHER")){
                Platform.runLater(this::setPrincipalSceneAsTeacher);
            }
            else if(newValue.equalsIgnoreCase("ADMIN")){
                Platform.runLater(this::setPrincipalSceneAsAdmin);
            }
            else if(newValue.equalsIgnoreCase("SUPER ADMIN")){
                Platform.runLater(this::setPrincipalSceneAsSuperAdmin);
            }
            else if(newValue.equalsIgnoreCase("CONNECTION ERROR")){
                Platform.runLater(()->primaryStage.setScene(connectionScene));
                showAlert(Alert.AlertType.ERROR, null, "Echec", "Nous n'avons pas pu effectuer la connexion.");
            }
            else if(newValue.equalsIgnoreCase("WAITING")){
                Platform.runLater(() -> primaryStage.setScene(waitingScene));
            }
        });

    }

    @Override
    public void setWaiting(boolean value){
        connectionStatus.setValue("WAITING");
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
                case "SUPER ADMIN":
                    connectionStatus.setValue("SUPER ADMIN");
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
}


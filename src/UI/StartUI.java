package UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class StartUI extends UI {
    private AdminUI adminUI;
    private StudentUI studentUI;
    private SuperAdminUI superAdminUI;
    private TeacherUI teacherUI;

    public static void main(String[] args) {
        Application.launch(StartUI.class, args);
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
                Platform.runLater(() -> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "Connected."));
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

        currentState.addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("FC SUCCESS")){
                Platform.runLater(()-> showAlert(Alert.AlertType.CONFIRMATION, null, "Success", "This account has been activated. You can now log in properly."));
                Platform.runLater(()->primaryStage.setScene(connectionScene));
            }
            else if(newValue.equalsIgnoreCase("FC FAILURE")){
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, null, "Failure", "Error: impossible to activate your account. Is it already activated? Otherwise, please check later."));
                Platform.runLater(()->primaryStage.setScene(connectionScene));
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


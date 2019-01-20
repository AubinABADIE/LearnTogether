package client.Groups;


import client.CoreClient;
import java.io.IOException;

/**
 * This is the business logic related to the department on the client side.
 * @author Audrey SAMSON
 */
public class DepartmentServices {

    /**
     * Attributes
     */
    private CoreClient coreClient;
    /**
     * Default constructor
     */
    public DepartmentServices() {
    }

    public DepartmentServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * Methods
     */

    /**
     * This method asks the server to create a department with the following attributes.
     * @param name is the department name.
     * @param refTeacherID is the referring teacher of this department, recognized by its ID.
     * @param descDep is the description of the department.
     */
    public void createDepartment(String name, int refTeacherID, String descDep) {
        try {
            coreClient.getConnection().sendToServer("#CREATEDEP-/-" + name + "-/-" + refTeacherID + "-/-" + descDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks the server to update an existing department with the following attributes.
     * @param name is the department name.
     * @param refTeacherID is the referring teacher of this department, recognized by its ID.
     * @param descDep is the description of the department.
     */
    public void updateDepartment(int idDep,String name, int refTeacherID, String descDep) {
        try {
            coreClient.getConnection().sendToServer("#UPDATEDEP-/-" + idDep + "-/-" + name + "-/-" + refTeacherID + "-/-" + descDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**This method asks to the server to delete an existing department.
     * @param idDep : department id
     */
    public void deleteDepartment(int idDep) {
        try {
            coreClient.getConnection().sendToServer("#DELETEDEP-/-" + idDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the server response when it created a department
     * @param msg :String with the state of creation
     */
    public void handleCreatedDepartment(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DC SUCCESS");
        else
            coreClient.getDisplay().setState("DC FAILURE");
    }

    /**
     * This method handles the server response when it updated a department
     * @param msg : String with the state of updated
     */
    public void handleUpdatedDepartment(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DEU SUCCESS");
        else
            coreClient.getDisplay().setState("DEU FAILURE");
    }

    /**
     * This method handles the server response when it deleted a department
     * @param msg : String with the state of deletion
     */
    public void handleDeletedDepartment(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DD SUCCESS");
        else
            coreClient.getDisplay().setState("DD FAILURE");
    }

    /**
     * This method send a message to the server to have the departments list
     */
    public void getDepartment() {
        try {
            coreClient.getConnection().sendToServer("#GETDEPARTMENT" );
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
package client.Groups;


import client.CoreClient;
import java.io.IOException;

/**
 * 
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

    /**
     * @param idDep
     */
    public void deleteDepartment(int idDep) {
        try {
            coreClient.getConnection().sendToServer("#DELETEDEP-/-" + idDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCreatedDepartment(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DC SUCCESS");
        else
            coreClient.getDisplay().setState("DC FAILURE");
    }

    public void handleUpdatedDepartment(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DEU SUCCESS");
        else
            coreClient.getDisplay().setState("DEU FAILURE");
    }

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
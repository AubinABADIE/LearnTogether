package client.Groups;


import client.CoreClient;
import java.io.IOException;

/**
 * 
 */
public class Department {

    /**
     * Attributes
     */
    private CoreClient coreClient;
    private String name;
    private int refTeacherID;
    private String descDep;

    /**
     * Default constructor
     */
    public Department() {
    }

    public Department(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * Getter and Setter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRefTeacherID() {
        return refTeacherID;
    }

    public void setRefTeacherID(int refTeacherID) {
        this.refTeacherID = refTeacherID;
    }

    public String getDescDep() {
        return descDep;
    }

    public void setDescDep(String descDep) {
        this.descDep = descDep;
    }

    /**
     * Methods
     */

    /**
     * @param name 
     * @param refTeacherID
     * @param descDep
     */
    public void createDepartment(String name, int refTeacherID, String descDep) {
        this.name=name;
        this.refTeacherID=refTeacherID;
        this.descDep=descDep;
        try {
            coreClient.getConnection().sendToServer("#CREATEDEP " + name + " " + refTeacherID + " " + descDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name 
     * @param refTeacherID
     * @param descDep
     */
    public void updateDepartment(String name, int refTeacherID, String descDep) {
        setName(name);
        setRefTeacherID(refTeacherID);
        setDescDep(descDep);
        try {
            coreClient.getConnection().sendToServer("#UPDATEDEP " + name + " " + refTeacherID + " " + descDep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idDep
     */
    public void deleteDepartment(int idDep) {
        try {
            coreClient.getConnection().sendToServer("#DELETEDEP " + idDep);
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
            coreClient.getDisplay().setState("DU SUCCESS");
        else
            coreClient.getDisplay().setState("DU FAILURE");
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
            System.out.println("ok1");
            coreClient.getConnection().sendToServer("#GETDEPARTMENT" );
            System.out.println("ok2");
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
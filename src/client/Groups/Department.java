package client.Groups;


import client.CoreClient;
import client.Users.TeacherServices;

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
     * @param idep
     */
    public void deleteDepartment(int idep) {
        try {
            coreClient.getConnection().sendToServer("#DELETEDEP " + idep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
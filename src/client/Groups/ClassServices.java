package client.Groups;


import client.CoreClient;

import java.io.IOException;

/**
 * This is the business logic related to the class on the client side.
 * @author Audrey SAMSON
 */
public class ClassServices {
    private CoreClient coreClient;


    public ClassServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    public CoreClient getCoreClient() {
        return coreClient;
    }

    public void setCoreClient(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * This methods asks to the server to read all the classes
     */
    public void getClasses() {
        try {
            coreClient.getConnection().sendToServer("#GETCLASS" );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks to the server to read all the classes in one promotion
     * @param idPromo : promotion id
     */
    public void getClassesByPromo(int idPromo) {
        try {
            coreClient.getConnection().sendToServer("#GETCLASSBYPROMO-/-" + idPromo);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method asks the server to create a class with the following attributes.
     * @param nameClass is the class name.
     * @param refPromo is the referring promotion of this department, recognized by its ID.
     * @param descClass is the description of the class.
     */
    public void createClass(String nameClass,String descClass,int refPromo ) {
        try {
            coreClient.getConnection().sendToServer("#CREATECLASS-/-" + nameClass + "-/-" + descClass + "-/-" + refPromo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks the server to update an existing department with the following attributes.
     * @param nameClass is the class name.
     * @param refPromo is the referring promotion of this class, recognized by its ID.
     * @param descClass is the description of the class.
     */
    public void updateClass(int idC,String nameClass,String descClass,int refPromo) {
        try {
            coreClient.getConnection().sendToServer("#UPDATECLASS-/-" + idC + "-/-" + nameClass + "-/-" + descClass + "-/-" + refPromo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks to the server to delete an existing class
     * @param idC : class id
     */
    public void deleteClass(int idC) {
        try {
            coreClient.getConnection().sendToServer("#DELETECLASS-/-" + idC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the server response when it created a class
     * @param msg : String with the state of creation
     */
    public void handleCreatedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLC SUCCESS");
        else
            coreClient.getDisplay().setState("CLC FAILURE");
    }

    /**
     * This method handles the server response when it updated a class
     * @param msg : String with the state of updated
     */
    public void handleUpdatedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLU SUCCESS");
        else
            coreClient.getDisplay().setState("CLU FAILURE");
    }

    /**
     * This ethod handles the server response when ot deleted a class
     * @param msg : String with the state of deletion
     */
    public void handleDeletedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLP SUCCESS");
        else
            coreClient.getDisplay().setState("CLP FAILURE");
    }
}

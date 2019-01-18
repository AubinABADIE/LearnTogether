package client.Groups;


import client.CoreClient;

import java.io.IOException;

public class ClassServices {
    private CoreClient coreClient;


    public ClassServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }


    public void getClasses() {
        try {
            coreClient.getConnection().sendToServer("#GETCLASS" );
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
     * @param idC
     */
    public void deleteClass(int idC) {
        try {
            coreClient.getConnection().sendToServer("#DELETECLASS-/-" + idC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CoreClient getCoreClient() {
        return coreClient;
    }

    public void setCoreClient(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    public void handleCreatedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLC SUCCESS");
        else
            coreClient.getDisplay().setState("CLC FAILURE");
    }

    public void handleUpdatedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLU SUCCESS");
        else
            coreClient.getDisplay().setState("CLU FAILURE");
    }

    public void handleDeletedClass(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CLP SUCCESS");
        else
            coreClient.getDisplay().setState("CLP FAILURE");
    }
}

package client.Groups;

import client.CoreClient;
import java.io.IOException;

/**
 * This is the business logic related to the promotion on the client side.
 * @author Audrey SAMSON
 */
public class PromotionServices {

    private CoreClient coreClient;

    /**
     * Default constructor
     */
    public PromotionServices() {
    }

    public PromotionServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }


    /**
     * This method asks the server to create a promotion with the following attributes.
     * @param promotionName : promotion name
     * @param descriptionPromo : promotion description
     * @param graduationYear : promotion graduation year
     * @param idDepartment : department id
     */
    public void createPromotion(String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
        try {
            coreClient.getConnection().sendToServer("#CREATEPROMOTION-/-" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks the server to update a promotion with the following attributes.
     * @param id : promotion id
     * @param promotionName : promotion name
     * @param descriptionPromo : promotion description
     * @param graduationYear : promotion graduation year
     * @param idDepartment : department id
     */
    public void updatePromotion(int id, String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
        try {
            coreClient.getConnection().sendToServer("#UPDATEPROMOTION-/-" + id + "-/-" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**This method asks the server to delete a promotion with the following attributes.
     * @param idPromo : promotion id
     */
    public void deletePromotion(int idPromo) {
        try {
            coreClient.getConnection().sendToServer("#DELETEPROMOTION-/-" + idPromo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *This method send a message to the server to have the promotion list
     */
    public void getPromotion() {
        try {
            coreClient.getConnection().sendToServer("#GETPROMOTION" );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method send a message to the server to have the promotion list according to a department
     * @param idDep : department id
     */
    public void getPromotionByDep(int idDep) {
        try {
            coreClient.getConnection().sendToServer("#GETPROMOTIONBYDEP-/-" + idDep );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the server response when it created a promotion
     * @param msg : String with the state of creation
     */
    public void handleCreatedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("PC SUCCESS");
        else
            coreClient.getDisplay().setState("PC FAILURE");
    }

    /**
     * This method handles the server response when it updated a promotion
     * @param msg : String  with the state of updated
     */
    public void handleUpdatedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("PU SUCCESS");
        else
            coreClient.getDisplay().setState("PU FAILURE");
    }

    /**
     * This method handles the server response when it deleted a promotion
     * @param msg : String with the state of deletion
     */
    public void handleDeletedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DP SUCCESS");
        else
            coreClient.getDisplay().setState("DP FAILURE");
    }

}
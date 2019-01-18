package client.Groups;

import client.CoreClient;
import java.io.IOException;

/**
 * 
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
     * @param
     * @param
     * @param
     * @param
     * @param
     */
    public void createPromotion(String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
        try {
            coreClient.getConnection().sendToServer("#CREATEPROMOTION-/-" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     */
    public void updatePromotion(int id, String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
        try {
            coreClient.getConnection().sendToServer("#UPDATEPROMOTION-/-" + id + "-/-" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idPromo
     */
    public void deletePromotion(int idPromo) {
        try {
            coreClient.getConnection().sendToServer("#DELETEPROMOTION-/-" + idPromo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    public void getPromotion() {
        try {
            coreClient.getConnection().sendToServer("#GETPROMOTION" );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCreatedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("PC SUCCESS");
        else
            coreClient.getDisplay().setState("PC FAILURE");
    }

    public void handleUpdatedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("PU SUCCESS");
        else
            coreClient.getDisplay().setState("PU FAILURE");
    }

    public void handleDeletedPromo(String msg){
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DP SUCCESS");
        else
            coreClient.getDisplay().setState("DP FAILURE");
    }

}
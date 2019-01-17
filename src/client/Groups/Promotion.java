package client.Groups;

import client.CoreClient;
import java.io.IOException;

/**
 * 
 */
public class Promotion {

    private CoreClient coreClient;
    private String promotionName;
    private String descriptionPromo;
    private int graduationYear;
    private int idDepartment;

    /**
     * Default constructor
     */
    public Promotion() {
    }

    public Promotion(CoreClient coreClient) {
        this.coreClient = coreClient;
    }


    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getDescriptionPromo() {
        return descriptionPromo;
    }

    public void setDescriptionPromo(String descriptionPromo) {
        this.descriptionPromo = descriptionPromo;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }



    /**
     * @param
     * @param
     * @param
     * @param
     * @param
     */
    public void createPromotion(String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
       this.promotionName=promotionName;
       this.descriptionPromo=descriptionPromo;
       this.graduationYear=graduationYear;
       this.idDepartment=idDepartment;

        try {
            coreClient.getConnection().sendToServer("#CREATEPROMOTION-/-" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     */
    public void updatePromotion(int id, String promotionName, String descriptionPromo, int graduationYear, int idDepartment) {
        setPromotionName(promotionName);
        setDescriptionPromo(descriptionPromo);
        setGraduationYear(graduationYear);
        setIdDepartment(idDepartment);
        try {
            coreClient.getConnection().sendToServer("#UPDATEPROMOTION-/-" + id + "-/" + promotionName + "-/-" + descriptionPromo + "-/-" + graduationYear + "-/-" + idDepartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param promo
     */
    public void deletePromotion(Promotion promo) {
        // TODO implement here
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
package server.DAO;

import Types.PromotionType;

import java.util.List;

/** This class is the abstract class for the DAO promotion
 * @author Audrey SAMSON
 */
public abstract class AbstractDAOPromotion {

    /**
     * This method returns all the promotion in the data base.
     * @return a list of promotions.
     */
    public abstract List<PromotionType> searchAllPromotion();

    /**
     * This method returns all the promotion in the data base.
     * @return a list a promotions.
     */
    public abstract List<PromotionType> searchAllPromotionByDep(int idDep);

    /**
     * This method creates a promotion in the data base.
     * @param name : promotion name
     * @param descriptionDep : promotion description
     * @param graduationYear : promotion gradation year
     * @param refDep : promotion department reference
     * @return 1 if the creation was successful, 0 otherwise.
     */
    public abstract int createPromotion(String name,String descriptionDep, int graduationYear,int refDep);

    /**
     * This method updates the promotion in the data base.
     * @param idPromo : promotion id
     * @param name : promotion name
     * @param descriptionDep : promotion description
     * @param graduationYear : promotion graduation year
     * @param refDep : promotion department id
     * @return 1 if the update was successful, 0 otherwise.
     */
    public abstract int updatePromotion(int idPromo, String name, String descriptionDep, int graduationYear, int refDep);

    /**
     * This method deletes the promotion in the data base
     * @param idPromotion : promotion id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deletePromotion(int idPromo);

}
package server.DAO;

import Types.PromotionType;

import java.util.List;

/** This class is the abstract class for the DAO promotion
 * @author Audrey SAMSON
 */
public abstract class AbstractDAOPromotion {

    /**
     * Default constructor
     */
    public AbstractDAOPromotion() {
    }

    public abstract List<PromotionType> searchAllPromotion();
    public abstract List<PromotionType> searchAllPromotionByDep(int idDep);

    /**
     * @param name
     * @param graduationYear
     * @param descriptionDep
     * @param refDep
     */
    public abstract int createPromotion(String name,String descriptionDep, int graduationYear,int refDep);

    /**
     * @param name
     * @param graduationYear
     * @param descriptionDep
     * @param refDep
     */
    public abstract int updatePromotion(int idPromo, String name, String descriptionDep, int graduationYear, int refDep);

    /**
     * @param idPromo
     */
    public abstract int deletePromotion(int idPromo);

}
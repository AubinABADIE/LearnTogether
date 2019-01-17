package server.DAO;

import Types.PromotionType;

import java.util.List;

/**
 * 
 */
public abstract class AbstractDAOPromotion {

    /**
     * Default constructor
     */
    public AbstractDAOPromotion() {
    }

    public abstract List<PromotionType> searchAllPromotion();

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
    public abstract int updatePromotion(int idPromo, int refDep, String name, int graduationYear, String descriptionDep);

    /**
     * @param idPromo
     */
    public abstract int deletePromotiont(int idPromo);

    public abstract int readDepartment(int idDep);
}
package server.DAO;

import Types.ClassType;

import java.util.List;

public abstract class AbstractDAOClass {

    /**
     * Default constructor
     */
    public AbstractDAOClass() {
    }

    /**
     * @param className
     * @param descClass
     * @param idPromotion
     */
    public abstract int createClass(String className, String descClass, int idPromotion);

    /**
     * @param className
     * @param descClass
     * @param idPromotion
     */
    public abstract int updateClass(int idClass,String className, String descClass, int idPromotion);

    /**
     * @param id
     */
    public abstract int deleteClass(int id);

    public abstract int readClass(int id);

    public abstract List<ClassType> searchAllClasses();

}

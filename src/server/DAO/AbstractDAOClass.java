package server.DAO;

import Types.ClassType;

import java.util.List;

/**This class is the abstract class which create the DAO for the classes
 * @author Audrey SAMSON
 */
public abstract class AbstractDAOClass {

    /**
     * Default constructor
     */
    public AbstractDAOClass() {
    }

    /**
     * This method creates a class in the database.
     * @param className the class name.
     * @param classDescription the class description.
     * @param idPromotion the promotion the class belongs to.
     * @return 1 if the creation was successful, 0 otherwise.
     */
    public abstract int createClass(String className, String descClass, int idPromotion);

    /**
     * This method updates an existing class in the database.
     * @param idClass the class ID.
     * @param className the new class name.
     * @param descClass the new class description.
     * @param idPromotion the new promotion the class belongs to.
     * @return 1 if the update was successful, 0 otherwise.
     */
    public abstract int updateClass(int idClass,String className, String descClass, int idPromotion);

    /**
     * This method deletes a class in the database.
     * @param id the id of the class to delete.
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteClass(int id);

    /**
     * This method searches for all classes in the database.
     * @return a list of classes.
     */
    public abstract List<ClassType> searchAllClasses();

    /**
     * This method searches for all the classes belonging to a promotiion.
     * @param idPromo the promotion ID.
     * @return a list of classes.
     */
    public abstract List<ClassType> searchAllClassesByPromo(int idPromo);

}

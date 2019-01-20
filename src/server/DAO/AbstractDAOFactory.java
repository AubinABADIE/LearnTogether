package server.DAO;

import java.sql.Connection;

/** This class initialize the abstract factory
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author  Yvan SANSON
 * @author Solene SERAFIN
 */
public abstract class AbstractDAOFactory{

    private AbstractDAOUser userDAO;
    private AbstractDAODepartment departmentDAO;
    private AbstractDAORoom roomDAO;
    private AbstractDAOCourse courseDAO;
    private AbstractDAOConversation conversationDAO;
    private AbstractDAOPromotion promotionDAO;
    private AbstractDAOClass classDAO;
    private AbstractDAORecords recordsDAO;

    /**
     * Default constructor
     */
    public AbstractDAOFactory() {

    }

    public abstract AbstractDAORoom getRoomDAO();

    public abstract AbstractDAOUser getUserDAO();

    public abstract AbstractDAODepartment getDepartmentDAO();

    public abstract AbstractDAOConversation getConversationDAO();
    
    public abstract AbstractDAOCourse getCourseDAO();

    public abstract AbstractDAOPromotion getPromotionDAO();

    public abstract AbstractDAOClass getClassDAO();

    public abstract AbstractDAORecords getRecordsDAO();

    public abstract void createDAOUser();

    public abstract void createDAODepartment();

    public abstract void createDAORoom();

    public abstract void createDAOConversation();

	public abstract void createDAOCourse();

    public abstract void createDAOPromotion();

    public abstract void createDAOClass();

    public abstract void createDAORecords();
}
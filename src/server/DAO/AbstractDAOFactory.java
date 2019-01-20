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
    
    public abstract AbstractDAOEvent getEventDAO();

    /**
     * This method creates a User DAO.
     */
    public abstract void createDAOUser();

    /**
     * This method creates a Department DAO.
     */
    public abstract void createDAODepartment();

    /**
     * This method creates a Room DAO.
     */
    public abstract void createDAORoom();

    /**
     * This method creates a Conversation DAO.
     */
    public abstract void createDAOConversation();

    /**
     * This method creates a Course DAO.
     */
	public abstract void createDAOCourse();

    /**
     * This method creates a Promotion DAO.
     */
    public abstract void createDAOPromotion();

    /**
     * This method creates a Class DAO.
     */
    public abstract void createDAOClass();

    /**
     * This method creates a Records DAO.
     */
    public abstract void createDAORecords();

    /**
     * This method creates a Events DAO.
     */
    public abstract void createDAOEvent();

	
}
package server.DAO;

/**
 * Ths class creates an SQLServer factory
 * It contains all the DAO used in the project, and provides ways to get access to it.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERFAFIN
 */
public class SQLServerFactory extends AbstractDAOFactory {

    private SQLServerDAOUser userDAO;
    private SQLServerDAODepartment departmentDAO;
    private SQLServerDAORoom roomDAO;
    private SQLServerDAOConversation conversationDAO;
    private SQLServerDAOCourse courseDAO;
    private SQLServerDAOPromotion promotionDAO;
    private SQLServerDAOClass classDAO;
    private SQLServerDAORecord recordsDAO;
    private SQLServerDAOEvent eventDAO;


    /**
     * Default constructor
     */
    public SQLServerFactory() {
    }

    /**
     * This method creates a User DAO.
     */
    @Override
    public void createDAOUser(){
        userDAO = new SQLServerDAOUser();
    }

    /**
     * This method creates a Department DAO.
     */
    @Override
    public void createDAODepartment(){
            departmentDAO = new SQLServerDAODepartment();
    }

    /**
     * This method creates a Room DAO.
     */
    @Override
    public void createDAORoom() {
        this.roomDAO = new SQLServerDAORoom();
    }

    /**
     * This method creates a Conversation DAO.
     */
    @Override
    public void createDAOConversation() {
        this.conversationDAO = new SQLServerDAOConversation();
    }

    /**
     * This method creates a Course DAO.
     */
    @Override
    public void createDAOCourse() {
        this.courseDAO = new SQLServerDAOCourse();
    }

    /**
     * This method creates a Promotion DAO.
     */
    @Override
    public void createDAOPromotion() {
        this.promotionDAO = new SQLServerDAOPromotion();
    }

    /**
     * This method creates a Class DAO.
     */
    @Override
    public void createDAOClass() { this.classDAO = new SQLServerDAOClass(); }

    /**
     * This method creates a Records DAO.
     */
    @Override
    public void createDAORecords(){this.recordsDAO = new SQLServerDAORecord();}

    /**
     * This method creates a Event DAO.
     */
    @Override
    public void createDAOEvent(){this.eventDAO = new SQLServerDAOEvent();}
    
    /**
     * This method returns the User DAO.
     * @return the User DAO.
     */
    
    @Override
    public SQLServerDAOUser getUserDAO() {
        return userDAO;
    }

    /**
     * This method returns the Department DAO.
     * @return the Department DAO.
     */
    @Override
    public SQLServerDAODepartment getDepartmentDAO() {
        return departmentDAO;
    }

    /**
     * This method returns the Room DAO.
     * @return the Room DAO.
     */
    @Override
    public SQLServerDAORoom getRoomDAO() {
        return roomDAO;
    }

    /**
     * This method returns the Promotion DAO.
     * @return the Promotion DAO.
     */
    @Override
    public SQLServerDAOPromotion getPromotionDAO() {
        return promotionDAO;
    }

    /**
     * This method returns the Conversation DAO.
     * @return the Conversation DAO.
     */
    @Override
    public SQLServerDAOConversation getConversationDAO() {
        return conversationDAO;
    }

    /**
     * This method returns the Course DAO.
     * @return the Course DAO.
     */
    @Override
    public AbstractDAOCourse getCourseDAO() { return courseDAO; }

    /**
     * This method returns the Class DAO.
     * @return the Class DAO.
     */
    @Override
    public SQLServerDAOClass getClassDAO() {
        return classDAO;
    }

    /**
     * This method returns the Records DAO.
     * @return the Records DAO.
     */
    @Override
    public SQLServerDAORecord getRecordsDAO() {
        return recordsDAO;
    }
    
    /**
     * This method returns the Event DAO.
     * @return the Event DAO.
     */
    @Override
    public SQLServerDAOEvent getEventDAO() {
        return eventDAO;
    }
}
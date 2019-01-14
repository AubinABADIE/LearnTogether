package server.DAO;

import client.Chat.Conversation;

import java.sql.Connection;

/**
 * 
 */
public abstract class AbstractDAOFactory{

    private AbstractDAOUser userDAO;
    private AbstractDAODepartment departmentDAO;
    private AbstractDAORoom roomDAO;
    private AbstractDAOCourse courseDAO;
    private AbstractDAOConversation conversationDAO;

    /**
     * Default constructor
     */
    public AbstractDAOFactory() {

    }

    public abstract Connection getConnection();

    public AbstractDAORoom getRoomDAO() {
        return roomDAO;
    }

    public void setRoomDAO(AbstractDAORoom roomDAO) {
        this.roomDAO = roomDAO;
    }

    public AbstractDAOUser getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(AbstractDAOUser userDAO) {
        this.userDAO = userDAO;
    }

    public AbstractDAODepartment getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(AbstractDAODepartment departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public AbstractDAOConversation getConversationDAO() {
        return conversationDAO;
    }

    public void setConversationDAO(AbstractDAOConversation conversationDAO) {
        this.conversationDAO = conversationDAO;
    }
    
    public AbstractDAOCourse getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(AbstractDAOCourse courseDAO) {
		this.courseDAO = courseDAO;
	}

    public abstract void createDAOUser();

    public abstract void createDAODepartment();

    public abstract void createDAORoom();

    public abstract void createDAOConversation();

	public abstract void createDAOCourse();
}
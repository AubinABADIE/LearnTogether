package server.DAO;

import client.Chat.Conversation;

import java.sql.Connection;

/**
 * 
 */
public abstract class AbstractDAOFactory{

    private AbstractDAOUser userDAO;
    private AbstractDAODepartment departmentDAO;
    private RoomDAO roomDAO;

    /**
     * Default constructor
     */
    public AbstractDAOFactory() {

    }

    public abstract Connection getConnection();

    public RoomDAO getRoomDAO() {
        return roomDAO;
    }

    public void setRoomDAO(RoomDAO roomDAO) {
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

    public void createDAOUser(){
    }

    public void createDAODepartment(){
    }

}
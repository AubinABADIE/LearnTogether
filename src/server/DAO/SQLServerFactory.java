package server.DAO;


import Types.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public class SQLServerFactory extends AbstractDAOFactory {

    private SQLServerDAOUser userDAO;
    private SQLServerDAODepartment departmentDAO;
    private SQLServerDAORoom roomDAO;
    private SQLServerDAOConversation conversationDAO;
    /**
     * Default constructor
     */
    public SQLServerFactory() {
    }

    @Override
    public Connection getConnection() {
            Connection connection = null;
            String hostName = "learntogether.database.windows.net"; // update me
            String dbName = "LearnTogether"; // update me
            String user = "ysanson"; // update me
            String password = "LearnTogether1"; // update me
            String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                    + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
            try {
                connection= DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
    }

    @Override
    public void createDAOUser(){
        userDAO = new SQLServerDAOUser();
    }

    @Override
    public void createDAODepartment(){
            departmentDAO = new SQLServerDAODepartment();
    }

    @Override
    public void createDAORoom() {
        this.roomDAO = new SQLServerDAORoom();
    }

    @Override
    public void createDAOConversation() {
        this.conversationDAO = new SQLServerDAOConversation();
    }

    @Override
    public SQLServerDAOUser getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(SQLServerDAOUser userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public SQLServerDAODepartment getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(SQLServerDAODepartment departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public SQLServerDAORoom getRoomDAO() {
        return roomDAO;
    }

    public void setRoomDAO(SQLServerDAORoom roomDAO) {
        this.roomDAO = roomDAO;
    }

    @Override
    public SQLServerDAOConversation getConversationDAO() {
        return conversationDAO;
    }

    public void setConversationDAO(SQLServerDAOConversation conversationDAO) {
        this.conversationDAO = conversationDAO;
    }

    public int readDAOUserByLogin(String login, String password) {
        return userDAO.readDAOUserByLogin(login, password);
    }
    public UserType readDAOUser(int id) {
        return userDAO.readDAOUser(id);
    }
    public boolean isPdwNull(String login){return userDAO.isPdwNull(login);}
    public boolean setNewPwd(String login, String password) {
        return userDAO.setNewPwd(login, password);
    }

}
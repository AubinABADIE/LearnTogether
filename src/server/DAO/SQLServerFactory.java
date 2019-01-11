package server.DAO;


import server.DBTypes.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public class SQLServerFactory extends AbstractDAOFactory {

    private SQLServerDAOUser userDAO;
    private SQLServerDAODepartment departmentDAO;
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


    public void createDAOUser(){
        createSQLServerDAOUser();
    }

    public void createDAODepartment(){
        createSQLServerDAODepartment();
    }

    public SQLServerDAOUser getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(SQLServerDAOUser userDAO) {
        this.userDAO = userDAO;
    }

    public SQLServerDAODepartment getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(SQLServerDAODepartment departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    /**
     * 
     */
    public void createSQLServerDAOUser() {
        userDAO = new SQLServerDAOUser();
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

    public void createSQLServerDAODepartment() {
        departmentDAO = new SQLServerDAODepartment();
    }
}
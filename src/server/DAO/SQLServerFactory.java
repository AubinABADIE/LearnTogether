package server.DAO;


import server.DBTypes.UserType;

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
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

    /**
     * 
     */
    public void createSQLServerDAOUser() {
        userDAO = new SQLServerDAOUser();
    }
    @Override
    public int readDAOUserByLogin(String login, String password) {
        return userDAO.readDAOUserByLogin(login, password);
    }
    @Override
    public UserType readDAOUser(int id) {
        return userDAO.readDAOUser(id);
    }
    @Override
    public boolean isPdwNull(String login){return userDAO.isPdwNull(login);}
    @Override
    public boolean setNewPwd(String login, String password) {
        return userDAO.setNewPwd(login, password);
    }

    public void createSQLServerDAODepartment() {
        departmentDAO = new SQLServerDAODepartment();
    }
}
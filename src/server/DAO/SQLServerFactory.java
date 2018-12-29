package server.DAO;


import server.DBTypes.UserType;

/**
 * 
 */
public class SQLServerFactory extends AbstractDAOFactory {

    private SQLServerDAOUser userDAO;
    /**
     * Default constructor
     */
    public SQLServerFactory() {
    }


    public void createDAOUser(){
        createSQLServerDAOUser();
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
}
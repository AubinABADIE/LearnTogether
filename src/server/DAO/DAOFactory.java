package server.DAO;

import server.DBTypes.UserType;

/**
 * 
 */
public class DAOFactory extends AbstractDAOFactory {

    /**
     * Default constructor
     */
    public DAOFactory() {
    }

    @Override
    public int readDAOUserByLogin(String login, String password) {
        return 0;
    }

    @Override
    public UserType readDAOUser(int id) {
        return null;
    }
}
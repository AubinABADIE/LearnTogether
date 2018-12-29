package server.DAO;

import server.DBTypes.UserType;

/**
 * 
 */
public class XMLFactory extends AbstractDAOFactory {

    /**
     * Default constructor
     */
    public XMLFactory() {
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
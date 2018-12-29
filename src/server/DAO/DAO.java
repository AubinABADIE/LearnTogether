package server.DAO;

import server.DBTypes.UserType;

/**
 * 
 */
public class DAO extends AbstractDAOFactory {

    /**
     * Default constructor
     */
    public DAO() {
    }

    /**
     *
     */
    public void connect() {

    }

    /**
     * 
     */
    public void find() {
        // TODO implement here
    }

    /**
     * 
     */
    public void create() {
        // TODO implement here
    }

    /**
     * 
     */
    public void update() {
        // TODO implement here
    }

    /**
     * 
     */
    public void delete() {
        // TODO implement here
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
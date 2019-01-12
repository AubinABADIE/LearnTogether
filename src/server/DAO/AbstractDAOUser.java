package server.DAO;


import Types.UserType;

import java.sql.Connection;

/**
 * 
 */
public abstract class AbstractDAOUser {

    /**
     * This method gets to the database and opens a connection.
     * @return the connection instance.
     */
    public abstract Connection getConnection();



    public abstract int readDAOUserByLogin(String login, String password);
    public abstract UserType readDAOUser(int id);
    public abstract boolean isPdwNull(String login);
    public abstract boolean setNewPwd(String login, String password);

}
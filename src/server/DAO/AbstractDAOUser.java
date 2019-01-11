package server.DAO;


import server.DBTypes.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public abstract class AbstractDAOUser {

    /**
     * This method gets to the database and opens a connection.
     * @return the connection instance.
     */
    public abstract Connection getConnection();

    /**
     * This method closes a connection to a database.
     * @param connexion
     */
    public void closeConnection(Connection connexion){
        try {
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract int readDAOUserByLogin(String login, String password);
    public abstract UserType readDAOUser(int id);
    public abstract boolean isPdwNull(String login);
    public abstract boolean setNewPwd(String login, String password);

}
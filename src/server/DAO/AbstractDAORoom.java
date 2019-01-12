package server.DAO;

import java.sql.Connection;

/**
 * 
 */
public abstract class AbstractDAORoom {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description);
}
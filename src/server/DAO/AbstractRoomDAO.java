package server.DAO;

import java.sql.Connection;

/**
 * 
 */
public abstract class AbstractRoomDAO {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract void createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description);
}
package server.DAO;

import server.DBTypes.RoomType;

import java.sql.Connection;
import java.util.List;

/**
 * 
 */
public abstract class AbstractDAORoom {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description);

    public abstract List<RoomType> searchAllRooms();
}
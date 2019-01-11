package server.DAO;

/**
 * 
 */
public abstract class AbstractRoomDAO {


    public abstract void createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description);
}
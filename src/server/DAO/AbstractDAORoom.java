package server.DAO;

import Types.RoomType;

import java.sql.Connection;
import java.util.List;

/**  This class is the abstract class for the DAO room
 * @author Marie SALELLES
 */
public abstract class AbstractDAORoom {

    /**
     * This method creates the connection with the data base
     * @return : a connection
     */
    public abstract Connection getConnection();

    /**
     * This method closes the connection with the data base
     * @param connection the active connection.
     */
    public abstract void closeConnection(Connection connection);

    /**
     * This method creates a room in the data base
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : small description of the room
     * @return 1 if the creation was successful, 0 otherwise.
     */
    public abstract int createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description);

    /**
     * This method returns the rooms list
     * @return the list of rooms.
     */
    public abstract List<RoomType> searchAllRooms();

    /**
     * This method deletes a room. It return an int to specify to the server the state of the deletion
     * @param id : room id
     * @return int who give the state of the deletion in the data base
     */
    public abstract int deleteRoom(int id);

    /**
     * This method updates a room in the data base. It return an int to specify to the server the state of the updated.
     * @param id : room id
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : room description
     * @return int who give the state of the updated in the data base
     */
    public abstract int updateRoom(int id,String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description );
}
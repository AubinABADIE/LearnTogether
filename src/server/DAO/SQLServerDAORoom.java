package server.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLServerDAORoom extends AbstractRoomDAO{

    public SQLServerDAORoom(){

    }

    /**
     * this method create a room in the data base
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : small description of the room
     */
    public void createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description ){
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rooms (roomName, building, hasComputer, hasProjector, descriptionRoom, capacity) VALUES ? ,? ,? ,? ,?, ? ");
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, building);
                preparedStatement.setBoolean(3, hasComputer);
                preparedStatement.setBoolean(4, hasProjector);
                preparedStatement.setString(5, description);
                preparedStatement.setInt(6, capacity);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }

    }
}

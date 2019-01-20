package server.DAO;

import Types.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class instantiates the methods relative to the room in SQLServer data base
 * @author Marie SALELLES
 */

public class SQLServerDAORoom extends AbstractDAORoom{

    public SQLServerDAORoom(){

    }

    /**
     * This method creates the connection with the data base
     * @return : a connection
     */
    public Connection getConnection() {
        {
            Connection connection = null;
            String hostName = "learntogether.database.windows.net"; // update me
            String dbName = "LearnTogether"; // update me
            String user = "ysanson"; // update me
            String password = "LearnTogether1"; // update me
            String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                    + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
            try {
                connection = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    /**
     * This method closes the connection with the data base
     */
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a room in the data base
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : small description of the room
     */
    @Override
    public int createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description ){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rooms(roomName, building, hasComputer, hasProjector, descriptionRoom, capacity) VALUES (? ,? ,? ,? ,?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, building);
                preparedStatement.setBoolean(3, hasComputer);
                preparedStatement.setBoolean(4, hasProjector);
                preparedStatement.setString(5, description);
                preparedStatement.setInt(6, capacity);
                result = preparedStatement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return result;
    }

    /**
     * This method returns the rooms list
     */
    public List<RoomType> searchAllRooms(){
        ArrayList rooms = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Rooms");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    rooms.add(new RoomType(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt("capacity"),
                            resultSet.getInt("building"),
                            resultSet.getBoolean("hasProjector"),
                            resultSet.getBoolean("hasComputer"),
                            resultSet.getString("descriptionRoom")));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return rooms;
    }

    /**
     * This method deletes a room. It return an int to specify to the server the state of the deletion
     * @param id : room id
     * @return int who give the state of the deletion in the data base
     */
    @Override
    public int deleteRoom (int id){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Rooms WHERE idRoom = ?");
                preparedStatement.setInt(1, id);
                result = preparedStatement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return result;
    }

    /**
     * This method updates a room in the data base. It return an int to specify to the server the state of the updated.
     * @param id : room id
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param projector : if the room has a projector
     * @param computer : if the room has computers
     * @param desc : room description
     * @return int who give the state of the updated in the data base
     */
    @Override
    public int updateRoom(int id, String name, int capacity, int building, boolean projector, boolean computer, String desc){
        Connection connection =getConnection();
        int result = 0;
        if(connection!= null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Rooms SET roomName = ?, building = ?, hasComputer = ?, hasProjector = ?, descriptionRoom = ?, capacity = ? WHERE idRoom = ?");
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, building);
                preparedStatement.setBoolean(3, computer);
                preparedStatement.setBoolean(4, projector);
                preparedStatement.setString(5, desc);
                preparedStatement.setInt(6, capacity);
                preparedStatement.setInt(7, id);
                result = preparedStatement.executeUpdate();
            } catch(SQLException e){
                e.printStackTrace();
            } finally {
                closeConnection(connection);
            }
         }
        return result;
    }
}



package server.DAO;

import java.sql.*;

public class SQLServerDAORoom extends AbstractRoomDAO{

    public SQLServerDAORoom(){

    }

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

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    @Override
    public int createRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description ){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Rooms (roomName, building, hasComputer, hasProjector, descriptionRoom, capacity) VALUES ? ,? ,? ,? ,?, ? ");
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
}

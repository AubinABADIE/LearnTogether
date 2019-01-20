package server.DAO;

/**
 * This class instantiate the method relative to the course in SQLServer data base
 * @author Solene SERAFIN
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lloseng.ocsf.server.ConnectionToClient;

import Types.EventType;

/**
 * This class instantiate the method relative to the event in SQLServer data base
 * @author Solene SERAFIN
 */

public class SQLServerDAOEvent extends AbstractDAOEvent{
    /**
     * Default constructor
     */
    public SQLServerDAOEvent (){}

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
     * This method closes the connection to the database.
     * @param connection the active connection.
     */
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   /**
     * @param idEvent : the id of the Event 
     * @param dateTimeEvent : the time and the date when the event begin
     * @param duration : the duration of the event
     * @param idRoom : the room when the event will take place
     * @param idCourse : the course related to the event 
     * @param idTeacher : the teacher related to the event
     * @param idClass : the class related to the event
     * @param idPromo : the promo related to the event
     * @param iddepartement : the departement related to the event
     */
   
    @Override
    public int createEvent(Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement, ConnectionToClient client){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Events(idEvent, dateTimeEvent, duration, idRoom, idCourse, idTeacher, idClass, idPromo, idDepartement) VALUES (? ,? ,? ,?, ?, ?, ?, ?)");
                preparedStatement.setDate(1, dateTimeEvent);
                preparedStatement.setFloat(2, duration);
                preparedStatement.setInt(3, idRoom);
                preparedStatement.setInt(4, idCourse);
                preparedStatement.setInt(5, idTeacher);
                preparedStatement.setInt(6, idClass);
                preparedStatement.setInt(7, idPromo);
                preparedStatement.setInt(8, idDepartement);
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
     * This method returns the events list
     * @return the events list.
     */
    public List<EventType> searchAllEvents(){
        ArrayList<EventType> events = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Events");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    events.add(new EventType(
                            resultSet.getInt(1),
                            resultSet.getDate(2),
                            resultSet.getFloat(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getInt(8),
                            resultSet.getInt(9)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return events;
    }

    /**
     * This method searches for all the events relative to a user in the database.
     * @param userID the user ID.
     * @return a list of events done by this user.
     */
    public List<EventType> searchAllEvents(int userID){
        ArrayList<EventType> events = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Events WHERE idTeacher = ?");
                preparedStatement.setInt(1,userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    events.add(new EventType(
                            resultSet.getInt(1),
                            resultSet.getDate(2),
                            resultSet.getFloat(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6),
                            resultSet.getInt(7),
                            resultSet.getInt(8),
                            resultSet.getInt(9)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return events;
    }
    
    /**
     * This method deletes a event. It return an int to specify to the server the state of the deletion
     * @param id : event id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    @Override
    public int deleteEvent(int id){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Events WHERE idEvent = ?");
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
     * @param idEvent : the id of the Event 
     * @param dateTimeEvent : the time and the date when the event begin
     * @param duration : the duration of the event
     * @param idRoom : the room when the event will take place
     * @param idCourse : the course related to the event 
     * @param idTeacher : the teacher related to the event
     * @param idClass : the class related to the event
     * @param idPromo : the promo related to the event
     * @param iddepartement : the departement related to the event
     */
    public int updateEvent(int idEvent, Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement){
        Connection connection = getConnection();
        int result = 0;
        if(connection!= null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Events SET idEvent = ?, dateTimeEvent = ?, duration = ?, idRoom = ?, idCourse = ?, idTeacher = ?, idClass = ?, idPromo = ?, idDepartement = ? WHERE idEvent = ?");
                preparedStatement.setInt(9, idEvent);
                preparedStatement.setDate(1, dateTimeEvent);
                preparedStatement.setFloat(2, duration);
                preparedStatement.setInt(3, idRoom);
                preparedStatement.setInt(4, idCourse);
                preparedStatement.setInt(5, idTeacher);
                preparedStatement.setInt(6, idClass);
                preparedStatement.setInt(7, idPromo);
                preparedStatement.setInt(8, idDepartement);

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
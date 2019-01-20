package server.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.lloseng.ocsf.server.ConnectionToClient;

import Types.EventType;
import Types.CourseType;
import Types.RoomType;

/** This class is the abstract class for the DAO course
 * @author Solène SERAFIN
 */
public abstract class AbstractDAOEvent{
    /**
     * This method creates the connection with the data base
     * @return : a connection
     */
    public abstract Connection getConnection();

    /**
     * This method closes the connection to the database.
     * @param connection the active connection.
     */
    public abstract void closeConnection(Connection connection);

    /**
     * this method creates a course in the data base
     * @param idEvent : the id of the Event 
     * @param dateTimeEvent : the time and the date when the event begin
     * @param duration : the duration of the event
     * @param idRoom : the room when the event will take place
     * @param idCourse : the course related to the event 
     * @param idTeacher : the teacher related to the event
     * @param idClass : the class related to the event
     * @param idPromo : the promo related to the event
     * @param iddepartement : the departement related to the event
     * @return 1 if the creation was successful, 0 otherwise.
     */
    
    public abstract int createEvent(Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement, ConnectionToClient client);
    
    /**
     * This method returns the events list
     * @return the events list.
     */
    public abstract List<EventType> searchAllEvents();

    /**
     * This method searches for all the events relative to a user in the database.
     * @param userID the user ID.
     * @return a list of events done by this user.
     */
    public abstract List<EventType> searchAllEvents(int userID);

    /**
     * This method deletes a event. It return an int to specify to the server the state of the deletion
     * @param id : event id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteEvent(int id);

    /**
     * This method updates a event in the database, with the event ID.
     * @param idEvent : the id of the Event 
     * @param dateTimeEvent : the time and the date when the event begin
     * @param duration : the duration of the event
     * @param idRoom : the room when the event will take place
     * @param idCourse : the course related to the event 
     * @param idTeacher : the teacher related to the event
     * @param idClass : the class related to the event
     * @param idPromo : the promo related to the event
     * @param iddepartement : the departement related to the event
     * @return 1 if the update was successful, 0 otherwise.
     */
    public abstract int updateEvent(int idEvent, Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement);

	

}


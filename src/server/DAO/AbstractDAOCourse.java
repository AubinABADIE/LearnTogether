package server.DAO;

import java.sql.Connection;
import java.util.List;

import Types.CourseType;
import Types.RoomType;

/** This class is the abstract class for the DAO course
 * @author Audrey SAMSON
 */
public abstract class AbstractDAOCourse{
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
     * @param name : course name
	 * @param description : small description of the course
	 * @param nbHourTotal : the total hours of the course
	 * @param idT : the referring teacher of the course
     * @return 1 if the creation was successful, 0 otherwise.
     */
    public abstract int createCourse(String name, String description, int totalHours, int idT, int promo);

    /**
     * This method returns the courses list
     * @return the courses list.
     */
    public abstract List<CourseType> searchAllCourses();

    /**
     * This method searches for all the courses relative to a user in the database.
     * @param userID the user ID.
     * @return a list of courses done by this user.
     */
    public abstract List<CourseType> searchAllCourses(int userID);

    /**
     * This method deletes a course. It return an int to specify to the server the state of the deletion
     * @param id : course id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteCourse(int id);

    /**
     * This method updates a course in the database, with the course ID.
     * @param idCourse the course ID.
     * @param courseName the new course name.
     * @param courseDescription the new course description.
     * @param nbHourTotal the new number of hours.
     * @param idTeacher the new teacher ID.
     * @param promoId the new promo ID.
     * @return 1 if the update was successful, 0 otherwise.
     */
    public abstract int updateCourse(int idCourse, String courseName, String coursDescription, int nbTotalHours, String idT, int promoId);

}


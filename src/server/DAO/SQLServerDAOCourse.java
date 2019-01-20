package server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Types.CourseType;

/**
 * This class instantiate the method relative to the course in SQLServer data base
 * @author Solene SERAFIN
 */

public class SQLServerDAOCourse extends AbstractDAOCourse{
    /**
     * Default constructor
     */
	public SQLServerDAOCourse (){}

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
     * this method creates a course in the data base
     * @param name : course name
	 * @param description : small description of the course
	 * @param nbHourTotal : the total hours of the course
	 * @param idT : the referring teacher of the course
     * @return 1 if the creation was successful, 0 otherwise.
     */
    @Override
    public int createCourse(String name, String description, int nbHourTotal, int idT, int promoId){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Courses(courseName, courseDescription, nbHourTotal, idTeacher, idPromo) VALUES (? ,? ,? ,?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, nbHourTotal);
                preparedStatement.setInt(4, idT);
                preparedStatement.setInt(5, promoId);
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
     * This method returns the courses list
     * @return the courses list.
     */
    public List<CourseType> searchAllCourses(){
        ArrayList<CourseType> courses = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Courses");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    courses.add(new CourseType(
                    		resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return courses;
    }

    /**
     * This method searches for all the courses relative to a user in the database.
     * @param userID the user ID.
     * @return a list of courses done by this user.
     */
    public List<CourseType> searchAllCourses(int userID){
        ArrayList<CourseType> courses = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Courses WHERE idTeacher = ?");
                preparedStatement.setInt(1,userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    courses.add(new CourseType(
                    		resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getInt(5),
                            resultSet.getInt(6)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return courses;
    }
    
    /**
     * This method deletes a course. It return an int to specify to the server the state of the deletion
     * @param id : course id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    @Override
    public int deleteCourse (int id){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Courses WHERE idCourse = ?");
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
     * This method updates a course in the database, with the course ID.
     * @param idCourse the course ID.
     * @param courseName the new course name.
     * @param courseDescription the new course description.
     * @param nbHourTotal the new number of hours.
     * @param idTeacher the new teacher ID.
     * @param promoId the new promo ID.
     * @return 1 if the update was successful, 0 otherwise.
     */
    public int updateCourse(int idCourse, String courseName, String courseDescription, int nbHourTotal, String idTeacher, int promoId){
        Connection connection = getConnection();
        int result = 0;
        if(connection!= null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Courses SET courseName = ?, courseDescription = ?, nbHourTotal = ?, idTeacher = ?, idPromo = ?  WHERE idCourse = ?");
                preparedStatement.setInt(6, idCourse);
                preparedStatement.setString(1, courseName);
                preparedStatement.setString(2, courseDescription);
                preparedStatement.setInt(3, nbHourTotal);
                preparedStatement.setString(4, idTeacher);
                preparedStatement.setInt(5, promoId);
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

package server.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Types.CourseType;

public class SQLServerDAOCourse extends AbstractDAOCourse{
	public SQLServerDAOCourse (){}
	
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
     * this method create a course in the data base
     * @param name : course name
	 * @param description : small description of the course
	 * @param totalHours : the total hours of the course
	 * @param promotion : the promotion related to the course
	 * @param referingTeacher : the referring teacher of the course
     */
    @Override
    public int createCourse(String name, String description, int nbHourTotal, int idT){
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Courses(courseName, courseDescription, nbHourTotal, idTeacher) VALUES (? ,? ,? ,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, description);
                preparedStatement.setInt(3, nbHourTotal);
                preparedStatement.setInt(4, idT);
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
     * This method return the courses list
     */
    public List<CourseType> searchAllCourses(){
        ArrayList courses = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Courses");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    courses.add(new CourseType(
                    		resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString("courseDescription"),
                            resultSet.getInt("nbHourTotal"),
                            resultSet.getInt("idTeacher")));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return courses;
    }
    
    public int readCourse(int idCourse) {
        Connection connection = getConnection();
        int id = -1;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Courses WHERE idDep = ? ");
                preparedStatement.setInt(1,idCourse);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                id = resultSet.getInt("idCourse");
            }catch (Exception e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return id;
    }
    
    /**
     * This methos delete a course. It return an int to specify to the server the state of the deletion
     * @param id : course id
     * @return int who give the state of the deletion in the data base
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
    
    public int updateCourse(int idCourse, String name, String courseDescription, int nbHourTotal, int idTeacher){
        Connection connection =getConnection();
        int result = 0;
        if(connection!= null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Courses SET courseName = ?, courseDescription = ?, nbHourTotal = ?, idTeacher = ?  WHERE idCourse = ?");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, courseDescription);
                preparedStatement.setInt(3, nbHourTotal);
                preparedStatement.setInt(4, idTeacher);
                preparedStatement.setInt(7, idCourse);
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

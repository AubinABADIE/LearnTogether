package server.DAO;

import java.sql.Connection;
import java.util.List;

import Types.CourseType;
import Types.RoomType;

/**
 * 
 */
public abstract class AbstractDAOCourse{
    
    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int createCourse(String name, String description, int totalHours, int idT);

    public abstract List<CourseType> searchAllCourses();

    public abstract int deleteCourse(int id);
    public abstract int updateCourse(int idCourse, String courseName, String coursDescription, int nbTotalHours, int idT);

}


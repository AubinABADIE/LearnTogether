package server.DAO;

import client.Courses.CourseServices;
import Types.UserType;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class SQLServerDAORecord extends AbstractDAORecords{

    /**
     * Default constructor
     */
    public SQLServerDAORecord() {
    }

    @Override
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

    @Override
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param name 
     * @param year 
     * @param courseID
     * @param record 
     * @param donatingUser
     */
    public int createRecord(String name, int year, int courseID, byte[] record, int donatingUser) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into Records(recordYear, idCourse, recordName, recordFile, idUser) values (?,?,?,?,?)");
                preparedStatement.setInt(1, year);
                preparedStatement.setInt(2, courseID);
                preparedStatement.setString(3, name);
                preparedStatement.setBytes(4, record);
                preparedStatement.setInt(5, donatingUser);
                result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param id 
     * @param donatingUser
     */
   public void deleteRecord(int id, UserType donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     */

    public void readRecord(int id) {
        // TODO implement here
    }

}
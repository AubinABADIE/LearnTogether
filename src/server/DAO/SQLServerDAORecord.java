package server.DAO;

import Types.RecordType;
import client.Courses.CourseServices;
import Types.UserType;

import java.io.File;
import java.sql.*;
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

    /** This method create in the data base a record
     * @param name : record name
     * @param year : exam year
     * @param courseID : record course
     * @param donatingUser : user id who give the record
     */
    public int createRecord(String name, int year, int courseID, int donatingUser) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into Records(recordYear, idCourse, recordName, idUser) values (?,?,?,?)");
                preparedStatement.setInt(1, year);
                preparedStatement.setInt(2, courseID);
                preparedStatement.setString(3, name);
                preparedStatement.setInt(4, donatingUser);
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

    /**
     * This method search in the data base the record list
     * @return : the record list
     */
    @Override
    public List<RecordType> searchAllRecords(){
        ArrayList<RecordType> records = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Records");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    records.add(new RecordType(resultSet.getString("recordName"),
                            resultSet.getInt("idCourse"),
                            resultSet.getInt("recordYear"),
                            resultSet.getInt("idUser")));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return records;
    }
}
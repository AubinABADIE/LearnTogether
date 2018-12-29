package server.DAO;

import Courses.Course;
import server.DBTypes.UserType;
import Groups.Promotion;

import java.io.File;
import java.sql.*;
import java.util.Date;

/**
 * 
 */
public class SQLServerDAOUser extends AbstractDAOUser {

    /**
     * Default constructor
     */
    public SQLServerDAOUser() {

    }


    /**
     * @param name 
     * @param firstname 
     * @param login 
     * @param birthDate 
     * @param courses 
     * @param promotions 
     * @param typeJob 
     * @param studentGroup
     */
    public void createDAOUser(String name, String firstname, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param firstname 
     * @param login 
     * @param birthDate 
     * @param courses 
     * @param promotions 
     * @param typeJob 
     * @param studentGroup 
     * @param password 
     * @param picture
     */
    public void updateDAOUser(String name, String firstname, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup, String password, File picture) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void deleteDAOUser(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public UserType readDAOUser(int id) {
        UserType user=null;
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT role from GeneralUsers WHERE idUser = ?");
                preparedStatement.setString(1, Integer.toString(id));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    user=new UserType(id, resultSet.getString("role"));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    public int readDAOUserByLogin(String login, String password){
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralUsers WHERE email = ? and login = ? ");
                preparedStatement.setString(1,login);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    return resultSet.getInt("idUser");
                }
            }catch (Exception e){e.printStackTrace();}
            finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

}
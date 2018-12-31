package server.DAO;

import Courses.Course;
import com.lloseng.ocsf.server.ConnectionToClient;
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
     * Reads the DB and retrieves the user by its ID
     * @param id the user ID in the DB
     */
    @Override
    public UserType readDAOUser(int id) {
        UserType user=null;
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT role from GeneralUsers WHERE idUser = ?");
                preparedStatement.setString(1, Integer.toString(id));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    resultSet.next();
                    user=new UserType(id, resultSet.getString("role"));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return user;
    }

    /**
     * Reads the DB and retrieves the user by its login and password.
     * @param login: the email of the user
     * @param password: the password of the user
     * @return the ID if successful, -1 otherwise
     */
    @Override
    public int readDAOUserByLogin(String login, String password){
        Connection connection = getConnection();
        int id = -1;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralUsers WHERE email = ? and password = ? ");
                preparedStatement.setString(1,login);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                id = resultSet.getInt("idUser");
            }catch (Exception e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return id;
    }

    /**
     * Checks the DB for a user, to see if its password is null or not.
     * @param login: the email of the user.
     * @return boolean, true if the user's password is null, false otherwise.
     */
    @Override
    public boolean isPdwNull(String login){
        Connection connection = getConnection();
        boolean result = false;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GeneralUsers WHERE email = ? AND password = NULL");
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                result = resultSet.getInt("idUser") != 0;
            }catch (SQLException e){
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return result;
    }

    @Override
    public boolean setNewPwd(String login, String password) {
        Connection connection = getConnection();
        Boolean result = false;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GeneralUsers SET password = ? WHERE idUser = ?");
                preparedStatement.setString(1,password);
                preparedStatement.setString(2,login);
                ResultSet resultSet = preparedStatement.executeQuery();
                result = true;
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return false;
    }


}
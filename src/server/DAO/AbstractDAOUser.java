package server.DAO;


import Types.AdminType;
import Types.TeacherType;
import Types.UserType;

import java.sql.Connection;
import java.util.List;

/**  This class is the abstract class for the DAO user
 * @author Aubin ABADIE
 * @author Yvan SANSON
 */
public abstract class AbstractDAOUser {

    /**
     * This method gets to the database and opens a connection.
     * @return the connection instance.
     */
    public abstract Connection getConnection();

    /**
     * This method closes a connection to a database.
     * @param connection the active connection.
     */
    public abstract void closeConnection(Connection connection);

    /**
     * This method create a user in the data base
     * @param name : user name
     * @param firstName : user first name
     * @param birthDate : user birth date
     * @param email : user login
     * @param password : user password
     * @param role : user role
     * @param jobType : the user job type.
     * @return 0 or 1 depending if the creation failed or succeeded.
     */
    public abstract int createDAOUser(String name, String firstName, String birthDate, String email, String password, String role, String jobType);

    /**
     * Reads the DB and retrieves the user by its ID
     * @param id the user ID in the DB
     * @return a user description.
     */
    public abstract UserType readDAOUser(int id);

    /**
     * This method updates a user in the DAO with its ID.
     * @param id the user ID
     * @param name the user name
     * @param firstName the user first name
     * @param birthDate the user birth date
     * @param email the user email
     * @param role the user role
     * @return 0 or 1 depending if the update failed or succeeded.
     */
    public abstract int updateDAOUser(int id, String name, String firstName, String email, String birthDate, String role);

     /**
      * Reads the DB and delete the user.
      * @param id the user ID.
      * @param role the user role.
      * @return 0 or 1 depending if the deletion failed or succeeded.
      */
    public abstract int deleteDAOUser(int id, String role);

    /**
     * Reads the DB and retrieves the user by its login and password.
     * Returns -1 if the email or password is null
     * @param login: the email of the user
     * @param password: the password of the user
     * @return the ID if successful, -1 otherwise
     */
    public abstract int readDAOUserByLogin(String login, String password);

    /**
     * Checks the DB for a user, to see if its password is null or not.
     * @param login: the email of the user.
     * @return boolean, true if the user's password is null, false otherwise.
     */
    public abstract boolean isPdwNull(String login);

    /**
     * Checks the DB and asks to set a new password to the client.
     * @param login: the email of the client. Since it is personal, there can't be two same emails.
     * @param password: the new client's password.
     * @return true if the change has been successful, false otherwise.
     */
    public abstract boolean setNewPwd(String login, String password);

   /**
     * This method returns the teachers list
     * @return the teacher list.
     */
    public abstract List<TeacherType> searchAllTeacher();

    /**
     * This method return the users list
     * @return the users list.
     */
    public abstract List<UserType> getAllUsers();
    
    /**
     * This method returns the users that can be admins.
     * It selects the teachers and staffs from the DB.
     * @return a list of users.
     */
    public abstract List<UserType> getPossibleAdmin();
    
    /**
     * This method retrieves the admins from the database.
     * @return a list of admins.
     */
    public abstract List<AdminType> getAllAdmin();

}
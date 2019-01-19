package server.DAO;


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


    public abstract int createDAOUser(String name, String firstname, String birthDate, String email, String password, String role);
    public abstract int readDAOUserByLogin(String login, String password);
    public abstract UserType readDAOUser(int id);
    public abstract boolean isPdwNull(String login);
    public abstract int updateDAOUser(int id, String name, String firstname, String birthDate, String email, String password, String role);
    public abstract boolean setNewPwd(String login, String password);
    public abstract int deleteDAOUser(int id);
    public abstract List<TeacherType> searchAllTeacher();
	public abstract List<UserType> getAllUsers();

}
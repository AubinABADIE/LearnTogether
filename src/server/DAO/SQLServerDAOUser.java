package server.DAO;

import Types.UserType;
import Types.TeacherType;
import Types.AdminType;
import Types.StaffType;


import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class instantiate the method relative to the user in SQLServer data base
 * @author Yvan SANSON
 * @author Aubin ABADIE
 */
public class SQLServerDAOUser extends AbstractDAOUser {

    /**
     * Default constructor
     */
    public SQLServerDAOUser() {

    }


    /**
     * This function create the connection with the data base
     * @return : a connection
     */
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

    /**
     * This method closes a connection to a database.
     * @param connexion
     */
    public void closeConnection(Connection connexion){
        try {
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method create a user in the data base
     * @param name : user name
     * @param firstName : user first name
     * @param birthDate : user birth date
     * @param email : user login
     * @param password : user password
     * @param role : user role
     * @return
     */
    @Override
	public int createDAOUser(String name, String firstName, String birthDate, String email, String role, String password, String jobType) {
    	Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
            	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO GeneralUsers (name, firstname, birthDate, email, password, role) VALUES (? ,? ,? ,? ,?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, firstName);
                preparedStatement.setString(3, birthDate);
                preparedStatement.setString(4, email);
                preparedStatement.setString(5, password);
                preparedStatement.setString(6, role.toUpperCase());
                preparedStatement.executeUpdate();
                
                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT idUser FROM GeneralUsers WHERE email = ?");
                preparedStatement2.setString(1, email);
                ResultSet resultSet = preparedStatement2.executeQuery();
                int id = 0;
                if(resultSet != null){
                    resultSet.next();
                    id = resultSet.getInt("idUser");
                }

            	if(role.equals("STUDENT")) {
            		PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO Students (idStudent) VALUES (?) ");
            		preparedStatement3.setInt(1, id);
                    result = preparedStatement3.executeUpdate();
            	} else if(role.equals("TEACHER")) {
            		PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO Teachers (idTeacher, isAdmin) VALUES (?, ?)");
            		preparedStatement3.setInt(1, id);
            		preparedStatement3.setBoolean(2, false);
            		result = preparedStatement3.executeUpdate();
            	} else if(role.equals("STAFF")) {
            		PreparedStatement preparedStatement3 = connection.prepareStatement("INSERT INTO Staffs (idStaff, jobType, isAdmin, isSuperAdmin) VALUES (?, ?, ?, ?)");
            		preparedStatement3.setInt(1, id);
            		preparedStatement3.setString(2, jobType);
            		preparedStatement3.setBoolean(3, false);
            		preparedStatement3.setBoolean(4, false);
                    result = preparedStatement3.executeUpdate();
            	}
                
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
     * Reads the DB and retrieves the user by its ID
     * @param id the user ID in the DB
     */
    @Override
    public UserType readDAOUser(int id) {
        UserType user=null;
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralUsers WHERE idUser = ?");
                preparedStatement.setString(1, Integer.toString(id));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    resultSet.next();
                    user=new UserType(id, resultSet.getString("name"), resultSet.getString("firstname"), resultSet.getString("email"), resultSet.getString("birthdate"), resultSet.getString("role"));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return user;
    }

    /**
     *
     * @param id
     * @param name
     * @param firstName
     * @param birthDate
     * @param email
     * @param role
     * @return
     */
    @Override
	public int updateDAOUser(int id, String name, String firstName, String email, String birthDate, String role) {
    	Connection connection = getConnection();
        int res = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GeneralUsers SET name = ?, firstName = ?, email = ?, birthDate = ?, role = ? WHERE idUser = ?");
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,firstName);
                preparedStatement.setString(3,email);
                preparedStatement.setString(4,birthDate);
                preparedStatement.setString(5,role.toUpperCase());
                preparedStatement.setInt(6,id);
                res = preparedStatement.executeUpdate();
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return res;
	}


    /**
     * Reads the DB and delete the user.
     * @param id
     */
    @Override
    public int deleteDAOUser(int id, String role) {
    	Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                switch (role) {
                    case "STUDENT": {
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Students WHERE idStudent = ?");
                        preparedStatement.setInt(1, id);
                        preparedStatement.executeUpdate();
                        break;
                    }
                    case "TEACHER": {
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Teachers WHERE idTeacher = ?");
                        preparedStatement.setInt(1, id);
                        preparedStatement.executeUpdate();
                        break;
                    }
                    case "ADMIN":
                    case "SUPERADMIN": {
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Staffs WHERE idStaff = ?");
                        preparedStatement.setInt(1, id);
                        preparedStatement.executeUpdate();
                        break;
                    }
                }
            	
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM GeneralUsers WHERE idUser = ?");
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
     * Reads the DB and retrieves the user by its login and password.
     * Returns -1 if the email or password is null
     * @param login: the email of the user
     * @param password: the password of the user
     * @return the ID if successful, -1 otherwise
     */
    @Override
    public int readDAOUserByLogin(String login, String password){
        Connection connection = getConnection();
        int id = -1;
        if(connection != null && password != null && login != null){
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
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM GeneralUsers WHERE email = ? AND password IS NULL");
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

    /**
     * Checks the DB and asks to set a new password to the client.
     * @param login: the email of the client. Since it is personal, there can't be two same emails.
     * @param password: the new client's password.
     * @return true if the change has been successful, false otherwise.
     */
    @Override
    public boolean setNewPwd(String login, String password) {
        Connection connection = getConnection();
        int res = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GeneralUsers SET password = ? WHERE email = ?");
                preparedStatement.setString(1,password);
                preparedStatement.setString(2,login);
                res = preparedStatement.executeUpdate();
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return res == 1;
    }


    /**
     * This method return the teachers list
     */
    @Override
    public List<TeacherType> searchAllTeacher() {
            ArrayList<TeacherType> teacher = new ArrayList();
            Connection connection = getConnection();
            if(connection != null){
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralUsers where role = 'TEACHER' ");
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        teacher.add(new TeacherType(resultSet.getInt("idUser"),
                                resultSet.getString("name"),
                                resultSet.getString("firstName"),
                                resultSet.getString("email"),
                                resultSet.getString("birthDate"),
                                resultSet.getString("role")));
                    }

                }catch (SQLException e){e.printStackTrace();}
                finally {
                    closeConnection(connection);
                }
            }
            return teacher;
    }
    /**
     * This method return the teachers list where teachers are not admins
     */
    @Override
    public List<TeacherType> searchAllTeacherNA() {
        ArrayList<TeacherType> teacher = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Teachers WHERE isAdmin = ? ");
                preparedStatement.setBoolean(1, false);
                ResultSet resultSet = preparedStatement.executeQuery();
                int id = 0;
                while (resultSet.next()){
                    System.out.println("teacher NA +1");
                    id = resultSet.getInt("idTeacher");
                    try {
                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * from GeneralUsers WHERE idUser = ? ");
                        preparedStatement2.setInt(1, id);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();
                        while (resultSet2.next()){
                            teacher.add(new TeacherType(resultSet2.getInt("idUser"),
                                    resultSet2.getString("name"),
                                    resultSet2.getString("firstName"),
                                    resultSet2.getString("email"),
                                    resultSet2.getString("birthDate"),
                                    resultSet2.getString("role")));}
                    }catch (SQLException e){e.printStackTrace();}
                }

            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return teacher;
    }
    
    /**
     * This method return the users list
     */
    @Override
    public List<UserType> getAllUsers() {
        List<UserType> users = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralUsers");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    users.add(new UserType(resultSet.getInt("idUser"),
                            resultSet.getString("name"),
                            resultSet.getString("firstName"),
                            resultSet.getString("email"),
                            resultSet.getString("birthDate"),
                            resultSet.getString("role")));
                }

            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return users;
    }



    @Override
    public List<AdminType> getAllAdmin() {
        List<AdminType> users = new ArrayList();
        Connection connection = getConnection();
        if(connection != null) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Staffs WHERE isAdmin = ? ");
                preparedStatement.setBoolean(1, true);

                ResultSet resultSet = preparedStatement.executeQuery();
                int id = 0;
                while (resultSet.next()) {
                    id = resultSet.getInt("idStaff");
                    System.out.print("+1 /");
                    try {
                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * from GeneralUsers WHERE idUser = ? ");
                        preparedStatement2.setInt(1, id);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();

                        while (resultSet2.next()) {
                            users.add(new AdminType(resultSet2.getInt("idUser"),
                                    resultSet2.getString("name"),
                                    resultSet2.getString("firstName"),
                                    resultSet2.getString("email"),
                                    resultSet2.getString("birthDate"),
                                    resultSet2.getString("role")));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                    try {
                        PreparedStatement preparedStatementT = connection.prepareStatement("SELECT * from Teachers WHERE isAdmin = ? ");
                        preparedStatementT.setBoolean(1, true);

                        ResultSet resultSetT = preparedStatementT.executeQuery();
                        int id2 = 0;
                        while (resultSetT.next()) {
                            id2 = resultSetT.getInt("idTeacher");
                            System.out.print("teach+1 /");
                            try {
                                PreparedStatement preparedStatementT2 = connection.prepareStatement("SELECT * from GeneralUsers WHERE idUser = ? ");
                                preparedStatementT2.setInt(1, id2);
                                ResultSet resultSetT2 = preparedStatementT2.executeQuery();
                                while (resultSetT2.next()) {
                                    users.add(new AdminType(resultSetT2.getInt("idUser"),
                                            resultSetT2.getString("name"),
                                            resultSetT2.getString("firstName"),
                                            resultSetT2.getString("email"),
                                            resultSetT2.getString("birthDate"),
                                            resultSetT2.getString("role")));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (SQLException e) {
                        e.printStackTrace();

                    }

        } catch (SQLException e) {
                e.printStackTrace();
            }
        }return users;
    }


    @Override
    public List<StaffType> getAllStaffNotAdmin() {
        List<StaffType> users = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Staffs WHERE isAdmin = ? ");
                preparedStatement.setBoolean(1, false);
                ResultSet resultSet = preparedStatement.executeQuery();
                int id = 0;
                while (resultSet.next()){
                    id = resultSet.getInt("idStaff");
                    try {
                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * from GeneralUsers WHERE idUser = ? ");
                        preparedStatement2.setInt(1, id);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();
                        while (resultSet.next()){
                            users.add(new StaffType(resultSet2.getInt("idUser"),
                                    resultSet2.getString("name"),
                                    resultSet2.getString("firstName"),
                                    resultSet2.getString("email"),
                                    resultSet2.getString("birthDate"),
                                    resultSet2.getString("role")));}
                    }catch (SQLException e){e.printStackTrace();}
                }

            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return users;
    }

    /**
     *
     * @param id
     * @param name
     * @param firstName
     * @param birthDate
     * @param email
     * @param role
     * @param isAdmin : 0=FALSE , 1=TRUE
     * @return
     */
    @Override
    public int updateDAOAdminUser(int id, String name, String firstName, String email, String birthDate, String role, int isAdmin) {
        Connection connection = getConnection();
        int res = 0;
        int result =0;
        int result2 =0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE GeneralUsers SET name = ?, firstName = ?, email = ?, birthDate = ?, role = ? WHERE idUser = ?");
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,firstName);
                preparedStatement.setString(3,email);
                preparedStatement.setString(4,birthDate);
                preparedStatement.setString(5,role.toUpperCase());
                preparedStatement.setInt(6,id);
                res = preparedStatement.executeUpdate();
            }catch (SQLException e){e.printStackTrace();}
            if (res==1){
            if(role.equals("TEACHER")) {
                PreparedStatement preparedStatement2 = null;
                try {
                    preparedStatement2 = connection.prepareStatement("UPDATE Teachers SET isAdmin = ? WHERE idTeacher = ?");
                    preparedStatement2.setInt(1, isAdmin);
                    preparedStatement2.setInt(2, id);
                    result = preparedStatement2.executeUpdate();
                } catch (SQLException e) {
                e.printStackTrace();
            }
                return result;
            } else if(role.equals("STAFF")) {
                PreparedStatement preparedStatement3 = null;
                try {
                    preparedStatement3 = connection.prepareStatement("UPDATE Staffs isAdmin = ? WHERE idStaff = ?");

                preparedStatement3.setInt(1, isAdmin);
                preparedStatement3.setInt(2, id);
                result2 = preparedStatement3.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return result2;
            }
            }

    }
        return res;
    }

}
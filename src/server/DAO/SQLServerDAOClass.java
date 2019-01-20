package server.DAO;

import Types.ClassType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class instantiates the methods relative to the class in SQLServer data base
 * @author Audrey SAMSON
 */
public class SQLServerDAOClass extends AbstractDAOClass {

    /**
     * Default constructor
     */
    public SQLServerDAOClass() {

    }

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
     * This method closes the connection with the database.
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
     * This method creates a class in the database.
     * @param className the class name.
     * @param classDescription the class description.
     * @param idPromotion the promotion the class belongs to.
     * @return 1 if the creation was successful, 0 otherwise.
     */
    @Override
    public int createClass(String className, String classDescription, int idPromotion) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Classes(className,classDescription,idPromotion) VALUES (? ,? ,?)");
                preparedStatement.setString(1, className);
                preparedStatement.setString(2, classDescription);
                preparedStatement.setInt(3, idPromotion);
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
     * This method updates an existing class in the database.
     * @param idClass the class ID.
     * @param className the new class name.
     * @param descClass the new class description.
     * @param idPromotion the new promotion the class belongs to.
     * @return 1 if the update was successful, 0 otherwise.
     */
    @Override
    public int updateClass(int idClass, String className, String descClass, int idPromotion) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Classes SET className = ?,  classDescription = ?, idPromotion = ? WHERE idClass = ? ");
                preparedStatement.setString(1, className);
                preparedStatement.setString(2, descClass);
                preparedStatement.setInt(3, idPromotion);
                preparedStatement.setInt(4, idClass);
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
     * This method deletes a class in the database.
     * @param id the id of the class to delete.
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    @Override
    public int deleteClass(int id) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Classes WHERE idClass = ? ");
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
     * This method searches for all classes in the database.
     * @return a list of classes.
     */
    @Override
    public List<ClassType> searchAllClasses() {
        ArrayList<ClassType> cl = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Classes");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    cl.add(new ClassType(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return cl;
    }

    /**
     * This method searches for all the classes belonging to a promotiion.
     * @param idPromo the promotion ID.
     * @return a list of classes.
     */
    @Override
    public List<ClassType> searchAllClassesByPromo(int idPromo) {
        ArrayList<ClassType> cl = new ArrayList<>();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Classes WHERE idPromotion = ? ");
                preparedStatement.setInt(1, idPromo);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    cl.add(new ClassType(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4)));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return cl;
    }

}

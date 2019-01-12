package server.DAO;

import java.sql.*;

public class SQLServerDAODepartment extends AbstractDAODepartment{

    /**
     * Default constructor
     */
    public SQLServerDAODepartment() {

    }

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
     * this method create a department in the data base
     * @param name : department name
     * @param refTeacherID : department's referent teacher
     * @param descriptionDep : small department of the department
     */
    @Override
    public int createDepartment(String name, String refTeacherID, String descriptionDep) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Department() VALUES (? ,? ,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, refTeacherID);
                preparedStatement.setString(3, descriptionDep);
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



    @Override
    public int updateDepartment(int idDep, String name, String refTeacherID, String descriptionDep) {

        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Department SET name = ?, refTeacherID = ?, descriptionDep = ? WHERE idDepartment = ? ");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, refTeacherID);
                preparedStatement.setString(3, descriptionDep);
                preparedStatement.setInt(4, idDep);
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

    @Override
    public int deleteDepartment(int idDep) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Department WHERE idDepartment = ? ");
                preparedStatement.setInt(1, idDep);
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

    @Override
    public int readDepartment(int idDep) {
        Connection connection = getConnection();
        int id = -1;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from GeneralDepartment WHERE idDep = ? ");
                preparedStatement.setInt(1,idDep);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                id = resultSet.getInt("idDep");
            }catch (Exception e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return id;
    }

    /**
     * @param cred
     * @param cred1
     * @param cred2
     */
    public void createDAODepartment(String cred, String cred1, String cred2){

    }

    /**
     * @param cred
     * @param cred1
     * @param cred2
     */
    public void updateDAODepartment(String cred, String cred1, String cred2){

    }

    /**
     * @param cred
     */
    public void deleteDAODepartment(String cred){

    }

    public void readDAODepartment(int iddep){

    }
}

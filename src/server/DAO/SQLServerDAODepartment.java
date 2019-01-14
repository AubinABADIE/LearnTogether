package server.DAO;

import Types.DepartmentType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Departments(depatmentName,descriptionDep,refTeacher) VALUES (? ,? ,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(3, refTeacherID);
                preparedStatement.setString(2, descriptionDep);
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

    /**
     * This methos delete a department. It return an int to specify to the server the state of the deletion
     * @param idDep : department id
     * @return int who give the state of the deletion in the data base
     */
    @Override
    public int deleteDepartment(int idDep) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Departments WHERE idDepartment = ? ");
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
     * This method return the departments list
     */
    @Override
    public List<DepartmentType> searchAllDepartment() {

        ArrayList dep = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Departments");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    dep.add(new DepartmentType(resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt("refTeacher"),
                            resultSet.getString("descriptionDep")));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return dep;
    }


}

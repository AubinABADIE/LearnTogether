package server.DAO;

import Types.PromotionType;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class instantiate the method relative to the promotion in SQLServer data base
 * @author Audrey SAMSON
 */

public class SQLServerDAOPromotion extends AbstractDAOPromotion {

    /**
     * Default constructor
     */
    public SQLServerDAOPromotion() {

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
     * This method closes the connection with the data base
     */
    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns all the promotion in the data base.
     * @return int which returns the state of the selection.
     */
    @Override
    public List<PromotionType> searchAllPromotion() {
        ArrayList promo = new ArrayList();
        Connection connection = getConnection();
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from Promotions");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    promo.add(new PromotionType(resultSet.getInt("idPromotion"),
                            resultSet.getString("promotionName"),
                            resultSet.getString("descriptionPromo"),
                            resultSet.getInt("graduationYear"),
                            resultSet.getInt("idDepartment")
                            ));
                }
            }catch (SQLException e){e.printStackTrace();}
            finally {
                closeConnection(connection);
            }
        }
        return promo;
    }

    /**
     * This method creates a promotion in the data base.
     * @param name : promotion name
     * @param descriptionDep : promotion description
     * @param graduationYear : promotion gradation year
     * @param refDep : promotion department reference
     * @return int which give the state of the creation
     */
    @Override
    public int createPromotion(String name, String descriptionDep,  int graduationYear,int refDep) {
            Connection connection = getConnection();
            int result = 0;
            if(connection != null){
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Promotions(promotionName,descriptionPromo,graduationYear,idDepartment) VALUES (? ,? ,?, ?)");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, descriptionDep);
                    preparedStatement.setInt(3, graduationYear);
                    preparedStatement.setInt(4, refDep);
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
     * This method updates the promotion in the data base.
     * @param idPromo : promotion id
     * @param name : promotion name
     * @param descriptionDep : promotion description
     * @param graduationYear : promotion graduation year
     * @param refDep : promotion department id
     * @return int which gives the state od the request
     */
    @Override
    public int updatePromotion(int idPromo, String name,String descriptionDep, int graduationYear,int refDep) {
        Connection connection = getConnection();
            int result = 0;
            if(connection != null){
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Promotions SET promotionName = ?,  descriptionPromo = ?, graduationYear = ?, idDepartment = ? WHERE idPromotion = ? ");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, descriptionDep);
                    preparedStatement.setInt(3, graduationYear);
                    preparedStatement.setInt(4, refDep);
                    preparedStatement.setInt(5, idPromo);
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
     * This method deletes the promotion in the data base
     * @param idPromotion : promotion id
     * @return int which gives the state od the request
     */
    @Override
    public int deletePromotion(int idPromotion) {
        Connection connection = getConnection();
        int result = 0;
        if(connection != null){
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Promotions WHERE idPromotion = ? ");
                preparedStatement.setInt(1, idPromotion);
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

}

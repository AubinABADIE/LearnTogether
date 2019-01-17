package server.DAO;

import Types.PromotionType;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLServerDAOPromotion extends AbstractDAOPromotion {

    /**
     * Default constructor
     */
    public SQLServerDAOPromotion() {

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


    @Override
    public int updatePromotion(int idPromo, int refDep, String name, int graduationYear, String descriptionDep) {
        return 0;
    }

    @Override
    public int deletePromotiont(int idPromo) {
        return 0;
    }

    @Override
    public int readDepartment(int idDep) {
        return 0;
    }

}

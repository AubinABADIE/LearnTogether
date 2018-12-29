package server.DAO;


import com.lloseng.ocsf.server.ConnectionToClient;
import server.DBTypes.UserType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public abstract class AbstractDAOUser {

    public Connection getConnection(){
        Connection connection = null;
        String hostName = "learntogether.database.windows.net"; // update me
        String dbName = "LearnTogether"; // update me
        String user = "ysanson"; // update me
        String password = "LearnTogether1"; // update me
        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
        try {
            connection= DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connexion){
        try {
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract int readDAOUserByLogin(String login, String password);
    public abstract UserType readDAOUser(int id);

}
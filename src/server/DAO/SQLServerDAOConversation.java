package server.DAO;

import java.sql.*;
import java.util.Calendar;

/**
 *
 */
public class SQLServerDAOConversation extends AbstractDAOConversation{

    /**
     * Default constructor
     */
    public SQLServerDAOConversation() {
    }

    @Override
    public Connection getConnection() {
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

    @Override
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int storeMessage(int senderID, String receiverEmail, String messageContent){
        Calendar cal = Calendar.getInstance();
        Connection connection = getConnection();
        int res = 0;
        if(connection != null){
            try {
                PreparedStatement receiverById = connection.prepareStatement("select idUser from GeneralUsers where email=?");
                receiverById.setString(1, receiverEmail);
                ResultSet rs = receiverById.executeQuery();
                rs.next();
                int receiverID = rs.getInt("idUser");
                if (receiverID != 0){
                    PreparedStatement storeMsg = connection.prepareStatement("insert into Messages(content, idSender, idReceiver) values(?,?,?)");
                    storeMsg.setString(1, messageContent);
                    storeMsg.setInt(2, senderID);
                    storeMsg.setInt(3, receiverID);
                    res = storeMsg.executeUpdate();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return res;
    }
}
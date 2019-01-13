package server.DAO;

import Types.MessageType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    /**
     * This method stores a message into the database.
     * @param senderID: The original sender ID.
     * @param receiverEmail: The receiver email.
     * @param messageContent: The message content.
     * @return: 1 if it was successful, 0 otherwise.
     */
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
                    PreparedStatement storeMsg = connection.prepareStatement("insert into Messages(content, messageDate, idSender, idReceiver) values(?,?,?,?)");
                    storeMsg.setString(1, messageContent);
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
                    storeMsg.setTimestamp(2, timestamp);
                    storeMsg.setInt(3, senderID);
                    storeMsg.setInt(4, receiverID);
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

    /**
     * Retrieves and returns a list of messages from a specific conversation.
     * It first needs to get the other's ID, then the asking email, in order to construct a list of MessageType.
     * @param askingID: The user asking for it
     * @param otherEmail: The other user the conversation was with.
     * @return the list of messages from this conversation.
     */
    @Override
    public List<MessageType> retrieveConversation(int askingID, String otherEmail) {
        Connection connection = getConnection();
        List<MessageType> conversationMessages = new ArrayList<>();
        if(connection != null){
            try {
                PreparedStatement getOtherById = connection.prepareStatement("select idUser from GeneralUsers where email=?");
                getOtherById.setString(1, otherEmail);
                ResultSet rs = getOtherById.executeQuery();
                rs.next();
                int otherID = rs.getInt("idUser");
                if (otherID != 0){
                    PreparedStatement getAskingMail = connection.prepareStatement("select email from GeneralUsers where idUser=?");
                    getAskingMail.setInt(1, askingID);
                    ResultSet askingMail = getAskingMail.executeQuery();
                    askingMail.next();
                    String askMail = askingMail.getString(1);
                   PreparedStatement retrieveConv = connection.prepareStatement("select * from Messages where (idReceiver=? and idSender=?) or (idSender = ? and idReceiver=?) order by messageDate");
                   retrieveConv.setInt(1, askingID);
                   retrieveConv.setInt(2, otherID);
                   retrieveConv.setInt(3, askingID);
                   retrieveConv.setInt(4, otherID);
                   ResultSet messages = retrieveConv.executeQuery();
                   while (messages.next()){
                       conversationMessages.add(new MessageType(messages.getInt(1),
                               messages.getString(2),
                               messages.getTime(3),
                               askMail,
                               otherEmail));
                   }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return conversationMessages;
    }

    @Override
    public List<String> getConversationEmails(int askingID) {
        Connection connection = getConnection();
        List<String> emails = new ArrayList<>();
        if(connection!=null){
            try {
                PreparedStatement getIds = connection.prepareStatement("select distinct idReceiver, idMessage from Messages where idSender=? order by idMessage");
                getIds.setInt(1, askingID);
                ResultSet ids = getIds.executeQuery();
                PreparedStatement retrieveMail;
                ResultSet email;
                while (ids.next()){
                    retrieveMail = connection.prepareStatement("select email from GeneralUsers where idUser=?");
                    retrieveMail.setInt(1, ids.getInt(1));
                    email = retrieveMail.executeQuery();
                    email.next();
                    emails.add(email.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return emails;
    }
}
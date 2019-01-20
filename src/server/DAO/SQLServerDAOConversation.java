package server.DAO;

import Types.MessageType;
import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

/**
 *This class instantiates the methods relative to the conversation in SQLServer data base
 * @author Yvan SANSON
 */
public class SQLServerDAOConversation extends AbstractDAOConversation{

    /**
     * Default constructor
     */
    public SQLServerDAOConversation() { }

    /**
     * This method creates the connection with the data base
     * @return : a connection
     */
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

    /**
     * This method closes the connection to the database.
     * @param connection the active connection.
     */
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
     * @return 1 if it was successful, 0 otherwise.
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
                   PreparedStatement retrieveConv = connection.prepareStatement("select idMessage, content, messageDate, idSender, idReceiver\n" +
                           "from Messages\n" +
                           "where idReceiver in(select idUser from GeneralUsers, Messages where email=? and idReceiver=idUser and idSender=?)\n" +
                           "or idSender in(select idUser from GeneralUsers, Messages where email=? and idSender=idUser and idReceiver=?)\n" +
                           "except (select idMessage,content, messageDate, idSender, idReceiver from Messages where idReceiver != ? and idSender != ?)\n" +
                           "order by idMessage");
                   retrieveConv.setString(1, otherEmail);
                   retrieveConv.setInt(2, askingID);
                   retrieveConv.setString(3, otherEmail);
                   retrieveConv.setInt(4, askingID);
                   retrieveConv.setInt(5, askingID);
                   retrieveConv.setInt(6, askingID);
                   ResultSet messages = retrieveConv.executeQuery();
                   PreparedStatement getSenderEmails;
                   PreparedStatement getReceiverEmails;
                   ResultSet senderEmails, receiverEmails;
                   while (messages.next()){
                       getSenderEmails = connection.prepareStatement("select email from GeneralUsers, Messages where idSender=? and idUser=idSender and idMessage=? ");
                       getSenderEmails.setInt(1, messages.getInt("idSender"));
                       getSenderEmails.setInt(2, messages.getInt("idMessage"));
                       getReceiverEmails = connection.prepareStatement("select email from GeneralUsers, Messages where idReceiver=? and idUser=idReceiver and idMessage=?");
                       getReceiverEmails.setInt(1, messages.getInt("idReceiver"));
                       getReceiverEmails.setInt(2, messages.getInt("idMessage"));
                       senderEmails = getSenderEmails.executeQuery();
                       senderEmails.next();
                       receiverEmails = getReceiverEmails.executeQuery();
                       receiverEmails.next();
                       conversationMessages.add(new MessageType(messages.getInt(1),
                               messages.getString(2),
                               messages.getTime(3),
                               senderEmails.getString(1),
                               receiverEmails.getString(1)));
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

    /**
     * This method retrieves all the emails which have a conversation with the ID.
     * @param askingID the client ID.
     * @return a list of emails.
     */
    @Override
    public List<String> getConversationEmails(int askingID) {
        Connection connection = getConnection();
        List<String> emails = new ArrayList<>();
        if(connection!=null){
            try {
                PreparedStatement getIds = connection.prepareStatement("select distinct email from Messages, GeneralUsers where idSender=? and idUser=idReceiver or idUser in(select idUser from GeneralUsers, Messages where idReceiver=? and idSender=idUser)");
                getIds.setInt(1, askingID);
                getIds.setInt(2, askingID);
                ResultSet ids = getIds.executeQuery();
                while (ids.next()){
                    emails.add(ids.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally {
                closeConnection(connection);
            }
        }
        return new ArrayList<>(new HashSet<>(emails));
    }

    /**
     * This method deletes the conversation between two users.
     * @param askingID the user ID that asks to delete.
     * @param otherEmail the other participant's email.
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    @Override
    public int deleteConversation(int askingID, String otherEmail) {
        int res = 0;
        Connection connection = getConnection();
        if(connection!=null){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("delete from Messages where idMessage in (select idMessage\n" +
                        "            from Messages\n" +
                        "                 where idReceiver in(select idUser from GeneralUsers, Messages where email=? and idReceiver=idUser and idSender=?)\n" +
                        "                 or idSender in(select idUser from GeneralUsers, Messages where email=? and idSender=idUser and idReceiver=?)\n" +
                        "                 except (select idMessage from Messages where idReceiver != ? and idSender != ?)\n" +
                        "                 )");
                preparedStatement.setString(1, otherEmail);
                preparedStatement.setInt(2, askingID);
                preparedStatement.setString(3, otherEmail);
                preparedStatement.setInt(4, askingID);
                preparedStatement.setInt(5, askingID);
                preparedStatement.setInt(6, askingID);
                res = preparedStatement.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return res;
    }
}
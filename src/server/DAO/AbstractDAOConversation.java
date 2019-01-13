package server.DAO;

import Types.MessageType;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAOConversation {
    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int storeMessage(int senderID, String receiverEmail, String messageContent);
    public abstract List<MessageType> retrieveConversation(int askingID, String otherEmail);
    public abstract List<String> getConversationEmails(int askingID);
}

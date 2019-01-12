package server.DAO;

import java.sql.Connection;

public abstract class AbstractDAOConversation {
    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int storeMessage(int senderID, String receiverEmail, String messageContent);
}

package server.DAO;

import Types.MessageType;

import java.sql.Connection;
import java.util.List;

/**This class is the abstract class for the DAO conversation
 * @author Yvan SANSON
 */
public abstract class AbstractDAOConversation {
    /**
     * This method creates the connection with the data base
     * @return : a connection
     */
    public abstract Connection getConnection();

    /**
     * This method closes the connection to the database.
     * @param connection the active connection.
     */
    public abstract void closeConnection(Connection connection);

    /**
     * This method stores a message into the database.
     * @param senderID: The original sender ID.
     * @param receiverEmail: The receiver email.
     * @param messageContent: The message content.
     * @return 1 if it was successful, 0 otherwise.
     */
    public abstract int storeMessage(int senderID, String receiverEmail, String messageContent);

    /**
     * Retrieves and returns a list of messages from a specific conversation.
     * It first needs to get the other's ID, then the asking email, in order to construct a list of MessageType.
     * @param askingID: The user asking for it
     * @param otherEmail: The other user the conversation was with.
     * @return the list of messages from this conversation.
     */
    public abstract List<MessageType> retrieveConversation(int askingID, String otherEmail);

    /**
     * This method retrieves all the emails which have a conversation with the ID.
     * @param askingID the client ID.
     * @return a list of emails.
     */
    public abstract List<String> getConversationEmails(int askingID);

    /**
     * This method deletes the conversation between two users.
     * @param askingID the user ID that asks to delete.
     * @param otherEmail the other participant's email.
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteConversation(int askingID, String otherEmail);
}

package client.Chat;

import client.CoreClient;

import java.io.IOException;
import java.util.List;


/**
 * 
 */
public class ConversationServices {

    private CoreClient client;
    /**
     * Default constructor
     */
    public ConversationServices(CoreClient client) {
        this.client=client;
    }

    /**
     * This method creates a conversation between this client and anoth
     * @param receiverEmail: Creates a conversation with the other user by its email.
     */
    public void createConversation(String receiverEmail) {
        try {
            client.getConnection().sendToServer("#CREATECONVERSATION " + receiverEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToClient(int id, String receiverEmail, String messageContent){
        try {
            client.getConnection().sendToServer("#SENDMSGTOCLIENT-/-" + id + "-/-" + receiverEmail +"-/-"+messageContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method asks the server to delete a certain conversation between this client and another.
     * @param askingID this client ID.
     * @param conversationEmail the other person's email.
     */
    public void deleteConversation(int askingID, String conversationEmail) {
        try {
            client.getConnection().sendToServer("#DELETECONVERSATION " + askingID + " " + conversationEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conv 
     * @param members
     */
    public void addMembers(ConversationServices conv, List<Integer> members) {
        // TODO implement here
    }

    /**
     * This method sends a demand to get all the messages from a specific conversation.
     * @param id: the asking ID
     * @param email: the other participant's email.
     *
     */
    public void readConversation(int id, String email) {
        try {
            client.getConnection().sendToServer("#RETRIEVECONVERSATION " +id+ " "+ email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used when the client wants to get all the conversations he has. It asks for the emails of its discussions.
     * @param userId: the asking ID.
     */
    public void getConversationEmail(int userId) {
        try {
            client.getConnection().sendToServer("#GETCONVEMAIL "+userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handles the response from the server for a conversation deletion.
     * @param msg: the message.
     */
    public void handleDeletedConversation(String msg) {
        String[] args = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("MD SUCCESS");
        else
            client.getDisplay().setState("MD FAILURE");
    }
}
package client.Chat;

import client.CoreClient;

import java.io.IOException;
import java.util.List;


/**
 * 
 */
public class Conversation {

    private CoreClient client;
    /**
     * Default constructor
     */
    public Conversation(CoreClient client) {
        this.client=client;
    }

    /**
     * @param receiverEmail: Creates a conversation with the other user by its email.
     */
    public void createConversation(String receiverEmail) {
        try {
            client.getConnection().sendToServer("#CREATECONVERSATION " + receiverEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsgToClient(int id, String receiverEmail){
        try {
            client.getConnection().sendToServer("#SENDMSGTOCLIENT-/-" + id + "-/-" + receiverEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conversation
     */
    public void deleteConversation(int conversation) {
        // TODO implement here
    }

    /**
     * @param conv 
     * @param members
     */
    public void addMembers(Conversation conv, List<Integer> members) {
        // TODO implement here
    }

    /**
     * @param conv
     */
    public void readConversation(Conversation conv) {
        // TODO implement here
    }

}
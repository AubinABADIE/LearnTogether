package client.Chat;

import Types.MessageType;
import client.CoreClient;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void sendMsgToClient(int id, String receiverEmail, String messageContent){
        try {
            client.getConnection().sendToServer("#SENDMSGTOCLIENT-/-" + id + "-/-" + receiverEmail +"-/-"+messageContent);
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
     * @param email
     *
     */
    public void readConversation(int id, String email) {
        try {
            client.getConnection().sendToServer("#RETRIEVECONVERSATION " +id+ " "+ email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleReceivedMessage(String msg) {
        String[] arguments = msg.split("-/-");
        SimpleDateFormat parser = new SimpleDateFormat(" '['HH:mm:ss']'");

        try {
            MessageType message = new MessageType(0, arguments[4], parser.parse(arguments[3]), arguments[1],arguments[2]);
            client.getDisplay().displayMessage(message);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
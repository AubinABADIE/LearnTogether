package Chat;

import Users.User;

/**
 * 
 */
public class Message {

    /**
     * Default constructor
     */
    public Message() {
    }

    /**
     * 
     */
    private String textMessage;

    /**
     * 
     */
    private Conversation conversation;

    /**
     * 
     */
    private User creator;





    /**
     * @param textMessage 
     * @param conversation 
     * @param creator
     */
    public void createMessage(String textMessage, Conversation conversation, User creator) {
        // TODO implement here
    }

    /**
     * @param mess 
     * @param messageText 
     * @param conversation 
     * @param creator
     */
    public void sendMessage(Message mess, String messageText, Conversation conversation, User creator) {
        // TODO implement here
    }

}
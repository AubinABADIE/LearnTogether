package Types;

import java.io.Serializable;
import java.util.Date;

public class MessageType implements Serializable {
    private int idMessage;
    private String content;
    private Date messageDate;
    private String senderEmail, receiverEmail;

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public MessageType(int idMessage, String content, Date messageDate, String senderEmail, String receiverEmail) {
        this.idMessage = idMessage;
        this.content = content;
        this.messageDate = messageDate;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
    }
}

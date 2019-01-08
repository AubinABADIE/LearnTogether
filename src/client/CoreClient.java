package client;

import client.Users.User;
import com.lloseng.ocsf.client.AdaptableClient;
import common.ClientIF;
import common.DisplayIF;

import java.io.IOException;

public class CoreClient implements ClientIF {
    private User user;
    private AdaptableClient client;
    private DisplayIF display;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AdaptableClient getClient() {
        return client;
    }

    public void setClient(AdaptableClient client) {
        this.client = client;
    }

    public DisplayIF getDisplay() {
        return display;
    }

    public void setDisplay(DisplayIF display) {
        this.display = display;
    }

    public CoreClient(String host, int port, DisplayIF display) throws IOException {
        client = new AdaptableClient(host, port, this);
        this.display = display;
        client.openConnection();
        user = new User(this);
    }

    /**
     * Handles whatever comes from the server. It then calls the related functions.
     * @param msg: what the server sent.
     */
    @Override
    public void handleMessageFromServer(Object msg) {
        if(msg instanceof String){
            if(((String) msg).startsWith("#LOGON")){
                user.handleAnswerLogin((String)msg);
            }
            else if(((String) msg).startsWith("#FIRSTCONN")){
                user.handleFirstConnAnswer((String) msg);
            }
        }
    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionException(Exception exception) {

    }

    @Override
    public void connectionEstablished() {

    }

    public void handleLogin(String login, String password) {
        user.handleLogin(login, password);
    }

    public void setFirstPassword(String login, String password) {
        user.setFirstPassword(login, password);
    }
}

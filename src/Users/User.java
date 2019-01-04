package Users;


import com.lloseng.ocsf.client.ObservableClient;
import common.DisplayIF;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {

    private DisplayIF display;
    private ObservableClient comm;

    public User() {
    }

    /**
     * Creates a new instance of User
      * @param host: the server address
     * @param port: the server port
     * @param display: the display
     * @throws IOException
     */
    public User(String host, int port, DisplayIF display) throws IOException {
        comm = new ObservableClient(host, port);
        comm.addObserver(this);
        this.display = display;
        comm.openConnection();
    }

    /**
     * This method is called whenever the client wants to connect.
     * The display calls this function.
     * @param login: the email the client enters.
     * @param password: the password the client enters
     */
    public void handleLogin(String login, String password){
        try {
            comm.sendToServer("#LOGIN " + login + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called whenever the client wants to set up his account for his first connection.
     * The display calls this function.
     * @param login: the email the client enters.
     * @param password: the (first) password the client enters.
     */
    public void setFirstPassword(String login, String password){
        try{
            comm.sendToServer("#FIRSTCONN " + login + " " + password);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Handles whatever comes from the server. It then calls the related functions.
     * @param arg: what the server sent.
     */
    private void handleMessageFromServer(Object arg) {
        if(arg instanceof String){
            if(((String) arg).startsWith("#LOGIN")){
                handleAnswerLogin((String)arg);
            }
            else if(((String) arg).startsWith("#FIRSTCONN")){
                handleFirstConnAnswer((String) arg);
            }
        }
    }

    /**
     * When the server responds to a LOGIN command sent by this client. This method interprets what is returned.
     * @param loginString: the response from the server.
     */
    private void handleAnswerLogin(String loginString){
        System.out.println(loginString);
        String[] credentials = loginString.split(" ");
        boolean isConnected;
        isConnected = credentials[1].matches("TRUE");
        int id = Integer.parseInt(credentials[2]);
        String role = credentials[3];
        display.showLogin(isConnected, id, role);
    }

    /**
     * When the server responds to a FIRSTCONN command send by this client. This method interprets what is returned.
     * @param firstConnString: the response from the server.
     */
    private void handleFirstConnAnswer(String firstConnString){
        System.out.println(firstConnString);
        String[] args = firstConnString.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            display.setState("FC SUCCESS");
        else
            display.setState("FC FAILURE");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof String){
            if(arg.equals(ObservableClient.CONNECTION_CLOSED))
                this.connectionClosed();
            else if(arg.equals(ObservableClient.CONNECTION_ESTABLISHED))
                this.connectionEstablished();
            else
                this.handleMessageFromServer(arg);
        }
        else if(arg instanceof Exception)
            this.connectionException((Exception)arg);

    }

    private void connectionException(Exception arg) {
    }

    private void connectionEstablished() {
    }


    private void connectionClosed() {
    }
}

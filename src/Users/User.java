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

    public User(String host, int port, DisplayIF display) throws IOException {
        comm = new ObservableClient(host, port);
        comm.addObserver(this);
        this.display = display;
        comm.openConnection();
    }

    /**
     * Handles what comes from the UI
     * @param instruction
     */
    public void handleInstrFromUI(Object instruction){
        if(instruction instanceof String){
            String command = (String)instruction;
            if(command.startsWith("#LOGIN")){
                String[] ids = command.split(" ");
                handleLogin(ids[1], ids[2]);
            }
        }
    }

    public void handleLogin(String login, String password){
        try {
            comm.sendToServer("#LOGIN " + login + " " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFirstPassword(String login, String password){
        try{
            comm.sendToServer("#FIRSTCONN " + login + " " + password);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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


    private void handleAnswerLogin(String loginString){
        System.out.println(loginString);
        String[] credentials = loginString.split(" ");
        boolean isConnected;
        isConnected = credentials[1].matches("TRUE");
        int id = Integer.parseInt(credentials[2]);
        String role = credentials[3];
        display.showLogin(isConnected, id, role);
    }

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

package client.Users;


import client.CoreClient;


import java.io.IOException;

public class UserServices{

    private CoreClient coreClient;

    public UserServices() {
    }

    public UserServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * This method is called whenever the client wants to connect.
     * The display calls this function.
     * @param login: the email the client enters.
     * @param password: the password the client enters
     */
    public void handleLogin(String login, String password){
        try {
            coreClient.getClient().sendToServer("#LOGIN " + login + " " + password);
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
            coreClient.getClient().sendToServer("#FIRSTCONN " + login + " " + password);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * When the server responds to a LOGIN command sent by this client. This method interprets what is returned.
     * @param loginString: the response from the server.
     */
    public void handleAnswerLogin(String loginString){
        System.out.println(loginString);
        String[] credentials = loginString.split(" ");
        boolean isConnected;
        isConnected = credentials[1].matches("TRUE");
        int id = Integer.parseInt(credentials[2]);
        String role = credentials[3];
        coreClient.getDisplay().showLogin(isConnected, id, role);
    }

    /**
     * When the server responds to a FIRSTCONN command send by this client. This method interprets what is returned.
     * @param firstConnString: the response from the server.
     */
    public void handleFirstConnAnswer(String firstConnString){
        System.out.println(firstConnString);
        String[] args = firstConnString.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("FC SUCCESS");
        else
            coreClient.getDisplay().setState("FC FAILURE");
    }

}

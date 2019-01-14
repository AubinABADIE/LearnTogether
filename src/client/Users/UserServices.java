package client.Users;


import client.CoreClient;
import javafx.scene.image.Image;

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
            coreClient.getConnection().sendToServer("#LOGIN " + login + " " + password);
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
            coreClient.getConnection().sendToServer("#FIRSTCONN " + login + " " + password);
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
    
    /**
     * This method is called when the client wants to get is information for his profile.
     * @param id: the user's id.
     */
    public void readUser(int id) {
    	try {
    		coreClient.getConnection().sendToServer("#GETUSER " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When the server responds to a GETUSER command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleReadUser(String msg) {
    	String[] credentials = msg.split(" ");
		
	}
    
    /**
     * This method is called when the client wants to set a new password.
     * @param id
     * @param password
     */
    public void updatePwd(String login, String pwd) {
    	try {
    		coreClient.getConnection().sendToServer("#UPDATEPWD " + login + " " + pwd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When the server responds to a UPDATEPWD command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleUpdatedPwd(String msg) {
        String[] args = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("FC SUCCESS");
        else
            coreClient.getDisplay().setState("FC FAILURE");
    }
    
    /**
     * This method is called when an admin wants to set different information of an user profile.
     * @param id
     * @param name
     * @param firstname
     * @param birthDate
     * @param email
     * @param password
     * @param role
     */
    public void updateUser(int id, String name, String firstname, String birthDate, String email, String password, String role) {
    	try {
    		coreClient.getConnection().sendToServer("#UPDATEUSER " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When the server responds to a UPDATEUSER command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleUpdatedUser(String msg) {
    	
    }
    
    /**
     * This method is called when the client wants to set a different image.
     * @param id
     * @param photo
     */
    public void updatePhoto(int id, Image photo) {
    	
    }

    /**
     * When the server responds to a UPDATEPHOTO command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
	public void handleUpdatedPhoto(String msg) {
		
	}

}

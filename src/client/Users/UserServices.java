package client.Users;


import client.CoreClient;
import javafx.scene.image.Image;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * This class handles all the business logic related to the User (login, etc.)
 *
 * @author Aubin ABADIE for the profile
 * @author Yvan SANSON for the login and password encryption.
 */
public class UserServices{

    private CoreClient coreClient;

    public UserServices() {
    }

    public UserServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * This method encrypts the password given by the user.
     * @param pwd: the original password.
     * @return the encrypted password.
     */
    private String encryptPwd(String pwd){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pwd.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * This method is called whenever the client wants to connect.
     * The display calls this function.
     * @param login: the email the client enters.
     * @param password: the password the client enters
     */
    public void handleLogin(String login, String password){
        try {
            coreClient.getConnection().sendToServer("#LOGIN " + login + " " + encryptPwd(password));
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
            coreClient.getConnection().sendToServer("#FIRSTCONN " + login + " " + encryptPwd(password));
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
     * This method is called when the admin wants to create a new user.
     * @param name
     * @param firstname
     * @param birthDate
     * @param email
     * @param password
     * @param role
     */
    public void createUser(String name, String firstname, String birthDate, String email, String role, String password, String jobType) {
    	try {
    		coreClient.getConnection().sendToServer("#CREATEUSER " + name + " " + firstname + " " + birthDate  + " " + email + " " + role + " " + password + " " + jobType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When the server responds to a CREATEUSER command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleCreatedUser(String msg) {
    	String[] args = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("CUS SUCCESS");
        else
            coreClient.getDisplay().setState("CUS FAILURE");
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
     * This method send a message to the server to have the user list
     */
    public void getUsers() {
        try {
            coreClient.getConnection().sendToServer("#GETUSERS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is called when the client wants to set a new password.
     * @param login
     * @param pwd
     */
    public void updatePwd(String login, String pwd) {
    	try {
    		coreClient.getConnection().sendToServer("#UPDATEPWD " + login + " " + encryptPwd(pwd));
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
            coreClient.getDisplay().setState("UP SUCCESS");
        else
            coreClient.getDisplay().setState("UP FAILURE");
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
    public void updateUser(int id, String name, String firstname, String email, String birthDate, String role) {
    	try {
    		coreClient.getConnection().sendToServer("#UPDATEUSER " + id + " " + name + " " + firstname + " " + email + " " + birthDate + " " + role);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When the server responds to a UPDATEUSER command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleUpdatedUser(String msg) {
    	String[] args = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("UU SUCCESS");
        else
            coreClient.getDisplay().setState("UU FAILURE");
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
	
	/**
	 * This method is called when the admin wants to delete a user.
	 * @param id
	 */
	public void deleteUser(int id, String role) {
    	try {
    		coreClient.getConnection().sendToServer("#DELETEUSER " + id + " " + role);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * When the server responds to a DELETEUSER command sent by this client. This method interprets what is returned.
     * @param msg: the response from the server.
     */
    public void handleDeletedUser(String msg) {
    	String[] args = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            coreClient.getDisplay().setState("DU SUCCESS");
        else
            coreClient.getDisplay().setState("DU FAILURE");
	}

}

package server;

import Courses.Course;
import com.lloseng.ocsf.server.ConnectionToClient;
import com.lloseng.ocsf.server.OriginatorMessage;
import common.ChatIF;
import server.DBTypes.UserType;
import Groups.Promotion;
import Records.Record;
import Users.User;
import com.lloseng.ocsf.server.ObservableOriginatorServer;
import common.DisplayIF;
import server.DAO.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
public class GeneralServer implements Observer {

    private ObservableOriginatorServer comm;
    private ChatIF display;
    final private static int DEFAULT_PORT = 5555;
    private Date currentDate;
    private SimpleDateFormat dateFormat;
    private AbstractDAOFactory dao;


    /**
     * Default constructor
     */
    public GeneralServer(int port, ChatIF display) {
        comm = new ObservableOriginatorServer(port);
        comm.addObserver(this);
        currentDate = new Date();
        dateFormat = new SimpleDateFormat(" '['HH:mm:ss']'");
        this.display=display;
        dao = new SQLServerFactory();
        dao.createDAOUser();
    }

    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        if (msg instanceof String) {
            if (((String) msg).startsWith("#"))
                handleInstrFromClient(((String) msg).substring(1), client);
        }
    }

    public void handleInstrFromClient(String instruction, ConnectionToClient client) {
        if (instruction.startsWith("LOGIN")) {
            String[] ids = instruction.split(" ");
            handleLoginFromClient(ids[1], ids[2], client);
        }
    }

    /**
     * @param isConnected : true if the connection succeeded, false otherwise
     * @param id          : the user ID. value -1 if not connected.
     * @param role        : the user role. Value null if not connected.
     */
    public void sendToClientLogin(boolean isConnected, int id, String role, ConnectionToClient client) {
        try {
            if (isConnected) {
                client.sendToClient("#LOGIN TRUE " + id + " " + role);
            } else {
                client.sendToClient("#LOGIN FALSE -1 null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param login
     * @param password
     */
    public void handleLoginFromClient(String login, String password, ConnectionToClient client) {
        int userID = dao.readDAOUserByLogin(login, password);
        if (userID != -1) {
            checkLogin(userID, client);
        } else {
            sendToClientLogin(false, -1, null, client);
        }
    }

    /**
     * @param id: the user ID
     * @param client: the client from which it originated.
     */
    public void checkLogin(int id, ConnectionToClient client) {
        UserType user = dao.readDAOUser(id);
        sendToClientLogin(true, user.getId(), user.getRole(), client);
    }

    /**
     * @param object
     */
    public void sendToClientRecord(Object object) {
        // TODO implement here
    }

    /**
     * @param name
     * @param year
     * @param record
     * @param course
     * @param donatingUser
     */
    public void handleCreateRecordFromClient(String name, Date year, File record, Course course, User donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     * @param donatingUser
     */
    public void handleDeleteRecordFromClient(Record id, User donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void handleReadRecordFromClient(Record id) {
        // TODO implement here
    }

    /**
     * @param message
     */
    public void checkSuccess(Boolean message) {
        // TODO implement here
    }

    /**
     * @param record
     */
    public void checkRecord(Record record) {
        // TODO implement here
    }

    /**
     * @param name
     * @param firstname
     * @param login
     * @param birthDate
     * @param courses
     * @param promotions
     * @param typeJob
     * @param studentGroup
     */
    public void handleCreateProfile(String name, String firstname, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup) {
        // TODO implement here
    }

    /**
     * @param name
     * @param firstName
     * @param login
     * @param birthDate
     * @param courses
     * @param promotions
     * @param typeJob
     * @param studentGroup
     * @param picture
     * @param password
     */
    public void handleUpdateProfile(String name, String firstName, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup, File picture, String password) {
        // TODO implement here
    }

    /**
     * @param object
     */
    public void sendToClientUser(Object object) {
        // TODO implement here
    }

    /**
     * @param user
     */
    public void checkUser(User user) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void handleReadProfile(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void handleDeleteProfileFromClient(int id) {
        // TODO implement here
    }

    /**
     * @param obj
     */
    public void handleMessageFromClient(Object obj) {
        // TODO implement here
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof OriginatorMessage) {
            if (((OriginatorMessage) arg).getOriginator() == null) {
                if (((OriginatorMessage) arg).getMessage().equals(ObservableOriginatorServer.SERVER_STARTED)) {
                    this.serverStarted();
                } else if (((OriginatorMessage) arg).getMessage().equals(ObservableOriginatorServer.SERVER_STOPPED))
                    this.serverStopped();
                else if (((OriginatorMessage) arg).getMessage().equals(ObservableOriginatorServer.SERVER_CLOSED))
                    this.serverClosed();
                else if (((OriginatorMessage) arg).getMessage().toString().contains(ObservableOriginatorServer.LISTENING_EXCEPTION))
                    this.listeningException(((OriginatorMessage) arg).getMessage());
            } else {
                if (((OriginatorMessage) arg).getMessage().equals(ObservableOriginatorServer.CLIENT_CONNECTED))
                    this.clientConnected(((OriginatorMessage) arg).getOriginator());
                else if (((OriginatorMessage) arg).getMessage().equals(ObservableOriginatorServer.CLIENT_DISCONNECTED))
                    this.clientDisconnected(((OriginatorMessage) arg).getOriginator());
                else if (((OriginatorMessage) arg).getMessage().toString().contains(ObservableOriginatorServer.CLIENT_EXCEPTION))
                    this.clientException(((OriginatorMessage) arg).getOriginator(), (Throwable) ((OriginatorMessage) arg).getMessage());
                else
                    this.handleMessageFromClient(((OriginatorMessage) arg).getMessage(), ((OriginatorMessage) arg).getOriginator());

            }

        }

    }

    private void clientException(ConnectionToClient originator, Throwable message) {
    }

    private void clientDisconnected(ConnectionToClient originator) {
    }
    private void clientConnected(ConnectionToClient originator) {
    }
    private void listeningException(Object message) {
    }

    private void serverStopped() {
    }

    private void serverStarted() {
    }
    private void serverClosed() {
    }


    public void handleMessageFromServerUI(String message) {
        display.display("No commands have been implemented yet.");
    }
}



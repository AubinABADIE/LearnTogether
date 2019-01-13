package client;

import java.io.IOException;
import java.util.List;

import com.lloseng.ocsf.client.AdaptableClient;

import Types.RoomType;
import client.Chat.Conversation;
import client.Groups.Department;
import client.Room.RoomServices;
import client.Users.UserServices;
import common.ClientIF;
import common.DisplayIF;
import javafx.scene.image.Image;

public class CoreClient implements ClientIF {
    //Business logics
    private UserServices user;
    private RoomServices room;
    private Department department;
    private Conversation conversations;

    //UI and Connections
    private AdaptableClient connection;
    private DisplayIF display;

    public UserServices getUser() {
        return user;
    }

    public void setUser(UserServices user) {
        this.user = user;
    }

    public AdaptableClient getConnection() {
        return connection;
    }

    public void setConnection(AdaptableClient client) {
        this.connection = client;
    }

    public DisplayIF getDisplay() {
        return display;
    }

    public void setDisplay(DisplayIF display) {
        this.display = display;
    }

    public CoreClient(String host, int port, DisplayIF display) throws IOException {
        connection = new AdaptableClient(host, port, this);
        this.display = display;
        connection.openConnection();
        user = new UserServices(this);
        department = new Department( this);
        room = new RoomServices(this);
        conversations = new Conversation(this);
    }

    /**
     * Handles whatever comes from the server. It then calls the related functions.
     *
     * @param msg: what the server sent.
     */
    @Override
    public void handleMessageFromServer(Object msg) {
        if (msg instanceof String) {
            if (((String) msg).startsWith("#LOGON")) {
                user.handleAnswerLogin((String) msg);
            } else if (((String) msg).startsWith("#FIRSTCONN")) {
                user.handleFirstConnAnswer((String) msg);
            } else if  (((String) msg).startsWith("#CREATEDROOM")){
                room.handleCreatedRoom((String) msg);
            } else if (((String)msg).startsWith("#DELETEROOM")){
                room.handleDeletedRoom((String)msg);
            }
            else if  (((String) msg).startsWith("#CREATEDDEPARTMENT")){
                department.handleCreatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#UPDATEDDEPARTMENT")){
                department.handleUpdatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#DELETEDDEPARTMENT")){
                department.handleDeletedDepartment((String) msg);
            }
            else if(((String) msg).startsWith("#MSGFORYOU"))
                conversations.handleReceivedMessage((String)msg);
        } else if (msg instanceof List) {
            if (((List)msg).get(0) instanceof RoomType)
                display.getRooms((List<RoomType>)msg);
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

    public void handleCreateRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description) {
        room.handleCreateRoom(name, capacity, building, hasProjector, hasComputer, description);
    }

    public void handleDeleteRoom(int id){
        room.handleDeleteRoom(id);
    }

    public void handleCreateDepartment(String name, int refTeacherID, String descriptionDep){
        department.createDepartment(name, refTeacherID, descriptionDep);
    }

    public void handleUpdateDepartment(String name, int refTeacherID, String descriptionDep){
        department.updateDepartment(name, refTeacherID, descriptionDep);
    }

    public void handleDeleteDepartment(int departmentID){
        department.deleteDepartment(departmentID);
    }

    /**
     * this method delegates getRooms to roomServices
     * @return a room list
     */
    public void getRooms() {
        room.getRooms();
    }

    public void createConversation(String receiverEmail){conversations.createConversation(receiverEmail);}

    public void sendMsgToClient(int id, String receiverEmail, String messageContent){conversations.sendMsgToClient(id, receiverEmail, messageContent);}

    public void  readConversation(int id, String email){conversations.readConversation(id, email);}
    
    /**********************
     * Profile
     **********************/
    
    public void handleCreateUser() {}
    
    public void handleReadUser(String login) {
    	user.readUser(login);
    }
    
    public void handleUpdateUser(int id) {
    	user.updatePhoto();
    }
    
    public void handleUpdateUser(int id, Image img, String pwd) {
    	user.updatePhoto();
    }
    
    public void handleDeleteUser() {}
}
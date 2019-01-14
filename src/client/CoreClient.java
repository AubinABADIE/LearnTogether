package client;

import java.io.IOException;
import java.util.List;

import Types.DepartmentType;
import Types.UserType;
import Types.*;
import client.Users.TeacherServices;
import com.lloseng.ocsf.client.AdaptableClient;

import client.Chat.Conversation;
import client.Groups.Department;
import client.Room.RoomServices;
import client.Users.UserServices;
import client.Courses.CourseServices;
import common.ClientIF;
import common.DisplayIF;
import javafx.scene.image.Image;

public class CoreClient implements ClientIF {
    //Business logics
    private UserServices user;
    private RoomServices room;
    private CourseServices course;
    private Department department;
    private Conversation conversations;
    private TeacherServices teacher;

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
        course = new CourseServices(this);
        teacher = new TeacherServices(this);
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
            } else if (((String)msg).startsWith("#DELETEDROOM")){
                room.handleDeletedRoom((String)msg);
            } else if (((String) msg).startsWith("#UPDATEDROOM")){
                room.handleUpdatedRoom((String) msg);
            } else if  (((String) msg).startsWith("#CREATEDCOURSE")){
                room.handleCreatedRoom((String) msg);
            } else if (((String)msg).startsWith("#DELETEDCOURSE")){
                room.handleDeletedRoom((String)msg);
            } else if (((String) msg).startsWith("#UPDATEDCOURSE")){
                room.handleUpdatedRoom((String) msg);
            } else if  (((String) msg).startsWith("#CREATEDDEPARTMENT")){
                department.handleCreatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#UPDATEDDEPARTMENT")){
                department.handleUpdatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#DELETEDDEPARTMENT")){
                department.handleDeletedDepartment((String) msg);
            }
            else if(((String) msg).startsWith("#READUSER")) {
            	user.handleReadUser((String)msg);
            }
            else if(((String) msg).startsWith("#UPDATEDPWD")) {
            	user.handleUpdatedPwd((String)msg);
            }
        } else if (msg instanceof List) {
            if (((List)msg).get(0) instanceof RoomType)
                display.getRooms((List<RoomType>)msg);
            else if(((List)msg).get(0) instanceof MessageType)
                display.setConversationMessages((List<MessageType>)msg);
            else if(((List)msg).get(0) instanceof String){
                if(((String) ((List) msg).get(0)).equalsIgnoreCase("CONVERSATION EMAILS"))
                    display.setConversationEmails((List<String>) msg);
            }
            else if (((List)msg).get(0) instanceof DepartmentType){
                display.getDepartment((List<DepartmentType>)msg);}
            else if (((List)msg).get(0) instanceof TeacherType){
                display.getTeacher((List<TeacherType>)msg);}

        }
        else if(msg instanceof UserType){
            //TODO
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

    /**
     * This method delegates the management of the room creation to the roomservices
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : small room description
     */
    public void handleCreateRoom(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description) {
        room.handleCreateRoom(name, capacity, building, hasProjector, hasComputer, description);
    }

    /**
     * This method delegates the management of the room deletion to the roomservices
     * @param id : room id
     */
    public void handleDeleteRoom(int id){
        room.handleDeleteRoom(id);
    }

    /**
     * This method delegates the management of the room update to the roomservices
     * @param id : room id
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param desc : small room description
     */
    public void handleUpdateRoom(int id, String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String desc){
        room.handleUpdateRoom(id, name, capacity, building, hasProjector, hasComputer, desc);
    }
    
    /**
     * This method delegates the management of the room creation to the roomservices
     * @param name : room name
     * @param description : small description of the course
	 * @param totalHours : the total hours of the course
	 * @param idT: the referring teacher of the course
     */
    public void handleCreateCourse(String name, String description, int totalHours, int idT) {
        course.handleCreateCourse(name, description, totalHours, idT);
    }

    /**
     * This method delegates the management of the room deletion to the courseservices
     * @param id : room id
     */
    public void handleDeleteCourse(int id){
        room.handleDeleteRoom(id);
    }

    /**
     * This method delegates the management of the room update to the courseservices
     * @param name : course name
     * @param description : small description of the course
	 * @param totalHours : the total hours of the course
	 * @param idT : the referring teacher of the course
     */
    public void handleUpdateCourse(int id, String name, String description, int totalHours, int idT){
        course.handleUpdateCourse(id, name, description, totalHours, idT);
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
     * This method delegates getRooms to roomServices
     * @return a room list
     */
    public void getRooms() {
        room.getRooms();
    }
    
    /**
     * This method delegates getCourses to courseServices
     * @return a room list
     */
    public void getCourses() {
    	System.out.println("ok2");
        course.getCourses();
        
    }

    /**
     * This method delegates getDepartment to Department class
     * @return a department list
     */
    public void getDepartment() {
        department.getDepartment();
    }

    public void createConversation(String receiverEmail){conversations.createConversation(receiverEmail);}

    public void sendMsgToClient(int id, String receiverEmail, String messageContent){conversations.sendMsgToClient(id, receiverEmail, messageContent);}

    public void  readConversation(int id, String email){conversations.readConversation(id, email);}
    
    public void getConversationEmail(int userID) {
        conversations.getConversationEmail(userID);
    }
    
    /**********************
     * Profile
     **********************/
    
    public void handleCreateUser() {}
    
    public void handleReadUser(int id) {
    	user.readUser(id);
    }
    
    public void handleUpdatePwd(String login, String pwd) {
    	user.updatePwd(login, pwd);
    }


    public void getTeacher() {
        teacher.getTeacher();
    }
    
}
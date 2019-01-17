package server;

import com.lloseng.ocsf.server.ConnectionToClient;
import com.lloseng.ocsf.server.OriginatorMessage;
import common.ChatIF;
import Types.*;
import com.lloseng.ocsf.server.ObservableOriginatorServer;
import server.DAO.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Main Business logic for the server side. It contains everything that the server needs to run properly.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class GeneralServer implements Observer {

    private ObservableOriginatorServer comm;
    private ChatIF display;
    final private static int DEFAULT_PORT = 5555;
    private Date currentDate;
    private SimpleDateFormat dateFormat;
    private AbstractDAOFactory dao;




    /**
     * The useful constructor
     * @param port: the server address
     * @param display: the server display
     * @throws IOException
     */
    public GeneralServer(int port, ChatIF display) throws IOException {
        comm = new ObservableOriginatorServer(port);
        comm.addObserver(this);
        comm.listen();
        currentDate = new Date();
        dateFormat = new SimpleDateFormat(" '['HH:mm:ss']'");
        this.display=display;
        dao = new SQLServerFactory();
        dao.createDAOUser();
        dao.createDAODepartment();
        dao.createDAORoom();
        dao.createDAOCourse();
        dao.createDAOConversation();
        dao.createDAOPromotion();
        dao.createDAOClass();
        display.display("Server is running on port " + port);
    }

    /**
     * This method is called whenever a client sends something to the server.
     * @param msg: the object the client sent.
     * @param client: the original client.
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        if (msg instanceof String) {
            if (((String) msg).startsWith("#"))
                handleInstrFromClient(((String) msg).substring(1), client);
        }
    }

    /**
     * This method is used whenever a message sent by a client starts with '#'
     * @param instruction: what the client sent.
     * @param client: the original client.
     */
    public void handleInstrFromClient(String instruction, ConnectionToClient client) {
        display.display(instruction);
        if (instruction.startsWith("LOGIN")) {
            String[] ids = instruction.split(" ");
            handleLoginFromClient(ids[1], ids[2], client);
        }
        else if(instruction.startsWith("FIRSTCONN")){
            String[] creds = instruction.split(" ");
            handleFirstConnectionFromClient(creds[1], creds[2], client);
        }
        else if(instruction.startsWith("CREATEDEP")){
            String[] creds = instruction.split("-/-");
            handleCreateDepartmentFromClient(creds[1], creds[2], creds[3], client);
        }else if(instruction.startsWith("UPDATEDEP")){
            String[] creds = instruction.split("-/-");
            handleUpdateDepartmentFromClient(creds[1], creds[2], creds[3],creds[4], client);
        }else if(instruction.startsWith("DELETEDEP")){
            String[] creds = instruction.split("-/-");
            handleDeleteDepartmentFromClient(Integer.parseInt(creds[1]), client);
        }else if (instruction.startsWith("GETDEPARTMENT")){
            handleListDepFromClient(client);
        }
        else if(instruction.startsWith("CREATEPROMOTION")){
            String[] creds = instruction.split("-/-");
            handleCreatePromotionFromClient(creds[1], creds[2], creds[3],creds[4], client);
        }else if(instruction.startsWith("UPDATEPROMOTION")){
            String[] creds = instruction.split("-/-");
            //handleUpdateDepartmentFromClient(creds[1], creds[2], creds[3],creds[4], client);
        }else if(instruction.startsWith("DELETEPROMOTION")){
            String[] creds = instruction.split("-/-");
            //handleDeleteDepartmentFromClient(Integer.parseInt(creds[1]), client);
        }else if (instruction.startsWith("GETPROMOTION")){
            handleListPromoFromClient(client);
        }
        else if(instruction.startsWith("CREATECLASS")){
            String[] creds = instruction.split("-/-");
            //handleCreatePromotionFromClient(creds[1], creds[2], creds[3],creds[4], client);
        }else if(instruction.startsWith("UPDATECLASS")){
            String[] creds = instruction.split("-/-");
            //handleUpdateDepartmentFromClient(creds[1], creds[2], creds[3],creds[4], client);
        }else if(instruction.startsWith("DELETECLASS")){
            String[] creds = instruction.split("-/-");
            //handleDeleteDepartmentFromClient(Integer.parseInt(creds[1]), client);
        }else if (instruction.startsWith("GETCLASS")){
            handleListClassFromClient(client);
        }
        else if (instruction.startsWith("CREATEROOM")){
            String[] attributes = instruction.split("-/-");
            handleCreateRoomFromClient(attributes[1], Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]), Boolean.parseBoolean(attributes[4]), Boolean.parseBoolean(attributes[5]),attributes[6], client);
        }else if (instruction.startsWith("DELETEROOM")){
            String[] attributes = instruction.split("-/-");
            handleDeleteRoomFromClient(Integer.parseInt(attributes[1]), client);
        } else if(instruction.startsWith("UPDATEROOM")){
            String[] attributes = instruction.split("-/-");
            handleUpdateRoomFromClient(Integer.parseInt(attributes[1]),attributes[2],Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]), Boolean.parseBoolean(attributes[5]),Boolean.parseBoolean( attributes[6]), attributes[7], client);
        } else if (instruction.startsWith("GETROOMS")){
            handleListRoomsFromClient(client);
        }
        else if (instruction.startsWith("CREATECOURSE")){
            String[] attributes = instruction.split("-/-");
            handleCreateCourseFromClient(attributes[1], attributes[2], Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]), client);
        }else if (instruction.startsWith("DELETECOURSE")){
            String[] attributes = instruction.split("-/-");
            handleDeleteCourseFromClient(Integer.parseInt(attributes[1]), client);
        } else if(instruction.startsWith("UPDATECOURSE")){
            String[] attributes = instruction.split("-/-");
            handleUpdateCourseFromClient(Integer.parseInt(attributes[1]),attributes[2],attributes[3], Integer.parseInt(attributes[4]), Integer.parseInt(attributes[5]), client);
        } else if (instruction.startsWith("GETCOURSES")){
        	System.out.println("ok4");
            handleListCoursesFromClient(client);
        }
        else if(instruction.startsWith("SENDMSGTOCLIENT")){
            String[] attributes = instruction.split("-/-");
            handleSendMessageToClient(Integer.parseInt(attributes[1]), attributes[2],attributes[3], client);
        }
        else if(instruction.startsWith("RETRIEVECONVERSATION")){
            String[] attributes = instruction.split(" ");
            handleReadConversation(Integer.parseInt(attributes[1]), attributes[2], client);
        }
        else if(instruction.startsWith("CREATEUSER")) {
        	String[] attributes = instruction.split(" ");
        	handleCreateUser(attributes[1], attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], client);  
        }
        else if(instruction.startsWith("GETUSER")) {
        	String[] attributes = instruction.split(" ");
        	handleReadUser(Integer.parseInt(attributes[1]), client);        	
        }
        else if(instruction.startsWith("UPDATEPWD")) {
        	String[] attributes = instruction.split(" ");
        	handleUpdatePwd(attributes[1], attributes[2], client);  
        }
        else if(instruction.startsWith("UPDATEUSER")) {
        	String[] attributes = instruction.split(" ");
        	handleUpdateUser(Integer.parseInt(attributes[1]), attributes[2], attributes[3], attributes[4], attributes[5], attributes[6], attributes[7], client);  
        }
        else if(instruction.startsWith("DELETEUSER")) {
        	String[] attributes = instruction.split(" ");
        	handleDeleteUser(Integer.parseInt(attributes[1]), client);  
        }
        else if(instruction.startsWith("GETCONVEMAIL")){
            String[] attributes = instruction.split(" ");
            handleGetConversationEmails(Integer.parseInt(attributes[1]), client);
        }
        else if (instruction.startsWith("GETTEACHER")){
            System.out.println("jecherchelesteacher");
            handleListTeacherFromClient(client);
        }
        else if(instruction.startsWith("DELETECONVERSATION")){
            String[] attributes = instruction.split(" ");
            handleDeleteConversation(Integer.parseInt(attributes[1]), attributes[2], client);
        }
    }




    /**
     * This method is used to send a client a response of a #LOGIN demand.
     * @param isConnected true if the connection succeeded, false otherwise
     * @param id  the user ID. value -1 if not connected.
     * @param role        : the user role. Value null if not connected.
     * @param client : the original client.
     */
    public void sendToClientLogin(boolean isConnected, int id, String role, ConnectionToClient client) {
        try {
            if (isConnected) {
                client.sendToClient("#LOGON TRUE " + id + " " + role);
            } else {
                client.sendToClient("#LOGON FALSE -1 null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to send a client a response of a #FIRSTCONN demand.
     * @param hasSucceeded: true id the first connection was a success, false otherwise.
     * @param client: the original client.
     */
    public void sendToClientFirstConn(boolean hasSucceeded, ConnectionToClient client){
        try{
            if(hasSucceeded){
                client.sendToClient("#FIRSTCONN SUCCESS");
            }else{
                client.sendToClient("#FIRSTCONN FAILURE");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used when a client wants to connect.
     * It needs to check in the database to see if credentials matches or not.
     * @param login : the email the client provided.
     * @param password: the password the client provided.
     * @param client: the original client.
     */
    public void handleLoginFromClient(String login, String password, ConnectionToClient client) {
        int userID = dao.getUserDAO().readDAOUserByLogin(login, password);
        if (userID != -1) {
            checkLogin(userID, client);
            client.setInfo("email", login);
            client.setInfo("id", userID);
        } else {
            sendToClientLogin(false, -1, null, client);
        }
    }

    /**
     * This method is used when a user wants to first connect to the application.
     * @param login: the email the client provided.
     * @param password: the (new) password the client provided.
     * @param client: the original client.
     */
    public void handleFirstConnectionFromClient(String login, String password, ConnectionToClient client){
        if(dao.getUserDAO().isPdwNull(login))
            if (dao.getUserDAO().setNewPwd(login, password)) {
                sendToClientFirstConn(true, client);
                return;
            }
        sendToClientFirstConn(false, client);

    }

    /**
     * This method is called when a user has the right credentials and connected.
     * It checks in the DB and grabs the user ID and its role.
     * It always sends true on the login, as it has already been checked.
     * @param id: the user ID
     * @param client: the client from which it originated.
     */
    public void checkLogin(int id, ConnectionToClient client) {
        UserType user = dao.getUserDAO().readDAOUser(id);
        sendToClientLogin(true, user.getId(), user.getRole(), client);
    }

    /**
     * @param name
     * @param refTeacherID
     * @param descriptionDep
     * @param client
     */
    public void handleCreateDepartmentFromClient(String name, String refTeacherID, String descriptionDep, ConnectionToClient client) {
        int refTeacher= Integer.parseInt(refTeacherID);
        int result = dao.getDepartmentDAO().createDepartment(name,refTeacher,descriptionDep);

        String mess;
        if (result == 1){
            mess = "#CREATEDEPARTMENT Success";
        }
        else{
            mess = "#CREATEDEPARTMENT Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idDep
     * @param name
     * @param refTeacherID
     * @param client
     */
    public void handleUpdateDepartmentFromClient(String idDep, String name, String refTeacherID, String descriptionDep, ConnectionToClient client) {
        int idDepart=Integer.parseInt(idDep);
        int result = dao.getDepartmentDAO().updateDepartment(idDepart,name,refTeacherID,descriptionDep);

        String mess;
        if (result == 1){
            mess = "#CREATEDDEPARTMENT Success";
        }
        else{
            mess = "#CREATEDEPARTMENT Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idDep
     * @param client
     */
    public void handleDeleteDepartmentFromClient(int idDep, ConnectionToClient client) {

        int result = dao.getDepartmentDAO().deleteDepartment(idDep);

        String mess;
        if (result == 1){
            mess = "#DELETEDDEPARTMENT Success";
        }
        else{
            mess = "#DELETEDDEPARTMENT Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idDep
     * @param client
     */
    public void handleReadDepartmentFromClient(String idDep, ConnectionToClient client) {
        int idDepart=Integer.parseInt(idDep);
        int result = dao.getDepartmentDAO().readDepartment(idDepart);

        String mess;
        if (result == 1){
            mess = "#READDEPARTMENT Success";
        }
        else{
            mess = "#READDEPARTMENT Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method delegates to the dao the research of the department
     */

    public void handleListDepFromClient(ConnectionToClient client){
        List<DepartmentType> dep =  dao.getDepartmentDAO().searchAllDepartment();
        try {
            client.sendToClient(dep);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method delegates to the dao the research of the department
     */

    public void handleListTeacherFromClient(ConnectionToClient client){
        List<TeacherType> teacher =  dao.getUserDAO().searchAllTeacher();
        try {
            client.sendToClient(teacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method delegates to the dao the room creation and interprets the result of the insert. At the end, ta message is sending to the client.
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if th room has computers
     * @param description : small description of the room
     */
    private void handleCreateRoomFromClient(String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description, ConnectionToClient client){
        int result = dao.getRoomDAO().createRoom(name, capacity, building, hasProjector, hasComputer, description);

        String mess;
        if (result == 1){
            mess = "#CREATEDROOM Success";
        }
        else{
            mess = "#CREATEDROOM Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method delegates to the dao the research of the room
     */

    public void handleListRoomsFromClient(ConnectionToClient client){
       List<RoomType> rooms =  dao.getRoomDAO().searchAllRooms();

        try {
            client.sendToClient(rooms);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method delegates to the dao the deletion of room
     * @param id : room id
     * @param client : client who deletes the room
     */
    public void handleDeleteRoomFromClient(int id, ConnectionToClient client){
        int result = dao.getRoomDAO().deleteRoom(id);

        String mess;
        if (result == 1){
            mess = "#DELETEDROOM Success" ;
        } else{
            mess = "#DELETEDROOM Failure";
        }

        try{
            client.sendToClient(mess);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method delegates to the dao the room update
     * @param id : room id
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room buildinf number
     * @param projector: if the room has a projector
     * @param computer : if the room has computers
     * @param desc : small description of room
     * @param client : client who wants to update
     */
    public void handleUpdateRoomFromClient (int id, String name, int capacity, int building, boolean projector, boolean computer, String desc, ConnectionToClient client ){
        int result = dao.getRoomDAO().updateRoom(id, name, capacity, building, projector, computer, desc);

        String mess;
        if (result == 1){
            mess = "#UPDATEDROOM Success" ;
        } else{
            mess = "#UPDATEDROOM Failure";
        }

        try{
            client.sendToClient(mess);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method delegates to the dao the course creation and interprets the result of the insert. At the end, ta message is sending to the client.
     * @param courseName : course name
     * @param courseDescription : small description of the course
     * @param nbHourTotal : number of total hour of the course
     * @param idTeacher : the id of the referring Teacher
     * @param client : client who create the course
     */
    
    private void handleCreateCourseFromClient(String courseName, String courseDescription, int nbHourTotal, int idTeacher, ConnectionToClient client){
        int result = dao.getCourseDAO().createCourse(courseName, courseDescription, nbHourTotal, idTeacher);

        String mess;
        if (result == 1){
            mess = "#CREATEDCOURSE Success";
        }
        else{
            mess = "#CREATEDCOURSE Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * This method delegates to the dao the research of the course
     */
    public void handleListCoursesFromClient(ConnectionToClient client){
        List<CourseType> courses =  dao.getCourseDAO().searchAllCourses();

         try {
        	 System.out.println("ok5");
             client.sendToClient(courses);
         } catch (IOException e) {
             e.printStackTrace();
         }

     }
    
    
    /**
     * This method delegates to the dao the deletion of course
     * @param id : course id
     * @param client : client who deletes the course
     */
    public void handleDeleteCourseFromClient(int id, ConnectionToClient client){
        int result = dao.getCourseDAO().deleteCourse(id);

        String mess;
        if (result == 1){
            mess = "#DELETEDCOURSE Success" ;
        } else{
            mess = "#DELETEDCOURSE Failure";
        }

        try{
            client.sendToClient(mess);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method delegates to the dao the course update
     * @param courseName : course name
     * @param courseDescription : small description of the course
     * @param nbHourTotal : number of total hour of the course
     * @param idTeacher : the id of the referring Teacher
     * @param client : client who update the course
     */
    
    public void handleUpdateCourseFromClient (int idCourse, String courseName, String courseDescription, int nbHourTotal, int idTeacher, ConnectionToClient client ){
        int result = dao.getCourseDAO().updateCourse(idCourse, courseName, courseDescription, nbHourTotal, idTeacher);

        String mess;
        if (result == 1){
            mess = "#UPDATEDCOURSE Success" ;
        } else{
            mess = "#UPDATEDCOURSE Failure";
        }

        try{
            client.sendToClient(mess);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves a message sent by a client to another.
     * It then sends back to the original client, and to the receiver if it is connected.
     * @param senderID: the sender ID from the DB
     * @param receiverEmail: The email of the receiver.
     * @param messageContent: The message content.
     * @param client: The original client.
     */
    private void handleSendMessageToClient(int senderID, String receiverEmail, String messageContent, ConnectionToClient client) {
        int res = dao.getConversationDAO().storeMessage(senderID, receiverEmail, messageContent);
        try{
            if(res == 1){
                client.sendToClient("#MESSAGE SENT");
            }else {
                client.sendToClient("#MESSAGE ERROR");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method retrieves all the messages from a specific confirmation and then sends it to the asking client.
     * @param askingId: the ID of the asking client.
     * @param otherEmail: the other participant to the conversation.
     * @param client: the asking client.
     */
    private void handleReadConversation(int askingId, String otherEmail, ConnectionToClient client) {
        List<MessageType> conversationMessages = dao.getConversationDAO().retrieveConversation(askingId, otherEmail);
        if(conversationMessages.size()!=0) {
            try {
                client.sendToClient(conversationMessages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method retrieves all the emails from the conversations the asking ID has. It then sends a List of String to the client, containing these emails.
     * @param askingID: the asking ID
     * @param client: the asking client.
     */
    private void handleGetConversationEmails(int askingID, ConnectionToClient client) {
        List<String> emails = dao.getConversationDAO().getConversationEmails(askingID);
        emails.add(0, "CONVERSATION EMAILS");
        try {
            client.sendToClient(emails);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method deletes a certain conversation between two users.
     * @param askingID: the asking ID
     * @param otherEmail: the other participant's email.
     * @param client: the demanding client.
     */
    private void handleDeleteConversation(int askingID, String otherEmail, ConnectionToClient client) {
        int res = dao.getConversationDAO().deleteConversation(askingID, otherEmail);
        try {
            if(res == 0)
                client.sendToClient("#DELETEDCONVERSATION FAILURE");
            else if(res == 1)
                client.sendToClient("#DELETEDCONVERSATION SUCCESS");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method creates a new user based on the information. It then sends a message concerning the success or not.
     * @param id
     * @param name
     * @param firstname
     * @param birthDate
     * @param email
     * @param password
     * @param role
     * @param client
     */
    private void handleCreateUser(String name, String firstname, String birthDate, String email, String password, String role, ConnectionToClient client) {
    	boolean res = dao.getUserDAO().createDAOUser(name, firstname, birthDate, email, password, role);
        try {
            if (res == false)
                client.sendToClient("#CREATEDUSER FAILURE");
            else if(res == true)
                client.sendToClient("#CREATEDUSER SUCCESS");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method get the information from the user ID. It then sends a message containing these information.
     * @param idUser: the asking ID.
     * @param client; the asking client.
     */
    private void handleReadUser(int idUser, ConnectionToClient client) {
    	UserType user = dao.getUserDAO().readDAOUser(idUser);
    	try {
            client.sendToClient(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method set a new password from the user ID. It then sends a message concerning the success or not.
     * @param login
     * @param pwd
     * @param client
     */
    private void handleUpdatePwd(String login, String pwd, ConnectionToClient client) {
    	boolean result = dao.getUserDAO().setNewPwd(login, pwd);
    	String msg;
        if (result == true){
        	msg = "#UPDATEDPWD SUCCESS" ;
        } else{
        	msg = "#UPDATEDPWD FAILURE";
        }
    	try {
            client.sendToClient(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method a user from the user ID. It then sends a message concerning the success or not.
     * @param id
     * @param name
     * @param firstname
     * @param birthDate
     * @param email
     * @param password
     * @param role
     * @param client
     */
    private void handleUpdateUser(int id, String name, String firstname, String birthDate, String email, String password, String role, ConnectionToClient client) {
    	boolean result = dao.getUserDAO().updateDAOUser(id, name, firstname, birthDate, email, password, role);
    	String msg;
        if (result == true){
        	msg = "#UPDATEDUSER SUCCESS" ;
        } else{
        	msg = "#UPDATEDUSER FAILURE";
        }
    	try {
            client.sendToClient(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method delete a user from the user ID. It then sends a message concerning the success or not.
     * @param id
     * @param client
     */
    private void handleDeleteUser(int id, ConnectionToClient client) {
    	boolean result = dao.getUserDAO().deleteDAOUser(id);
    	String msg;
        if (result == true){
        	msg = "#DELETEDUSER SUCCESS" ;
        } else{
        	msg = "#DELETEDUSER FAILURE";
        }
    	try {
            client.sendToClient(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * This method delegates to the dao the research of the department
     */

    public void handleListPromoFromClient(ConnectionToClient client){

        List<PromotionType> promo =  dao.getPromotionDAO().searchAllPromotion();
        try {
            client.sendToClient(promo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param name
     * @param descriptionPromo
     * @param graduationYear
     * @param idDepartment
     * @param client
     */
    public void handleCreatePromotionFromClient(String name, String descriptionPromo, String graduationYear, String idDepartment, ConnectionToClient client) {
        int g=Integer.parseInt(graduationYear);
        int id=Integer.parseInt(idDepartment);
        int result = dao.getPromotionDAO().createPromotion(name,descriptionPromo,g,id);

        String mess;
        if (result == 1){
            mess = "#CREATEDPROMO Success";
        }
        else{
            mess = "#CREATEDEDPROMO Failure";
        }
        try {
            client.sendToClient(mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method delegates to the dao the research of the classes
     */

    public void handleListClassFromClient(ConnectionToClient client){
        List<ClassType> cl =  dao.getClassDAO().searchAllClasses();
        System.out.print("taille:"+cl.size());
        try {
            client.sendToClient(cl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param message
     */
    public void checkSuccess(Boolean message) {
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



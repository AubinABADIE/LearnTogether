package client;

import java.io.File;
import java.io.IOException;
import java.util.List;

import Types.DepartmentType;
import Types.UserType;
import Types.*;
import client.Groups.ClassServices;
import client.Groups.DepartmentServices;
import client.Groups.PromotionServices;
import client.Records.RecordServices;
import client.Users.TeacherServices;
import com.lloseng.ocsf.client.AdaptableClient;

import client.Chat.ConversationServices;
import client.Room.RoomServices;
import client.Users.UserServices;
import client.Courses.CourseServices;
import common.ClientIF;
import common.DisplayIF;

/**
 * Core business logic for the client side. Its main role is to dispatch commands received from the UI, and to communicate with the server.
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class CoreClient implements ClientIF {
    //Business logics
    private UserServices user;
    private RoomServices room;
    private CourseServices course;
    private DepartmentServices department;
    private ConversationServices conversations;
    private TeacherServices teacher;
    private PromotionServices promo;
    private ClassServices classes;
    private RecordServices records;

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
        department = new DepartmentServices( this);
        room = new RoomServices(this);
        conversations = new ConversationServices(this);
        course = new CourseServices(this);
        teacher = new TeacherServices(this);
        promo = new PromotionServices(this);
        classes = new ClassServices(this);
        records = new RecordServices(this);
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
                course.handleCreatedCourse((String) msg);
            } else if (((String)msg).startsWith("#DELETEDCOURSE")){
                course.handleDeletedCourse((String)msg);
            } else if (((String) msg).startsWith("#UPDATEDCOURSE")){
                course.handleUpdatedCourse((String) msg);
            } else if  (((String) msg).startsWith("#CREATEDDEPARTMENT")){
                department.handleCreatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#UPDATEDDEPARTMENT")){
                department.handleUpdatedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#DELETEDDEPARTMENT")){
                department.handleDeletedDepartment((String) msg);
            }
            else if  (((String) msg).startsWith("#CREATEDPROMOTION")){
                promo.handleCreatedPromo((String) msg);
            }
            else if  (((String) msg).startsWith("#UPDATEDPROMOTION")){
                promo.handleUpdatedPromo((String) msg);
            }
            else if  (((String) msg).startsWith("#DELETEDPROMOTION")){
                promo.handleDeletedPromo((String) msg);
            }
            else if  (((String) msg).startsWith("#CREATEDCLASS")){
                classes.handleCreatedClass((String) msg);
            }
            else if  (((String) msg).startsWith("#UPDATEDCLASS")){
                classes.handleUpdatedClass((String) msg);
            }
            else if  (((String) msg).startsWith("#DELETEDCLASS")){
                classes.handleDeletedClass((String) msg);
            }
            else if(((String) msg).startsWith("#CREATEDUSER")) {
            	user.handleCreatedUser((String)msg);
            }
            else if(((String) msg).startsWith("#UPDATEDPWD")) {
            	user.handleUpdatedPwd((String)msg);
            }
            else if(((String) msg).startsWith("#UPDATEDUSER")) {
            	user.handleUpdatedUser((String)msg);
            }
            else if(((String) msg).startsWith("#DELETEDUSER")) {
            	user.handleDeletedUser((String)msg);
            }
            else if(((String) msg).startsWith("#DELETEDCONVERSATION")){
                conversations.handleDeletedConversation((String)msg);
            }
            else if(((String) msg).startsWith("#RECORDUPLOAD")){
                records.handleRecordUploaded((String)msg);
            }

        } else if (msg instanceof List) {
            if (((List) msg).get(0) instanceof RoomType)
                display.getRooms((List<RoomType>) msg);
            else if (((List) msg).get(0) instanceof MessageType)
                display.setConversationMessages((List<MessageType>) msg);
            else if (((List) msg).get(0) instanceof String) {
                if (((String) ((List) msg).get(0)).equalsIgnoreCase("CONVERSATION EMAILS"))
                    display.setConversationEmails((List<String>) msg);
            } else if (((List) msg).get(0) instanceof DepartmentType) {
                display.getDepartment((List<DepartmentType>) msg);
            } else if (((List) msg).get(0) instanceof TeacherType) {
                display.getTeacher((List<TeacherType>) msg);
            } else if (((List) msg).get(0) instanceof PromotionType) {
                display.getPromo((List<PromotionType>) msg);
            }else if (((List) msg).get(0) instanceof ClassType) {
                display.getClasses((List<ClassType>) msg);
            } else if (((List)msg).get(0) instanceof DepartmentType){
                display.getDepartment((List<DepartmentType>)msg);
            } else if (((List)msg).get(0) instanceof TeacherType) {
            	display.getTeacher((List<TeacherType>)msg);
            } else if (((List)msg).get(0) instanceof CourseType) {
            	System.out.println("réception courses Core client ");
            	display.getCourses((List<CourseType>)msg);
            } else if (((List)msg).get(0) instanceof UserType) {
            	display.getUsers((List<UserType>) msg);
            }
            else if (((List)msg).get(0) instanceof RecordType){
                display.getRecords((List<RecordType>)msg);
            }
        }
        else if (msg instanceof UserType) {
        	display.setUser((UserType) msg);
        }else if(msg instanceof RecordType){
            records.handleReceivedRecord((RecordType)msg);
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

    /**
     * This method delegates to userServices the login request
     * @param login : login
     * @param password : password
     */
    public void handleLogin(String login, String password) {
        user.handleLogin(login, password);
    }

    /**
     * This method delegates to userServices the first password change request
     * @param login : login
     * @param password : password
     */
    public void setFirstPassword(String login, String password) {
        user.setFirstPassword(login, password);
    }

    /**********************
     * Rooms
     **********************/
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
     * This method delegates getRooms to roomServices
     * @return a room list
     */
    public void getRooms() {
        room.getRooms();
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
        course.handleDeleteCourse(id);
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

    /**********************
     * Departments
     **********************/

    /**
     * This method delegates to departmentServices the department creation request.
     * @param name : department name
     * @param refTeacherID : teacher in charge of the department
     * @param descriptionDep : department description
     */
    public void handleCreateDepartment(String name, int refTeacherID, String descriptionDep){
        department.createDepartment(name, refTeacherID, descriptionDep);
    }

    /**
     * This method delegates to departmentServices the department updated request
     * @param idDep : department id
     * @param name : department name
     * @param refTeacherID : teacher who is in charge of the department
     * @param descriptionDep : department description
     */
    public void handleUpdateDepartment(int idDep,String name, int refTeacherID, String descriptionDep){
        department.updateDepartment(idDep,name, refTeacherID, descriptionDep);
    }

    /**
     * This method delegates the department deletion request
     * @param departmentID : department id
     */
    public void handleDeleteDepartment(int departmentID){
        department.deleteDepartment(departmentID);
    }

    
    /**
     * This method delegates getCourses to courseServices
     * @return a course list
     */
    public void getCourses(int userID) {
        course.getCourses(userID);
    }
    
    public void getCourses() {
        course.getCourses();
        
    }

    /**
     * This method delegates getDepartment to DepartmentServices class
     * @return a department list
     */
    public void getDepartment() {
        department.getDepartment();
    }


    /***************
     * Conversations
     ***************/
    /**
     * This method delegates to the conversation Business Logic
     * @param receiverEmail: The conversation you want to create.
     */
    public void createConversation(String receiverEmail){conversations.createConversation(receiverEmail);}

    /**
     * This method delegates to the conversation Business Logic.
     * @param id: the sender's id.
     * @param receiverEmail: the receiver email.
     * @param messageContent: the message content.
     */
    public void sendMsgToClient(int id, String receiverEmail, String messageContent){conversations.sendMsgToClient(id, receiverEmail, messageContent);}

    /**
     * This method delegates to the conversation Business Logic.
     * @param id: the asking ID.
     * @param email: the other participant's email.
     */
    public void  readConversation(int id, String email){conversations.readConversation(id, email);}

    /**
     * This method delegates to the conversation Business Logic.
     * @param userID: the asking ID.
     */
    public void getConversationEmail(int userID) {
        conversations.getConversationEmail(userID);
    }

    /**
     * This method delegates to the conversation Business Logic.
     * @param userID: the asking ID.
     * @param conversationEmail: the conversation to delete.
     */
    public void deleteConversation(int userID, String conversationEmail){conversations.deleteConversation(userID, conversationEmail);}
    
    /**********************
     * Profile
     **********************/
    /**
     * This method delegates to userServices the user creation request
     * @param name : user name
     * @param firstname : user first name
     * @param birthDate : user birth date
     * @param email : user login
     * @param password : user password
     * @param role : user role
     */
    public void handleCreateUser(String name, String firstname, String birthDate, String email, String password, String role) {
    	user.createUser(name, firstname, birthDate, email, password, role);
    }

    /**
     * This method delegates to userServices the user reading request
     * @param id : user id
     */
    public void handleReadUser(int id) {
    	user.readUser(id);
    }

    /**
     * This method delegates to userServices the password updated request
     * @param login : user email
     * @param pwd : user password
     */
    public void handleUpdatePwd(String login, String pwd) {
    	user.updatePwd(login, pwd);
    }

    /**
     * This method delegates to userServices the user updated request
     * @param id : user id
     * @param name : user name
     * @param firstname : user first name
     * @param birthDate : user birth date
     * @param email : user login
     * @param password : user password
     * @param role : user role
     */
    public void handleUpdateUser(int id, String name, String firstname, String birthDate, String email, String password, String role) {
    	user.updateUser(id, name, firstname, birthDate, email, password, role);
    }

    /**
     * This method delegates to userServices the user deletion request
     * @param id : user id
     */
    public void handleDeleteUser(int id) {
    	user.deleteUser(id);
    }

    /**
     * This method delegates to teacherServices the teachers' reading request
     */
    public void getTeacher() {
        teacher.getTeacher();
    }
    
    public void getUsers() {
    	user.getUsers();
    }
    
    

    /**
     * This method delegates getPromotion to PromotionServices class
     */
    public void getPromo() {
        promo.getPromotion();
    }

    /**
     * This method delegates to promotionServices the promotion creation request
     * @param name : promotion name
     * @param descriptionPromo : promotion description
     * @param graduationPromo : promotion graduation year
     * @param refDep : promotion department
     */
    public void handleCreatePromotion(String name, String descriptionPromo, int graduationPromo, int refDep){
        promo.createPromotion(name, descriptionPromo,graduationPromo,refDep);
    }

    /**
     * This method delegates to promotionServices the promotion deletion request
     * @param idPromo : promotion id
     */
    public void handleDeletePromotion(int idPromo){
        promo.deletePromotion(idPromo);
    }

    /**
     * This method delegates to promotionServices the promotion updated request
     * @param idPromo : promotion id
     * @param name : promotion name
     * @param descriptionPromo : promotion description
     * @param graduationPromo : promotion gradation year
     * @param refDep : promotion department
     */
    public void handleUpdatePromotion(int idPromo,String name, String descriptionPromo, int graduationPromo, int refDep){
        promo.updatePromotion(idPromo,name,descriptionPromo,graduationPromo,refDep);
    }
    /**
     * This method delegates getClasses to ClassServices class
     * @return a class list
     */
    public void getClasses() {
        classes.getClasses();
    }

    /**
     * This method delegates to classServices the class creation request
     * @param nameClass : class name
     * @param descriptionClass : class description
     * @param refPromo : class promotion reference
     */
    public void handleCreateClass(String nameClass, String descriptionClass,int refPromo){
        classes.createClass(nameClass, descriptionClass,refPromo);
    }

    /**
     * This method delegates to classServices the class deletion request
     * @param classId : class id
     */
    public void handleDeleteClass(int classId){
        classes.deleteClass(classId);
    }

    /**
     * This method delegates to classServices the class updated request
     * @param idC : class id
     * @param nameClass : class name
     * @param descriptionClass : class description
     * @param refP : class promotion reference
     */
    public void handleUpdateClass(int idC,String nameClass, String descriptionClass, int refP){
        classes.updateClass(idC,nameClass,descriptionClass,refP);
    }


    /**********************
     * Records
     **********************/
    /**
     * This method delegates to recordServices the record creation request
     * @param courseId : course id
     * @param year : exam year
     * @param recordFile : the file
     * @param userID : user who put the record
     */
    public void createRecord(int courseId, int year, File recordFile, int userID) {
        records.createRecord(courseId, year, recordFile, userID);
    }

    /**
     * This metod delegates getAllRecords to RecordsServices class
     */
    public void getAllRecords(){records.getAllRecord();}

    /**
     * This method delegates to the recordServices the download request
     * @param id
     */
    public void downloadRec(int id){
        records.downloadRec(id);
    }
}
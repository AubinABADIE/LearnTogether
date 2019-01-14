package common;

import Types.*;

import java.util.List;

public interface DisplayIF {

    /**
     * Method that when overriden is used to display objects onto
     * a UI.
     */
    void display(String message);
    void setState(String cmd);
    void showLogin(boolean isConnected, int id, String role);
    void getRooms(List<RoomType> rooms);
    void getCourses(List<CourseType> courses);
    void displayMessage(MessageType message);
    void setConversationMessages(List<MessageType> conversationMessages);
    void setConversationEmails(List<String> emails);
	
    void getDepartment(List<DepartmentType> dep);
    void getTeacher(List<TeacherType> teacher);
}

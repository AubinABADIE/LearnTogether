package common;

import Types.*;

import Types.RoomType;
import Types.UserType;
import Types.CourseType;

import java.util.List;

/**
 * Interface between the UI and the business logic client side.
 */
public interface DisplayIF {

    void display(String message);
    void setState(String cmd);
    void showLogin(boolean isConnected, int id, String role);
    void getRooms(List<RoomType> rooms);
    void getCourses(List<CourseType> courses);
    void setConversationMessages(List<MessageType> conversationMessages);
    void setConversationEmails(List<String> emails);
    void setUser(UserType user);
	
    void getDepartment(List<DepartmentType> dep);
    void getTeacher(List<TeacherType> teacher);
    void getPromo(List<PromotionType> promo);
    void getClasses(List<ClassType> classes);
    void getUsers(List<UserType> users);
    void getRecords(List<RecordType>records);
    void getRecordByUser(List<RecordType>records);
    void getAdmin(List<AdminType> adm);
    void getStaff(List<StaffType> adm);

}

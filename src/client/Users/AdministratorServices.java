package client.Users;

import java.util.Date;
import java.util.List;
import client.Courses.CourseServices;

/**
 * 
 */
public class AdministratorServices extends UserServices {

    /**
     * Default constructor
     */
    public AdministratorServices() {
    	super();
    }

    /**
     * @param userID 
     * @param name 
     * @param firstName 
     * @param email 
     * @param birthDate
     */
    public void updateUser(int userID, String name, String firstName, String email, Date birthDate) {
        // TODO implement here
    }

    /**
     * @param user
     */
    public void deleteUser(UserServices user) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param firstName 
     * @param email 
     * @param birthDate 
     * @param jobType
     */
    public void createStaff(String name, String firstName, String email, Date birthDate, String jobType) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param firstName 
     * @param email 
     * @param birthDate 
     * @param courseList
     */
    public void createTeacher(String name, String firstName, String email, Date birthDate, List<CourseServices> courseList) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param firstName 
     * @param email 
     * @param birthDate
     */
    public void createStudent(String name, String firstName, String email, Date birthDate) {
        // TODO implement here
    }

}
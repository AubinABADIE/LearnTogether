package client.Users;

import client.CoreClient;
import client.Courses.*;

import java.io.IOException;
import java.util.*;


/**
 * This class handles all the business logic related to the teachers.
 *
 * @author Audrey SAMSON
 */
public class TeacherServices{


    private CoreClient coreClient;

    /**
     * Default constructor
     */
    public TeacherServices() {
    }

    public TeacherServices(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    /**
     * 
     */
    private List<CourseServices> courseList;

    /**
     * @return
     */
    public boolean isAdmin() {
        // TODO implement here
        return false;
    }

    /**
     * @param course
     */
    public void addCourse(CourseServices course) {
        // TODO implement here
    }

    /**
     * @param courses
     */
    public void addCourses(List<CourseServices> courses) {
        // TODO implement here
    }

    /**
     * This method asks to the server to have the teacher list
     */
    public void getTeacher() {
        try {
            coreClient.getConnection().sendToServer("#GETTEACHER" );
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
package client.Users;

import client.CoreClient;
import client.Courses.*;

import java.io.IOException;
import java.util.*;


/**
 * 
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
    private List<Course> courseList;

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
    public void addCourse(Course course) {
        // TODO implement here
    }

    /**
     * @param courses
     */
    public void addCourses(List<Course> courses) {
        // TODO implement here
    }

    public void getTeacher() {
        try {
            coreClient.getConnection().sendToServer("#GETTEACHER" );
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

}
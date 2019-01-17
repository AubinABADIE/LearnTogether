package server.DAO;

import client.Courses.CourseServices;
import Types.UserType;

import java.io.File;
import java.util.*;

/**
 * 
 */
public class SQLServerDAORecord extends RecordsDAO{

    /**
     * Default constructor
     */
    public SQLServerDAORecord() {
    }

    /**
     * @param name 
     * @param year 
     * @param course 
     * @param record 
     * @param donatingUser
     */
    public void createRecord(String name, Date year, CourseServices course, File record, UserType donatingUser) {
        // TODO implement here
    }

    /**
     * @param id 
     * @param donatingUser
     */
   public void deleteRecord(int id, UserType donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     */

    public void readRecord(int id) {
        // TODO implement here
    }

}
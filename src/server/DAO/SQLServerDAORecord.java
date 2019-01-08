package server.DAO;

import client.Courses.Course;
import client.Records.Record;
import server.DBTypes.UserType;

import java.io.File;
import java.util.*;

/**
 * 
 */
public class SQLServerDAORecord {

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
    public void createRecord(String name, Date year, Course course, File record, UserType donatingUser) {
        // TODO implement here
    }

    /**
     * @param id 
     * @param donatingUser
     */
   public void deleteRecord(Record id, UserType donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     */

    public void readRecord(Record id) {
        // TODO implement here
    }

}
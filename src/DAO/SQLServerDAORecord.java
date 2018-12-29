package DAO;

import Courses.Course;
import Users.User;

import java.io.File;
import java.util.*;

/**
 * 
 */
public class SQLServerDAORecord extends DAORecord {

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
    public void createRecord(String name, Date year, Course course, File record, User donatingUser) {
        // TODO implement here
    }

    /**
     * @param id 
     * @param donatingUser
     */
   /* public void deleteRecord(Record id, User donatingUser) {
        // TODO implement here
    }*/

    /**
     * @param id
     */
    /*
    public void readRecord(Record id) {
        // TODO implement here
    }*/

}
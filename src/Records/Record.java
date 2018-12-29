package Records;

import Courses.Course;
import Users.User;
import common.DisplayIF;

import java.time.Year;
import java.util.*;

/**
 * 
 */
public class Record implements Observer {

    /**
     * Default constructor
     */
    public Record() {
    }

    /**
     * 
     */
    private Date recordYear;

    /**
     * 
     */
    private Course course;

    /**
     * 
     */
    private User donatingUser;


    /**
     * @param recYear 
     * @param course 
     * @param donatingUser
     */
    public void CreateRecord(Year recYear, Course course, User donatingUser) {
        // TODO implement here
    }

    /**
     * @param rec 
     * @param donatingUser
     */
    public void DeleteRecord(Record rec, User donatingUser) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void ReadRecord(int id) {
        // TODO implement here
    }

    /**
     * @param o : the object to display
     */
    public void display(Object o) {
        // TODO implement here
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
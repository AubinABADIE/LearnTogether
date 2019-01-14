package client.Records;


import client.Courses.CourseServices;

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
     * @param recYear 
     * @param course 
     * @param donatingUserID
     */
    public void CreateRecord(Year recYear, CourseServices course, int donatingUserID) {
        // TODO implement here
    }

    /**
     * @param rec 
     * @param donatingUserID
     */
    public void DeleteRecord(Record rec, int donatingUserID) {
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
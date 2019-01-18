package client.Records;


import Types.RecordType;
import client.CoreClient;
import client.Courses.CourseServices;

import java.io.File;
import java.io.IOException;
import java.time.Year;
import java.util.*;

/**
 * 
 */
public class RecordServices {
    private CoreClient client;
    /**
     * Default constructor
     */
    public RecordServices(CoreClient client) {
        this.client=client;
    }


    /**
     * This method creates a RecordType to send to the server. It encapsulates every parameters.
     * @param courseID the ID of the course from which the record originated.
     * @param examYear the year the exam was given.
     * @param record the actual file.
     * @param donatingUser the user that donated the record.
     */
    public void createRecord(int courseID, int examYear, File record, int donatingUser) {
        System.out.println("Records OK");
        RecordType recordObj = new RecordType(courseID, examYear, record,donatingUser);
        try {
            client.getConnection().sendToServer(recordObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param rec 
     * @param donatingUserID
     */
    public void deleteRecord(int rec, int donatingUserID) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void readRecord(int id) {
        // TODO implement here
    }


}
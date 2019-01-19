package Types;

import java.io.File;
import java.io.Serializable;

/**
 * This class is used to model the Record found in the database.
 * It implements Serializable to allow a RacordType object to go through sockets.
 *
 * @author Marie SALELLES
 * @author Yvan SANSON
 */
public class RecordType implements Serializable {
    private int courseID;
    private int examYear;
    private byte[] record;
    private String name;
    private int donatingUser;

    public RecordType(String name, int courseID, int examYear, byte[] recordFile, int donatingUser) {
        this.name = name;
        this.courseID = courseID;
        this.examYear = examYear;
        this.record = recordFile;
        this.donatingUser = donatingUser;
    }
    public RecordType(String name, int courseID, int examYear, int donatingUser){
        this.name = name;
        this.courseID = courseID;
        this.examYear = examYear;
        this.donatingUser = donatingUser;
    }



    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getExamYear() {
        return examYear;
    }

    public void setExamYear(int examYear) {
        this.examYear = examYear;
    }

    public byte[] getRecord() {
        return record;
    }

    public void setRecord(byte[] record) {
        this.record = record;
    }

    public int getDonatingUser() {
        return donatingUser;
    }

    public void setDonatingUser(int donatingUser) {
        this.donatingUser = donatingUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

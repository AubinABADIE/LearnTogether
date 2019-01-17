package Types;

import java.io.File;
import java.io.Serializable;

/**
 * This class is used to model the Record found in the database.
 * It implements Serializable to allow a RacordType object to go through sockets.
 *
 * @author Aubin ABADIE
 * @author Marie SALELLES
 * @author Audrey SAMSON
 * @author Yvan SANSON
 * @author Solene SERAFIN
 */
public class RecordType implements Serializable {
    private int courseID;
    private int examYear;
    private File recordFile;
    private int donatingUser;

    public RecordType(int courseID, int examYear, File recordFile, int donatingUser) {
        this.courseID = courseID;
        this.examYear = examYear;
        this.recordFile = recordFile;
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

    public File getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public int getDonatingUser() {
        return donatingUser;
    }

    public void setDonatingUser(int donatingUser) {
        this.donatingUser = donatingUser;
    }
}

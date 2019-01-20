package server.DAO;

import Types.RecordType;

import java.sql.Connection;
import java.util.List;

/**
 * This class is the abstract class for the DAO record
 * @author Yvan SANSON
 * @author Marie SALELLES
 */
public abstract class AbstractDAORecords {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);


    /**
     * This method searches all the records in the data base
     * @return : the record list
     */
    public abstract List<RecordType> searchAllRecords();

    /** This method creates in the data base a record
     * @param name : record name
     * @param year : exam year
     * @param courseID : record course
     * @param donatingUser : user id who give the record
     * @return 1 if the creation is successful, 0 otherwise.
     */
    public abstract int createRecord(String name, int year, int courseID, int donatingUser);

    /**
     * This method retrieves all the information related to a record stored in the database.
     * @param recordID the record ID in the database.
     * @return a RecordType instance containing all the record metadata.
     */
    public abstract RecordType getRecord(int recordID);

    /**
     * This method searches the records by user in the data base
     * @param id : user id
     * @return : the record list of one user
     */
    public abstract List<RecordType> searchRecordsByUser(int id);

    /**
     * This method deletes a record from the database.
     * @param recordID the record ID.
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteRecord(int recordID);
}

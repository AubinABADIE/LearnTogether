package server.DAO;

import Types.RecordType;

import java.sql.Connection;
import java.util.List;

/**This class is the abstract class for the DAO record
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
    
    public abstract int createRecord(String name, int year, int courseID, int donatingUser);

    public abstract RecordType getRecord(int recordID);

    /**
     * This method searches the records by user in the data base
     * @param id : user id
     * @return : the record list of one user
     */
    public abstract List<RecordType> searchRecordsByUser(int id);

    public abstract int deleteRecord(int recordID);
}

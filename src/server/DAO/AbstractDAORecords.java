package server.DAO;

import Types.RecordType;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAORecords {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);


    /**
     * This method search all the records in the data base
     * @return : the record list
     */
    public abstract List<RecordType> searchAllRecords();
    public abstract int createRecord(String name, int year, int courseID, int donatingUser);
}

package server.DAO;

import java.sql.Connection;

public abstract class AbstractDAORecords {

    public abstract Connection getConnection();
    public abstract void closeConnection(Connection connection);

    public abstract int createRecord(String name, int year, int courseID, int donatingUser);
}

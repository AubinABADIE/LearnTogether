package server.DAO;

/**
 * 
 */
public abstract class AbstractDAODepartment{

    /**
     * Default constructor
     */
    public AbstractDAODepartment() {
    }

    /**
     * @param cred
     * @param cred1
     * @param cred2
     */
    public abstract void handleCreateDepartmentFromClient(String cred, String cred1, String cred2);

    /**
     * @param cred
     * @param cred1
     * @param cred2
     */
    public abstract void handleUpdateDepartmentFromClient(String cred, String cred1, String cred2);

    /**
     * @param cred
     */
    public abstract void handleDeleteDepartmentFromClient(String cred);
}
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
     * @param name
     * @param refTeacherID
     * @param descriptionDep
     */
    public abstract int createDepartment(String name, String refTeacherID, String descriptionDep);

    /**
     * @param name
     * @param refTeacherID
     * @param descriptionDep
     */
    public abstract int updateDepartment(int idDep, String name, String refTeacherID, String descriptionDep);

    /**
     * @param idDep
     */
    public abstract int deleteDepartment(int idDep);

    public abstract int readDepartment(int idDep);
}
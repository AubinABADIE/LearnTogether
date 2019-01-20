package server.DAO;

import Types.DepartmentType;

import java.util.List;

/** This class is the abstract class for the DAO department
 * @author Audrey SAMSON
 */
public abstract class AbstractDAODepartment{

    /**
     * Default constructor
     */
    public AbstractDAODepartment() {
    }

    /**
     * This method creates a department in the data base
     * @param name : department name
     * @param refTeacherID : department's referent teacher
     * @param descriptionDep : small department of the department
     * @return 1 if the creation was successful, 0 otherwise.
     */
    public abstract int createDepartment(String name, int refTeacherID, String descriptionDep);

    /**
     * This method updates a department in the data base
     * @param idDep : department id
     * @param name : department name
     * @param refTeacherID : teacher in chage od the department
     * @param descriptionDep : department description
     * @return 1 if the update was successful, 0 otherwise.
     */
    public abstract int updateDepartment(int idDep, String name, String refTeacherID, String descriptionDep);

    /**
     * This method deletes a department. It return an int to specify to the server the state of the deletion
     * @param idDep : department id
     * @return 1 if the deletion was successful, 0 otherwise.
     */
    public abstract int deleteDepartment(int idDep);

    /**
     * This method selects a department in the data base.
     * @param idDep : department id
     * @return department id, or -1 if it doesn't exist.
     */
    public abstract int readDepartment(int idDep);

    /**
     * This method returns the departments list
     * @return a list of department
     */
    public abstract List<DepartmentType> searchAllDepartment();
}
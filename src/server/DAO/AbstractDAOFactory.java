package server.DAO;

/**
 * 
 */
public abstract class AbstractDAOFactory{

    private AbstractDAOUser userDAO;
    private AbstractDAODepartment departmentDAO;

    /**
     * Default constructor
     */
    public AbstractDAOFactory() {

    }

    public AbstractDAOUser getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(AbstractDAOUser userDAO) {
        this.userDAO = userDAO;
    }

    public AbstractDAODepartment getDepartmentDAO() {
        return departmentDAO;
    }

    public void setDepartmentDAO(AbstractDAODepartment departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    public void createDAOUser(){
    }

    public void createDAODepartment(){
    }

}
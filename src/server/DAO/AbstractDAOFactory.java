package server.DAO;

/**
 * 
 */
public abstract class AbstractDAOFactory extends AbstractDAOUser{

    private AbstractDAOUser userDAO;
    private AbstractDAODepartment departmentDAO;

    /**
     * Default constructor
     */
    public AbstractDAOFactory() {

    }

    public void createDAOUser(){
    }

    public void createDAODepartment(){
    }

}
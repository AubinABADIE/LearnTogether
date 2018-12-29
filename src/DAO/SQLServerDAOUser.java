package DAO;

import Courses.Course;
import Groups.Promotion;

import java.io.File;
import java.util.*;

/**
 * 
 */
public class SQLServerDAOUser extends DAOUser {

    /**
     * Default constructor
     */
    public SQLServerDAOUser() {
    }

    /**
     * @param name 
     * @param firstname 
     * @param login 
     * @param birthDate 
     * @param courses 
     * @param promotions 
     * @param typeJob 
     * @param studentGroup
     */
    public void createDAOUser(String name, String firstname, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param firstname 
     * @param login 
     * @param birthDate 
     * @param courses 
     * @param promotions 
     * @param typeJob 
     * @param studentGroup 
     * @param password 
     * @param picture
     */
    public void updateDAOUser(String name, String firstname, String login, Date birthDate, Course courses, Promotion promotions, String typeJob, Class studentGroup, String password, File picture) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void deleteDAOUser(int id) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void readDAOUser(int id) {
        // TODO implement here
    }

}
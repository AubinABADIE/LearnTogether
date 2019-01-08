package client.Grades;

import client.Courses.Course;
import client.Users.Student;

import java.util.*;

/**
 * 
 */
public class Grade{

    /**
     * Default constructor
     */
    public Grade() {
    }

    /**
     * @param description 
     * @param grades 
     * @param students 
     * @param course 
     * @param coeff 
     * @param teacherID
     */
    public void createGrade(String description, List<Float> grades, List<Student> students, Course course, float coeff, int teacherID) {
        // TODO implement here
    }

    /**
     * @param description 
     * @param grades 
     * @param students 
     * @param course 
     * @param coeff 
     * @param teacherID
     */
    public void updateGrade(String description, List<Float> grades, List<Student> students, Course course, float coeff, int teacherID) {
        // TODO implement here
    }

    /**
     * @param grade 
     * @param teacherID
     */
    public void deleteGrade(Grade grade, int teacherID) {
        // TODO implement here
    }

    /**
     * @param o : The object to display
     */
    public void display(Object o) {
        // TODO implement here
    }

}
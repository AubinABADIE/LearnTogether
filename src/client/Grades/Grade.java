package client.Grades;

import client.Courses.CourseServices;

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
     * @param studentsID
     * @param course 
     * @param coeff 
     * @param teacherID
     */
    public void createGrade(String description, List<Float> grades, List<Integer> studentsID, CourseServices course, float coeff, int teacherID) {
        // TODO implement here
    }

    /**
     * @param description 
     * @param grades 
     * @param studentsID
     * @param course 
     * @param coeff 
     * @param teacherID
     */
    public void updateGrade(String description, List<Float> grades, List<Integer> studentsID, CourseServices course, float coeff, int teacherID) {
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
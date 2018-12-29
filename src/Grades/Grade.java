
import Courses.Course;
import Users.Student;
import Users.Teacher;
import common.DisplayIF;

import java.util.*;

/**
 * 
 */
public class Grade implements DisplayIF {

    /**
     * Default constructor
     */
    public Grade() {
    }

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private List<Float> grades;

    /**
     * 
     */
    private List<Student> students;

    /**
     * 
     */
    private Course course;

    /**
     * 
     */
    private float coeff;

    /**
     * 
     */
    private Teacher teacher;







    /**
     * @param description 
     * @param grades 
     * @param students 
     * @param course 
     * @param coeff 
     * @param teacher
     */
    public void createGrade(String description, List<Float> grades, List<Student> students, Course course, float coeff, Teacher teacher) {
        // TODO implement here
    }

    /**
     * @param description 
     * @param grades 
     * @param students 
     * @param course 
     * @param coeff 
     * @param teacher
     */
    public void updateGrade(String description, List<Float> grades, List<Student> students, Course course, float coeff, Teacher teacher) {
        // TODO implement here
    }

    /**
     * @param grade 
     * @param teacher
     */
    public void deleteGrade(Grade grade, Teacher teacher) {
        // TODO implement here
    }

    /**
     * @param Object o : The object to display
     */
    public void display(Object o) {
        // TODO implement here
    }

    @Override
    public void display(String message) {

    }
}
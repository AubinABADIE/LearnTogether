package Groups;

import java.util.Date;

import Users.Teacher;

/**
 * 
 */
public class Promotion {

    /**
     * Default constructor
     */
    public Promotion() {
    }

    /**
     * 
     */
    private String namePromo;

    /**
     * 
     */
    private Teacher referenceTeacher;

    /**
     * 
     */
    private String descriptionPromo;

    /**
     * 
     */
    private Date graduationYear;

    /**
     * 
     */
    private Department department;



    /**
     * @param name 
     * @param refTeacher 
     * @param desc 
     * @param gradYear 
     * @param dep
     */
    public void createPromotion(String name, Teacher refTeacher, String desc, Date gradYear, Department dep) {
        // TODO implement here
    }

    /**
     * @param name 
     * @param refTeacher 
     * @param desc 
     * @param gradYear 
     * @param dep
     */
    public void updatePromotion(String name, Teacher refTeacher, String desc, Date gradYear, Department dep) {
        // TODO implement here
    }

    /**
     * @param promo
     */
    public void deletePromotion(Promotion promo) {
        // TODO implement here
    }

}
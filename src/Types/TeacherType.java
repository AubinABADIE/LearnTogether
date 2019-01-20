package Types;

import java.io.Serializable;

/**
 * This class represents a teacher in the database.
 * @author Audrey SAMSON
 */
public class TeacherType extends UserType implements Serializable {


    public TeacherType(int id, String name, String firstName, String email, String birthDate, String role) {
        super(id, name, firstName, email, birthDate, role);

    }

    @Override
    public String toString() {
        return getName() + " " + getFirstName()+ " "+ getId();
    }
}



package Types;

import java.io.Serializable;

/**
 * This class represents a Staff in the DataBase
 * @author Audrey SAMSON
 */

public class StaffType extends UserType implements Serializable {

    //role = "STAFF"

    public StaffType(int id, String name, String firstName, String email, String birthDate, String role) {
        super(id, name, firstName, email, birthDate, role);

    }

    @Override
    public String toString() {
        return getName() + " " + getFirstName()+ " "+ getId();
    }
}

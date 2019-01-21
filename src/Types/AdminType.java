package Types;

import java.io.Serializable;

/**
 * This class represents an admin in the database.
 * @author Audrey SAMSON
 */
public class AdminType extends UserType implements Serializable {
    //role = "ADMIN"

    public AdminType(int id, String name, String firstName, String email, String birthDate, String role) {
        super(id, name, firstName, email, birthDate, role);

    }

    @Override
    public String toString() {
        return getName() + " " + getFirstName()+ " "+ getId();
    }
}


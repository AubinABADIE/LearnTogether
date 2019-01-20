package Types;

import java.io.Serializable;

public class AdminType extends UserType implements Serializable {
/**
 * This class represents an admin in the database.
 * @author Aubin ABADIE
 */
    //role = "ADMIN"

    public AdminType(int id, String name, String firstName, String email, String birthDate, String role) {
        super(id, name, firstName, email, birthDate, role);

    }

    @Override
    public String toString() {
        return getName() + " " + getFirstName()+ " "+ getId();
    }
}


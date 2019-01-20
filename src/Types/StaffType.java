package Types;

import java.io.Serializable;

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

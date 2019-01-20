package Types;

import java.io.Serializable;

/**
 * This class represents a user in the database.
 * @author Aubin ABADIE
 * @author Yvan SANSON
 */
public class UserType implements Serializable {
    private int id;
    private String name;
    private String firstName;
    private String email;
    private String birthDate;
    private String role;

    public UserType(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserType(int id, String name, String firstName, String email, String birthDate, String role) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String isAdmin(){
        if (role.equalsIgnoreCase("ADMIN")) {
            return "YES";
        }
        else return "NO";
    }

    @Override
    public String toString() {
        return "Name: " + name + " " + firstName;
    }
}

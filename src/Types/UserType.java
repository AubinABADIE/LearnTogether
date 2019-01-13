package Types;

public class UserType {
    private int id;
    private String role;
    private String name;
    private String firstName;
    private String birthDate;
    
    public UserType(int id, String role) {
		this.id = id;
		this.role = role;
	}
    
	public UserType(int id, String role, String name, String firstName, String birthDate) {
		this.id = id;
		this.role = role;
		this.name = name;
		this.firstName = firstName;
		this.birthDate = birthDate;
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

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
    
}

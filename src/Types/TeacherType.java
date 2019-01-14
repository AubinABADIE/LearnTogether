package Types;

import java.util.ArrayList;

public class TeacherType extends UserType {
	
	private ArrayList<CourseType> courseList;

	public TeacherType(int id, String role, ArrayList<CourseType> courseList) {
		super(id, role);
	}

}

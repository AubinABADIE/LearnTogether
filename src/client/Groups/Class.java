package Groups;

import Users.Teacher;

public class Class {
	
	private String className;
	private Teacher referenceTeacher;
	private String descriptionClass;
	private Promotion promotion;
	
	public Class(String className, Teacher referenceTeacher, String descriptionClass, Promotion promotion) {
		super();
		this.className = className;
		this.referenceTeacher = referenceTeacher;
		this.descriptionClass = descriptionClass;
		this.promotion = promotion;
	}
	
}

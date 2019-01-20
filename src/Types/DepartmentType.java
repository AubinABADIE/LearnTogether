package Types;

import java.io.Serializable;

/**
 * This class represents a department in the database.
 * @author Audrey SAMSON
 */
public class DepartmentType implements Serializable {
    private int idDepartment;
    private String nameDep;
    private int refTeacherId;
    private String descriptionDep;

    public DepartmentType(int idDepartment, String nameDep, int refTeacherId, String descriptionDep){
        this.idDepartment=idDepartment;
        this.nameDep=nameDep;
        this.refTeacherId=refTeacherId;
        this.descriptionDep=descriptionDep;
    }

    public int getIdDepartment(){
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDep() {
        return nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    public int getRefTeacher() {
        return refTeacherId;
    }

    public void setRefTeacher(int refTeacherId) {
        this.refTeacherId = refTeacherId;
    }

    public String getDescriptionDep() {
        return descriptionDep;
    }

    public void setDescriptionDep(String descriptionDep) {
        this.descriptionDep = descriptionDep;
    }


    @Override
    public String toString() {
        return nameDep;
    }
}

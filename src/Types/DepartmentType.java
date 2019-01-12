package Types;

public class DepartmentType {
    private int idDeppartment;
    private String nameDep;
    private int refTeacherId;
    private String descriptionDep;

    public DepartmentType(int idDeppartment, String nameDep, int refTeacherId, String descriptionDep){
        this.idDeppartment=idDeppartment;
        this.nameDep=nameDep;
        this.refTeacherId=refTeacherId;
        this.descriptionDep=descriptionDep;
    }

    public int getIdDeppartment(){
        return idDeppartment;
    }

    public void setIdDeppartment(int idDeppartment) {
        this.idDeppartment = idDeppartment;
    }

    public String getNameDep() {
        return nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    public int getRefTeacherId() {
        return refTeacherId;
    }

    public void setRefTeacherId(int refTeacherId) {
        this.refTeacherId = refTeacherId;
    }

    public String getDescriptionDep() {
        return descriptionDep;
    }

    public void setDescriptionDep(String descriptionDep) {
        this.descriptionDep = descriptionDep;
    }
}

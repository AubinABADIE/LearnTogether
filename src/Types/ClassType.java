package Types;

public class ClassType {
    private int idClass;
    private String nameClass;
    private int refPromo;
    private String descriptionClass;

    public ClassType(int idClass, String nameClass, int refPromo,  String graduationClass){
        this.idClass=idClass;
        this.nameClass=nameClass;
        this.refPromo=refPromo;
        this.descriptionClass=descriptionClass;

    }


    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getRefDep() {
        return refPromo;
    }

    public void setRefDep(int refPromo) {
        this.refPromo = refPromo;
    }

    public String getDescriptionClass() {
        return descriptionClass;
    }

    public void setDescriptionClass(String descriptionClass) {
        this.descriptionClass = descriptionClass;
    }

}

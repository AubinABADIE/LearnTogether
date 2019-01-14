package Types;

public class PromotionType {
    private int idPromo;
    private String namePromo;
    private String graduationPromo;
    private int refDep;
    private String descriptionPromo;

    public PromotionType(int idPromo, String namePromo, int refDep, String descriptionPromo, String graduationPromo){
        this.idPromo=idPromo;
        this.namePromo=namePromo;
        this.graduationPromo=graduationPromo;
        this.refDep=refDep;
        this.descriptionPromo=descriptionPromo;

    }

    public String getGraduationPromo() {
        return graduationPromo;
    }

    public void setGraduationPromo(String graduationPromo) {
        this.graduationPromo = graduationPromo;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getNamePromo() {
        return namePromo;
    }

    public void setNamePromo(String namePromo) {
        this.namePromo = namePromo;
    }

    public int getRefDep() {
        return refDep;
    }

    public void setRefDep(int refDep) {
        this.refDep = refDep;
    }

    public String getDescriptionPromo() {
        return descriptionPromo;
    }

    public void setDescriptionPromo(String descriptionPromo) {
        this.descriptionPromo = descriptionPromo;
    }
}

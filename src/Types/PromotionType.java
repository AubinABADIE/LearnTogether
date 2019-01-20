package Types;

import java.io.Serializable;

public class PromotionType implements Serializable {
    private int idPromo;
    private String namePromo;
    private String descriptionPromo;
    private int graduationPromo;
    private int refDep;


    public PromotionType(int idPromo, String namePromo,  String descriptionPromo, int graduationPromo, int refDep){
        this.idPromo=idPromo;
        this.namePromo=namePromo;
        this.descriptionPromo=descriptionPromo;
        this.graduationPromo=graduationPromo;
        this.refDep=refDep;

    }

    public int getGraduationPromo() {
        return graduationPromo;
    }

    public void setGraduationPromo(int graduationPromo) {
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

    @Override
    public String toString() {
        return namePromo;
    }
}

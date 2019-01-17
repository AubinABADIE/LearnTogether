package Types;

import java.io.Serializable;

public class ClassType implements Serializable {
    private int idClass;
    private String className;
    private String classDescription;
    private int idPromotion;

    public ClassType(int idClass, String className, String classDescription,  int idPromotion){
        this.idClass=idClass;
        this.className=className;
        this.classDescription=classDescription;
        this.idPromotion=idPromotion;

    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String descClass) {
        this.classDescription = descClass;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }
    @Override
    public String toString() {
        return "Name: " + className;
    }
}

package client.Groups;


import client.CoreClient;

import java.io.IOException;

public class Class {
    private CoreClient coreClient;

    private String className;
    private String descClass;
    private int idPromotion;


    /**
     * Default constructor
     */
    public Class() {
    }

    public Class(CoreClient coreClient) {
        this.coreClient = coreClient;
    }


    public void getClasses() {
        try {
            coreClient.getConnection().sendToServer("#GETCLASS" );
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CoreClient getCoreClient() {
        return coreClient;
    }

    public void setCoreClient(CoreClient coreClient) {
        this.coreClient = coreClient;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescClass() {
        return descClass;
    }

    public void setDescClass(String descClass) {
        this.descClass = descClass;
    }

    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }
}

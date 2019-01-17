package client.Groups;


import client.CoreClient;

import java.io.IOException;

public class ClassServices {
    private CoreClient coreClient;


    public ClassServices(CoreClient coreClient) {
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
}

package client.Room;

import client.CoreClient;

import java.io.IOException;

public class RoomServices {
    private CoreClient client;

    public RoomServices(CoreClient coreClient){
        client = coreClient;
    }

    /**
     * This method creates String with all room attributes and send it to the server
     */
    public void handleCreateRoom (String name,int capacity, int building, boolean hasProjector, boolean hasComputer, String description){
        String objConstruct = name + "-/-" + capacity + "-/-" + building + "-/-" + hasProjector + "-/-" + hasComputer + "-/-" + description;

        try {
            client.getConnection().sendToServer("#CREATEROOM-/-" + objConstruct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

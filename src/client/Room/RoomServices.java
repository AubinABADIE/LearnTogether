package client.Room;

import client.CoreClient;

import java.io.IOException;

public class RoomServices {
    private CoreClient client;

    public RoomServices(CoreClient coreClient){
        client = coreClient;
    }

    /**
     *  This method creates String with all room attributes and send it to the server
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if there is a projeector in the room
     * @param hasComputer : if there are computers in the room
     * @param description : small description of the room
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

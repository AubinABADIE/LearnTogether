package client.Room;

import client.CoreClient;

import java.io.IOException;

public class RoomServices {
    private CoreClient client;

    public RoomServices(CoreClient coreClient){
        client = coreClient;
    }

    /**
     *  This method creates String with all room attributes and send it to the server to creat the room
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

    /**
     * This method create String with all room attributes and sed it to the server to update the room
     * @param id : room identifiant
     * @param name : room name
     * @param capacity : room capacity
     * @param building : room building number
     * @param hasProjector : if the room has a projector
     * @param hasComputer : if the room has computers
     * @param description : small description of the room 
     */
    public void handleUpdateRoom (int id, String name,int capacity, int building, boolean hasProjector, boolean hasComputer, String description){
        String objConstruct = id + "-/-" + name + "-/-" + capacity + "-/-" + building + "-/-" + hasProjector + "-/-" + hasComputer + "-/-" + description;

        try {
            client.getConnection().sendToServer("#UPDATEROOM-/-" + objConstruct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteRoom (int id){
        try {
            client.getConnection().sendToServer("#DELETEROOM-/-" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

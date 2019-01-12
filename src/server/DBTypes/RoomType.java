package server.DBTypes;

import java.io.Serializable;

public class RoomType implements Serializable {
    private int id;
    private String name;
    private int capacity;
    private int building;
    private boolean hasProjector;
    private boolean hasComputer;
    private String description;

    public RoomType(int id, String name, int capacity, int building, boolean hasProjector, boolean hasComputer, String description){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.building = building;
        this.hasProjector = hasProjector;
        this.hasComputer = hasComputer;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public boolean isHasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public boolean isHasComputer() {
        return hasComputer;
    }

    public void setHasComputer(boolean hasComputer) {
        this.hasComputer = hasComputer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Name: " + name;
    }
}

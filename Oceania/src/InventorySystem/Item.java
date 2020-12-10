package InventorySystem;

import javafx.scene.layout.Pane;

public class Item {

    private String name;
    private int size;
    private ItemType type;
    private Coords coords;
    public Pane pane;

    public Item(String name, int size, ItemType type, Coords coords, Pane pane) {
        this.name = name;
        this.size = size;
        this.type = type;
        this.coords = coords;
        this.pane = pane;
    }



    //Getters
    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public ItemType getType() { return type; }

    public Coords getCoords() {return coords; }

    //Setters
    public void setName(String name) { this.name = name; }

    public void setSize(int size) { this.size = size; }

    public void setType(ItemType type) { this.type = type; }

    public void setCoords(Coords coords) { this.coords = coords; }
}

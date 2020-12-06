package InventorySystem;

import javafx.scene.layout.Pane;

public class Item {

    private String name;
    private int size;
    private ItemType type;
    private Pane pane;

    public Item (String name, int size, Pane pane)
    {
        this.name = name;
        this.size = size;
        this.type = ItemType.UNSPECIFIED;
    }
    public Item(String name, int size, ItemType type) {
        this.name = name;
        this.size = size;
        this.type = type;
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

    //Setters
    public void setName(String name) { this.name = name; }

    public void setSize(int size) { this.size = size; }

    public void setType(ItemType type) { this.type = type; }
}

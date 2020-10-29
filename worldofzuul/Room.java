//package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Task> tasksInRoom;
    private HashMap<String, Item> itemsInRoom;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        tasksInRoom = new HashMap<String, Task>();
        itemsInRoom = new HashMap<String, Item>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + "\n" + getTasksInRoom() + "\n" + getItemInRoom();
    }

    public String getItemInRoom()
    {
        String temp = "Items:";
        Set<String> items = itemsInRoom.keySet();
        for (String itemsInRoom: items)
        {
            temp += " " + itemsInRoom;
        }
        return temp;
    }


    public String getTasksInRoom()
    {
        String temp = "Tasks:";
        Set<String> tasks = tasksInRoom.keySet();
        for(String tasksInRoom : tasks)
        {
            temp += " " + tasksInRoom;
        }
        return temp;
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public void addItem(String itemName, Item item)
    {
        itemsInRoom.put(itemName, item);
    }

    public void addTask(String taskName,Task task)
    {
        tasksInRoom.put(taskName, task);
    }
}


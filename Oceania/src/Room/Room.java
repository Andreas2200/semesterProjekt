package Room;

import InventorySystem.*;
import TaskSystem.*;
import NPC.*;
import javafx.scene.image.Image;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashMap;



public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Task> tasksInRoom;
    private HashMap<String, Item> itemsInRoom;
    private HashMap<String, NPC> NPCsInRoom;
    private int[][] boundaries;
    private int[][] exitLocations;
    private int exitCounter = 0;
    private Image roomImage;

    public Room(String description, int exitLocations)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        tasksInRoom = new HashMap<String, Task>();
        itemsInRoom = new HashMap<String, Item>();
        NPCsInRoom = new HashMap<String, NPC>();
        this.exitLocations = new int[exitLocations*2][2];
        boundaries = new int[37][2];
        setBoundaries();
    }

    private void setBoundaries()
    {
        int x = 0;
        int y = 0;

        for (int i = 0; i < boundaries.length; i++)
        {
            if(i < 9 && x < 1728 && y <= 0)
            {
                for (int j = 0; j < boundaries[i].length; j++)
                {
                    if(j == 0)
                    {
                        boundaries[i][j] = x;
                    }
                    else if(j == 1)
                    {
                        boundaries[i][j] = y;
                    }
                }
                x += 192;
            }
            if(i > 8 && i < 18 && x == 1728 && y < 972)
            {
                for (int j = 0; j < boundaries[i].length; j++)
                {
                    if(j == 0)
                    {
                        boundaries[i][j] = x;
                    }
                    else if(j == 1)
                    {
                        boundaries[i][j] = y;
                    }
                }
                y += 108;
            }
            if(i > 18 && i < 28 && x >= 0 && y == 972)
            {
                for (int j = 0; j < boundaries[i].length; j++)
                {
                    if(j == 0)
                    {
                        boundaries[i][j] = x;
                    }
                    else if(j == 1)
                    {
                        boundaries[i][j] = y;
                    }
                }
                x -= 192;
            }
            if(i >= 28 && x == 0 && y >= 0)
            {
                for (int j = 0; j < boundaries[i].length; j++)
                {
                    if(j == 0)
                    {
                        boundaries[i][j] = x;
                    }
                    else if(j == 1)
                    {
                        boundaries[i][j] = y;
                    }
                }
                y -= 108;
            }
        }
    }

    public String getShortDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + getTasksInRoom() + getItemStringsInRoom() + getNPCInRoom();
    }

    public Item getItem(String name){
        Item item = itemsInRoom.get(name);
        return item;
    }

    public void removeItem(String name){
        itemsInRoom.remove(name);

    }

    public String getItemStringsInRoom()
    {
        String temp = " ";
        if(isItemsInRoom())
        {
            temp = "\n" + "Items:";
            Set<String> items = itemsInRoom.keySet();
            for (String itemsInRoom: items)
            {
                temp += " " + itemsInRoom;
            }
        }
        return temp;
    }

    public boolean isItemsInRoom()
    {
        if(itemsInRoom.size() >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getTasksInRoom()
    {
        String temp = " ";
        if(isTasksInRoom())
        {
            temp = "\n" + "Tasks in the room:";
            Set<String> tasks = tasksInRoom.keySet();
            for(String tasksInRoom : tasks)
            {
                temp += " " + tasksInRoom;
            }
        }
        return temp;
    }

    public void removeTaskFromRoom(String task)
    {
        tasksInRoom.remove(task);
    }

    public void setRoomImage(String pathName)
    {
        File file = new File(pathName);
        roomImage = new Image(file.toURI().toString());
    }

    public void setRoomExit(int x, int y)
    {
        exitLocations[exitCounter][0] = x;
        exitLocations[exitCounter][1] = y;
        moveBoundary(x,y);
    }

    private void moveBoundary(int x, int y)
    {
        for (int i = 0; i < boundaries.length; i++)
        {
            if(boundaries[i][0] == x)
            {
                if(boundaries[i][1] == y)
                {
                    if(x == 0)
                    {
                        boundaries[i][0] -= 192;
                    }
                    else if(x == 1728)
                    {
                        boundaries[i][0] += 192;
                    }
                    else if(y == 0)
                    {
                        boundaries[i][1] -= 108;
                    }
                    else if(y == 972)
                    {
                        boundaries[i][1] += 108;
                    }
                }
            }
        }
    }

    public boolean isWall(int x, int y)
    {
        boolean result = false;

        for (int i = 0; i < boundaries.length; i++)
        {
            if(boundaries[i][0] == x)
            {
                if(boundaries[i][1] == y)
                {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean isTasksInRoom()
    {
        if(tasksInRoom.size() >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getNPCInRoom()
    {
        String temp = " ";
        if(isNPCInRoom())
        {
            temp = "\n";
            Set<String> NPCs = NPCsInRoom.keySet();
            for (String NPCsInRoom: NPCs)
            {
                temp += " " + NPCsInRoom + " is in the Room.";
            }

        }
        return temp;
    }

    public boolean isNPCInRoom()
    {
        if(NPCsInRoom.size() >= 1)
        {
            return true;
        }
        else
        {
            return false;
        }
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

    public Task getTask(String name)
    {
        return tasksInRoom.get(name);
    }

    public void addItem(String itemName, Item item)
    {
        itemsInRoom.put(itemName, item);
    }

    public void addTask(String taskName,Task task)
    {
        tasksInRoom.put(taskName, task);
    }

    public void addNPC(String npcName, NPC npc)
    {
        NPCsInRoom.put(npcName, npc);
    }
}


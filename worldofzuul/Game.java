//package worldofzuul;

import javax.swing.*;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private TaskSystem ts;
    private PointSystem ps;
    private Inventory inv;

    public Game() 
    {
        ts = new TaskSystem(10);
        createRooms();
        parser = new Parser();
        ps = new PointSystem();
        inv = new Inventory(10);
    }

    private void createRooms()
    {
        Item apple, bottle, freezer;

        apple = new Item("Apple", 1, ItemType.BIOLOGICAL);
        freezer = new Item("Freezer", 10, ItemType.METAL);
        bottle = new Item("Bottle", 1, ItemType.PLASTIC);

        Room town_square, garbage_disposal, shopping_street, fish_store, harbour_east, harbour_west, beach, pier_1, pier_2, reef;
      
        town_square = new Room("in the Town Square" );
        garbage_disposal = new Room("at the Garbage disposal");
        shopping_street = new Room("in the shopping street");
        fish_store = new Room("in the fish store"
                + "\n" + "Hey Andersen, i am so glad you could make it down here. We need your help."
                + "\n" + "Subsidies, or support provided to the fishing industry to offset the costs of doing business,"
                + "\n" + " are another key driver of overfishing. "
                + "\n" + "Subsidies can lead to overcapacity of fishing vessels and skewing of production "
                + "\n" + "costs so that fishing operations continue when they would otherwise not make economic sense. "
                + "\n" + "Today’s worldwide fishing fleet is estimated to be up to two-and-a-half times "
                + "\n" + "the capacity needed to catch what we actually need. "
                + "\n" + "The United Nations 2030 Agenda for Sustainable Development has called for an end to harmful subsidies."
                + "\n" + " We need your help. We have been given fishermen subsidies and need it back, so we can stop overfishing ");
        harbour_east = new Room("at Harbour east");
        harbour_west = new Room("at Harbour west");
        beach = new Room("on the Beach");
        pier_1 = new Room("at Pier 1");
        pier_2 = new Room("at Pier 2");
        reef = new Room("on the reef");

        town_square.setExit("east", shopping_street);
        town_square.setExit("south", harbour_west);
        town_square.setExit("west", garbage_disposal);
        town_square.addItem("Apple", apple);

        garbage_disposal.setExit("east", town_square);

        shopping_street.setExit("west", town_square);
        shopping_street.setExit("east", fish_store);
        shopping_street.setExit("south", harbour_east);

        fish_store.setExit("west", shopping_street);
        fish_store.addItem("Freezer", freezer);

        harbour_west.setExit("north", town_square);
        harbour_west.setExit("east", harbour_east);
        harbour_west.setExit("south", pier_1);
        harbour_west.addTask(ts.mainTask.getTaskName(), ts.mainTask);

        harbour_east.setExit("north", shopping_street);
        harbour_east.setExit("east", beach);
        harbour_east.setExit("south", pier_2);
        harbour_east.setExit("west", harbour_west);

        beach.setExit("west", harbour_east);
        beach.addItem("Bottle", bottle);

        pier_1.setExit("north", harbour_west);
        pier_1.addTask(ts.testTrack2.getTaskName(),ts.testTrack2);

        pier_2.setExit("north", harbour_east);
        pier_2.setExit("east", reef);

        //assign Task Steps
        ts.assignStepRoom(ts.mainTask, 0, fish_store);
        ts.assignStepRoom(ts.mainTask, 1, pier_2);
        ts.assignStepRoom(ts.mainTask, 2, fish_store);
        ts.assignStepRoom(ts.mainTask,3,harbour_west);

        ts.assignStepRoom(ts.testTrack2,0, beach);
        ts.assignStepRoom(ts.testTrack2, 1, pier_1);
        ts.assignBadStepRoom(ts.testTrack2, 0, pier_2);

        currentRoom = town_square;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            checkTasks();
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void checkTasks()
    {
        if(ts.getActiveTask() != null)
        {
            Task activeTask = ts.getActiveTask();
            if(activeTask.getRoomStep() == currentRoom) //Checks if a task step requires you to be in the current room, and if true, then the task step is marked as completed
            {
                activeTask.completedStep();
            }
            else
            {
                try
                {
                    activeTask.completedBadStep();
                }
                catch (NullPointerException e)
                {
                }
            }
            if(activeTask.isCompleted())
            {
                System.out.println("Congratulations task: " + activeTask.getTaskName() + " has been completed!");
                ts.moveCompletedTask(activeTask);
                ps.addPoint(activeTask.getRewardPoints());
            }
            else if(activeTask.isCompletedBad())
            {
                System.out.println("Wow really? You just made the oceans health worse. Good job!");
                ts.moveCompletedTask(activeTask);
                ps.addPoint(activeTask.getRewardPoints());
            }
        }
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("It has been a very hard night.");
        System.out.println("You wake up after a night full of drinking. You do not know where you are.");
        System.out.println("A goverment official comes up to you,");
        System.out.println( "\"hey you are finally awake, you are the famous marine biologist Andersen right? We need your help. The world is in trouble, but you can help us save it.");
        System.out.println("Andersen \" No it is done, the world is going to end. Leave me alone.");
        System.out.println("Goverment official " + "You know that isn't true, but do me a favor go down to the habour, there is someone who needs your help. We can still make it");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        switch (commandWord) {
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case SHOW -> show(command);
            case ACCEPT -> accept(command);
            case SPEAK -> speak(command);
            case TAKE -> take(command);
            case DROP -> drop(command);
            case INV -> printInventory();
            case QUIT -> wantToQuit = quit(command);
            case INSPECT -> inspect(command);
        }

        return wantToQuit;
    }

    private void inspect(Command command) {
        if(command.hasSecondWord() == false){
            return;
        }
        String item_name = command.getSecondWord();
        Item[] inventory = inv.getInventory();
        if (inventory.length == 0) {
            System.out.println("Inventory is empty");
            return;
        }
        for(Item item : inventory){
            if(item.getName().equals(item_name)) {
                System.out.println("Item " + item.getName() + " is of size " + item.getSize() + " and type " + item.getType().toString());
                return;
            }
        }
        System.out.println("Item not found");
    }

    private void take(Command command){
        if(command.hasSecondWord() == false){
            return;
        }
        String secondWord = command.getSecondWord();
        Item item = currentRoom.getItem(secondWord);
        if (item == null){
            System.out.println("Item not found");
            return;
        }


        if (inv.addItem(item)){
            System.out.println("Took" + " " + item.getName());
            currentRoom.removeItem(secondWord);
        } else {
            System.out.println("Not enough space in inventory");
        }
    }

    private void drop(Command command){
        if(command.hasSecondWord() == false){
            return;
        }
        String secondWord = command.getSecondWord();
        Item item = inv.removeItem(secondWord);
        if (item == null){
            System.out.println("Item not found");
            return;
        }
        currentRoom.addItem(secondWord, item);
        System.out.println("Dropped" + " " + item.getName());
    }

    private void printInventory(){
        Item[] inventory = inv.getInventory();
        if (inventory.length == 0){
            System.out.println("Inventory is empty");
            return;
        }
        for(Item item : inventory){
            System.out.println(item.getName());
        }
        System.out.println("Total weight" + " " + inv.getTotalWeight());
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands(); // Udskriver alle tilgængelige kommandoer for input, på nær UNKNOWN, da den bliver sorteret fra.
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void speak(Command command)
    {
        System.out.println("Under construction");
    }

    private void show(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Show what?");
            return;
        }

        String showing = command.getSecondWord();

        switch (showing)
        {
            case "point":
                System.out.println(ps.toString());
                break;

            case "task":
                String task = command.getThirdWord();
                if(!command.hasThirdWord())
                {
                    System.out.println(ts.toString());
                    break;
                }
                else if(ts.getActiveTaskCounter() > 0)
                {
                    if(ts.isATask(task))
                    {
                        Task temp = ts.getTask(task);
                        System.out.println(ts.showTaskStep(temp));
                        break;
                    }
                }
            case "completed":
                System.out.println("Completed tasks: " + ts.getCompletedTask());
                break;
            default:
                System.out.println(showing + " is not a valid command for show");
        }
    }

    private void accept(Command command)
    {
        if(!command.hasSecondWord())
        {
            System.out.println("Accept what?");
            return;
        }
        String task = command.getSecondWord();
        System.out.println("Accepting " + task);

        if(currentRoom.getTask(task) != null)
        {
            Task temp = currentRoom.getTask(task);
            ts.addTask(temp);
            currentRoom.removeTaskFromRoom(task);
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}

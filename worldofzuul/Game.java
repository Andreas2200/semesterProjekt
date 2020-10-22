//package worldofzuul;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        

    public Game() 
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        Room town_square, garbage_disposal, shopping_street, fish_store, harbour_east, harbour_west, beach, pier_1, pier_2, reef;
      
        town_square = new Room("in the Town Square");
        garbage_disposal = new Room("at the Garbage disposal");
        shopping_street = new Room("in the shopping street");
        fish_store = new Room("in the fish store");
        harbour_east = new Room("at Harbour east");
        harbour_west = new Room("at Harbour west");
        beach = new Room("on the Beach");
        pier_1 = new Room("at Pier 1");
        pier_2 = new Room("at pier 2");
        reef = new Room("on the reef)");

        town_square.setExit("west", garbage_disposal);
        town_square.setExit("east", shopping_street);
        town_square.setExit("south", harbour_west);

        garbage_disposal.setExit("east", town_square);

        shopping_street.setExit("west", town_square);
        shopping_street.setExit("east", fish_store);
        shopping_street.setExit("south", harbour_east);

        fish_store.setExit("west", shopping_street);

        harbour_west.setExit("north", town_square);
        harbour_west.setExit("east", harbour_east);
        harbour_west.setExit("south", pier_1);

        harbour_east.setExit("north", shopping_street);
        harbour_east.setExit("east", beach);
        harbour_east.setExit("south", pier_2);
        harbour_east.setExit("west", harbour_west);

        beach.setExit("west", harbour_east);

        pier_1.setExit("north", harbour_west);

        pier_2.setExit("north", harbour_east);
        pier_2.setExit("east", reef);

        currentRoom = town_square;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
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

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
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

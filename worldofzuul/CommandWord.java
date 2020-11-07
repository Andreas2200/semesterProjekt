//package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"),UNKNOWN("?"),
    SHOW("show"), ACCEPT("accept"), SPEAK("speak"), TAKE("take"),
    DROP("drop"), INV("inventory"), INSPECT("inspect");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}

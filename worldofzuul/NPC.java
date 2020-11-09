public class NPC {

    private String name;
    private boolean forced;


    public NPC (String name, boolean forced)
    {
        this.name = name;
        this.forced = forced;
    }

    public NPC()
    {}

    public String victor()
    {
        String temp = "Hey Andersen, i am so glad you could make it down here. We need your help."
                + "\n" + "Subsidies, or support provided to the fishing industry to offset the costs of doing business,"
                + "\n" + " are another key driver of overfishing. "
                + "\n" + "Subsidies can lead to overcapacity of fishing vessels and skewing of production "
                + "\n" + "costs so that fishing operations continue when they would otherwise not make economic sense. "
                + "\n" + "Today’s worldwide fishing fleet is estimated to be up to two-and-a-half times "
                + "\n" + "the capacity needed to catch what we actually need. "
                + "\n" + "The United Nations 2030 Agenda for Sustainable Development has called for an end to harmful subsidies."
                + "\n" + " We need your help. We have been given fishermen subsidies and need it back, so we can stop overfishing";
        return temp;
    }

    public String sigurd()
    {
        String temp = "Golly, you're one pretty lady/man or everything in between or around or what ever.."
                + "\n" + " you know, doesn't matter, I don't judge either way - oh gosh, now I'm just embarrassed"
                + "\n" + "here; take my (fishing) rod";
        return temp;
    }

    public String kenneth() {
        String temp = "Hi";
        return temp;
    }
    public boolean getForced() { return forced; }

    public String getName() { return name; }
}

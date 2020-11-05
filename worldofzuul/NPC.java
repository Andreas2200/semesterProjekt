public class NPC {

    private String name;
    private boolean forced;

    public NPC (String name, boolean forced)
    {
        this.name = name;
        this.forced = forced;
    }

    public String victor()
    {
        String temp = "hi there";
        return temp;
    }

    public String sigurd()
    {
        String temp = "hello fuckhead";
        return temp;
    }

    public boolean getForced() { return forced; }

    public String getName() { return name; }
}

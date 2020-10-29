public class Item {

    private String name;
    private int size;

    Item (String name, int size)
    {
        this.name = name;
        this.size = size;
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

    //Setters
    public void setName(String name) { this.name = name; }

    public void setSize(int size) { this.size = size; }
}

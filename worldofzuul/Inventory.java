import java.util.ArrayList;

public class Inventory {
    
    private int maxSize = 10;
    private int currentSize = 0;
    
    private ArrayList<Item> items = new ArrayList<Item>(maxSize);
    //maxSize sættes til 10, og der laves en arraylist istedet for et array.
    //Arraylist manager det normale array for os.

    public void addItem (Item item){
        if (currentSize + item.getSize() > maxSize){
            return;
    //Når størrelsen af items>mængden af maxitems returneres ingenting(Går ud af metoden).
        }
        
        currentSize += item.getSize();
        items.add(item);
    //Vi laver en additem metode, som kan tilføje items til inventaret.
    }

    public void removeItem (int index){
        Item removedItem = items.remove(index);
        currentSize -= removedItem.getSize();
    //Vi laver en removeitem metode, som kan fjerne items fra inventaret.
    }

    public Item getItem (int index){
        return items.get(index);
    //Tager et item fra vores inventar og returnerer det.
    }
}
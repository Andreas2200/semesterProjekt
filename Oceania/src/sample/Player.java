package sample;

import InventorySystem.Coords;
import InventorySystem.Inventory;
import InventorySystem.Item;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.*;

public class Player
{
    private Label playerIcon;
    private ImageView playerImage;
    private double xPos;
    private double yPos;
    private Inventory playerInventory = new Inventory(10);

    public Player(Label playerLabel, ImageView image)
    {
        playerImage = image;
        playerIcon = playerLabel;
        xPos = playerLabel.getLayoutX();
        yPos = playerLabel.getLayoutY();
    }

    public void dropItem(int index){
        Item item = playerInventory.removeItem(index);
        item.setCoords(new Coords((int)xPos, (int)yPos));
        item.enable();
    }

    public void setPlayerImage(Image image)
    {
        playerImage.setImage(image);
    }

    public void setPlayerX(double x)
    {
        xPos = x;
        playerIcon.setLayoutX(xPos);
    }

    public void setPlayerY(double y)
    {
        yPos = y;
        playerIcon.setLayoutY(yPos);
    }

    public double getPlayerX()
    {
        return xPos;
    }

    public double getPlayerY()
    {
        return yPos;
    }

    public void getPlayerIcon()
    {
        System.out.println(playerIcon.getBackground());
    }

    public Inventory getPlayerInventory(){return playerInventory;}
}

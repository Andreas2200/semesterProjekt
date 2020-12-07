package sample;

import javafx.scene.control.Label;

public class Player
{
    public Label playerIcon;
    private double xPos;
    private double yPos;

    public Player(Label playerLabel)
    {
        playerIcon = playerLabel;
        xPos = playerLabel.getLayoutX();
        yPos = playerLabel.getLayoutY();
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
}

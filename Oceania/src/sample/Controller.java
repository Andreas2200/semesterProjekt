package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import Room.*;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
    public Label myLabel;
    public Button myButton;
    public AnchorPane myAnchorPane;
    public ImageView backgroundImage;
    public double width,height;
    public Image woods,island;
    public Pane pane1;
    private Room town_square, shopping_street, currentRoom;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createRooms();
        width = backgroundImage.getFitWidth();
        height = backgroundImage.getFitHeight();
        currentRoom = town_square;
    }

    private void createRooms()
    {
        town_square = new Room("at the town square",3);
        shopping_street = new Room("at the shopping street",3);

        town_square.setRoomImage("island.png");
        town_square.setRoomExit(768,972);
        town_square.setRoomExit(960,972);

        shopping_street.setRoomImage("woods.jpg");
    }

    public void changeText(ActionEvent actionEvent) {
        myLabel.setText("TESTY McTest");
    }

    public void handle(javafx.scene.input.KeyEvent keyEvent) {
        switch (keyEvent.getCode())
        {
            case W: moveUp(currentRoom);   break;
            case A: moveLeft(currentRoom); break;
            case S: moveDown(currentRoom); break;
            case D: moveRight(currentRoom);break;
        }
    }

    public void moveUp(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x,y- (int) height/10))
        {
            myLabel.setLayoutY(myLabel.getLayoutY() - height/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }

    }
    public void moveDown(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x,y + (int) height/10))
        {
            myLabel.setLayoutY(myLabel.getLayoutY() + height/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }
    }
    public void moveLeft(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x - (int) width/10, y))
        {
            myLabel.setLayoutX(myLabel.getLayoutX() - width/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }
    }
    public void moveRight(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x + (int) width/10, y))
        {
            myLabel.setLayoutX(myLabel.getLayoutX() + width/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }
    }

    public void changeColor(MouseEvent mouseEvent)
    {
        Pane tempPane = (Pane) mouseEvent.getTarget();
        tempPane.setStyle("-fx-background-color: #ff0000;");
    }

    public void changeColorNormal(MouseEvent mouseEvent)
    {
       Pane tempPane = (Pane) mouseEvent.getTarget();
       tempPane.setStyle("-fx-background-color: #ffffff;");
    }
}

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
import javafx.scene.text.TextFlow;

public class Controller implements Initializable {
    public double width,height;
    public boolean toggleHelpPane;

    public Label myLabel;
    public Button myButton;
    public AnchorPane myAnchorPane;
    public ImageView backgroundImage;
    public Pane pane1;
    public Pane helpPane;
    public Label helpPaneText;
    private Room currentRoom;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        createRooms();
        createHelpPane();
        width = backgroundImage.getFitWidth();
        height = backgroundImage.getFitHeight();
    }

    private void changeRoom(Room room)
    {
        currentRoom = room;
        backgroundImage.setImage(currentRoom.getRoomImage());
        int x = (int)myLabel.getLayoutX();
        int y = (int)myLabel.getLayoutY();
        if(myLabel.getLayoutX() == 0)
        {
            myLabel.setLayoutX(width-(2*width/10));
            System.out.println("Set x:" + myLabel.getLayoutX());
        }
        else if(myLabel.getLayoutX() == width-(width/10))
        {
            myLabel.setLayoutX(width/10);
            System.out.println("Set x:" + myLabel.getLayoutX());
        }
        else if(myLabel.getLayoutY() == 0)
        {
            myLabel.setLayoutY(height - (2*height/10));
            System.out.println("Set y:" + myLabel.getLayoutY());
        }
        else if(myLabel.getLayoutY() == height-(height/10))
        {
            myLabel.setLayoutY(height/10);
            System.out.println("Set y:" + myLabel.getLayoutY());
        }
    }

    private void createRooms()
    {
        Room town_square, shopping_street, test1, test2;

        town_square = new Room(3);
        shopping_street = new Room(1);
        test1 = new Room(1);
        test2 = new Room(1);

        town_square.setRoomImage("island.png");
        town_square.setRoomExit(768,972);
        town_square.setRoomExit(960,972);
        town_square.setRoomExit(0,432);
        town_square.setRoomExit(0,540);
        town_square.setRoomExit(1728,432);
        town_square.setRoomExit(1728,540);
        town_square.setRoomNeighbour(0,shopping_street);
        town_square.setRoomNeighbour(1,shopping_street);
        town_square.setRoomNeighbour(2,test1);
        town_square.setRoomNeighbour(3,test1);
        town_square.setRoomNeighbour(4,test2);
        town_square.setRoomNeighbour(5,test2);

        shopping_street.setRoomImage("woods.jpg");
        shopping_street.setRoomExit(768,0);
        shopping_street.setRoomExit(960,0);
        shopping_street.setRoomNeighbour(0,town_square);
        shopping_street.setRoomNeighbour(1,town_square);

        test1.setRoomExit(1728,432);
        test1.setRoomExit(1728,540);
        test1.setRoomNeighbour(0,town_square);
        test1.setRoomNeighbour(1,town_square);

        test2.setRoomExit(0,432);
        test2.setRoomExit(0, 540);
        test2.setRoomNeighbour(0,town_square);
        test2.setRoomNeighbour(1,town_square);

        currentRoom = town_square;
    }

    private void createHelpPane()
    {
        String helpPaneTextFieldText = "So you need help with the controls, huh?" + "\n\n" +
                "To see a map of the game, simply press 'M'" + "\n\n" +
                "To walk between rooms, use 'WASD'" + "\n\n" +
                "To accept tasks, simply go up to an NPC and press 'T' to talk with them and see their tasks" + "\n\n" +
                "To show which tasks you currently have, press 'L' to view your Task Log" + "\n\n" +
                "To pick up an item, walk over the tile with the item on it and press 'U'" + "\n\n" +
                "To view your inventory, press 'I'" + "\n\n" +
                "To inspect an item, hover your mouse over the item in your inventory" + "\n\n" +
                "To drop an item, press the red cross on the item in your inventory" + "\n\n" +
                "To hide/show this help window, press 'H'" + "\n\n" +
                "To quit the game, press 'ESC'";
        helpPaneText.setText(helpPaneTextFieldText);
        toggleHelpPane = true;
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
            case H: help(); break;
            case ESCAPE:
        }
    }

    public void moveUp(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x,y - (int) height/10))
        {
            myLabel.setLayoutY(myLabel.getLayoutY() - height/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }
        if(currentRoom.isExit(x,(int)myLabel.getLayoutY()))
        {
            System.out.println("You are on an exit");
            int exitNumber = currentRoom.getExitNumber((int)myLabel.getLayoutX(),(int)myLabel.getLayoutY());
            Room nextRoom = currentRoom.getRoomFromExitNumber(exitNumber);
            changeRoom(nextRoom);
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
        if(currentRoom.isExit(x,(int)myLabel.getLayoutY()))
        {
            System.out.println("You are on an exit");
            int exitNumber = currentRoom.getExitNumber((int)myLabel.getLayoutX(),(int)myLabel.getLayoutY());
            Room nextRoom = currentRoom.getRoomFromExitNumber(exitNumber);
            changeRoom(nextRoom);
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
        if(currentRoom.isExit((int)myLabel.getLayoutX(),y))
        {
            System.out.println("You are on an exit");
            int exitNumber = currentRoom.getExitNumber((int)myLabel.getLayoutX(),(int)myLabel.getLayoutY());
            Room nextRoom = currentRoom.getRoomFromExitNumber(exitNumber);
            changeRoom(nextRoom);
        }
    }
    public void moveRight(Room currentRoom)
    {
        int x = (int) myLabel.getLayoutX();
        int y = (int) myLabel.getLayoutY();
        if(!currentRoom.isWall(x + (int) width/10, y)) //This line checks if we are trying to move into a boundary, and only runs the codeblock if we are not entering a boundary
        {
            myLabel.setLayoutX(myLabel.getLayoutX() + width/10);
            System.out.println(myLabel.getLayoutX() + ", " + myLabel.getLayoutY());
        }
        if(currentRoom.isExit((int)myLabel.getLayoutX(),y)) //This line checks if we are moving into an active exit and if so we change the room to the room corresponding with the exit
        {
            System.out.println("You are on an exit");
            int exitNumber = currentRoom.getExitNumber((int)myLabel.getLayoutX(),(int)myLabel.getLayoutY());
            Room nextRoom = currentRoom.getRoomFromExitNumber(exitNumber);
            changeRoom(nextRoom);
        }
    }

    private void help()
    {
        toggleHelpPane = !toggleHelpPane;
        helpPane.setVisible(toggleHelpPane);
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

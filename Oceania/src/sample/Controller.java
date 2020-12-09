package sample;

import Pollution.Pollution;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import Room.*;
import MusicPlayer.PlayMusic;
import javafx.scene.layout.Pane;

public class Controller implements Initializable {
    public double width,height;
    public boolean toggleHelpPane;
    public boolean toggleMapPane;
    public ProgressBar progressbar;
    private String musicFile1 = "MusicFileVictor.wav";
    private String musicFile2 = "gameMusic.wav";

    public Label myLabel;
    public Button myButton;
    public AnchorPane myAnchorPane;
    public ImageView backgroundImage;
    public Pane pane1;
    public Pane helpPane;
    public Pane mapPane;
    public Label helpPaneText;
    private Room currentRoom;
    private Room town_square,harbor_east, harbor_west, shopping_street, fish_store, garbage_disposal, beach, pier_1, pier_2;
    private PlayMusic musicPlayer;
    private Pollution ps;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        musicPlayer = new PlayMusic(musicFile1, musicFile2);
        changeMusic();
        createRooms();
        createHelpPane();
        width = backgroundImage.getFitWidth();
        height = backgroundImage.getFitHeight();
        ps = new Pollution(50);
        progressbar.setProgress(ps.getPollution()/100);
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

        //progressbar.setProgress(ps.getPoint()/100);

        changeMusic();
    }

    private void changeMusic()
    {
        if(currentRoom == town_square)
        {
            musicPlayer.stopMusicFile2();
            musicPlayer.startMusicFile1();
        }
        else
        {
            if(!musicPlayer.isPlaying2())
            {
                musicPlayer.stopMusicFile1();
                musicPlayer.startMusicFile2();
            }
        }
    }

    private void createRooms()
    {
        town_square = new Room(3);
        harbor_east = new Room(8);
        harbor_west = new Room(4);
        shopping_street = new Room(4);
        fish_store = new Room(2);
        garbage_disposal = new Room(1);
        beach = new Room(2);
        pier_1 = new Room(2);
        pier_2 = new Room(2);


        town_square.setRoomImage("town_square.png");
        town_square.setRoomExit(768,972);
        town_square.setRoomExit(960,972);
        town_square.setRoomExit(0,432);
        town_square.setRoomExit(0,540);
        town_square.setRoomExit(1728,432);
        town_square.setRoomExit(1728,540);
        town_square.setRoomNeighbour(0,harbor_west);
        town_square.setRoomNeighbour(1,harbor_west);
        town_square.setRoomNeighbour(2,garbage_disposal);
        town_square.setRoomNeighbour(3,garbage_disposal);
        town_square.setRoomNeighbour(4, shopping_street);
        town_square.setRoomNeighbour(5, shopping_street);
        town_square.addBoundary(192,324);
        town_square.addBoundary(384,324);
        town_square.addBoundary(576,216);
        town_square.addBoundary(768,216);
        town_square.addBoundary(768,108);
        town_square.addBoundary(1152,108);
        town_square.addBoundary(1152,216);
        town_square.addBoundary(1344,216);
        town_square.addBoundary(1536,216);
        town_square.addBoundary(576,864);
        town_square.addBoundary(384,864);
        town_square.addBoundary(384,756);
        town_square.addBoundary(384,648);
        town_square.addBoundary(192,648);
        
        harbor_west.setRoomImage("HarborWest.png");
        harbor_west.setRoomExit(768,0);
        harbor_west.setRoomExit(960,0);
        harbor_west.setRoomExit(1728,108);
        harbor_west.setRoomExit(1728,216);
        harbor_west.setRoomExit(1728,324);
        harbor_west.setRoomExit(1728,432);
        harbor_west.setRoomExit(768,972);
        harbor_west.setRoomExit(960,972);
        harbor_west.setRoomNeighbour(0,town_square);
        harbor_west.setRoomNeighbour(1,town_square);
        harbor_west.setRoomNeighbour(2,harbor_east);
        harbor_west.setRoomNeighbour(3,harbor_east);
        harbor_west.setRoomNeighbour(4,harbor_east);
        harbor_west.setRoomNeighbour(5,harbor_east);
        harbor_west.setRoomNeighbour(6,pier_1);
        harbor_west.setRoomNeighbour(7,pier_1);
        harbor_west.addBoundary(576,540);
        harbor_west.addBoundary(384,540);
        harbor_west.addBoundary(192,540);
        harbor_west.addBoundary(576,432);
        harbor_west.addBoundary(384,432);
        harbor_west.addBoundary(192,432);
        harbor_west.addBoundary(384,324);
        harbor_west.addBoundary(384,216);
        harbor_west.addBoundary(192,864);
        harbor_west.addBoundary(1536,756);

        harbor_east.setRoomImage("HarborEast.png");
        harbor_east.setRoomExit(0,108);
        harbor_east.setRoomExit(0,216);
        harbor_east.setRoomExit(0,324);
        harbor_east.setRoomExit(0,432);
        harbor_east.setRoomExit(768,0);
        harbor_east.setRoomExit(960,0);
        harbor_east.setRoomExit(1728,216);
        harbor_east.setRoomExit(1728,324);
        harbor_east.setRoomExit(1728,432);
        harbor_east.setRoomExit(1728,540);
        harbor_east.setRoomExit(768,972);
        harbor_east.setRoomExit(960,972);
        harbor_east.setRoomNeighbour(0,harbor_west);
        harbor_east.setRoomNeighbour(1,harbor_west);
        harbor_east.setRoomNeighbour(2,harbor_west);
        harbor_east.setRoomNeighbour(3,harbor_west);
        harbor_east.setRoomNeighbour(4,shopping_street);
        harbor_east.setRoomNeighbour(5,shopping_street);
        harbor_east.setRoomNeighbour(6,beach);
        harbor_east.setRoomNeighbour(7,beach);
        harbor_east.setRoomNeighbour(8,beach);
        harbor_east.setRoomNeighbour(9,beach);
        harbor_east.setRoomNeighbour(10,pier_2);
        harbor_east.setRoomNeighbour(11,pier_2);
        harbor_east.addBoundary(192,540);
        harbor_east.addBoundary(192,648);
        harbor_east.addBoundary(192,756);
        harbor_east.addBoundary(192,864);
        harbor_east.addBoundary(384,648);
        harbor_east.addBoundary(384,756);
        harbor_east.addBoundary(384,864);
        harbor_east.addBoundary(1536,648);
        harbor_east.addBoundary(1536,756);
        harbor_east.addBoundary(1536,864);
        harbor_east.addBoundary(1536,108);

        shopping_street.setRoomImage("ShoppingStreet.png");
        shopping_street.setRoomExit(0,432);
        shopping_street.setRoomExit(0,540);
        shopping_street.setRoomExit(768,972);
        shopping_street.setRoomExit(960,972);
        shopping_street.setRoomExit(1728,432);
        shopping_street.setRoomExit(1728,540);
        shopping_street.setRoomNeighbour(0,town_square);
        shopping_street.setRoomNeighbour(1,town_square);
        shopping_street.setRoomNeighbour(2,harbor_east);
        shopping_street.setRoomNeighbour(3,harbor_east);
        shopping_street.setRoomNeighbour(4,fish_store);
        shopping_street.setRoomNeighbour(5,fish_store);

        fish_store.setRoomImage("fiskebod.png");
        fish_store.setRoomExit(0,216);
        fish_store.setRoomExit(0,324);
        fish_store.setRoomExit(0,432);
        fish_store.setRoomExit(0,540);
        fish_store.setRoomNeighbour(0,shopping_street);
        fish_store.setRoomNeighbour(1,shopping_street);
        fish_store.setRoomNeighbour(2,shopping_street);
        fish_store.setRoomNeighbour(3,shopping_street);

        garbage_disposal.setRoomImage("garbage_disposal.png");
        garbage_disposal.setRoomExit(1728,432);
        garbage_disposal.setRoomExit(1728, 540);
        garbage_disposal.setRoomNeighbour(0,town_square);
        garbage_disposal.setRoomNeighbour(1,town_square);

        beach.setRoomImage("Beach.done.png");
        beach.setRoomExit(0,216);
        beach.setRoomExit(0,324);
        beach.setRoomExit(0,432);
        beach.setRoomExit(0,540);
        beach.setRoomNeighbour(0,harbor_east);
        beach.setRoomNeighbour(1,harbor_east);
        beach.setRoomNeighbour(2,harbor_east);
        beach.setRoomNeighbour(3,harbor_east);
        beach.addBoundary(192,864);
        beach.addBoundary(384,864);
        beach.addBoundary(576,864);
        beach.addBoundary(768,864);
        beach.addBoundary(960,864);
        beach.addBoundary(1152,864);
        beach.addBoundary(1344,864);
        beach.addBoundary(1536,864);

        pier_1.setRoomImage("pier_1.png");
        pier_1.setRoomExit(768,0);
        pier_1.setRoomExit(960,0);
        pier_1.setRoomNeighbour(0,harbor_west);
        pier_1.setRoomNeighbour(1,harbor_west);
        pier_1.addBoundary(192,864);
        pier_1.addBoundary(384,864);
        pier_1.addBoundary(576,864);
        pier_1.addBoundary(768,864);
        pier_1.addBoundary(960,864);
        pier_1.addBoundary(1152,864);
        pier_1.addBoundary(1344,864);
        pier_1.addBoundary(1536,864);
        pier_1.addBoundary(1344,756);
        pier_1.addBoundary(1152,756);

        pier_2.setRoomImage("pier_2.png");
        pier_2.setRoomExit(768,0);
        pier_2.setRoomExit(960,0);
        pier_2.setRoomNeighbour(0,harbor_east);
        pier_2.setRoomNeighbour(1,harbor_east);
        pier_2.addBoundary(192,864);
        pier_2.addBoundary(384,864);
        pier_2.addBoundary(576,864);
        pier_2.addBoundary(768,864);
        pier_2.addBoundary(960,864);
        pier_2.addBoundary(1152,864);
        pier_2.addBoundary(1344,864);
        pier_2.addBoundary(1536,864);

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
        toggleHelpPane = false;
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
            case M: map(); break;
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

    private void map()
    {
        toggleMapPane = !toggleMapPane;
        mapPane.setVisible(toggleMapPane);
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

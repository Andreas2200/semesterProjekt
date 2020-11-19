package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Controller {
    public Label myLabel;
    public Button myButton;
    public AnchorPane myAnchorPane;
    public ImageView backgroundImage;
    //public Image woods = "@../../backgroundTest.jpg";


    public void changeText(ActionEvent actionEvent) {
        myLabel.setText("TESTY McTest");
    }

    public void handle(javafx.scene.input.KeyEvent keyEvent) {
        double width = myAnchorPane.getWidth();
        double height = myAnchorPane.getHeight();
        switch (keyEvent.getCode())
        {
            case W: myLabel.setText("W"); myLabel.setLayoutY(myLabel.getLayoutY() - height/10); /*backgroundImage.setImage("@../../backgroundTest.jpg")*/;
            case A: myLabel.setText("A"); myLabel.setLayoutX(myLabel.getLayoutX() - width/10); break;
            case S: myLabel.setText("S"); myLabel.setLayoutY(myLabel.getLayoutY() + height/10); break;
            case D: myLabel.setText("D"); myLabel.setLayoutX(myLabel.getLayoutX() + width/10); break;
        }
    }
}

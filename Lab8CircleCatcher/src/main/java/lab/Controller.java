package lab;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Pane root;

    @FXML
    private Label titleLbL;

    @FXML
    private Label titleLbL1;

    @FXML
    private MenuBar menu;

    @FXML
    private AnchorPane titleScreen;

    @FXML
    void playGame(ActionEvent event) {
        titleScreen.setVisible(false);
        menu.setVisible(true);
        root.getChildren().clear();
        startGame();
    }


    void startGame(){
        ArrayList<RandomCircle> captured = new ArrayList<RandomCircle>();
        int numberOfCircles = 5;
        for (int i = 0; i < numberOfCircles; i++){
            RandomCircle circ = new RandomCircle(root);
            circ.setOnMouseClicked(event -> {
                TranslateTransition returnToCenter = new TranslateTransition(new Duration(500), circ);
                returnToCenter.setToX(root.getWidth()/2);
                returnToCenter.setToY(root.getHeight()/2);
                returnToCenter.play();
                circ.setCaptured(true);
                captured.add(circ);
                if(captured.size() == numberOfCircles){
                stopGame();

            }
            });
            root.getChildren().add(circ);


        }
    }

    void stopGame(){
        root.getChildren().clear();
        titleScreen.setVisible(true);
        menu.setVisible(false);
        titleLbL.setText("Congratulations You Won!");

    }

    @FXML
    public void initialize(){
        titleScreen.setVisible(true);
        menu.setVisible(false);
        titleLbL.setText("Circle Catcher");
    }

}

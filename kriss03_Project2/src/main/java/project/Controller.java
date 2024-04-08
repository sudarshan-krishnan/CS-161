package project;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Name: Shaurya Beriwala
 * Username: beris01
 */

public class Controller {
	@FXML
    private HBox dealerHandHbox;

    @FXML
    private Label dealerScoreLbl;

    @FXML
    private Label dealerValueLbl;

    @FXML
    private Button hitBtn;

    @FXML
    private Button playBtn;

    @FXML
    private HBox playerHandHbox;

    @FXML
    private Label playerScoreLbl;

    @FXML
    private Label playerValueLbl;

    @FXML
    private Label resultLbl;

    @FXML
    private Button standBtn;
    
    @FXML
    private ImageView bj_logo;
    
    static Player player, dealer;
    
    public void initialize() {
    	player= new Player();
    	dealer= new Player();
    	player.deck= dealer.deck;
    	hitBtn.setVisible(false);
    	standBtn.setVisible(false);
    	bj_logo.setImage(new Image("file:bj_logo.png"));
    }
    
    void updateHand(Player p, HBox handBox, Label handValue) {
    
    	ArrayList<Card> currentHand= p.getHand();
    	handBox.getChildren().add(currentHand.get(currentHand.size()-1));
    	handValue.setText(handValue.getText().substring(0, 31)+" "+p.valueOfHand());
    }
    @FXML
    void hit(ActionEvent event) {
    	if(!player.busted()) {  
    		player.hit();
    		updateHand(player, playerHandHbox, playerValueLbl);
    	}
    	if(player.busted())
    		endGame();
    }

    @FXML
    void stand(ActionEvent event) {
        boolean continueLoop = true;
        do {
            dealer.deck = player.deck;
            dealer.hit();
            updateHand(dealer, dealerHandHbox, dealerValueLbl);
            
            if (dealer.stand(player.valueOfHand())) {
                continueLoop = false;
            }
            
            if (dealer.busted()) {
                continueLoop = false;
            }
            
        } while (continueLoop);
        
        endGame();
    }

    
    @FXML
    void startGame(ActionEvent event) {
    	player.deck.reset();
    	dealer.deck.reset();
    	playerHandHbox.getChildren().clear();
    	player.clearHand();
    	dealerHandHbox.getChildren().clear();
    	dealer.clearHand();
    	playBtn.setVisible(false);
    	hitBtn.setVisible(true);
    	standBtn.setVisible(true);
    	playerValueLbl.setText("Player Hand              Value:");
    	dealerValueLbl.setText("Dealer Hand              Value:");
    	resultLbl.setText("");
    	dealer.hit();
    	updateHand(dealer, dealerHandHbox, dealerValueLbl);
    	player.deck= dealer.deck;
    }
    
    void endGame() {
        if (!player.busted()) {
            if (!dealer.busted()) {
                if (player.valueOfHand() == dealer.valueOfHand()) {
                    resultLbl.setText("Push! No one wins.");
                } else if (player.valueOfHand() < dealer.valueOfHand()) {
                    resultLbl.setText("Dealer wins!");
                    dealerScoreLbl.setText("Dealer Score: " + dealer.win());
                } else {
                    resultLbl.setText("Player wins!");
                    playerScoreLbl.setText("Player Score: " + player.win());
                }
            } else {
                resultLbl.setText("Player wins!");
                playerScoreLbl.setText("Player Score: " + player.win());
            }
        } else {
            if (dealer.busted()) {
                resultLbl.setText("Push! No one wins.");
            } else {
                resultLbl.setText("Dealer wins!");
                dealerScoreLbl.setText("Dealer Score: " + dealer.win());
            }
        }
        hitBtn.setVisible(false);
        standBtn.setVisible(false);
        playBtn.setVisible(true);
    }
}

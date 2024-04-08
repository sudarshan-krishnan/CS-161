package project;
import static project.Card.faces;
import static project.Card.suits;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Card extends ImageView {
	static String[] faces= {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
	static String[] suits= {"Spade", "Hearts", "Diamond", "Clubs"};
	static int height= 130;
	private String face;
	
	public Card(String face, String suit) {
		this.face= face;
		Image cardImg = new Image("file:cards/" + face + "_" + suit + ".png");
		setImage(cardImg);
		setFitHeight(height);
		setPreserveRatio(true);
	
	}
	
	String getFace() {
		return this.face;
	}
	
	int valueOf() {
	    if ("J".equals(face) || "Q".equals(face) || "K".equals(face)) {
	        return 10;
	    } else if ("A".equals(face)) {
	        return 11;
	    } else {
	        return Integer.parseInt(face);
	    }
	}

}
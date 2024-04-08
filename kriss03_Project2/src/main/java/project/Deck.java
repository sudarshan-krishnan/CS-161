package project;

import java.util.ArrayList;
import java.util.Random;
import static project.Card.faces;
import static project.Card.suits;

public class Deck {
    private ArrayList<Card> cards;
    private Random random;

    public Deck() {
        cards = new ArrayList<>();
        random = new Random();
        reset();
    }

    public Card dealCard() {
        int randomIndex = random.nextInt(cards.size());
        return cards.remove(randomIndex);
    }

    public void reset() {
        cards.clear();
        for (String suit : suits) {
            for (String face : faces) {
                cards.add(new Card(face, suit));
            }
        }
    }
}

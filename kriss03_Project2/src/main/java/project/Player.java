/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

package project;

import java.util.ArrayList;

public class Player {
    static Deck deck;
    private ArrayList<Card> hand;
    private int wins;

    public Player() {
        hand = new ArrayList<>();
        wins = 0;
        if (deck == null) {
            deck = new Deck();
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int valueOfHand() {
        int value = 0;
        for (Card card : hand) {
            if (!card.getFace().equals("A")) {
                value += card.valueOf();
            } else {
                if (value + 11 > 21) {
                    value += 1; 
                } else {
                    value += 11; 
                }
            }
        }
        return value;
    }


    public void clearHand() {
        hand.clear();
    }

    public boolean stand(int otherPlayerValue) {
        double rand = Math.random();
        int value = valueOfHand();

        if (value == 21) {
            return true;
        }
        
        if (value >= 16) {
            if (value > otherPlayerValue) {
                return true;
            }
            
            if (value == otherPlayerValue && rand >= 0.5) {
                return true;
            }
        }
        
        return false;
    }


    public boolean busted() {
        return valueOfHand() > 21;
    }

    public void hit() {
        hand.add(deck.dealCard());
    }

    public int win() {
        return ++wins;
    }
}

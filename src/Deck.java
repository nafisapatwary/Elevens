import java.util.*;
public class Deck {
    private ArrayList<Card> deck;
    public Deck(ArrayList<Card> hand){
        deck = Card.buildDeck();
        for (int i = 0; i < hand.size(); i++){
            Card current = hand.get(i);
            for (int j = 0; j < deck.size(); j++){
                if (deck.get(j).equals(current)){
                    deck.remove(j);
                    j--;
                }
            }
        }
    }

    public ArrayList<Card> getDeck(){
        return deck;
    }
    public Card getNewCard(){
        int randomCardIdx = (int)(Math.random() * deck.size());
        Card newCard = deck.get(randomCardIdx);
        deck.remove(newCard);
        return newCard;
    }

    public void returnCard(Card c){
        deck.add(c);
    }
}

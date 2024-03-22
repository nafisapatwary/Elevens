import java.util.*;
public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    public Deck(ArrayList<Card> hand){
        deck = Card.buildDeck();
        this.hand = hand;
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

    public boolean canBeReplaced(){

    }
    public boolean noPossibleCombinationsLeft(){
        for (int i = 0; i < hand.size(); i++){
            for (int j = 1; j < hand.size(); j++){

            }
        }
    }
    public void returnCard(Card c){
        deck.add(c);
    }
}

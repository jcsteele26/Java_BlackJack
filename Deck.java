import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> cards;
    
    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    public void createDeck(){
        //Generate cards
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()){
                //Add new card to deck
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public String toString(){
        String cardListOutput = " ";
        int i = 0;
        for(Card aCard : this.cards){
            cardListOutput += "\n" + i + " "+ aCard.toString();
            i++;
        }
        return cardListOutput;
    }
    
}
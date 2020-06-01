public class Blackjack{

    public static void main(String[] args) {
        
       //Welcome Message
       
       //Create playing deck
       Deck playingDeck = new Deck();
       playingDeck.createDeck();

       System.out.println(playingDeck);
    }
}
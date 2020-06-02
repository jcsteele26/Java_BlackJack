import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        
       //Welcome Message
       System.out.println("Welcome To Gardner Blackjack!!");
       
       //Create playing deck
       Deck playingDeck = new Deck();
       playingDeck.createDeck();
       playingDeck.shuffle();

       //Create a hand for the player
       Deck playerDeck = new Deck();
       //Create a hand for the dealer
       Deck dealerDeck = new Deck();

       double playerMoney = 200.00;

       Scanner userInput = new Scanner(System.in);

       //Game Loop
       while(playerMoney > 0){
           //Keep playing
           //Get the player bet
           System.out.println("You have $" + playerMoney + ", how much would you like to bet? ");
           double playerBet = userInput.nextDouble();
           if(playerBet > playerMoney){
               System.out.println("You can not bet more than you have. Bye. ");
               break;
           }

           boolean endRound = false;

           //Start dealing cards
           //Player gets two cards
           playerDeck.draw(playingDeck);
           playerDeck.draw(playingDeck);

           //Dealer gets two cards
           dealerDeck.draw(playingDeck);
           dealerDeck.draw(playingDeck);

           while(true){
               //Display Player Hand
               System.out.println("Your hand: ");
               System.out.print(playerDeck.toString());
               System.out.println("");
               System.out.println("Your total: " + playerDeck.cardValue());
               System.out.println("");

               //Display Dealer Hand
               System.out.println("Dealer hand: ");
               System.out.println(dealerDeck.getCard(0).toString() + " and [Hidden]");
               System.out.println(" ");

               //What does player want to do?
               System.out.println("Would you like to (1) Hit or (2) Stand");
               int response = userInput.nextInt();

               //If Hit 
               if(response == 1){
                   playerDeck.draw(playingDeck);
                   System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());

                   if(playerDeck.cardValue() > 21){
                       System.out.println("Bust! Your total is: " + playerDeck.cardValue());
                       System.out.println("Dealer Wins! ");
                       playerMoney -= playerBet;
                       endRound = true;
                       break;
                   }
               }
               if(response == 2){
                   break;
               }
           }

           //Reveal Dealer Hand
           System.out.println("Dealer Cards: " + dealerDeck.toString());
           if((dealerDeck.cardValue() > playerDeck.cardValue()) && endRound == false) {
               System.out.println("Dealer Wins! ");
               playerMoney -= playerBet;
               endRound = true;
           }

           //Dealer Draws at 16 and stand at 17
           while((dealerDeck.cardValue() < 17) && endRound == false){
              dealerDeck.draw(playingDeck);
              System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());               
           }

           //Display Total Value for Dealer
           System.out.println("Dealer's Hand Total: " + dealerDeck.cardValue()); 
           if((dealerDeck.cardValue() > 21) && endRound == false){
               System.out.println("Dealer Bust! You Win! ");
               playerMoney += playerBet * 2;
               endRound = true;
           }

           //Determine if Push
           if((playerDeck.cardValue() == dealerDeck.cardValue()) && endRound == false){
               System.out.println("Push");
               endRound = true;
           }
           
           //Player Wins
           if((playerDeck.cardValue() > dealerDeck.cardValue()) && endRound == false){
               System.out.println("You Win!");
               playerMoney += playerBet * 2;
               endRound = true;
           } 
           //else {
             //  System.out.println("Dealer Wins! ");
             //  playerMoney -= playerBet;
             //  endRound = true;
           //}

           playerDeck.moveAllToDeck(playingDeck);
           dealerDeck.moveAllToDeck(playingDeck);
           System.out.println("End of Hand.");
           
       }

       System.out.println("Sorry Game Over!!");

       //System.out.println(playingDeck);
       userInput.close();
    }
}
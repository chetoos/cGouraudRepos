/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgouraudblackjack;

/**
 *
 * @author Charles Gouraud
 */
import java.util.Scanner;
import java.lang.Math;
public class Cgouraudblackjack {

    static String[] deck;
    static int cardpos;
    public static void main(String[] args) {
        deck=new String[52];
        //The following Forloop defines the deck, by placing a card at each point in the deck.
        for(int i =0; i<4;i++){
            int j = i*13;
            deck[j]="A";
            deck[j+1]="2";
            deck[j+2]="3";
            deck[j+3]="4";
            deck[j+4]="5";
            deck[j+5]="6";
            deck[j+6]="7";
            deck[j+7]="8";
            deck[j+8]="9";
            deck[j+9]="T";
            deck[j+10]="J";
            deck[j+11]="Q";
            deck[j+12]="K";
        }
        
        System.out.print("Hello, player, welcome to Blackjack. Please enter your initials: ");
        Scanner keyboard = new Scanner(System.in);
        String playerName = keyboard.next();
        int game = 1;
        Character end = 'Y';
        do{
            cardpos = 0;
            boolean handend = false;
            System.out.println("Game "+game+". Deck shuffled. In this game, 10 cards are listed as T.");
            String playerhand = hit()+hit();
            String dealerhand = hit()+hit();
            int playerval=getvalue(playerhand);
            int dealerval=getvalue(dealerhand);
            boolean playerbust = false;
            boolean dealerbust = false;
            System.out.println(playerName+", your hand is "+playerhand+". Its highest value without busting, if it contains an ace, is "+playerval);
            if(playerval==21){
                handend = true;
            }
            if(dealerval == 21){
                handend = true;
            }
            //this next section is the execution of the game. It was originally a while loop, when I hadn't read the rules as in depth. The player starts off deciding how many times to it, until they stand or bust.
            // Originally, I misunderstood the rules, and thought it was like normal poker, where you hit in turn.
            if(!handend){
                boolean playerstand = false;
                boolean dealerstand = false;
                while(playerstand == false&&playerbust==false){
                System.out.println(playerName+", hit or stand? H for hit, S for stand.");
                String hit =keyboard.next();
                if(hit.equals("H")||hit.equals("h")){
                    playerhand+=hit();
                    playerval = getvalue(playerhand);
                }
                else
                    playerstand = true;
                System.out.println(playerName+", your current hand is "+playerhand+". Its highest value is "+playerval+".");
                if(playerval >21){
                    playerbust=true;
                    System.out.println(playerName+", you have busted. Your hand is over");
                }
                }
                while(dealerval<17){
                    dealerhand+=hit();
                    dealerval = getvalue(dealerhand);
                    System.out.println("Dealer hits.");
                }
                
                    dealerstand = true;
                    System.out.println("Dealer stands.");
                
                
                if(dealerval > 21)
                    dealerbust = true;
            }
            if(playerbust&&dealerbust){
                System.out.println(playerName+", both players bust, the dealer wins.");
            }
            else if(playerbust){
                System.out.println(playerName+", you busted, while the dealer did not. Dealer wins");
            }
            else if (dealerbust)
                System.out.println(playerName+", the dealer busts, you win.");
            else{
                if(playerval==dealerval)
                    System.out.println(playerName+", both hands have the same value. The hand is a tie");
                else if(playerval>dealerval)
                    System.out.println(playerName+", your hand's value of "+playerval+" beats the dealer's hand of "+dealerval+". You win this hand.");
                else
                    System.out.println(playerName+", your hand's value of "+playerval+" fails to beat the the dealer's hand of "+dealerval+". Dealer win this hand.");
            }
            System.out.println(playerName+", do you wish to play another hand? Enter Y to continue, or any other value to exit");
            end=keyboard.next().charAt(0);
        }while(end.equals('Y')||end.equals('y'));
    }
    //this method is used to draw a card. It's called hit because that's the game action. It uses the current cardpos value to determine how many cards have been drawn.
    //Then returns a random value between the current cardpos and the end of the deck. After that, it makes sure to swap the current card at cardpos and the drawn card.
    //This is to ensure that the drawn cards are safe from being used again. After that, it increments cardpos, reducing the amount of cards available.
    public static String hit(){
        int a=cardpos+(int)(Math.random()*(52-cardpos));
        String temp = deck[cardpos];
        deck[cardpos]=deck[a];
        deck[a]=temp;
        cardpos++;
        return deck[cardpos-1];
    }
    //This method gets the value for the current hand. I'm sure there is a more efficient way to accomplish this, storing the previous value and passing it along to this method, but this method works.
    public static int getvalue(String hand){
        int value=0;
        boolean ace = false;
        for (int i = 0; i<hand.length();i++){
            if(!(hand.charAt(i)=='A'||hand.charAt(i)=='K'||hand.charAt(i)=='Q'||hand.charAt(i)=='J'||hand.charAt(i)=='T'))
                value +=Character.getNumericValue(hand.charAt(i));
            
            else if(hand.charAt(i)=='A'){
                value +=1;
                ace = true;
            }
            else
                value +=10;
        }
        if(ace&&value<12)
            value+=10;
        return value;
    }
    
}

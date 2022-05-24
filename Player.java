/************************************************
 * Player class
 * 
 * This class creates a player, contains numerous 
 * methods to manipulate the Card objects in the 
 * user's hand, and contains methods for betting,
 * winning, and an accessor method for checing 
 * their bankroll.
 * 
 * (This is a heavily modified version of 
 * Cannon's Player class template)
 * 
 * @author: Garrett Gregor-Splaver (glg2125)
 * @date: 11/4/18
 ************************************************/

//Necessary utility to utilize ArrayLists
import java.util.ArrayList;

public class Player 
{
	
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;
		
	public Player()
    {		
	    // create a player here
	    // 
	    // first, create a new hand
	    // then, we set the initial bankroll
	    // arbitrarily (5 maximum value games
	    // felt pretty fair to me)
        hand = new ArrayList<Card>();
        bankroll = 25;
	}

	public void addCard(Card c)
    {
	    // add the card c to the player's hand
	    hand.add(c);
	}

	public void removeCard(Card c)
    {
	    // remove the card c from the player's hand
	    hand.remove(c);
    }
    
    //an accessor method for our current hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }
		
    public void bets(double amt)
    {
        // player makes a bet
        bet = amt;
        bankroll -= bet;
    }

    public void winnings(double odds)
    {
        // adjust bankroll if player wins
        bankroll += (odds * bet);
    }

    public double getBankroll()
    {
        // return current balance of bankroll
        return bankroll;
    }

}



/************************************************
 * Card class
 * 
 * This class contains numerous methods to 
 * compare Card objects, a method for creating
 * a Card object, a toString method to print 
 * these Card objects, and accessor methods for 
 * other classes to get the rank and a suit of 
 * a Card object.
 * 
 * (This is a heavily modified version of 
 * Cannon's Card class template)
 * 
 * @author: Garrett Gregor-Splaver (glg2125)
 * @date: 11/4/18
 ************************************************/

import java.util.*;
import java.lang.*;

public class Card implements Comparable<Card>
{
	
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank
	
	public Card(int s, int r)
    {
		//make a card with suit s and value r
		suit = s;
        rank = r;
	}
	
	public int compareTo(Card c)
    {
		//use this method to compare cards so they 
		//may be easily sorted
		// 
		//Here, we guage whether a card has a higher
		//rank or suit, order by suit, and if suits
		//are tied, order by rank
		int rankRes = (this.getRank() - c.getRank());
        int suitRes = (this.getSuit() - c.getSuit());
        
        if(suitRes > 0)
        {
            suitRes = 1;
        }
        if(suitRes < 0)
        {
            suitRes = -1;
        }
        else
        {
            suitRes = 0;
        }
        
        if(rankRes > 0)
        {
            rankRes = 1;
        }
        if(rankRes < 0)
        {
            rankRes = -1;
        }
        if(rankRes == 0)
        {
            rankRes = suitRes;
        }
        return rankRes;
	}
	
	public String toString()
    {
		//use this method to easily print a Card object
		// 
		//Make some new strings, initialize two of them,
		//and then translate suits and ranks into plain
		//english for the player
		String description;
        String cSuit = "-1";
        String cRank = "-1";
        
        if(suit == 1)
        {
            cSuit = "Clubs";
        }
        
        else if(suit == 2)
        {
            cSuit = "Diamonds";
        }
        
        else if(suit == 3)
        {
            cSuit = "Hearts";
        }
        
        else if(suit == 4)
        {
            cSuit = "Spades";
        }
        
        if(rank == 1)
        {
            cRank = "Ace";
        }
        
        else if(rank > 1 && rank < 11)
        {
            cRank = ""+rank+"";
        }
        
        else if(rank == 11)
        {
            cRank = "Jack";
        }
        
        else if(rank == 12)
        {
            cRank = "Queen";
        }
        
        else if(rank == 13)
        {
            cRank = "King";
        }
        
        description = cRank + " of " + cSuit;
        
        return description;
	}
	
    private int getSuit()
    {
        //self-explanatory
        return suit;
    }
    
    public int getRank()
    {
        //self-explanatory
        return rank;
    }
    
    public boolean duplicateSuit(Card c)
    {
        //return true or false based on 
        //whether or not cards share a suit
        return (getSuit() == c.getSuit());
    }
    
    public boolean duplicateRank(Card c)
    {
        //return true or false based on
        //wheter or not cards share a rank
        return (getRank() == c.getRank());
    }
    
    public int rankDifferential(Card c)
    {
        //return the value of the difference
        //between cards
        return (getRank() - c.getRank());
    }

}

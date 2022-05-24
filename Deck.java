/************************************************
 * Deck class
 * 
 * This class creates a deck, and contains methods
 * to deal cards from the top of it and shuffle it.
 * 
 * (This is a heavily modified version of 
 * Cannon's Deck class template)
 * 
 * @author: Garrett Gregor-Splaver (glg2125)
 * @date: 11/4/18
 ************************************************/

//So that was can use the Math.random method
import java.util.Random;

public class Deck 
{
	
	private Card[] cards;
	private int top; // the index of the top of the deck
	
	public Deck()
    {
		// make a 52 card deck here
		// 
		// This is done by starting with
		// clubs, running from Ace to King
		// and creating corresponding cards,
		// and then continuing with diamonds,
		// and so on, until all 4 suits are 
		// completed.
		
        int deckLocation = 0;
        
        Card card;
        
        cards = new Card[52];
        
        for(int t = 1; t <= 4; t++)
        {
            for(int v = 1; v <= 13; v++)
            {
                card = new Card(t, v);
                cards[deckLocation] = card;
                deckLocation++;
            }
        }
        top = 0;
	}
	
	public void shuffle()
    {
		// shuffle the deck here
		// 
		// This runs a loop 9999 times that 
		// picks one of the cards in the deck 
		// at random and swaps it with the
		// card on top. It's a bit overkill, 
		// but quit effective method of shuffling    
		Card moved;
        
        for(int mix = 0; mix < 10000; mix++)
        {
            int random = (int)(Math.random() * 52);
            moved = cards[0];
            cards[0] = cards[random];
            cards[random] = moved;  
        }
    }
    
	public Card deal()
    {
		// deal the top card in the deck
		// 
		// If the top index is out of bounds,
		// create a null card and set the top
		// back to the beginning.
		if(top > 51)
        {
            top = 0;
            return new Card(0, 0);
        }
        // Otherwise, deal from the top
        else
        {
            top++;
            return cards[top - 1];
        }
	}

}

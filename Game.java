/************************************************
 * Game class
 * 
 * This games contains a constructor for both
 * pre-designed test hands, and organically delt 
 * hands in addition to a play method to start a 
 * game. To that end, it has a method to create a
 * hand based on the test input. In addition, it
 * has methods to check to sort a user's hand,
 * take that sorted hand and check for wins, 
 * individual boolean flags and integers for each
 * of those win styles, and finally, a method to
 * payout winnings based on the initial user bet.
 * 
 * (This is a heavily modified version of 
 * Cannon's Game class template)
 * 
 * @author: Garrett Gregor-Splaver (glg2125)
 * @date: 11/4/18
 ************************************************/

//Here I just import the entire
//utilities and language packages 
//instead of manually specifying all
//the built-in methods I might want 
//to use for simplicities sake
import java.util.*;
import java.lang.*;

public class Game 
{
	
	private Player p;
	private Deck cards;
    private ArrayList<Card> hand;
    private boolean restart;
    private int replay;
    private int odds;
    private double tokens;
    private double stack;
	
	public Game(String[] testHand)
    {
		// This constructor is to help test your code.
		// use the contents of testHand to
		// make a hand for the player
		// use the following encoding for cards
		// c = clubs
		// d = diamonds
		// h = hearts
		// s = spades
		// 1-13 correspond to ace-king
		// example: s1 = ace of spades
		// example: testhand = {s1, s13, s12, s11, s10} = royal flush
        
        p = new Player();
        
        stack = (p.getBankroll());
        
        restart = true;
        
        cards = new Deck();
        
        cards.shuffle();
        
        makeHand(testHand);
        
        hand = new ArrayList<Card>(p.getHand());

        sortHand(hand);
        
        System.out.println(p.getHand());
	}
	
	public Game()
    {
		// This no-argument constructor is to actually play a normal game
		
        p = new Player();
        
        stack = (p.getBankroll());
        
        restart = true;
        
        cards = new Deck();
        
        cards.shuffle();
        
        for(int i = 0; i < 5; i++)
        {
            p.addCard(cards.deal());
        }
        hand = new ArrayList<Card>(p.getHand());
	}
	
	public void play()
    {
     // this method should play the game
     
     // set the players money stack to their
     // current bankroll
     stack = (p.getBankroll());
        
     //if they aren't broke... allow them to play
     if(stack > 0)
     {
      
      //if the user chose to restart, start again
      while(restart == true)
      {
        
        //user a scanner to find out how many tokens the user wants to bet
        System.out.println("Welcome to Video Poker 9000!");
        System.out.println("You currently have "+stack+" tokens remaining.");
        System.out.println("Please insert between 1 and 5 tokens to play.");
        
        Scanner tokenSlot = new Scanner(System.in);
        tokens = Double.parseDouble(tokenSlot.nextLine());
        
        //remove those tokens from their bankroll
        p.bets(tokens);
        
        //show them their hand
        System.out.println(p.getHand());
        
        //query the user for their next move with a new scanner
        System.out.println("What's your move? Enter...");
        System.out.println("'1' to keep your current hand, " +
                           "'2' to replace 1, 2, 3, or 4 of your cards, "
                           + "or '3' to replace your entire hand.");
    
        Scanner move = new Scanner(System.in);
        int choice = Integer.parseInt(move.nextLine());
        
        //catch errors in their movement choice
        while(choice < 1 || choice > 3)
        {
            System.out.println("That's not a valid choice! Try again...");
            choice = Integer.parseInt(move.nextLine());
        }
        
        if(choice == 1)
        {
            //print the hand again, check what they have, and pay them
            System.out.println("Great! Let's continue the game...");
            System.out.println(p.getHand());
            System.out.println(this.checkHand(hand));     
            this.payout();
            
            //ask if they want to play again with a new scanner
            System.out.println("Enter '1' if you'd like to play again. "
                               + "Any other entry closes the game.");
                
            Scanner again1 = new Scanner(System.in);
            replay = Integer.parseInt(again1.nextLine());
            
            //if they do, shuffle the deck, deal new cards, and
            //start a new game; otherwise, end the game and thank them
            if(replay == 1)
            {
                restart = true;
                cards.shuffle(); 
                
                for(int z = 0; z < 5; z++)
                {
                    p.removeCard(hand.get(z));
                    p.addCard(cards.deal());
                }
                hand = new ArrayList<Card>(p.getHand());
                
                play();
            }
            else
            {
                restart = false;
                System.out.println("Thanks for playing!");
            }
        }
          
        if(choice == 2)
        {
            //create an error catching scanner to query the user for how
            //many cards they'd like to swap out
            System.out.println("How many cards do you want to swap?");
            Scanner numSel = new Scanner(System.in);
            int numTrashed = Integer.parseInt(numSel.nextLine());
            
                while(numTrashed < 1 || numTrashed > 4)
                {
                    System.out.println("Invalid number! Try again...");
                    numTrashed = Integer.parseInt(numSel.nextLine());
                }
                
                //once we know how many they want to get rid of,
                //run a loop to ask which ones in particular and
                //subsequently replace them
                for(int k = 0; k < numTrashed; k++)
                {
                    System.out.println("Which card number do you "
                                       + "want to replace?");
                    System.out.println("e.g.: entering '1' will "
                                       + "replace your first card.");
                    Scanner posSel = new Scanner(System.in);
                    int discarded = Integer.parseInt(posSel.nextLine());
                    p.removeCard(hand.get(discarded - 1));
                    p.addCard(cards.deal());
                }
                hand = new ArrayList<Card>(p.getHand());
                //print the hand again, check what they have, and pay them
                System.out.println("Here's your new hand: ");
                System.out.println(p.getHand());
                System.out.println(this.checkHand(hand));
                this.payout();
                
                //ask if they want to play again with a new scanner
                System.out.println("Enter '1' if you'd like to play again."
                               + " Any other entry closes the game.");
                
                Scanner again2 = new Scanner(System.in);
                replay = Integer.parseInt(again2.nextLine());
            
                //if they do, shuffle the deck, deal new cards, and
            //start a new game; otherwise, end the game and thank them
                if(replay == 1)
                {
                    restart = true;
                    cards.shuffle();
                    
                    for(int y = 0; y < 5; y++)
                    {
                        p.removeCard(hand.get(y));
                        p.addCard(cards.deal());
                    }
                    hand = new ArrayList<Card>(p.getHand());
                    
                    play();
                }
                else
                {
                    restart = false;
                    System.out.println("Thanks for playing!");
                }
        }
            
        if (choice == 3)
        {
            //deal them a new hand with the remaining cards
            System.out.println("Okay. Let's deal you a new hand...");
            for(int l = 0; l < 5; l++)
            {
                p.removeCard(hand.get(l));
                p.addCard(cards.deal());
            }
            hand = new ArrayList<Card>(p.getHand());
            //print the hand again, check what they have, and pay them
            System.out.println("Here's your new hand: ");
            System.out.println(p.getHand());
            System.out.println(this.checkHand(hand));
            this.payout();
            
            //ask if they want to play again with a new scanner
            System.out.println("Enter '1' if you'd like to play again. "
                               + "Any other entry closes the game.");
                
            Scanner again3 = new Scanner(System.in);
            replay = Integer.parseInt(again3.nextLine());
            
            //if they do, shuffle the deck, deal new cards, and
            //start a new game; otherwise, end the game and thank them
            if(replay == 1)
            {
                restart = true;
                cards.shuffle(); 
                
                for(int e = 0; e < 5; e++)
                {
                    p.removeCard(hand.get(e));
                    p.addCard(cards.deal());
                }
                hand = new ArrayList<Card>(p.getHand());
                
                play();
            }
            else
             {
                restart = false;
                System.out.println("Thanks for playing!");
             }
            }
      }
     }   
     //if they are broke... stop the game and don't allow
     //them to restart
     else
     {
         System.out.println("You're out of money. So get " 
                            + "help for your gambling addiction!");
         restart = false;
     }
    }
	
	private String checkHand(ArrayList<Card> hand)
    {
		// this method should take an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		// 
		// first, sort the hand for simplicites sake
        hand = sortHand(hand);
        
        if(isRoyalFlush(hand) == true)
        {
            return "You have a Royal Flush! That pays 250 to 1.";
        }
        
        else if(isStraightFlush(hand) == true)
        {
            return "You have a Straight Flush! That pays 50 to 1.";
        }
        
        else if(isFourOfAKind(hand) == true)
        {
            return "You have Four of a kind! That pays 25 to 1.";
        }
        
        else if(isFullHouse(hand) == true)
        {
            return "You have a Full House! That pays 6 to 1.";
        }
        
        else if(isFlush(hand) == true)
        {
            return "You have a Flush! That pays 5 to 1.";
        }
        
        else if(isStraight(hand) == true)
        {
            return "You have a Straight! That pays 4 to 1.";
        }
        
        else if(isThreeOfAKind(hand) == true)
        {
            return "You have Three of a kind! That pays 3 to 1.";
        }
        
        else if(isTwoPair(hand) == true)
        {
            return "You have two pairs! That pays 2 to 1.";
        }
        
        else if(pairCount(hand) == 1)
        {
            return "You have one pair! That pays 1 to 1.";
        }
        
        //if it's not any of the winning hands, it must be a losing one...
        else 
        {
            return "You have nothing! Thanks for the money, sucker!";
        }
    }
    
	private static boolean isRoyalFlush(ArrayList<Card> hand)
    {
        //a royal flush is just a straight flush from ace to king
        if((isStraightFlush(hand) == true) && (hand.get(0).getRank() == 1)
           && (hand.get(4).getRank() == 13))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private static boolean isStraightFlush(ArrayList<Card> hand)
    {
        //a striaght flush is just a straight and a flush
        if((isFlush(hand) == true) && (isStraight(hand) == true))
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
    private static boolean isFourOfAKind(ArrayList<Card> hand)
    {
        //if you have a two pair and a three of a kind, you have either
        //a full house or a four of a kind
        if((pairCount(hand) == 2) && (isThreeOfAKind(hand) == true))
        {
            //if the 1st and 4th or 2nd and 5th cards are the same
            //after ordering, it can't be a full house, and thus
            //must be a four of a kind
            if(((hand.get(1).duplicateRank(hand.get(4))) || 
               hand.get(0).duplicateRank(hand.get(3))))
            {
                return true;
            }
            else
            {
            return false;
            }
        }
        else
        {
            return false;
        }
    }
    
    private static boolean isFullHouse(ArrayList<Card> hand)
    {
        //if it's not a four of a kind and it's a three of a kind
        //and two pairs, then it's a full house because the second
        //pair is contained within the three of a kind condition
        if((pairCount(hand) == 2) && (isThreeOfAKind(hand) == true)
           && (isFourOfAKind(hand) == false))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private static boolean isFlush(ArrayList<Card> hand)
    {
        //you can't have a flush if there's a pair, because 
        //each suit has only one unique card of each rank
        if(pairCount(hand) != 0)
        {
            return false;
        }
        //if that condition is passed, run a loop through 
        //our whole hand checking for duplicate suits if
        //everything is the same suit, it's a fush by definiton
        else
        {
            int q = 1;
            for(Card element : hand)
            {
                if(!element.duplicateSuit(hand.get(q)))
                {
                    return false;
                }
                else if(q < (hand.size() - 1))
                {
                    q++;
                }
            }
            return true;
        }
    }
    
    private static boolean isStraight(ArrayList<Card> hand)
    {
        //create new card objects that reference cards in our
        //hand to simplify this check for the special case
        Card cOne = hand.get(0);
        Card cTwo = hand.get(1);
        Card cThree = hand.get(2);
        Card cFour = hand.get(3);
        Card cFive = hand.get(4);
        
        //if there's any pairs, it can't be a straight
        if(pairCount(hand) != 0)
        {
            return false;
        }
        //if, after ordering, the highest card is 4 higher
        //than the lowest, it must be a straight by definition
        else if(cFive.rankDifferential(cOne) == 4)
        {
            return true;
        }
        //in the special case where we have
        //Ace, 10, Jack, Queen, King it's also a straight
        else if((cOne.getRank() == 1) && (cTwo.getRank() == 10) &&
                (cThree.getRank() == 11) && (cFour.getRank() == 12)
                && (cFive.getRank() == 13))
        {
            return true;
        }    
        else
        {
            return false;        
        }
    }
    
    private static boolean isThreeOfAKind(ArrayList<Card> hand)
    {
        //a three of a kind has to have at least 1 pair
        if(pairCount(hand) == 0)
        {
            return false;
        }
        //if the 1st and 3rd, 2nd and 4th, or 3rd and 5th cards
        //are the same after ordering, then both those and the one
        //in the middle must have the same rank after ordering,
        //and thus, we must have a three of a kind
        else if((hand.get(2).duplicateRank(hand.get(4))) ||
                (hand.get(1).duplicateRank(hand.get(3))) ||
                (hand.get(0).duplicateRank(hand.get(2))))
        {
            return true;
        }
        else
        {
            return false;  
        }
    }
    
    private static boolean isTwoPair(ArrayList<Card> hand)
    {
        //if it's not a four of a kind or three of a kind, but
        //we have 2 pairs total, it must be a two pair
        if((isFourOfAKind(hand) == false) &&
           (isThreeOfAKind(hand) == false) && (pairCount(hand) == 2))
        {
            return true;
        }
        else
        {
            return false;        
        }
    }
    
    //by using an int here, we can actually cont pairs and
    //drastically simplify previous checking algorithms
    private static int pairCount(ArrayList<Card> hand)
    {
        Card temp;
        int s = 1;
        int pairsNum = 0;
        
        while(s < hand.size())
        {
            temp = hand.get(s - 1);
            
            if(temp.duplicateRank(hand.get(s)))
            {
                s++;
                pairsNum++;
            }
            s++;
        }
        return pairsNum;     
    }
    
    //this method takes the string arguments that
    //could be passed on the command line and turns
    //them into the first hand we'll play with for
    //both debugging and grading purposes
    private void makeHand(String[] testHand)
    {
        Card card;
        int suit = -1;
        int rank;
        char suitAlpha;
        String rankValue;
        
        //give them all the right suit integer
        for(int n = 0; n < 5; n++)
        {
            //suit comes first, e.g. s8 = 8 of Spades
            suitAlpha = testHand[n].charAt(0);
            
            //rank comes after suit, e.g. c1 = Ace of Clubs
            rankValue = testHand[n].substring(1);
                
            if(suitAlpha == 'c')
            {
                suit = 1;
            }
            else if(suitAlpha == 'd')
            {
                suit = 2;
            }
            else if(suitAlpha == 'h')
            {
                suit = 3;
            }
            else if(suitAlpha == 's')
            {
                suit = 4;
            }
            
            //rank remains coded correctly already
            rank = Integer.parseInt(rankValue);
            
            //turn all of these into new Card objects
            card = new Card(suit, rank);
            
            p.addCard(card);
        }   
    }
    
    //sorting is nice and simpe because hands are ArrayLists
    private ArrayList<Card> sortHand(ArrayList<Card> hand)
    {
        Collections.sort(hand);
        return hand;
    }
    
    private void payout()
    {
        //initialize odds as 0 so that if none of the winning
        //conditions are met, we pay nothing as we should for a loss
        odds = 0;
        
        //correctly set all the odds
        if(isRoyalFlush(hand) == true)
        {
            odds = 250;
        }
        else if(isStraightFlush(hand) == true)
        {
            odds = 50;
        }
        else if(isFourOfAKind(hand) == true)
        {
            odds = 25;
        }
        else if(isFullHouse(hand) == true)
        {
            odds = 6;
        }
        else if(isFlush(hand) == true)
        {
            odds = 5;
        }
        else if(isStraight(hand) == true)
        {
            odds = 4;
        }
        else if(isThreeOfAKind(hand) == true)
        {
            odds = 3;
        }
        else if(isTwoPair(hand) == true)
        {
            odds = 2;
        }
        else if(pairCount(hand) == 1)
        {
            odds = 1;
        }
        
        //payout against the odds and inform the user;
        //these get multiplied by the bet in the player class
        //so that the payout scales correctly with the bet
        p.winnings(odds);
        System.out.println("You won "+(odds*tokens)+" tokens.");
        System.out.println("You currently have "+p.getBankroll()+" tokens.");
    }
}
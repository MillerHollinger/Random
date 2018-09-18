import java.util.ArrayList;
import java.util.Random;

public class Player {
	// Player ID. Used to recognize other players.
	public int id;
	// The player's hand. 1 to 5 are resources, 6 is glory.
	public ArrayList<Integer> hand;
	// The player's job, corresponding to the resource they use.
	public int job;
	// The player's role. true if they are the dissident.
	public boolean isDiss;
	// Incoming cards from trades.
	public ArrayList<Integer> incoming;
	// Memory of who they've sent Glory to; id based.
	public ArrayList<Integer> gSent;
	
	// Normal constructor, only accepts a job.
	public Player(int i, int j)
	{
		id = i;
		hand = new ArrayList<Integer>();
		job = j;
		isDiss = false;
		incoming = new ArrayList<Integer>();
		gSent = new ArrayList<Integer>();
	}
	
	// Returns true if the player can make and did make a stack.
	public boolean makeStack()
	{
		if (countInstances(job) >= 3)
		{
			int removed = 0;
			for (int i = 0; i < hand.size() && removed < 3; i++)
			{
				if (hand.get(i) == job)
				{
					hand.remove(i);
					i--;
					removed++;
				}
			}
			return true;
		}
		return false;
	}
	
	// Adds a random card to the hand.
	public void addCard(int cards)
	{
		Random rn = new Random();
		for (int i = 0; i < cards; i++)
			hand.add(rn.nextInt(5) + 1); // 1 2 3 4 5
	}
	
	// Check if hand is over 6 cards; if it is, remove unwanted cards.
	// Removes glory (6) if the player has over two.
	// Removes the oldest resource (1-5) in the player's hand that isn't associated with their job otherwise,
	//  or one associated with their job if they only have their own resource.
	public void checkHand() {
		while (hand.size() >= 7) { // While hand is too large.
			if (countInstances(6) >= 3) // If hand contains 3+ Glory, remove one.
				hand.remove(hand.indexOf(6));
			else {
				boolean removed = false;
				for (int i = 0; i < hand.size() && !removed; i++) {
					if (hand.get(i) != job && hand.get(i) != 6) {
						hand.remove(i);
						removed = true;
					}
				}
				if (!removed) {
					for (int i = 0; i < hand.size() && !removed; i++) {
						if (hand.get(i) != 6) {
							hand.remove(i);
							removed = true;
						}
					}
				}
			}
		}
	}

	// Counts the instances of a given card.
	public int countInstances(int c)
	{
		int count = 0;
		for (int i = 0; i < hand.size(); i++)
			if (hand.get(i) == c)
				count++;
		return count;
	}
	
}

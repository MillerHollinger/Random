import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// TODO automatically go thru every possible config
// TODO better output

public class MainTester {

	public static void main(String[] args) {
		Random rn = new Random();
		Scanner userInput = new Scanner(System.in);
		
		// How many games to play.
		final int GAMES = 50000;
		// How much a token costs in gold.
		final int TOKEN_COST = 10;
		// How many coins the king gets per turn.
		final int COINS_TURN = 4;
		// How many tokens are needed to win.
		final int TOKENS_WIN = 9;
		// Stack size.
		final int STACK_SIZE = 3;
		// How likely the dissident is to send a Glory. Out of 100%.
		final int PERCENT_SEND = 50;
		// How aggressive the king is; how many players he will investigate per turn if he can. 1 to 4.
		final int SEARCH_SCORE = 2;
		// The chance players will try to stop an investigation on themselves if they can. Out of 100%. Each card being sent to the player adds 20%.
		final int PERCENT_NULLIFY = 20;
		// If text should be displayed.
		final boolean disp = false;
		
		// Players.
		ArrayList<Player> players;
		// King's gold.
		int kG;
		// King's tokens.
		int tokens;
		// Citizen's gold.
		int cG;
		
		// If the game has been won.
		boolean victory;
		
		// Results.
		int kWin = 0;
		int cWin = 0;
		
		for (int game = 0; game < GAMES; game++)
		{
			if (disp) System.out.println("> > > > > > BEGINNING OF GAME "+game);
			players = new ArrayList<Player>();
			for (int i = 0; i < 5; i++)
				players.add(new Player(i, i));
			kG = 0;
			cG = 0;
			tokens = 0;
			victory = false;
			
			// Select a random dissident.
			players.get(rn.nextInt(players.size())).isDiss = true;
			
			while (!victory) // Normal game loop.
			{
				// King earns COINS_TURN gold. 
				kG += COINS_TURN;
				// If the king has at least TOKEN_COST + rn.nextInt(4), they'll buy a token.
				if (kG >= TOKEN_COST + rn.nextInt(4))
				{
					kG -= TOKEN_COST;
					tokens++;
					if (disp) System.out.println("King bought a Token: He now has " + tokens + " tokens; " + kG + " Gold.");
				}
					// If the king has 3 control tokens, they win.
				if (tokens < TOKENS_WIN)
				{
					// Players draw cards.
					for (int i = 0; i < players.size(); i++)
					{
						if (!players.get(i).isDiss) // Citizens get two random resources.
						{
							players.get(i).addCard(2);
							if (disp) System.out.println("A citizen drew resources.");
						}
						else // Dissident draws Glory or resources.
						{
							switch (players.get(i).countInstances(6)) {
							case 0: // 0 Glory; draw 2.
								players.get(i).hand.add(6);
								players.get(i).hand.add(6);
								if (disp) System.out.println("The dissident drew two glory.");
								break;
							case 1: // 1 Glory, draw 1 and 1.
								players.get(i).addCard(1);
								players.get(i).hand.add(6);
								if (disp) System.out.println("The dissident went 1 and 1.");
								break;
							default: // Draw 2 resource.
								players.get(i).addCard(2);
								if (disp) System.out.println("The dissident drew two resources.");
								break;
							}
						}
					}
					if (disp) System.out.println("Citizens drew cards.");	
					
					// Players trade cards.
					for (int i = 0; i < players.size(); i++)
					{
						boolean resolved = false;
						int tries = 0;
						while (!resolved && tries < 256)
						{
							// Each citizen selects another random player. If they have that job's card, they'll send it.
							int select = rn.nextInt(players.size());
							tries++; // After 12 tries they give up.
							if (select != i) // Not sending to self.
							{
								// The dissident selects a random player. If they think that player doesn't have Glory, they'll send a Glory PERCENT_SEND percent of the time. Else, they send a resource.
								if (!players.get(i).gSent.contains(players.get(select).id) && players.get(i).isDiss)
								{
									if (rn.nextInt(100) + 1 <= PERCENT_SEND)
									{
										players.get(i).hand.remove(players.get(i).hand.indexOf(6)); // Remove glory from sending hand.
										players.get(select).incoming.add(6);
										// If the dissident sends Glory to a player, add that player's id to their gSent list.
										players.get(i).gSent.add(players.get(select).id);
										resolved = true;
										if (disp) System.out.println("The dissident, " + i + ", sent glory to " + select);
									}
									// Else: Decided not to send, try another player.
								}
								// Citizen sending to other player based on job.
								else if (players.get(i).hand.contains(players.get(select).job)) 
								{
									players.get(i).hand.remove(players.get(i).hand.indexOf(players.get(select).job));
									players.get(select).incoming.add(players.get(select).job);
									resolved = true;
									if (disp) System.out.println("A citizen, " + i + ", sent a card to " + select);
								}
								// Else, try again with another player.
							}
						}
					}
					if (disp) System.out.println("Citizens traded cards.");
					
					
					// King interrupts trades.
						// The king picks SEARCH_SCORE players to investigate; prioritizing those with more cards. Gold is spent.
					for (int i = 0; i < SEARCH_SCORE && kG > 0; i++)
					{
						boolean found = false;
						for (int priority = 4; !found && priority > 0; priority--)
						{
							for (int j = 0; j < players.size(); j++)
							{
								if (players.get(j).incoming.size() == priority)
								{
									// The king has found a player to investigate.
									found = true;
									kG--;
									cG++;
									// The player decides to nullify or not.
									if (rn.nextInt(100) + 1 <= PERCENT_NULLIFY + players.get(j).incoming.size() * 20 && cG >= 4) // Nullify.
									{
										// Spend gold.
										cG -= 4;
										// Randomize roles.
										for (int r = 0; r < players.size(); r++)
											players.get(r).isDiss = false;
										// Select a random dissident.
										players.get(rn.nextInt(players.size())).isDiss = true;
										if (disp) System.out.println("The King investigated a trade, but the people nullified it.");
									}
									else // Allow search.
									{
										// Non-nullifying players reveal their card(s). If it is glory, tokens++, remove the cards, and randomize dissident.
										if (players.get(j).incoming.contains(6))
										{
											players.get(j).incoming = new ArrayList<Integer>();
											tokens++;
											// Randomize roles.
											for (int r = 0; r < players.size(); r++)
												players.get(r).isDiss = false;
											// Select a random dissident.
											players.get(rn.nextInt(players.size())).isDiss = true;
											if (disp) System.out.println("The King investigated a trade and found Glory.");
											j = players.size(); // TODO Janky exit; find better way
											priority = 0;
											i = SEARCH_SCORE;
										}
										if (tokens >= TOKENS_WIN) // If the king has 3 control tokens, they win.
										{
											kWin++;
											victory = true;
											if (disp) System.out.println("The king got all needed control tokens after finding glory in a trade. He won.");
										}
									}
								}
							}
						}
					}
					// Check that king hasn't yet won.
					if (!victory)
					{
						// Cards are received.
						for (int i = 0; i < players.size(); i++)
							if (players.get(i).incoming.size() > 0)
								for (int j = 0; j < players.get(i).incoming.size(); j++)
									players.get(i).hand.add(players.get(i).incoming.get(j));
						if (disp) System.out.println("Citizens recieved cards.");
						// Stacks of STACK_SIZE are made. Gold is earned.
						for (int i = 0; i < players.size(); i++) 
							if (players.get(i).makeStack())
								cG++;
						if (disp) System.out.println("Citizens made stacks.");
						// If the current dissident thinks every player has Glory, they'll revolt. If every player has Glory, the citizens win; if not, the king wins.
						for (int i = 0; i < players.size(); i++)
						{
							if (players.get(i).isDiss && players.get(i).gSent.size() >= 4 && players.get(i).hand.contains(6))
							{
								for (int j = 0; j < players.size(); j++)
								{
									if (!players.get(j).hand.contains(6)) // Someone doesn't have Glory somehow; king wins.
									{
										kWin++;
										victory = true;
										if (disp) System.out.println("Citizens failed to revolt.");
									}
								}
								if (!victory)
								{
									cWin++;
									victory = true;
									if (disp) System.out.println("Citizens revolted and won.");
								}
							}
						}
					}
				}
				else
				{
					kWin++;
					victory = true;
				}
			}
		}
		System.out.println("::FINAL SCORE::");
		System.out.println("King WINS: "+kWin);
		System.out.println("Citizen WINS: "+cWin);
		System.out.println("King/Citizen Win Ratio: " + ((kWin * 1.0) / (cWin* 1.0)));
		System.out.println("Distance from Perfect: " + Math.abs(((kWin * 1.0) / (cWin* 1.0)) -  1));
	}
}

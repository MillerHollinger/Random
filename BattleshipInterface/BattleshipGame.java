/* Welcome to the Battleship Project File!
 *    By Miller Hollinger
 * 
 * To complete this project, fill in each function as BattleshipInterface tells you to.
 * Then, fill in sections labeled with : TODO
 * You can find TODO items easily with the CTRL+F search function.
 * After you complete a TODO item, and it works, delete the TODO so you know you can move on.
 * When you're done, if all your functions do what they're supposed to, you'll have a
 *  fully functional version of Battleship.
 *  
 */

import java.util.*;

public class BattleshipGame implements BattleshipInterface{

	public static void main(String[] args) {
		// The scanner. Use it to get user input.
		Scanner scan = new Scanner(System.in);
		
		// The Battleship object. It will run all your functions.
		Battleship game = new Battleship();
		
		/* TODO An intro for your game.
		 *  Tell the player what game they're playing and who made it.
		 *  Feel free to be fancy.
		 */
		
		// The ship's lengths. There should be five ships, of lengths 2,3,3,4,5.
		int[] shipLengths = {2,3,3,4,5};
		
		/* TODO Create some characters.
		 * There should be five unique ship characters in this array.
		 * Remember, your characters can't be " ", "X", or "O".
		 */
		// The ship's display characters. There should be five.
		String[] shipChars = {"" , "", "", "", ""};
		
		// Set up the player's and AI's board.
		String[][] board = game.setPlayerBoard(shipLengths, shipChars, 10, 10);
		String[][] ai = game.setAIBoard(shipLengths, shipChars, 10, 10);
		
		// The character that means "this tile hasn't been discovered yet."
		String hiddenChar = ".";
		
		// Player / AI take turns shooting
		while (!game.checkVictory(board, shipChars) && !game.checkVictory(ai, shipChars))
		{
			// This is the main game loop. The player and AI alternate until somebody wins.
			
			/* TODO Tell the player it is their turn. */
			// Displays the player and AI's board (AI's is hidden).
			System.out.println("Your board:");
			game.printFullBoard(board);
			System.out.println("AI's board:");
			game.printHiddenBoard(ai, hiddenChar);
			
			/* TODO Ask the player which row they will shoot. */
			int tRow = Integer.parseInt(scan.nextLine()); // Gets the target row.
			/* TODO Ask the player with column they will shoot. */
			int tCol = Integer.parseInt(scan.nextLine()); // Gets the target column.
			
			// Fire the player's shot.
			game.shootAt(ai, tRow, tCol);
			/* TODO Tell the player their shot was fired and it is now the AI's turn. */
			// The AI takes their turn.
			game.takeAIMove(board);
		}
		if (game.checkVictory(board, shipChars)) // If the AI has won, this section will run.
		{
			/* TODO Demean the player for losing to the AI. Boo! */
			System.out.println("Your board: (LOSE)");
			game.printFullBoard(board); // Shows the player's final board.
			System.out.println("AI's board: (WIN)");
			game.printFullBoard(ai); // Shows the player's final board.
		}
		else // If the player has won, this section will run.
		{
			/* TODO Congratulate the player for defeating the AI. Hooray! */
			System.out.println("Your board: (WIN)");
			game.printFullBoard(board); // Show the player's final board.
			System.out.println("AI's board: (LOSE)");
			game.printFullBoard(ai); // Show the AI's final board.
		}
	}
	
	/* TODO Fill in functions.
	 * Below are all the functions from BattleshipInterface.
	 * Read each function's description in BattleshipInterface to see
	 *  how to fill them in.
	 *  
	 * Your file will not compile until you complete each function.
	 * 
	 */
	
	/* TODO : Checks to see if there is a ship block somewhere.
	 * Check BattleshipInterface for more information.
	 */
	public boolean shipAt(String[][] board, int row, int col) {
		
		return false; // TODO Default return statement, replace.
	}

	/* TODO : Places a ship into the board given its location.
	 * Check BattleshipInterface for more information.
	 */
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Prints out the given board with everything revealed, including ships, water, hits, and misses.
	 * Check BattleshipInterface for more information.
	 */
	public void printFullBoard(String[][] board) {
		
	}

	/* TODO : Prints out the given board with ships hidden.
	 * Check BattleshipInterface for more information.
	 */
	public void printHiddenBoard(String[][] board, String hiddenChar) {
		
	}

	/* TODO : Walks the player through setting up their board.
	 * Check BattleshipInterface for more information.
	 */
	public String[][] setPlayerBoard(int[] shipLengths, String[] shipChars, int rows, int cols) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Automatically sets up the AI's board.
	 * Check BattleshipInterface for more information.
	 */
	public String[][] setAIBoard(int[] shipLengths, String[] shipChars, int rows, int cols) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Given the enemy's board, returns where the AI will fire.
	 * Check BattleshipInterface for more information.
	 */
	public int[] getAIMove(String[][] board) {
		
		return new int[1]; // TODO Default return statement, replace.
	}

	/* TODO : Fires a shot at a board.
	 * Check BattleshipInterface for more information.
	 */
	public String[][] shootAt(String[][] board, int row, int col) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Calculates and applies the AI's move to a given board.
	 * Check BattleshipInterface for more information.
	 */
	public String[][] takeAIMove(String[][] board) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Looks to see which ships are destroyed.
	 * Check BattleshipInterface for more information.
	 */
	public String[] checkDestruction(String[][] board, String[] ships) {
		
		return new String[1]; // TODO Default return statement, replace.
	}

	/* TODO : Checks to see if the given board has been destroyed (no ships left).
	 * Check BattleshipInterface for more information.
	 */
	public boolean checkVictory(String[][] board, String[] ships) {
		
		return false; // TODO Default return statement, replace.
	}
}

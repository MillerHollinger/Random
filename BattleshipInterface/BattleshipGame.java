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
/* <Description of what this method does>
 * ------PARAMETERS------
 * <Type> <Name> : <Description>
 * 		<Requirement>, <Requirement>, <...>
 * <...>
 * ------RETURN VALUES------
 * IF <Condition 1>
 * 		<AND/OR> <Condition 2>
 * 		<...>
 * 		RETURN <Return value>
 * <IF/ELSE ...>
 */
// Method header

/* This is the main file for Battleship.
 * 
 * Each method description includes USES, PARAMETERS, and RETURN VALUES.
 * USES are methods in this interface that are recommended to be used in the function.
 * PARAMETERS are descriptions of each parameter in the function header. 
 * 				Listed under each description are restrictions on the parameter's value.
 * RETURN VALUES are descriptions of what to return and when.
 * 				Pay attention to AND/OR requirements.
 * 
 * Assume entered variables are valid.
 */

/* Notes specifically regarding Battleship:
 *  - The board is made of Water, Hit Markers, and Ship Blocks.
 *  	- Water is simply a String with one space: " "
 *  	- Hit markers are "X" or "O". "X" is a hit, "O" is a miss.
 *  	- Ship Blocks are any character except " ", "X", and "O".
 *  Each Ship placed by the AI or Player is made of Ship Blocks.
 *  For example, a Ship of length 3 takes up 3 spaces in the String[][];
 *   each space the ship takes up is a Ship Block.
 *  For example:{{ ,C,B},
 *               { ,C,B},
 *               {A,A,B}}
 * 	 				contains 7 Ship Blocks, comprising 3 Ships (A=2, B=3, C=2)
 */
import java.util.*;

public class BattleshipGame implements BattleshipInterface{

	/* You won't need to edit anything inside the main aside the TODO requests.
	 * In fact, if you do change anything, it probably won't work as intended.
	 * Make sure you're editing the right code.
	 */
	public static void main(String[] args) {
		// The scanner. Use it to get user input.
		Scanner scan = new Scanner(System.in);
		
		// The Battleship object. It will run all your functions.
		BattleshipGame game = new BattleshipGame();
		
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
	 */
	
	/* TODO : Checks to see if there is a ship block somewhere.
	 * USES: -/-
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * int row : The row in board to check.
	 * 		row > 0, row < board.length
	 * int col : The column in board to check.
	 * 		col > 0, col < board[0].length
	 * ------RETURN VALUES------
	 * IF there is a ship block (not " ", "X", or "O") at board[row][col],
	 * 		RETURN true.
	 * ELSE
	 * 		RETURN false.
	 */
	public boolean shipAt(String[][] board, int row, int col) {
		
		return false; // TODO Default return statement, replace.
	}

	/* TODO : Places a ship into the board given its location.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board for this Battleship game.
	 * 		board.length > 0, board[0].length > 0
	 * int len : How long the ship being placed is.
	 * 		len > 0, len < board.length, len < board[0].length
	 * int sRow : Where the first block of this ship is, row.
	 * 		sRow >= 0, sRow < board.length
	 * int sCol : Where the first block of this ship is, column.
	 * 		sCol >= 0, sCol < board.length[]
	 * boolean isVert : If the ship is vertical or not. 
	 * 		If true, the ship is vertical, i.e. it is in one column and several rows.
	 * 		If false, the ship is horizonal, i.e. it is in one row and several columns.
	 * String disp : What the ship will display as. 
	 * 		disp.length() == 1, !disp.equals("X"), !disp.equals("O")
	 * ------RETURN VALUES------
	 * RETURN the board with the ship added.
	 */
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Prints out the given board with everything revealed,
	 *  including ships, water, hits, and misses.
	 * Best used to show the player's board as they are allowed to see their own ships.
	 * USES: -/-
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * void
	 */
	public void printFullBoard(String[][] board) {
		
	}

	/* TODO : Prints out the given board with ships hidden.
	 * Display any space that is not "X" or "O" as hiddenChar.
	 * Best used to show the AI's board because the player shouldn't know ship's locations.
	 * USES: -/-
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * String hiddenChar : What to show any hidden space (not "X" or "O") as
	 * 		hiddenChar.length() == 1
	 * ------RETURN VALUES------
	 * void
	 */
	public void printHiddenBoard(String[][] board, String hiddenChar) {
		
	}

	/* TODO : Walks the player through setting up their board.
	 * The player DOES NOT decide: Ship Lengths, Board Size
	 * The player DOES decide: Ship Locations, Ship Rotations
	 * Show the player the board before they place each ship.
	 * USES: placeShip(), shipAt(), printFullBoard()
	 * ------PARAMETERS------
	 * int[] shipLengths : The lengths of each ship the player will place.
	 * 		shipLengths.length > 0
	 * int[] shipChars : The characters of each ship the player will place.
	 * 		shipChars.length == shipLengths.length
	 * int rows : How many rows the new board will have.
	 * 		rows > 0
	 * int cols : How many columns the new board will have.
	 * 		cols > 0
	 * ------RETURN VALUES------
	 * RETURN the newly created player's board.
	 */
	public String[][] setPlayerBoard(int[] shipLengths, String[] shipChars, int rows, int cols) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Automatically sets up the AI's board.
	 * The player should not have to do anything for this step to happen.
	 * The AI cannot place ships on top of other ships, of off the board.
	 * USES: placeShip(), shipAt()
	 * ------PARAMETERS------
	 * int[] shipLengths : The lengths of each ship the AI will place.
	 * 		shipLengths.length > 0
	 * int[] shipChars : The characters of each ship the AI will place.
	 * 		shipChars.length == shipLengths.length
	 * int rows : How many rows the new board will have.
	 * 		rows > 0
	 * int cols : How many columns the new board will have.
	 * 		cols > 0
	 * ------RETURN VALUES------
	 * RETURN the newly created AI's board.
	 */
	public String[][] setAIBoard(int[] shipLengths, String[] shipChars, int rows, int cols) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Given the enemy's board, returns where the AI will fire.
	 * The returned coordinates must have not been shot at yet.
	 * 		An "X" or "O" designates a space that has been shot at.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * RETURN an int[] of length 2. [0] is the row to shoot, [1] is the column to shoot.			
	 */
	public int[] getAIMove(String[][] board) {
		
		return new int[1]; // TODO Default return statement, replace.
	}

	/* TODO : Fires a shot at a board.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * int row : The row in board to fire at.
	 * 		row > 0, row < board.length
	 * int col : The column in board to fire at.
	 * 		col > 0, col < board[0].length
	 * ------RETURN VALUES------
	 * IF there is a ship at board[row][col],
	 * 		RETURN board with an "X" at board[row][col]. This denotes a "hit".
	 * ELSE
	 * 		RETURN board with an "O" at board[row][col]. This denotes a "miss".
	 */
	public String[][] shootAt(String[][] board, int row, int col) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Calculates and applies the AI's move to a given board.
	 * USES: getAIMove(), shootAt()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's (player's) board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * RETURN the board with a shot taken to it.
	 */
	public String[][] takeAIMove(String[][] board) {
		
		return new String[1][1]; // TODO Default return statement, replace.
	}

	/* TODO : Looks to see which ships are destroyed.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board which is being checked.
	 * 		board.length > 0, board[0].length > 0
	 * String[] ships : The characters which represent ships in this board.
	 * 		ships.length > 0
	 * ------RETURN VALUES------
	 * RETURN a String[] which contains each character in ships which is no longer in board, i.e. has been destroyed.
	 */
	public String[] checkDestruction(String[][] board, String[] ships) {
		
		return new String[1]; // TODO Default return statement, replace.
	}

	/* TODO : Checks to see if the given board has been destroyed (no ships left).
	 * USES: checkDestruction()
	 * ------PARAMETERS------
	 * String[][] board : The board which is being checked.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * IF all ships in the board have been destroyed (i.e. board is comprised of only " ", "X", and "O"),
	 * 		RETURN true.
	 * ELSE
	 * 		RETURN false.
	 */
	public boolean checkVictory(String[][] board, String[] ships) {
		
		return false; // TODO Default return statement, replace.
	}
}

import java.util.ArrayList;

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

/* This is the interface for Battleship.
 * 
 * Each method description includes USES, PARAMETERS, and RETURN VALUES.
 * USES are methods in this interface that are recommended to be used in the function.
 * PARAMETERS are descriptions of each parameter in the function header. 
 * 				Listed under each description are restrictions on the parameter's value.
 * RETURN VALUES are descriptions of what to return and when.
 * 				Pay attention to AND/OR requirements.
 * 
 * Assume entered variables are valid.
 * 
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
 * 
 */

public interface BattleshipInterface {
	/* Checks to see if there is a ship block somewhere.
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
	public boolean shipAt(String[][] board, int row, int col);
	
	/* Places a ship into the board given its location.
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
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp);
	
	/* Prints out the given board with everything revealed,
	 *  including ships, water, hits, and misses.
	 * Best used to show the player's board as they are allowed to see their own ships.
	 * USES: -/-
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * void
	 */
	public void printFullBoard(String[][] board);
	
	/* Prints out the given board with ships hidden.
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
	public void printHiddenBoard(String[][] board, String hiddenChar);
	
	/* Walks the player through setting up their board.
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
	public String[][] setPlayerBoard(int[] shipLengths, String[] shipChars, int rows, int cols);
	
	/* Automatically sets up the AI's board.
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
	public String[][] setAIBoard(int[] shipLengths, String[] shipChars, int rows, int cols);
	
	/* Given the enemy's board, returns where the AI will fire.
	 * The returned coordinates must have not been shot at yet.
	 * 		An "X" or "O" designates a space that has been shot at.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * RETURN an int[] of length 2. [0] is the row to shoot, [1] is the column to shoot.			
	 */
	public int[] getAIMove(String[][] board);
	
	/* Fires a shot at a board.
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
	public String[][] shootAt(String[][] board, int row, int col);
	
	/* Calculates and applies the AI's move to a given board.
	 * USES: getAIMove(), shootAt()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's (player's) board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * RETURN the board with a shot taken to it.
	 */
	public String[][] takeAIMove(String[][] board);
	
	/* Looks to see which ships are destroyed.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board which is being checked.
	 * 		board.length > 0, board[0].length > 0
	 * String[] ships : The characters which represent ships in this board.
	 * 		ships.length > 0
	 * ------RETURN VALUES------
	 * RETURN a String[] which contains each character in ships which is no longer in board, i.e. has been destroyed.
	 */
	public String[] checkDestruction(String[][] board, String[] ships);
	
	/* Checks to see if the given board has been destroyed (no ships)
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
	public boolean checkVictory(String[][] board, String[] ships);
}

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
 * public class <Your Game's Name> implements BattleshipInterface { ... }
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

public interface BattleshipInterface {
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
	 * IF the ship can fit inside the board, 
	 * 		AND it does not cover another ship,
	 * 		RETURN the board with the ship added.
	 * ELSE
	 * 		RETURN the original board with no changes made. This denotes failure to execute properly.
	 */
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp);
	
	/* Walks the player through setting up their board.
	 * The player DOES NOT decide: Ship Lengths, Board Size
	 * The player DOES decide: Ship Locations, Ship Rotations
	 * The player cannot place ships on top of other ships, of off the board.
	 * USES: placeShip(), shipAt()
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
	
	/* Finds and applies the AI's move to a given board.
	 * USES: getAIMove()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's (player's) board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * RETURN the board with a shot taken to it.
	 */
	public String[][] takeAIMove(String[][] board);
	
	
	/* Checks to see if there is a ship block somewhere.
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * int row : The row in board to check.
	 * 		row > 0, row < board.length
	 * int col : The column in board to check.
	 * 		col > 0, col < board[0].length
	 * ------RETURN VALUES------
	 * IF there is  a ship block at board[row][col],
	 * 		AND all variables are valid,
	 * 		RETURN true.
	 * ELSE
	 * 		RETURN false.
	 * This function can not fail to execute.
	 */
	public boolean shipAt(String[][] board, int row, int col);
	
	/* Fires a shot at a board.
	 * USES shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board.
	 * 		board.length > 0, board[0].length > 0
	 * int row : The row in board to fire at.
	 * 		row > 0, row < board.length
	 * int col : The column in board to fire at.
	 * 		col > 0, col < board[0].length
	 * ------RETURN VALUES------
	 * IF there is a ship at board[row][col],
	 * 		AND all variables are valid,
	 * 		RETURN board with an "X" at board[row][col]. This denotes a "hit".
	 * ELSE IF there is not a ship at board[row][col],
	 * 		AND board[row][col] has not yet been shot at,
	 * 		AND all variables are valid,
	 * 		RETURN board with an "O" at board[row][col]. This denotes a "miss".
	 * ELSE 
	 * 		RETURN board with no changes. This denotes a failure to execute properly.
	 */
	public String[][] shootAt(String[][] board, int row, int col);
	
	/* Looks to see which ships are destroyed.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The board which is being checked.
	 * 		board.length > 0, board[0].length > 0
	 * String[] ships : The characters which represent ships.
	 * 		ships.length > 0
	 * ------RETURN VALUES------
	 * IF all entered variables are valid,
	 * 		RETURN a String[] which contains each character in ships which is no longer in board, i.e. has been destroyed.
	 * ELSE
	 * 		RETURN an empty String[]
	 */
	public String[] checkDestruction(String[][] board, String[] ships);
	
	/* <Description of what this method does>
	 * ------PARAMETERS------
	 * String[][] board : The board which is being checked.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * IF all ships in the board have been destroyed, (i.e. board is comprised of only " ", "X", and "O")
	 * 		AND all entered variables are valid,
	 * 		RETURN true.
	 * ELSE
	 * 		RETURN false.
	 */
	public boolean checkVictory(String[][] board);
}

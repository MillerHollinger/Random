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
 * USES are methods in this interface that can or should be used inside this function.
 * PARAMETERS are descriptions of each parameter in the function header. 
 * 				Listed under each description are restrictions on the parameter's value.
 * RETURN VALUES are descriptions of what to return and when.
 * 				Pay attention to AND/OR requirements.
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
	 * IF all entered PARAMETERS are valid,
	 * 		AND the ship can fit inside the board, 
	 * 		AND it does not cover another ship,
	 * 		RETURN the board with the ship added.
	 * ELSE
	 * 		RETURN the original board with no changes made. This denotes failure to execute properly.
	 */
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp);
	
	/* Walks the player through setting up their board.
	 * The player must decide exactly where each ship will go.
	 * The player must be able to restart the process.
	 * USES: placeShip(), shipAt()
	 * ------PARAMETERS------
	 * int[] shipLengths : The lengths of each ship the player will place.
	 * 		shipLengths.length > 0
	 * int rows : How many rows the new board will have.
	 * 		rows > 0
	 * int cols : How many columns the new board will have.
	 * 		cols > 0
	 * ------RETURN VALUES------
	 * IF all entered PARAMETERS are valid,
	 * 		AND the player successfully placed all their ships,
	 * 		RETURN the newly created player's board.
	 * 				Any space without a ship block is simply a single space: " ".
	 * ELSE
	 * 		RETURN a String[][] with 1 row and 1 col. [0][0] == -1. This denotes failure to execute properly.
	 */
	public String[][] setPlayerBoard(int[] shipLengths, int rows, int cols);
	
	/* Automatically sets up the AI's board.
	 * The player must not need to input anything for this step to happen.
	 * USES: placeShip(), shipAt()
	 * ------PARAMETERS------
	 * int rows : How many rows the new board will have.
	 * 		rows > 0
	 * int cols : How many columns the new board will have.
	 * 		cols > 0
	 * ------RETURN VALUES------
	 * RETURN the newly created AI's board.
	 * This function can not fail to execute.
	 */
	public String[][] setAIBoard(int rows, int cols);
	
	/* Given the enemy's board, returns where the AI will fire.
	 * USES: shipAt()
	 * ------PARAMETERS------
	 * String[][] board : The enemy's board.
	 * 		board.length > 0, board[0].length > 0
	 * ------RETURN VALUES------
	 * IF all entered parameters are valid,
	 * 		RETURN an int[] of length 2. [0] is the row to shoot, [1] is the column to shoot.
	 * 				The returned coordinates must have not been shot at yet.
	 * ELSE
	 * 		RETURN an int[] of length 1. [0] == -1. This denotes failure to execute properly.
	 */
	public int[] getAIMove(String[][] board);
	
	// TODO
	public String[] takeAIMove();
	
	
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
	 * 		RETURN board with an X at board[row][col]. This denotes a "hit".
	 * ELSE IF there is not a ship at board[row][col],
	 * 		AND board[row][col] has not yet been shot at,
	 * 		AND all variables are valid,
	 * 		RETURN board with an O at board[row][col]. This denotes a "miss".
	 * ELSE 
	 * 		RETURN board with no changes. THis denotes a failure to execute properly.
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
	 * RETURNS a String[] which contains each character which is no longer in board, i.e. has been destroyed.
	 * This function can not fail to execute.
	 */
	public String[] checkDestruction(String[][] board, String[] ships);
	
	// TODO
	public boolean checkVictory(String[][] board);
}

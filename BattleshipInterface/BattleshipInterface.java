import java.util.ArrayList;

public interface BattleshipInterface {
	
	public boolean shipAt(String[][] board, int row, int col);
	
	public String[][] placeShip(String[][] board, int len, int sRow, int sCol, boolean isVert, String disp);
	
	public void printFullBoard(String[][] board);
	
	public void printHiddenBoard(String[][] board, String hiddenChar);
	
	public String[][] setPlayerBoard(int[] shipLengths, String[] shipChars, int rows, int cols);
	
	public String[][] setAIBoard(int[] shipLengths, String[] shipChars, int rows, int cols);
	
	public int[] getAIMove(String[][] board);
	
	public String[][] shootAt(String[][] board, int row, int col);
	
	public String[][] takeAIMove(String[][] board);
	
	public String[] checkDestruction(String[][] board, String[] ships);
	
	public boolean checkVictory(String[][] board, String[] ships);
}

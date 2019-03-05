import java.util.*;
public class Stretch
{
   public static void main(String[] args)
   {
      Scanner userInput = new Scanner(System.in);
      for (int runs = 0; runs < 5; runs++)
      {
         Block[][] board; // the board
         String input = userInput.nextLine(); // raw input
         String[] inputArrStrs = input.split(" "); // split input
         int[] inputArr = new int[inputArrStrs.length];
         for (int i = 0; i < inputArrStrs.length; i++)
         {
            inputArr[i] = Integer.parseInt(inputArrStrs[i]);
            System.out.println(inputArr[i]); // DEBUG
         }
         int rows = inputArr[0];
         int cols = inputArr[1];
         int start = inputArr[2] - 1;
         int blocks = inputArr[3];
         Block[] board1d = new Block[rows*cols]; // the board before it's been made 2d
      // set up board1d
         for (int i = 0; i < board1d.length; i++)
            board1d[i] = new Block(false, false, false);
      // add blocks
         for (int i = 4; i < 4 + blocks; i++)
         {
            board1d[inputArr[i] - 1].blocked = true;
         }
         board1d[start].starter = true;
         board = new Block[rows][cols];
      // reformat board as 2d array
         int curRow = 0;
         int curCol = 0; 
         for (int i = 0; i < board1d.length; i++)
         {
            try
            {
               board[curRow][curCol] = board1d[i];
            }
            catch (Exception e)
            {
               System.out.println(e);
            }
            curCol++;
            if (curCol == cols)
            {
               curRow++;
               curCol = 0;
            }
         }
      
      // DEBUG PRINTER
         for (int i = 0; i < board.length; i++)
         {
            for (int j = 0; j < board[0].length; j++)
            {
               System.out.print(board[i][j]);
            }
            System.out.println();
         }
      // END DEBUG
      
         boolean goingRight = false; // the direction of placement. true = rightwards (left to right); false = leftwards (right to left)
      // determine direction
         if (start % cols == 0)
         {
            goingRight = true;
            System.out.println("GOING RIGHT"); // DEBUG
         }
         if (start % cols == 1)
         {
            goingRight = false;
            System.out.println("GOING LEFT"); // DEBUG
         }
      
         boolean complete = false;
         ArrayList<String> placeOrder = new ArrayList<String>(); // the order blocks have been placed in. as output is always left to right, leftwards placements will be inverted in post.
         int activeRow = 0; // the last placed connector piece's row.
         int activeCol = 0; // the last placed connector piece's col.
      // find start location
         for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
            {
               if (board[i][j].starter == true)
               {
                  activeRow = i;
                  activeCol = j - 1;
                  System.out.println("Start location is: " + i + ", " + j); // DEBUG
               }
            }
      
         System.out.println(" blocked? "+board[0][0].blocked);
      
      // begin to place blocks:
         while (!complete)
         {
         // attempt to place A
            try
            {
               System.out.println("Flag!1"); // DEBUG
               if (goingRight)
               {
                  System.out.println("Flag!2"); // DEBUG
               // check
                  if (board[activeRow][activeCol + 2].blocked == false)
                  {
                     System.out.println("A : Not blocked 2 cols away"); // DEBUG
                     if (board[activeRow][activeCol + 3].blocked == false)
                     {
                        placeOrder.add("A");
                        activeCol += 3;
                        System.out.println("Placing A"); // DEBUG
                     }
                  }
               }
               else
               {
                  if (board[activeRow][activeCol - 2].blocked == false)
                  {
                     if (board[activeRow][activeCol - 3].blocked == false)
                     {
                        placeOrder.add("A");
                        activeCol -= 3;
                        System.out.println("Placing A"); // DEBUG
                     }
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println("Failed to place A");
            }
            System.out.println("ActiveRow " + activeRow + " ActiveCol " + activeCol); // DEBUG
         // attempt to place B
            try
            {
               if (goingRight)
               {
                  // check
                  if (board[activeRow + 1][activeCol + 1].blocked == false)
                  {
                     System.out.println("B : Not blocked 1 row, 1 col away"); // DEBUG
                     if (board[activeRow + 1][activeCol + 2].blocked == false)
                     {
                        // update row and col
                        placeOrder.add("B");
                        activeRow += 1;
                        activeCol += 2;
                        System.out.println("Placing B"); // DEBUG
                     }
                  }
                  
               }
               else
               {
                  // check
                  if (board[activeRow][activeCol - 2].blocked == false)
                  {
                     if (board[activeRow - 1][activeCol - 2].blocked == false)
                     {
                        // update row and col
                        placeOrder.add("B");
                        activeRow -= 1;
                        activeCol -= 2;
                        System.out.println("Placing B"); // DEBUG
                     }
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println("Failed to place B");
            }
            System.out.println("ActiveRow " + activeRow + " ActiveCol " + activeCol); // DEBUG
         // attempt to place C
            try
            {
               if (goingRight)
               {
                  // check 
                  if (board[activeRow][activeCol + 2].blocked == false)
                  {
                     System.out.println("C : Not blocked 2 col away" + board[activeRow][activeCol + 2].blocked); // DEBUG
                     if (board[activeRow + 1][activeCol + 2].blocked == false)
                     {
                        System.out.println("C : Not blocked 1 row, 2 col away" + board[activeRow + 1][activeCol + 2].blocked); // DEBUG
                        if (board[activeRow + 2][activeCol + 2].blocked == false)
                        {
                           // update row and col
                           placeOrder.add("C");
                           activeRow += 2;
                           activeCol += 2;
                           System.out.println("Placing C"); // DEBUG
                        }
                     }
                  }
               }
               else
               {
                  // check 
                  if (board[activeRow - 1][activeCol - 1].blocked == false)
                  {
                     if (board[activeRow - 1][activeCol - 2].blocked == false)
                     {
                        if (board[activeRow - 2][activeCol - 2].blocked == false)
                        {
                           // update row and col
                           placeOrder.add("C");
                           activeRow -= 2;
                           activeCol -= 2;
                           System.out.println("Placing C"); // DEBUG
                        }
                     }
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println("Failed to place C");
            }
            
            
            System.out.println("ActiveRow " + activeRow + " ActiveCol " + activeCol); // DEBUG
          // check for complete
            if (goingRight)
            {
               if (activeCol == cols - 1)
               {
                  complete = true;
               }
            }
            else
            {
               if (activeCol == 0)
               {
                  complete = false;
               }
            }
         }
      
      // output:
         if (goingRight)
         {
         // goingRight = true: normal print
            for (int i = 0; i < placeOrder.size(); i++)
            {
               System.out.print(placeOrder.get(i));
            }
         }
         else
         {
         //            = false: reverse print
            for (int i = placeOrder.size() - 1; i > 0; i++)
            {
               System.out.print(placeOrder.get(i));
            }
         } 
         System.out.println();
      }
   }

   public static class Block
   {
      public boolean connector; // if this connects two blocks
      public boolean blocked; // if this is an impassable block space
      public boolean starter;
      public Block(boolean c, boolean b, boolean s)
      {
         connector = c;
         blocked = b;
         starter = s;
      }
    
      public String toString()
      {
         if (blocked && connector)
            return "ERR";
         if (blocked)
            return "[X]";
         if (connector)
            return "[O]";
         if (starter)
            return "[S]";
         return "[ ]";
      }
   }

}

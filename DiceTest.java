import java.util.Random;
// Plays a simple dice game.
// When you roll a 1:
//  - If you have two 1's, you lose BET.
//  - If you have three 1's, you lose BET * LOSS.
//  - Otherwise, reroll any non 1's.
// When you roll a 6: (After checking for 1's)
//  - Increase win to win * BONUS
//  - Reroll the 6.
// When you don't have to roll any more dice, and you didn't lose, you win BET * win.
public class DiceTest
{

   // Game info
   static final int NUM_DICE = 4; // Should be 3
   static final int GAMES = 100;
   static final int SIDES_PER_DIE = 4; // Should be 6 (or potentially 4)
      
      // Betting quantities
   static final int BET = 5;
   static double win = 2;
   static final double LOSS = 2;
   static final double BONUS = 1;
      
   static int[] dice = new int[NUM_DICE];
   static double rollerProfit = 0;
   static double bankProfit = 0;
   public static void main(String[] args)
   {
      boolean resolved = false;
      
      for (int i = 0; i < GAMES; i++)
      {
         System.out.println("Starting round --------------------------- " + i);  
         rollAll();
         
         resolved = false;
         win = 2;
         
         while (!resolved)
         {
            // Count ones.
            switch (countOnes())
            {
               case 0:
                  System.out.println("Zero Ones");
                  // Check for sixes
                  checkForSixes();
                  // If still no ones, player wins
                  if (countOnes() == 0)
                  {
                     System.out.println("The Roller wins: 0 Ones");
                     pay((int) (BET * win) - BET, false);
                     resolved = true;
                  }
                  break;
               case 1: 
                  System.out.println("One One");
                  // Reroll non-ones
                  rollNonOnes();
                  // Check for more ones
                  switch (countOnes())
                  {
                     case 1:
                        if(!checkForSixes()) // No sixes
                        {
                           System.out.println("The Roller Wins: 1 One");
                           pay((int) (BET * win) - BET, false);
                           resolved = true;
                        }
                        else if (countOnes() == 1)
                        {
                           System.out.println("The Roller Wins: 1 One");
                           pay((int) (BET * win) - BET, false);
                           resolved = true;
                        }
                        break;
                     case 2: // Loss
                        System.out.println("The Bank Wins: 2 Ones");
                        pay(BET, true);
                        resolved = true;
                        break;
                     case 3: // Big Loss
                     case 4:
                        System.out.println("The Bank Wins+: 3 Ones");
                        pay((int)(BET * LOSS), true);
                        resolved = true;
                        break;
                  }
                  break;
               case 2: // Loss
                  System.out.println("The Bank Wins: 2 Ones");
                  pay((int)(BET), true);
                  resolved = true;
                  break;
               case 3: // Big Loss
               case 4:
                  System.out.println("The Bank Wins+: 3 Ones");
                  pay((int)(BET * LOSS), true);
                  resolved = true;
                  break;
               
            }
         }
      }
      System.out.println("Games over -- RESULTS ###########################################");
      System.out.println("Roller Profit: " + rollerProfit);
      System.out.println("Bank Profit: " + bankProfit);
   }
   
   public static void pay(int cost, boolean rollerPaying)
   {
      if (rollerPaying)
      {
         rollerProfit -= cost;
         bankProfit += cost;
      }
      else
      {
         rollerProfit += cost;
         bankProfit -= cost;
      }
   }
   
   public static void rollAll()
   {
      Random rn = new Random();
      for (int i = 0; i < dice.length; i++)
         dice[i] = rn.nextInt(SIDES_PER_DIE) + 1;
      System.out.println("Rolled dice.");  
      for (int j = 0; j < dice.length; j++)  
         System.out.print(dice[j] + " ");  
      System.out.println();  
   }
   
   public static int countOnes()
   {
      int count = 0;
      for (int i = 0; i < dice.length; i++)
         if (dice[i] == 1)
            count++;
      System.out.println("Counted ones: " + count);  
      return count;
   }
   
   public static void rollNonOnes()
   {
      Random rn = new Random();
      for (int i = 0; i < dice.length; i++)
         if (dice[i] != 1)
            dice[i] = rn.nextInt(SIDES_PER_DIE) + 1;
      System.out.println("Rolled non-ones.");  
      for (int j = 0; j < dice.length; j++)  
         System.out.print(dice[j] + " ");  
      System.out.println();  
   }
   
   public static boolean checkForSixes() // FOURS
   {
      Random rn = new Random();
      boolean six = false;
      for (int i = 0; i < dice.length; i++)
         if (dice[i] == 4)
         {
            win += BONUS;
            dice[i] = rn.nextInt(SIDES_PER_DIE) + 1;
            six = true;
         }
      System.out.println("Checked for FOURS:" + six);  
      System.out.println("Dice are now :");
      for (int j = 0; j < dice.length; j++)  
         System.out.print(dice[j] + " ");  
      System.out.println();  
      return six;
   }
   
}
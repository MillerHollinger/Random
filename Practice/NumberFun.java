import java.util.*;
public class NumberFun
{
   public static void main(String[] args)
   {
      Scanner userInput = new Scanner(System.in);
      for (int i = Integer.parseInt(userInput.nextLine()); i > 0; i--)
      {
         int a = userInput.nextInt();
         int b = userInput.nextInt();
         int result = userInput.nextInt(); userInput.nextLine();
         if (a + b == result ||
             a / (b * 1.0) == result ||
             Math.abs(a-b) == result ||
             b / (a * 1.0) == result ||
             a * b == result)
             System.out.println("Possible");
         else
            System.out.println("Impossible");
      }
   }
}
import java.util.*;
public class Limits
{
   public static void main(String[] args)
   {
      Scanner userInput = new Scanner(System.in);
      
      ArrayList<Long> found = new ArrayList<Long>();
      
      long current = userInput.nextLong();
      
      while (!found.contains(current))
      {
         found.add(current);
         current = spliteval(current);
      }
      
      long sum = 0;
      for (long i : found)
         sum += i;
         
      System.out.println(found);
   }
   
   public static long spliteval(long x)
   {
      long result = 0;
      while (x > 0)
      {
         result += Math.pow(x % 10, 3);
         x /= 10;
      }
      return result;
   }
}
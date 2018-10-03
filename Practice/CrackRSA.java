import java.util.*;
public class CrackRSA
{
   public static void main(String[] args)
   {
      Scanner userInput = new Scanner(System.in);
      ArrayList<Integer> primes = new ArrayList<Integer>();
      // Generate a list of primes
      for (int i = 2; i < 1000; i++)
      {
         primes.add(i); // add number
         for (int j = 2; j < i; j++)
            if (i % j == 0) // if it's not prime...
            {
               primes.remove(primes.size() - 1); // .. remove it
               j = i; // and end this run of the loop
            }
      }
      for (int i = Integer.parseInt(userInput.nextLine()); i > 0; i--)
      {
         long n = userInput.nextInt();
         long e = userInput.nextInt();
         // n = p * q, where p and q are primes
         long p = 0, q = 0;
         for (int a = 0; a < primes.size(); a++) // index of prospective prime
            for (int b = a + 1; b < primes.size(); b++) // index of second prospective prime
               if (primes.get(a) * primes.get(b) == n)
               {
                  //System.out.println("p = " + primes.get(a) + "; q = " + primes.get(b));
                  p = primes.get(a);
                  q = primes.get(b);
               }
         // (d * e) % ((p-1)*(q-1)) == 1
         for (int d = 0; d < (p-1) * (q-1); d++)
            if ((d * e) % ( (p - 1) * (q - 1) ) == 1)
               System.out.println(d);
      }
   }
}
import java.util.*;

public class SieveOfEratosthenes {
    
    public static void findPrimes(int n) {
        // Create a boolean array "isPrime[0..n]" 
        // true represents that the index is a prime number, false means composite
        boolean[] isPrime = new boolean[n + 1];
        
        // Initialize all entries as true. 
        Arrays.fill(isPrime, true);
        
        // 0 and 1 are not prime numbers
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        
        // Loop from 2 up to the square root of n
        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is not changed, then it is a prime
            if (isPrime[p]) {
                // Update all multiples of p greater than or equal to the square of it
                // Numbers less than p*p have already been marked by smaller primes
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        // Print all prime numbers
        System.out.println("Prime numbers up to " + n + ":");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int limit = 30; 
        findPrimes(limit);
    }
}

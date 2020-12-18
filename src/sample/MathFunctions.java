package sample;

// This class contains functions that produce a specific answer (nth factorial, nth Fibonacci number, etc.)
public class MathFunctions {

    // Produces the factorial of the number n
    // * Input: Natural Number 'n'
    // * Output: n!
    public static long factorial (long n) {
        if (n == 0) return 1;
        else return n * factorial(--n);
    }

    // Wrapper function for fibonacci number function
    // * Input: Natural Number 'n'
    // * Output: nth fibonacci number
    public static long fibonacci(long n) {
        return produceFibonacciNumber(0, 1, n);
    }

    // Produces the nth fibonacci number
    // * Input: Natural Numbers n0, n1, n, where:
    //          n0, n1 are the first two Fibonacci numbers.
    //          n is the nth Fibonacci number to produce
    // * Output: nth fibonacci number
    private static long produceFibonacciNumber(long n0, long n1, long n) {
        if (n == 0)  return n0;
        else return produceFibonacciNumber(n1, (n0 + n1), --n);
    }

    // Produces the greatest common divisor of two integers
    // * Input: Integers 'a', 'b'
    // * Output: The greatest common divisor of a, b, using the Euclidean method
    public static long gcd(long a, long b) {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
    
}

package sample;

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
    public static long fibonacciWrapper(long n) {
        return fibonacci(0, 1, n);
    }

    // Produces the nth fibonacci number
    // * Input: Natural Number 'n'
    // * Output: nth fibonacci number
    public static long fibonacci(long n0, long n1, long n) {
        if (n == 0)  return n0;
        else return fibonacci (n1, (n0 + n1), --n);
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

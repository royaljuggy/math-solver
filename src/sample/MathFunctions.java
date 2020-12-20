package sample;

import javafx.application.Application;

// This class contains functions that produce a specific answer (nth factorial, nth Fibonacci number, etc.)
public class MathFunctions {

    /**
     * Produces the factorial of n
     * @param n a non-negative integer to apply the factorial to
     * @return The factorial of parameter n, also known as: n!
     */
    public static long factorial(long n) {
        if (n == 0) return 1;
        else return n * factorial(--n);
    }

    /**
     * Produces a string message indicating the factorial of n
     * @param n a non-negative integer to apply the factorial to
     * @return A string message of the factorial of parameter n
     */
    public static String factorialString(long n) {
        return "The factorial of n = " + n + " is " + factorial(n);
    }

    /**
     * Produces the nth Fibonacci number
     * @param n a non-negative integer to find the nth Fibonacci number
     * @return The nth Fibonacci number
     */
    public static long fibonacci(long n) {
        return produceFibonacciNumber(0, 1, n);
    }

    /**
     * Produces a string message indicating the nth Fibonacci number
     * @param n a non-negative integer to find the nth Fibonacci number
     * @return A string message of the nth Fibonacci number
     */
    public static String fibonacciString(long n) {
        return "The n = " + n + " Fibonacci number is " + fibonacci(n);
    }

    /**
     * Produces the nth Fibonacci number, starting at the first two Fibonacci numbers
     * @param n_i the first of a pair of Fibonacci numbers
     * @param n_i1 the second of a pair of Fibonacci numbers
     * @param n a natural number indicating the nth Fibonacci number to produce
     * > Parameter requirements: n_i, n_i1 start at 0, 1, the 0th and 1st Fibonacci numbers (we are using simple recursion to produce values)
     *                           n is non-negative
     * @return The nth Fibonacci number
     */
    private static long produceFibonacciNumber(long n_i, long n_i1, long n) {
        if (n == 0)  return n_i;
        else return produceFibonacciNumber(n_i1, (n_i + n_i1), --n);
    }

    /**
     * Produces the greatest common divisor of two integers using the Euclidean method
     * @param a any integer
     * @param b any integer
     * @return The greatest common divisor of a and b
     */
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

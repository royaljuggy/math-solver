package sample;

// This class contains functions that solve some mathematical equation (Linear Diophantine equations, etc.)
public class MathSolvers {
    
    /**
     * Produces a string indicating the integer solutions of x,y to the equation ax+by=c (for integer constants a,b,c)
     * @param a a constant of ax+by=c
     * @param b a constant of ax+by=c
     * @param c a constant of ax+by=c
     * @return A string to indicate integer solutions x,y
     */
    public static String linearDiophantine(long a, long b, long c) {
        // Temporary values to send into linearDiophantineSolver method
        long[] tempX = new long[2];
        long[] tempY = new long[2];
        long[] tempQ = new long[2];
        long[] tempR = new long[2];
        String output = "";

        // Initializing our starting rows:
        // > Row1: 1, 0, a, 0; Row2: 0, 1, b, 0
        tempX[0] = 1;
        tempX[1] = 0;

        tempY[0] = 0;
        tempY[1] = 1;

        tempQ[0] = Math.abs(a); // We need a and b to be non-negative to use the Extended Euclidean Algorithm
        tempQ[1] = Math.abs(b);

        tempR[0] = 0;
        tempR[1] = 0;

        long[] solution = certificateOfCorrectness(tempX, tempY, tempQ, tempR);

        if (c % solution[2] == 0) { // We note that integer solutions of (x,y) to the equation ax+by=c exist if and only if gcd(a,b) divides c.
            output = "One solution to " + a + "x+" + b + "y=" + c + " is x = " +
                    (solution[0] * c * (a/Math.abs(a))) + " y = " + (solution[1] * c * (b/Math.abs(b)));
        } else {
            output = "This particular linear Diophantine equation has no x,y integer solutions.";
        }

        return output;
    }

    /**
     * Gives a particular solution to the linear Diophantine Equation (ax + by = gcd(a, b)) using the Extended Euclidean Algorithm
     *      > We call this solution the certificate of correctness.
     *      * Input: Size 2 Integer Arrays x, y, q, r, where the first element represents the i-2 row, and the second element represents the i-1row
     *      Example input: Row1: 1, 0, a, 0; Row2: 0, 1, b, 0
     * @param x two rows of the x column in the Extended Euclidean Algorithm table
     * @param y two rows of the y column in the Extended Euclidean Algorithm table
     * @param q two rows of the q column in the Extended Euclidean Algorithm table
     * @param r two rows of the r column in the Extended Euclidean Algorithm table
     * @return A long array, where the first element is the x solution, the second is the y solution, and the third is the greatest common divisor of a and b
     */
    private static long[] certificateOfCorrectness(long[] x, long[] y, long[] q, long[] r) {

        // Recursive Base Case
        if (q[1] == 0) {
            long[] temp = new long[3];
            // x,y are your particular solution, q is your GCD of (a, b)
            temp[0] = x[0];
            temp[1] = y[0];
            temp[2] = q[0];
            return temp;
        } else {
            long newQ = Math.floorDiv(q[0], q[1]);
            long[] tempX = new long[2];
            long[] tempY = new long[2];
            long[] tempQ = new long[2];
            long[] tempR = new long[2];

            // Initializing the next row
            tempX[0] = x[1];
            tempX[1] = x[0] - (newQ * x[1]);

            tempY[0] = y[1];
            tempY[1] = y[0] - (newQ * y[1]);

            tempQ[0] = q[1];
            tempQ[1] = q[0] % q[1];

            tempR[0] = r[1];
            tempR[1] = newQ;

            return certificateOfCorrectness(tempX, tempY, tempQ, tempR);
        }

    }

}

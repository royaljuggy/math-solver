package sample;

public class MathSolvers {

    // Produces a set of integer solutions to the equation ax + by = c (where a,b,c are constant coefficients with a, b non-zero)
    // If no solutions exist, produce the empty array
    // * Input: Integers a, b, c
    // * Output: String of the set of solutions
    public static String linearDiophantine(long a, long b, long c) {
        // Temporary values to send into linearDiophantineSolver method/output
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
            output = "One solution to " + a + "x+" + b + "y=" + c + " is x = " + (solution[0] * c * (a/Math.abs(a))) + " y = " + (solution[1] * c * (b/Math.abs(b)));
        } else {
            output = "This particular linear Diophantine equation has no x,y integer solutions.";
        }

        return output;
    }

    // Gives a particular solution to the linear Diophantine Equation (ax + by = gcd(a, b)) using the Extended Euclidean Algorithm
    // > We call this solution the certificate of correctness.
    // * Input: Size 2 Integer Arrays x, y, q, r, where the first element represents the i-2 row, and the second element represents the i-1row
    // Example input: Row1: 1, 0, a, 0; Row2: 0, 1, b, 0
    // ** > Requires: a,b are non-negative integers
    // * Output: Integer array, where the first element is the x solution, and the second element is the y solution
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

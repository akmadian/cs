import java.util.*;

/**
 * TODO:
 * 
 * @author 
 *
 */
public class RPN {

    /**
     * TODO: 
     * 
     * @param args
     */
    public static void main(String[] args) {
        testRPN();
    }

    /**
     * Tests the RPN evaluator
     */
    public static void testRPN() {
        String[] tests = {
                "2 2 +", 
                "2 3 -", 
                "4 5 *", 
                "6 5 /", 
                "1 2 3 4 5 6 7 8 9 + + + + + + + +",
        "5 2 2 * - 1 2 + /"};
        double[] results = {4.0,-1,20,1.2,45, 0.3333333333333333};

        for (int i = 0; i < tests.length; i++) {
            System.out.println(String.format("Evaluating => %s", tests[i]));
            double result = evaluateRPN(tests[i]);
            System.out.println(String.format("Result => %s", result));
            if (result != results[i]) {
                System.out.println(String.format("Error on test %s expected %s, received %s", 
                        tests[i], results[i], evaluateRPN(tests[i])));
                return;
            }
        }
        System.out.println("Congratulations - you passed the tests");

    }
    
    /**
     * TODO:     
     *  
     * @param input - TODO
     * @return double - TODO
     */
    public static double evaluateRPN(String input) {
        // Create queue, transfer input into it "2 3 +" -> [2 3 +]

        // Print input

        // Create new empty stack

        // Pop off each item in the queue and evaluate it
                // if operator such as '*' - pop two operands, evaluate, push result: Queue [+] Stack [2 3] => [][5]

                // else operands such as "5" just need to be pushed [3 +][2]=>[+][2 3]

        // return last item in stack [][5]
    }

    /**
     * TODO:     
     *
     * @param input - TODO: 
     * @return boolean - TODO: 
     */
    private static boolean isOperator(String input) {
        // Check if the String is one of the + - * / characters 
        // Either compare against each character individually or use String.contains 
    }

    /**
     * TODO:     
     * 
     * evaluateBinaryOperator(2, "+", 3)=> "5"
     * 
     * @param op1 - TODO: 
     * @param operator - TODO: 
     * @param op2 - TODO: 
     * @return TODO: 
     */
    private static String evaluateBinaryOperator(Double op1, String operator, Double op2) {
        // Individual if/else or case switch to find operator + - * /
            // Return op1 <operator>
    }

}

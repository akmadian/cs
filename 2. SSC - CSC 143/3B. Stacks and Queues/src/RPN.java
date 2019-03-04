import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

/**
 * @author Ari Madian
 *
 *  Calculates the value of a mathematical expression in RPN format.
 */
public class RPN {

    /**
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
     * Evaluates a Reverse Polish Notation (RPN) expression.
     *  
     * @param input - A mathematical expression in RPN format.
     * @return double - The value of the given RPN expression.
     */
    public static double evaluateRPN(String input) {
        LinkedList<String> expression = new LinkedList<String>(
                Arrays.asList(input.split(" "))
        );
        int expressionSize = expression.size(); // Save original expression size
        System.out.println(expression);

        Stack<String> stack = new Stack<>();
        for (int op = 0; op < expressionSize; op++) {
            if (expression.size() <= 0) break; // If nothing left in expression to process
            if (isOperator(expression.get(op))) { // If looking at operator
                double operand2 = Double.parseDouble(stack.pop());
                double operand1 = Double.parseDouble(stack.pop());
                stack.push(evaluateBinaryOperator(operand1, expression.get(op), operand2)); // Calulate, push onto stack

                expression.remove(op); // Remove operator from expression
                op--;

                System.out.print(expression); System.out.println(stack);
            } else { // If NOT an operator
                stack.push(expression.get(op)); // Push operand onto stack

                expression.remove(op); // Remove operand from expression
                op--;
            }
        }

        return Double.parseDouble(stack.pop());
    }

    /**
     * Helper method to discern whether or not a given string value is a mathematical operator.
     * Ex: "*" -> true, "a" -> false, "+" -> true
     * @param input - The string value to test
     * @return boolean - Whether or not the given value is a mathematical operator.
     */
    private static boolean isOperator(String input) {
        // Check if the String is one of the + - * / characters 
        // Either compare against each character individually or use String.contains
        return (input.equals("*") || input.equals("/") || input.equals("+") || input.equals("-"));
    }

    /**
     * Helper method to compute the value of a two-operand expression.
     * 
     * evaluateBinaryOperator(2, "+", 3)=> "5"
     * 
     * @param op1 - The left operand of the expression
     * @param operator - The operator of the expression
     * @param op2 - The right operand of the expression
     * @return The expression's value.
     */
    private static String evaluateBinaryOperator(Double op1, String operator, Double op2) {
        switch (operator) {
            case "*": return String.valueOf(op1 * op2);
            case "+": return String.valueOf(op1 + op2);
            case "-": return String.valueOf(op1 - op2);
            case "/": return String.valueOf(op1 / op2);
        }

        return null;
    }

}

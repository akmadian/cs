import java.util.Scanner;
import java.math.*;
// Ari Madian
// CSC 142
// Ravi Gandham
// 10/20/18

public class Gradanator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("This program read exam/homework scores\nand reports your overall course grade.\n");
        double weightedMidtermScore = midtermOrFinal("Midterm:");
        double weightedFinalScore = midtermOrFinal("Final:");
        double weightedHomeWorkScore = homework();
        double totalGrade = weightedMidtermScore + weightedFinalScore + weightedHomeWorkScore;

        printFinalGrades(totalGrade);
    }

    /*
    <midtermOrFinal> - Gets all relevant values, calculates weighted midterm or final score.
        <param>   String mof - Either "Midterm:" or "Final:" tells the method what to print for the section header.
        <returns> double - The rounded, weighted, score for the given section.
     */
    private static double midtermOrFinal(String mof){
        System.out.println(mof);

        // Get Starting Values
        System.out.print("Weight (0-100)? "); double Weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Score Earned? "); double ScoreEarned = Double.parseDouble(scanner.nextLine());
        System.out.print("Were Scores Shifted (1=yes, 2=no)? "); int ScoresShifted = Integer.parseInt(scanner.nextLine());

        double weightScalar = Weight * .01;

        // If Scores Are Shifted, Get Shift Amount
        if (ScoresShifted == 1){
            System.out.print("Shift Amount? "); int shiftAmount = Integer.parseInt(scanner.nextLine());
            if ((shiftAmount + ScoreEarned) >=  100) { // Check to see if shifted amount is > 100, if yes, = 100.
                ScoreEarned = 100;
            } else {
                ScoreEarned += ScoresShifted;
            }
        }

        // Print totals
        printSectionFinal(ScoreEarned, 100.0, weightScalar);

        System.out.println();
        return round(ScoreEarned * weightScalar);
    }

    /*
    <homework> - Gets all relevant values, calculates weighted homework score.
        <return> double - The rounded, weighted, homework section score.
     */
    private static double homework(){
        double earnedPoints = 0.0;
        double totalPoints = 0.0;

        // Get Starting Values
        System.out.println("Homework:");
        System.out.print("Weight (0-100)? "); double Weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Number Of Assignments? "); int numAssignments = Integer.parseInt(scanner.nextLine());
        double weightScalar = Weight * .01;

        // For number of assignments given, get assignment score info.
        for (int assignmentNum = 1; assignmentNum <= numAssignments; assignmentNum++){
            // Get Input
            System.out.print("Assignment " + assignmentNum + " score and max? "); String scoreAndMax= scanner.nextLine();

            // Extract Values From String
            double earned = Double.parseDouble(scoreAndMax.substring(0, 2));
            double total = Double.parseDouble(scoreAndMax.substring(scoreAndMax.length() - 2, scoreAndMax.length()));

            // Add Points Up
            earnedPoints += earned;
            totalPoints += total;
        }

        // Section Points
        System.out.print("How many sections did you attend? "); int sectionsAttended = Integer.parseInt(scanner.nextLine());
        if ((sectionsAttended * 3) >= 20){ // Cap points if greater than max allowed
            earnedPoints += 20;
            System.out.println("Section Points = 20 / 20");
        } else {
            earnedPoints += sectionsAttended * 3;
            System.out.println("Section Points = " + sectionsAttended * 3 + " / 20");
        }
        totalPoints += 20;

        printSectionFinal(earnedPoints, totalPoints, weightScalar);

        System.out.println();
        return round((earnedPoints / totalPoints) * Weight);
    }

    /*
    <round> - Rounds a double to the first decimal point.
        <param>   double value - The value to round
        <returns> double - The rounded value
     */
    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /*
    <printSectionFinal> - Prints the total points earned out of points possible, and the weighted score for the section.
        <param> double earnedPoints - The number of points earned for the given section.
        <param> double totalPoints - The total number of points possible for the given section.
        <param> double scalarValue - The section's weighing scalar value.
     */
    private static void printSectionFinal(double earnedPoints, double totalPoints, double scalarValue){
        double oneHunTScalar = 100 * scalarValue;
        System.out.println("Total Points = " + round(earnedPoints) + " / " + (int)totalPoints);
        System.out.println("Weighted Score = " + round((earnedPoints * oneHunTScalar) / totalPoints) + " / " + (int)oneHunTScalar);

    }

    /*
    <printFinalGrades> - Prints the overall grade, rounded to the first decimal point, minimum GPA, and a custom message.
        <param> double grade - The student's total, rounded, grade.
     */
    private static void printFinalGrades(double grade){
        System.out.println("Overall Percentage = " + grade);
        if (grade >= 85.0){ System.out.println("Your grade will be at least: 3.0\nGood Job!");
        } else if (grade >= 75.0){ System.out.println("Your grade will be at least: 2.0\nNot too bad...");
        } else if (grade >= 60.0){ System.out.println("Your grade will be at least: 0.7\nYou should probably study more next time.");
        } else { System.out.println("Your grade will be at least: 0.0\nReally?"); }
    }
}

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Gradanator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("This program read exam/homework scores\nand reports your overall course grade.\n");
        double weightedMidtermScore = midtermOrFinal("Midterm:");
        double weightedFinalScore = midtermOrFinal("Final:");
        double weightedHomeWorkScore = homework();

        double grade = round(weightedMidtermScore + weightedFinalScore + weightedHomeWorkScore);
        double GPA = round((grade * 4.0) / 100.0);
        System.out.println("Overall Percentage = " + grade);
        System.out.println("Your Grade Will Be At Least: " + GPA);

    }

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

        System.out.println("");
        return ScoreEarned;
    }

    private static double homework(){
        double earnedPoints = 15.0;
        double totalPoints = 20.0;

        // Get Starting Values
        System.out.println("Homework:");
        System.out.print("Weight (0-100)? "); double Weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Number Of Assignments? "); int numAssignments = Integer.parseInt(scanner.nextLine());

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

        double weightScalar = Weight * .01;

        printSectionFinal(earnedPoints, totalPoints, weightScalar);

        System.out.println("");
        return (earnedPoints / totalPoints) * Weight;
    }

    private static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void printSectionFinal(double earnedPoints, double totalPoints, double scalarValue){
        System.out.println("Total Points = " + round(earnedPoints) + " / " + (int)totalPoints);
        System.out.println("Weighted Score = " + round(earnedPoints * scalarValue) + " / " + (int)(100 * scalarValue));
    }

    private static void printFinalGrades(double mid, double fin, double hom){
        double grade = round(mid + fin + hom);
        double GPA = round((grade * 4.0) / 100.0);
        System.out.println("Overall Percentage = " + grade);
        System.out.println("Your Grade Will Be At Least: " + GPA);
    }

}

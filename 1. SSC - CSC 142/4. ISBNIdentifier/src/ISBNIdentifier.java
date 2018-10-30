import java.util.Arrays;
import java.util.Scanner;

// ISBN Identifier
// Ari Madian
// CSC 142
// Ravi Gandham
// 10/30/18

public class ISBNIdentifier {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean quitFlag = false;

    public static void main(String[] args) {
        while (!quitFlag) {
            System.out.println("Please enter an ISBN to test.");
            System.out.print("ISBN: ");
            String inString = scanner.nextLine();
            if (inString.equals("q") || inString.equals("Q")) {
                quitFlag = true;
            } else {
                validateIsbn(inString);
            }
        }
    }

    /*
    <validateISBN> - Runs necessary checks on given ISBN.
        <param>   String ISBN - The ISBN to validate.
     */
    private static void validateIsbn(String ISBN){
        boolean formatValid = validateFormat(ISBN);
        boolean checkSumValid;

        // Only validate checksum if format is valid, will throw error otherwise.
        if (formatValid) {
            checkSumValid = validateCheckSum(ISBN);
            if (!checkSumValid){
                System.out.println("ISBN Checksum Invalid.");
            } else {
                System.out.println("ISBN Is Valid.");
            }
        }
        System.out.println("");
    }

    /*
    <validateFormat> - Runs formatting checks on given ISBN.
        <param>   String ISBN - The ISBN to validate.
        <returns> boolean - Whether or not the ISBN's format is valid, true if yes, false if no.
     */
    private static boolean validateFormat(String ISBN){
        int numDashes = countOccurrences(ISBN);
        String formattedISBN = ISBN.replace("-", ""); // Remove dashes from isbn, but not checksum
        String[] ISBNArray = formattedISBN.split("");
        String[] TruncISBNArray = Arrays.copyOf(ISBNArray, ISBNArray.length - 1); // ISBN without checksum
        int ISBNLength = formattedISBN.length();

        // Check case for invalid character in ISBN.
        for (String index : TruncISBNArray){
            try{ // Try to parse as int, will throw error if can't parse
                Integer.parseInt(index);
            } catch (NumberFormatException ex){
                System.out.println("Invalid ISBN - Invalid character in ISBN");
                return false;
            }
        }

        // Check case for ISBN length.
        if (ISBNLength > 10) { //
            System.out.println("Invalid ISBN - Too many digits.");
            return false;
        } else if (ISBNLength < 10){
            System.out.println("Invalid ISBN - Too few digits.");
            return false;
        }

        // Check case for correct amount of dashes.
        if (numDashes != 0){ // If ISBN has zero dashes.
            if (numDashes < 3){
                System.out.println("Invalid ISBN - Too few dashes.");
                return false;
            } else if (numDashes > 3){
                System.out.println("Invalid ISBN - Too many dashes.");
                return false;
            }
        }

        // Check cases for starting or ending dashes
        String[] UnformattedISBNArray = ISBN.split("");
        if (UnformattedISBNArray[0].equals("-")){
            System.out.println("Invalid ISBN - ISBN Starts with dash.");
            return false;
        }
        if (UnformattedISBNArray[UnformattedISBNArray.length - 1].equals("-")){
            System.out.println("Invalid ISBN - ISBN Ends with dash.");
            return false;
        }

        // Check case for sequential dashes.
        if (ISBN.contains("--")){
            System.out.println("Invalid ISBN - Sequential Dashes.");
            return false;
        }

        return true;
    }

    /*
    <validateFormat> - Runs a checkSum test on given ISBN.
        <param>   String ISBN - The ISBN to validate.
        <returns> boolean - Whether or not the ISBN's checksum is valid, true if yes, false if no.
     */
    private static boolean validateCheckSum(String ISBN){
        String formattedISBN = ISBN.replace("-", "");
        String[] ISBNArray = formattedISBN.split("");
        String[] TruncISBNArray = Arrays.copyOf(ISBNArray, ISBNArray.length - 1);
        int correctSum;
        int total = 0;

        // Set correct sum
        if (ISBNArray[ISBNArray.length - 1].equals("X")){
            correctSum = 10;
        } else {
            correctSum = Integer.parseInt(ISBNArray[ISBNArray.length - 1]);
        }

        for (int currIndex = 0; currIndex < TruncISBNArray.length; currIndex++){
            total += Integer.parseInt(TruncISBNArray[currIndex]) * (currIndex + 1);
        }

        return (total % 11) == correctSum;
    }

    /*
    <countOccurrences> - Counts the number of times - occurs in a given string.
        <param>   String str - The main string to check.
        <returns> int - The number of times that - occurs in str.
     */
    private static int countOccurrences(String str){
        return str.split("-", -1).length-1;
    }
}

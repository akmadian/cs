import java.util.Scanner;

public class DateTesting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // testAllMethods();

        // Get Today's Date
        System.out.print("Please enter today's date in the form (mm d yyyy): ");
        String todayIn = scanner.nextLine();
        String[] numbers1 = todayIn.split(" ");
        Date today = new Date(Integer.parseInt(numbers1[2]), Integer.parseInt(numbers1[0]), Integer.parseInt(numbers1[1]));

        // Get Birthday
        System.out.print("Please enter your birthday in the form (mm d yyyy): ");
        String bdayIn = scanner.nextLine();
        String[] numbers2 = bdayIn.split(" ");
        Date bday = new Date(Integer.parseInt(numbers2[2]), Integer.parseInt(numbers2[0]), Integer.parseInt(numbers2[1]));

        System.out.println("You were born: " + bday.toString() + ", which was a " + bday.getDayOfWeek());
        System.out.println("You are " + bday.advanceTo(today) + " days old.");
    }

    /*
    <resetDate> - Resets the date back to a standard (My Birthday)
     */
    private static void resetDate(Date date){
        date.day = 29;
        date.month = 6;
        date.year = 2000;
    }

    private static void testAllMethods() {
        Date test1 = new Date(2000, 6, 29);

        Date endOfMonth = new Date(2000, 6, 30);
        Date endOfYear = new Date(2000, 12, 31);
        Date febLeapYear = new Date(2016, 2, 29);

        // toString
        System.out.println("toString Test - " + test1.toString() + " - Expected: 2000/6/29");

        // equals
        System.out.println("equals Test - " + test1.equals(new Date(2000, 6, 29)) + " - Expected: True");
        System.out.println("equals Test - " + test1.equals(new Date(2001, 6, 29)) + " - Expected: False");

        // isLeapYear
        System.out.println("isLeapYear Test - " + new Date(2001, 6, 29).isLeapYear() + " - Expected: False");
        System.out.println("isLeapYear Test - " + new Date(2016, 6, 29).isLeapYear() + " - Expected: True");

        // nextDay
        System.out.print("nextDay Test (endOfMonth) - " + endOfMonth.toString());
        endOfMonth.nextDay();
        System.out.println(" -> " + endOfMonth.toString());

        System.out.print("nextDay Test (endOfYear) - " + endOfYear.toString());
        endOfYear.nextDay();
        System.out.println(" -> " + endOfYear.toString());

        System.out.print("nextDay Test (febLeapYear) - " + febLeapYear.toString());
        febLeapYear.nextDay();
        System.out.println(" -> " + febLeapYear.toString());

        // advanceTo
        // Reset date before next test.
        resetDate(test1);
        System.out.println("advanceTo tests");
        System.out.println("Starting Date: " + test1.toString());
        int t1 = test1.advanceTo(new Date(2004, 8, 20));

        // Reset date before next test.
        resetDate(test1);
        int t2 = test1.advanceTo(new Date(2018, 6, 29));
        System.out.println(t1 + " to y:2004 m:8 d:20 - Expected: 1513");
        System.out.println(t2 + " to y:2018 m:6 d:29 - Expected: 6574");

        // getDayOfWeek
        resetDate(test1);
        System.out.println(new Date(2018, 12, 11).getDayOfWeek() + " - Expected: Tuesday");
        System.out.println(test1.getDayOfWeek() + " - Expected: Thursday");
        System.out.println(new Date(1969, 7, 20).getDayOfWeek() + " - Expected: Sunday"); // Moon Landing :)
        System.out.println(new Date(2018, 2, 6).getDayOfWeek() + " - Expected: Tuesday"); // Falcon Heavy Maiden Flight :)

        // equals
    }
}

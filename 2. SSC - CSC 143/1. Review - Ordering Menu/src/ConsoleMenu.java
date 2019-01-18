/*
Ari Madian
January 16, 2019
*/


import com.sun.tools.javac.Main;

import java.awt.desktop.ScreenSleepEvent;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ConsoleMenu {

    private static final Scanner scanner = new Scanner(System.in);

    private static double totalCost = 0.0;

    private static final Map<String, String> MainMenu;
    static
    {
        MainMenu = new HashMap<String, String>();
        MainMenu.put("C", "Coffee");
        MainMenu.put("T", "Tea");
        MainMenu.put("H", "Help");
    }

    private static final Map<String, String[]> CoffeeMenu;
    static
    {
        CoffeeMenu = new HashMap<String, String[]>();
        CoffeeMenu.put("L", new String[]{"Latte", "5.60"});
        CoffeeMenu.put("M", new String[]{"Mocha", "5.00"});
        CoffeeMenu.put("E", new String[]{"Espresso", "4.50"});
    }

    private static final Map<String, String[]> TeaMenu;
    static
    {
        TeaMenu = new HashMap<String, String[]>();
        TeaMenu.put("G", new String[]{"Green Tea", "5.20"});
        TeaMenu.put("B", new String[]{"Black Tea", "4.20"});
        TeaMenu.put("D", new String[]{"Decaf Tea", "4.00"});
    }

    public static void main(String[] args) {
        DisplayMainMenu();
    }

    /** Displays the main menu
     *
     */
    private static void DisplayMainMenu() {
        System.out.println("Select one of the following...");

        // Print options
        for (Map.Entry<String, String> pair : MainMenu.entrySet()
             ) {
            System.out.println("    " + pair.getKey() + " for " + pair.getValue());
        }
        System.out.println("    Q to quit");

        // Get input
        System.out.print("--->: "); String input = scanner.nextLine().toUpperCase();

        if (!MainMenu.containsKey(input) && !input.equals("Q")) { // If input is not in options
            System.out.println("\n\nWhat? ...select again!");
            DisplayMainMenu();
        }

        System.out.println("\n\n");
        switch (input) { // Act upon input
            case "C":
                DisplayMenu(CoffeeMenu);
            case "T":
                DisplayMenu(TeaMenu);
            case "H":
                DisplayHelp();
                DisplayMainMenu();
            case "Q":
                System.out.printf("Thank you for using our cafe ordering system! Total Cost: %.2f", totalCost);
                System.exit(0);
        }
    }

    /** Displays a given menu, takes user input, and parses it.
     *
     * @param menu: The menu to display
     */
    private static void DisplayMenu(Map<String, String[]> menu) {
        System.out.println("Awesome... select one of the following items:");

        // Print out options
        for (Map.Entry<String, String[]> pair : menu.entrySet()
             ) {
            System.out.println("    " + pair.getKey() + " for " + pair.getValue()[0]);
        }
        System.out.println("    X to previous menu");

        // Get input
        System.out.print("--->: "); String input = scanner.nextLine().toUpperCase();

        // Act upon input
        if (input.equals("X")) { // If previous menu
            System.out.println("\n\nOk... taking back to main menu");
            DisplayMainMenu();
        } else {
            if (!menu.containsKey(input)) { // If input is NOT in available options
                System.out.println("\n\nWhat? ...select again!");
                DisplayMenu(menu);
            } else {
                // Print selected item
                System.out.print("\n\nYou have selected " + menu.get(input)[0] + " -- ");

                // Print item price, total price
                totalCost += Double.parseDouble(menu.get(input)[1]);
                System.out.print(menu.get(input)[1]);
                System.out.printf(" Total Cost: %.2f", totalCost);
                System.out.println();

                DisplayMenu(menu);
            }
        }
    }

    /** Displays a help menu.
     *
     */
    private static void DisplayHelp() {
        System.out.println("To order, enter the letter corresponding to the desired option, and press enter.");
    }
}

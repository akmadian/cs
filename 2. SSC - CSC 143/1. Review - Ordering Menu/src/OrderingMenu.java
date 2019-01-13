import com.sun.tools.javac.Main;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class OrderingMenu {

    private static final Scanner scanner = new Scanner(System.in);

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

    private static void DisplayMainMenu() {
        System.out.println("Select one of the following...");
        for (Map.Entry<String, String> pair : MainMenu.entrySet()
             ) {
            System.out.println("    " + pair.getKey() + " for " + pair.getValue());
        }
        System.out.println("    Q to quit");
        System.out.print("--->: "); String input = scanner.nextLine().toUpperCase();

        if (!MainMenu.containsKey(input) && !input.equals("Q")) {
            System.out.println("\n\nWhat? ...select again!");
            DisplayMainMenu();
        }

        System.out.println("\n\n");
        switch (input) {
            case "C":
                DisplayMenu(CoffeeMenu);
            case "T":
                DisplayMenu(TeaMenu);
            case "H":
                DisplayHelp();
                DisplayMainMenu();
            case "Q":
        }
    }

    private static void DisplayMenu(Map<String, String[]> menu) {
        System.out.println("Select one of the following...");
        for (Map.Entry<String, String[]> pair : menu.entrySet()
             ) {
            System.out.println("    " + pair.getKey() + " for " + pair.getValue()[0]);
        }
        System.out.println("    X to previous menu");
        System.out.print("--->: "); String input = scanner.nextLine().toUpperCase();

        if (input.equals("X")) {
            System.out.println("\n\n");
            DisplayMainMenu();
        } else {
            if (!menu.containsKey(input)) {
                System.out.println("\n\nWhat? ...select again!");
                DisplayMenu(menu);
            } else {
                System.out.println("\n\nYou have selected " + menu.get(input)[0] + " -- " + menu.get(input)[1]);
                DisplayMenu(menu);
            }
        }
    }

    public static void DisplayHelp() {
        System.out.println("Help Menu");
    }
}

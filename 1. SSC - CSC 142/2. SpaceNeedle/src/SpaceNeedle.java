public class SpaceNeedle {

    private static int size = 100;
    private static int totalSpacesPerRow = (size * 6) + 2;

    public static void main(String[] args) {
        System.out.println("Size - " + size);
        xByX();
        dome();
        bottom();
        xByX();
        shaft();
        dome();
    }

    /*
    <printSpaces> - Prints the necessary amount of spaces before each row
        <param> int spacesPerRow - The number of spaces to print
     */
    private static void printSpaces(int spacesPerRow){
        for (int space = 1; space <= spacesPerRow; space++){ // Print spaces before row
            System.out.print(" ");
        }
    }

    /*
    <twoByFour> - Draw the four pairs of two vertical lines
     */
    private static void xByX() {
        int numRows = size;
        int spacesPerRow = (totalSpacesPerRow - 2) / 2;
        for(int row = 1; row <= numRows; row++){ // For four rows
            printSpaces(spacesPerRow);
            System.out.println("||");
        }
    }

    /*
    <dome> - Draw the top of the 'saucer'

    for five rows
        <chars>         <expression>                        <comment>
           :           (row - 1) * 6                        row number minus 1 (to account for no colons on the first line)
                                                                times 6
         space (totalSpacesPerRow - 8 - numColons) / 2      Total number of chars per row minus colons and default
                                                                chars divided by 2
     */
    private static void dome(){
        int numRows = size;
        for (int row = 1; row <= numRows; row++){
            int numColons = (row - 1) * 6;
            int spacesPerRow = (totalSpacesPerRow - 8 - numColons) / 2;
            printSpaces(spacesPerRow);

            System.out.print("__/"); // Print first bit

            for (int colonRowPart = 1; colonRowPart <= 3; colonRowPart++){ // Divides each row with colons into three parts
                if (colonRowPart == 2){ // If on the middle part, draw the vertical lines
                    System.out.print("||");
                } else { // Else draw the colons
                    for (int colon = 1; colon <= (numColons / 2); colon++){
                        System.out.print(":");
                    }
                }
            }
            System.out.println("\\__"); // Print last bit
        }
        System.out.print("|"); // Print final divider
        for (int quote = 1; quote <= (totalSpacesPerRow - 2); quote++){
            System.out.print("\"");
        }
        System.out.println("|");
    }

    /*
    <bottom> - Draw the underside of the 'saucer'

    for four rows
        <chars>                  <expression>                           <comment>
        / and \  ((totalSpacesPerRow - 4) / 2) - (2 * row -2)    Total figure size minus two
         space  (totalSpacesPerRow - (numSlashes * 2) - 4) / 2   Total number of chars per line minus the slash pairs
                                                                         and default chars divided by 2
     */
    private static void bottom(){
        int numRows = size;
        for (int row = 1; row <= numRows; row++){
            int numSlashes = ((totalSpacesPerRow - 4) / 2) - (2 * row -2);
            int spacesPerRow = (totalSpacesPerRow - (numSlashes * 2) - 4) / 2;
            printSpaces(spacesPerRow);
            System.out.print("\\_");

            for (int slashPair = 0; slashPair < numSlashes; slashPair++){
                System.out.print("/\\");
            }
            System.out.println("_/");
        }
    }

    /*
    <shaft> - Draws the main 'shaft' of the space needle
        <chars>                  <expression>                           <comment>
           %                       size - 2                           Total figure size minus two
         space  (totalSpacesPerRow - 4 - (numPercentPerRow * 2)) / 2  Total number of chars per line minus the slash pairs
                                                                         and default chars divided by 2
     */
    private static void shaft() {
        int numRows = (size * 4) - 1;
        int numPercentPerRow = size - 2;
        int spacesPerRow = (totalSpacesPerRow - 4 - (numPercentPerRow * 2)) / 2;
        for (int row = 1; row <= numRows; row++) {
            printSpaces(spacesPerRow);
            System.out.print("|");

            for (int shaftRowPart = 1; shaftRowPart <= 3; shaftRowPart++) {
                if (shaftRowPart == 2) {
                    System.out.print("||");
                } else {
                    for (int percent = 1; percent <= numPercentPerRow; percent++) {
                        System.out.print("%");
                    }
                }
            }
            System.out.println("|");
        }
    }
}

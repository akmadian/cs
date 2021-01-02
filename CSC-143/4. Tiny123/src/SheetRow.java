import java.util.LinkedList;

public class SheetRow {

    private LinkedList<String> cells = new LinkedList<>();


    /**
     * Constructs a SheetRow instance
     * @param row The row the SheetRow will be constructed at
     */
    public SheetRow(int row) {
        String cellValBase = "R" + String.valueOf(row) + "C"; // Create base cell value
        for (int i = 1; i <= 9; i++) {
            cells.add(cellValBase + String.valueOf(i)); // Add cells
        }
    }

    /**
     * Adds a cell to the SheetRow instance's
     * @param index Which index to add the cell at
     * @param s The value to add the cell to
     */
    public void addCell(int index, String s) {
        cells.add(index, s);
    }

    /**
     * Removes the cell at the given index
     * @param index The index of the cell to remove
     */
    public void removeCell(int index) {
        cells.remove(index);
    }

    /**
     * Sets the value of a given cell to a given value
     * @param cell The index of the cell to set
     * @param s The value to set
     */
    public void setValue(int cell, String s) {
        cells.set(cell, s);
    }

    /**
     * Converts the row to a string
     * @return A string with all cells and the row's sum
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String val : cells) {
            sb.append(String.format("%-8s", val)); // Append a cell value
        }
        sb.append("Sum = " + getSum()); // Append the row's sum
        return sb.toString();
    }

    /**
     * Gets the value of a given cell
     * @param cell The index of the cell to get
     * @return The cell's value
     */
    int getValue(int cell) {
        return Integer.parseInt(cells.get(cell));
    }

    /**
     * Gets the sum of all cells in the row
     * @return The sum of all cells in the row
     */
    int getSum() {
        int sum = 0;

        for (String val : cells) { // For each cell
            try {
                sum += Integer.parseInt(val); // Try to add the value to the sum
            } catch (NumberFormatException e) {} // If NaN, catch exception and do nothing
        }

        return sum;
    }

    /**
     * Gets the size of the row
     * @return The number of cells in the row
     */
    int size() {
        return cells.size();
    }
}

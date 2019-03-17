import java.util.LinkedList;

public class Sheet {
    private LinkedList<SheetRow> rows;

    /**
     * Constructs a sheet
     */
    public Sheet() {
        rows = new LinkedList<>();

        for (int i = 1; i <= 9; i++) {
            rows.add(new SheetRow(i)); // Populate rows
        }
    }

    /**
     * Inserts a new row into the sheet
     * @param row The index at which to insert the row
     */
    public void insertRow(int row) {
        rows.add(row, new SheetRow(row));
    }

    /**
     * Deletes a row from the sheet
     * @param row The index of the row to delete
     */
    public void deleteRow(int row) {
        rows.remove(row);
    }

    /**
     * Inserts a column at the given index
     * @param col The index at which to insert the column
     */
    public void insertCol(int col) {
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).addCell(col, "R" + String.valueOf(i) + "C" + String.valueOf(col));
        }
    }

    /**
     * Deletes a column from the sheet
     * @param col The index of the column to delete
     */
    public void deleteCol(int col) {
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).removeCell(col);
        }
    }

    /**
     * Sets the value of a cell
     * @param row The cell's row
     * @param col The cell's column
     * @param s The value to set
     */
    public void setValue(int row, int col, String s) {
        rows.get(row).setValue(col, s);
    }

    /**
     * Converts the sheet to a string
     * @return The cell with all values and sums
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(); // Sheet sb

        for (SheetRow row : rows) { // Append each row
            sb.append(row.toString() + "\n");
        }

        StringBuilder sumRow = new StringBuilder(); // Sum row sb
        int numCols = -1;
        try {
            numCols = rows.get(0).size();          // Get the number of columns in the sheet
        } catch (IndexOutOfBoundsException e) { }  // If there are no rows, catch exception, do nothing

        if (numCols > 0) { // If there are columns in the sheet
            for (int i = 0; i < rows.get(0).size(); i++) {
                sumRow.append(String.format("%-8s", "Sum=" + getColumnSum(i))); // Add each column's sum
            }
            sumRow.append(String.format("%-8s", "Sum=0"));
        }

        sb.append(sumRow.toString());
        return sb.toString();
    }

    /**
     * Computes the sum of a column
     * @param col The index of the column
     * @return The sum of all cells in a given column
     */
    int getColumnSum(int col) {
        int colSum = 0;

        for (SheetRow row : rows) { // For row in sheet
            try {
                colSum += row.getValue(col); // Try to add the cell's value
            } catch (NumberFormatException e) { } // If NaN, catch, do nothing
        }

        return colSum;
    }

    /**
     * Computes the sum of all cells in a sheet
     * @return The sum of all cells in the sheet
     */
    int getSheetSum() {
        int sheetSum = 0;

        for (SheetRow row : rows) {
            sheetSum += row.getSum();
        }

        return sheetSum;
    }
}

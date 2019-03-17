import java.util.LinkedList;

public class Sheet {
    private LinkedList<SheetRow> rows;

    public Sheet() {
        rows = new LinkedList<>();

        for (int i = 1; i <= 9; i++) {
            rows.add(new SheetRow(i));
        }
    }

    public void insertRow(int row) {
        rows.add(row, new SheetRow(row));
    }

    public void deleteRow(int row) {
        rows.remove(row);
    }

    public void insertCol(int col) {
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).addCell(col, "R" + String.valueOf(i) + "C" + String.valueOf(col));
        }
    }

    public void deleteCol(int col) {
        for (int i = 0; i < rows.size(); i++) {
            rows.get(i).removeCell(col);
        }
    }

    public void setValue(int row, int col, String s) {
        rows.get(row).setValue(col, s);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (SheetRow row : rows) {
            try {
                sb.append(row.toString() + "\n");
            } catch (IndexOutOfBoundsException e) { }
        }

        StringBuilder sumRow = new StringBuilder();
        int numCols = -1;
        try {
            numCols = rows.get(0).numCols();
        } catch (IndexOutOfBoundsException e) { }

        if (numCols > 0) {
            for (int i = 0; i < rows.get(0).numCols(); i++) {
                try {
                    sumRow.append(String.format("%-8s", "Sum=" + getColumnSum(i)));
                } catch (IndexOutOfBoundsException e) { }
            }
            sumRow.append(String.format("%-8s", "Sum=0"));
        }

        sb.append(sumRow.toString());
        return sb.toString();
    }

    int getColumnSum(int col) {
        int colSum = 0;

        for (SheetRow row : rows) {
            try {
                colSum += row.getValue(col);
            } catch (NumberFormatException e) { }
        }

        return colSum;
    }

    int getSheetSum() {
        int sheetSum = 0;

        for (SheetRow row : rows) {
            sheetSum += row.getSum();
        }

        return sheetSum;
    }
}

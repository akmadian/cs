import java.util.LinkedList;

public class SheetRow {

    private LinkedList<String> cells = new LinkedList<>();

    public SheetRow(int row) {
        String cellValBase = "R" + String.valueOf(row) + "C";
        for (int i = 1; i <= 9; i++) {
            cells.add(cellValBase + String.valueOf(i));
        }
    }

    public void addCell(int index, String s) {
        cells.add(index, s);
    }

    public void removeCell(int index) {
        cells.remove(index);
    }

    public void setValue(int cell, String s) {
        cells.set(cell, s);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String val : cells) {
            sb.append(String.format("%-8s", val));
        }
        sb.append("Sum = " + getSum());
        return sb.toString();
    }

    int getValue(int cell) {
        return Integer.parseInt(cells.get(cell));
    }

    int getSum() {
        int sum = 0;

        for (String val : cells) {
            try {
                sum += Integer.parseInt(val);
            } catch (NumberFormatException e) {}
        }

        return sum;
    }

    int numCols() {
        return cells.size();
    }
}

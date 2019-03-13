public class main {
    public static void main(String[] args) {
        SheetRow row = new SheetRow(1);
        row.setValue(0, "2");

        System.out.println(row.toString());
    }
}

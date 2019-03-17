public class main {
    public static void main(String[] args) {
        Sheet sht = new Sheet();

        System.out.println("Initial State");
        System.out.println(sht+"\n");

        sht.insertRow(0);
        System.out.println("Insert Row 0");
        System.out.println(sht+"\n");

        sht.deleteRow(8);
        System.out.println("Delete Row 8");
        System.out.println(sht+"\n");

        sht.insertCol(0);
        System.out.println("Insert Col 0");
        System.out.println(sht+"\n");

        sht.deleteCol(6);
        System.out.println("Delete Col 6");
        System.out.println(sht+"\n");

        for (int row = 1; row <= 3; row ++) {
            for (int col = 1; col <= 3; col++) {
                sht.setValue(row,  col, ""+((row-1)*3+col));
             }
        }
        System.out.println("Fill 3x3 array");
        System.out.println(sht+"\n");

        sht.insertRow(2);
        sht.insertRow(4);
        sht.insertCol(2);
        sht.insertCol(4);
        System.out.println("Insert Row 2,4, Col 2,4");
        System.out.println(sht+"\n");

        for (int i = 10; i >= 6; i--) {
            sht.deleteRow(i);
            sht.deleteCol(i);
        }
        System.out.println("Delete Row 10-6, Col 10-6");
        System.out.println(sht+"\n");

        for (int i = 0; i <= 5; i++) {
             sht.deleteRow(0);
             sht.deleteCol(0);
        }
        System.out.println("Delete Row 0x6, Col 0x6");
        System.out.println(sht+"\n");

    }
}

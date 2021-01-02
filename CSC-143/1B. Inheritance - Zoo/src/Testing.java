public class Testing {

    public static void main(String[] args) {
        Zoo test = new Zoo();

        test.PutInCage1(new Tiger());
        test.PutInCage2(new Giraffe());

        test.print();
    }
}

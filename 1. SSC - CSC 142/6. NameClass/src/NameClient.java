public class NameClient {

    public static void main(String[] args) {
        Name name = new Name("Ari", 'K', "Madian");
        System.out.println(name.ToString());
        System.out.println(name.getFirst());
        System.out.println(name.getInitial());
        System.out.println(name.getLast());
        System.out.println(name.reverseName());
        System.out.println(name.equals(new Name("Ari", 'K', "Madian")));
        System.out.println(name.equals(new Name("First", 'M', "Last")));
    }
}

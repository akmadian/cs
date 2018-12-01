public class Name {

    private String firstName;

    private String lastName;

    private char middleInitial;

    public Name(String firstName, char middleInitial, String lastName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
    }

    public void setName(String newFirst, char newInitial, String newLast) {
        this.firstName = newFirst;
        this.middleInitial = newInitial;
        this.lastName = newLast;
    }

    public String ToString() {
        return this.firstName + " " + this.middleInitial + ". " + this.lastName;
    }

    public String reverseName() {
        return this.lastName + ", " + this.firstName + " " + this.middleInitial + ".";
    }

    public boolean equals(Name other) {
        return other.firstName.equals(this.firstName) &&
                other.lastName.equals(this.lastName) &&
                other.middleInitial == this.middleInitial;
    }

    public String getFirst() { return this.firstName; }
    public String getLast()  { return this.lastName; }
    public char getInitial() { return this.middleInitial; }

}

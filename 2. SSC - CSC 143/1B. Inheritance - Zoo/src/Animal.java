// Ari Madian
// Ravi Gandham CSC 143 W19

public abstract class Animal {

    private int hunger;

    // Contructs an animal instance and sets the animal's hunger to 0.
    public Animal()
    {
        this.hunger = 0;
    }

    // Returns the animal's hunger value.
    public int GetHunger()
    {
        return this.hunger;
    }

    // Increments the animal's hunger.
    public void TimePasses()
    {
        this.hunger++;
    }

    // Resets the animal's hunger to 0.
    public void Feed()
    {
        this.hunger = 0;
    }

    // Prints what the animal would say.
    abstract public void Talk();

    public String ToString() {
        return "Animal";
    }

}

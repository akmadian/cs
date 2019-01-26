// Ari Madian
// Ravi Gandham CSC 143 W19

public class Tiger extends Animal {

    public Tiger()
    {
        super();
    }

    public void Talk()
    {
        System.out.println("Roar!");
    }

    public void TimePasses()
    {
        super.TimePasses();

        if (this.GetHunger() > 3)
            System.out.println("The tiger paces hungrily.");

    }

    public String ToString()
    {
        return "Tiger";
    }
}

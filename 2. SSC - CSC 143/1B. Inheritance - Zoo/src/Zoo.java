// Ari Madian
// Ravi Gandham CSC 143 W19

public class Zoo {

    private Animal cage1;
    private Animal cage2;
    private Animal cage3;

    private Animal[] Animals;

    public Zoo()
    {
    }

    // Sets the animal in cage 1.
    public void PutInCage1(Animal Animal)
    {
        this.cage1 = Animal;
    }

    // Sets the animal in cage 2.
    public void PutInCage2(Animal Animal)
    {
        this.cage2 = Animal;
    }

    // Sets the animal in cage 3.
    public void PutInCage3(Animal Animal)
    {
        this.cage3 = Animal;
    }

    // Updates the list of animals.
    private void UpdateAnimals()
    {
        this.Animals = new Animal[] {cage1, cage2, cage3};
    }

    // Feeds all animals in the zoo.
    public void FeedAll()
    {
        UpdateAnimals();
        for (Animal Animal : this.Animals
             ) {
            if (Animal != null) {
                Animal.Feed();
            }
        }
    }

    // Calls the talk methods of each animal in the zoo.
    public void AllTalk()
    {
        UpdateAnimals();
        for (Animal Animal : this.Animals
             ) {
            if (Animal != null) {
                Animal.Talk();
            }
        }
    }

    // Increments the hunger of each animal in the zoo.
    public void TimePasses()
    {
        UpdateAnimals();
        for (Animal Animal : this.Animals
             ) {
            if (Animal != null) {
                Animal.TimePasses();
            }
        }
    }

    // Prints out all animals in the zoo.
    public void print()
    {
        UpdateAnimals();
        System.out.println("The zoo contains the following:");
        for (Animal Animal : this.Animals
             ) {
            if (Animal != null) {
                System.out.println("    " + Animal);
            }
        }
    }
}

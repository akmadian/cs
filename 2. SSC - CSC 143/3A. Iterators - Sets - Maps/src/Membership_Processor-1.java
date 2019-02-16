import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

class Membership_Processor {

    public static void main(String[] args) throws FileNotFoundException  {
        PartB();
    }

    /**
     * Always add a useful Javadoc method header
     * Give me a preview of what the function does
     * Tell me what to look for in the function
     */
    public static void PartB() throws FileNotFoundException {

    }

        
    /**
     * Testing code - do not modify

     * Tests functionality of Part B()
     * @param changesList - ArrayList of changes.   
     */
    public static void testPartB (List<Person> changesList) {
        Boolean passed = true;

        String[] expectedEmails = {
                "+E*louisa@cronauer.com+Evite",
                "+E*alease@buemi.com+Evite",
                "-E*oretha_menter@yahoo.com-Evite",
                "-E*tsmith@aol.com-Evite",
                "-E*kris@gmail.com-Evite",
        };

        // Check total # of changes
        if (changesList.size() != expectedEmails.length) {
            passed = false;
            System.out.println(String.format("Expected %d changes, received %d changes", expectedEmails.length, changesList.size()));
        }

        // Check for each change
        for (String sExpectedEmail : expectedEmails) {
            Boolean found = false;
            Iterator<Person> iter = changesList.iterator();
            while(iter.hasNext()) {
                Person p = iter.next();
                if (p.getEmail().equals(sExpectedEmail)) {
                    found = true;
                    // remove from list so we can see what's leftover accidentally
                    iter.remove();
                }
            }// while iter
            // Output any missing changes
            if (!found) {
                passed = false;
                System.out.println(String.format("Expected to find email: %s",  sExpectedEmail));
            }
        } // for-each
        
        // print any extras
        for (Person pChange : changesList) {
            System.out.printf("Not expected but found change %s\n", pChange.getEmail());
        }
        
        if (passed)
            System.out.println("Congratulations - passed Part B");
        else
            System.out.println("Oops - failed Part B");
    }
}

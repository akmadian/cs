import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.*;

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
        Map<String, Person> excelMap = readNamesMap("excel");
        Map<String, Person> eviteMap = readNamesMap("evite");

        ArrayList<Person> diff = new ArrayList<>();

        // Find adds
        Iterator<String> iter = excelMap.keySet().iterator();
        while (iter.hasNext()) {
            String email = iter.next();
            if (!eviteMap.containsKey(email)) { // If member is not in evite list
                Person toAdd = new Person( // Create Person
                        excelMap.get(email).getFirstName(),
                        excelMap.get(email).getLastName(),
                        email + "+Evite"
                );
                diff.add(toAdd); // Add person
            }
        }

        // Find removes
        Set<String> nKeySet = new HashSet<>(eviteMap.keySet());
        nKeySet.removeAll(excelMap.keySet());
        for (String email : nKeySet) { // For each item in keyset diff
            eviteMap.get(email).setEmail(email + "-Evite"); // Add email suffix
            diff.add(eviteMap.get(email)); // Add person
        }

        testPartB(diff);
    }

    public static HashMap<String, Person> readNamesMap(String filename) throws FileNotFoundException {
        HashMap<String, Person> outMap = new HashMap<String, Person>();
        File f = new File(filename + ".csv");
        Scanner sc = new Scanner(f);

        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner scLine = new Scanner(line);
            scLine.useDelimiter(",");

            String first = scLine.next();
            String last = scLine.next();
            String email = scLine.next();

            outMap.put(email, new Person(first, last, email));
        }
        return outMap;
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

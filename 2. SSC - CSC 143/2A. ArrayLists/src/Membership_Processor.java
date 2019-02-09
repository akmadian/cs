import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Membership_Processor {

	public static void main(String[] args) throws FileNotFoundException {
		PartA();
	}

	/**
	 * Always add a useful Javadoc method header Give me a preview of what the
	 * function does Tell me what to look for in the function
	 */
	public static void PartA() throws FileNotFoundException {
		ArrayList<Person> membership = readNames();

		System.out.println(String.format("Read Total of %d names", membership.size()));

	}

	/**
	 * Reads file of names
	 * 
	 * @return ArrayList of Person class
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Person> readNames() throws FileNotFoundException {
		// Open scanner on file "members.csv"
		File f = new File("members.csv");
		Scanner sc = new Scanner(f);

		// TODO: create ArrayList of Person class variable p

		// Discard first line - it's the headers "first_name last_name email" etc
		sc.nextLine();

		// Loop through lines of the file
		while (sc.hasNextLine()) {

			// Get the next line
			String line = sc.nextLine();
			Scanner scLine = new Scanner(line);

			// This allows us to use comma as the delimiter instead of whitespace
			scLine.useDelimiter(",");

			// Scan the line for the names & emails
			String first = scLine.next();
			String last = scLine.next();
			String email = scLine.next();

			// TODO: put the person into the ArrayList
		}

		// TODO: return the ArrayList you defined
		return new ArrayList<Person>();
	}

	/**
	 * Testing code - do not modify
	 * 
	 * @param membership
	 */
	public static void testPartA(ArrayList<Person> membership) {
		// Test for correct total # of names
		if (membership.size() != 445) {
			System.out.println("Wrong number of names.  There should be 445 after all removals and adds");
			return;
		}

		// Test all 10 names removed
		for (Person p : membership) {
			if (p.getEmail().contains("-")) {
				System.out.println(String.format("Oops - didn't remove person %s %s %s", p.getFirstName(),
						p.getLastName(), p.getEmail()));
				return;
			}
		}

		// Test that only 5 names added
		int count = 0;
		for (Person p : membership) {
			if (p.getEmail().contains("*"))
				count++;
		}
		if (count != 5) {
			System.out.println("didn't add the right number of names");
			return;
		}

		// Check sorting & overall work at specific random items
		int[] memberIndexes = { 0, 10, 20, 30, 40, 400 };
		String[] expectedEmails = { "Bill_frey@frey.com*", "alaine_bergesen@cox.net", "amber_monarrez@monarrez.org",
				"apinilla@cox.net", "barrett.toyama@toyama.org", "tasia_andreason@yahoo.com" };
		for (int i = 0; i < memberIndexes.length; i++) {
			String memberEmail = membership.get(memberIndexes[i]).getEmail();
			if (!memberEmail.equals(expectedEmails[i]))
				System.out.println(String.format("Index %d expected %s but found %s", memberIndexes[i],
						expectedEmails[i], memberEmail));
		}

		System.out.println("Congrats - you passed all tests");
	}
}
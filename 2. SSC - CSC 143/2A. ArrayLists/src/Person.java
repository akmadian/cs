import java.util.*;
import java.io.*;

/**
 * Programming Project 2
 * Part A - ArrayLists
 * 
 * @author 
 *
 */

/**
 * Represents a person - first, last, email
 *
 */
public class Person implements Comparable<Person> {
	private String firstName;
	private String lastName;
	private String email;

	public Person(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Print out the name
	 */
	public String toString() {
		return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email + "]";
	}

	/**
	 *
	 * @param other The other Person object to compare.
	 * @return 1 if objects are equal, 0 if not.
	 */
	public int compareTo(Person other) {
		if (other.getEmail().equals(this.email)) {
			return 1;
		} else {
			return 0;
		}
	}

}

/**
 * 
 */
/**
 * @author Administrator
 *
 */
package serialization;

import java.io.Serializable;

public class Participants implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6708618330637509772L;

	@Override
	public String toString() {
		return "Participants [firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + "]";
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	private  final String firstName;
	private  final String lastName;
	private int age;
	
	public Participants(String fName, String lName, int a)
	{
		firstName=fName;
		lastName=lName;
		age=a;
	}
}
package PT2019.demo.Model;

/**
 * This class represents a row from the Client table present in the database.
 * 
 * @author Sándor
 *
 */
public class Client {

	/**
	 * Variable that will contain the id of the client.
	 */
	private int id;
	
	/**
	 * Variable that will contain the first name of the client.
	 */
	private String firstName;
	
	/**
	 * Variable that will contain the last name of the client.
	 */
	private String lastName;
	
	/**
	 * Class constructor.
	 * 
	 * @param id - the id of the client
	 * @param fName - the first name of the client.
	 * @param lName - the last name of the client.
	 */
	public Client(int id, String fName, String lName) {
		this.id = id;
		this.firstName = fName;
		this.lastName = lName;
	}

	/**
	 * Returns the client id
	 * 
	 * @return - int, client id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifies the cient id.
	 * 
	 * @param id - int, the new id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the first name of the client.
	 * 
	 * @return - String, the first name of the client.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Modifies the first name of the client.
	 * 
	 * @param firstName - String, the new first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the last name of the client.
	 * 
	 * @return - String, the last name of the client.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Modifies the last name of the client.
	 * 
	 * @param lastName - String, the new last name.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}

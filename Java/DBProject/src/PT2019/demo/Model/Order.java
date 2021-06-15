package PT2019.demo.Model;

/**
 * This class represents a line form the Order table, present in the database.
 * 
 * @author Sándor
 *
 */
public class Order {

	/**
	 * Variable to store the client's id.
	 */
	private int clientID;
	
	/**
	 * Variable to store the product's id.
	 */
	private int productID;
	
	/**
	 * Variable to store the quantity of items ordered. 
	 */
	private int quantity;
	
	/**
	 * Variable to store the total price of the order.
	 */
	private float total;
	
	/**
	 * Class constructor.
	 * 
	 * @param cID - int, a client's id.
	 * @param pID - int, a product's id.
	 * @param q - int, the quantity of the product.
	 * @param tot - float, the total price of the order.
	 */
	public Order(int cID, int pID, int q, float tot) {
		clientID = cID;
		productID = pID;
		quantity = q;
		total = tot;
	}

	/**
	 * Returns the client's id.
	 * 
	 * @return - int, the client's id.
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * Returns the product's id.
	 * 
	 * @return - int, the product's id.
	 */
	public int getProductID() {
		return productID;
	}

	/**
	 * Returns the number of items ordered.
	 * 
	 * @return - int, number of items ordered.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns the total price of the order.
	 * 
	 * @return - float, the total price of the order.
	 */
	public float getTotal() {
		return total;
	}
}

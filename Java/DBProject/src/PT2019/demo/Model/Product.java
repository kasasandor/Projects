package PT2019.demo.Model;

/**
 * This class represents a line from the Product table present int the datavase.
 * 
 * @author Sándor
 *
 */
public class Product {

	/**
	 * Variable to store the product id.
	 */
	private int id;
	
	/**
	 * Variable to store the amount of products on stock.
	 * Initially 0.
	 */
	private int stock = 0;
	
	/**
	 * Variable to store the price of the product.
	 */
	private float price;
	
	/**
	 * Variable to store the description of the product.
	 */
	private String desc;
	
	/**
	 * Class constructor.
	 * 
	 * @param id - int, the product's id.
	 * @param price - float, the product's price.
	 * @param Descr - String, the product's description.
	 */
	public Product(int id, float price, String Descr) {
		this.id = id;
		this.price = price;
		this.desc = Descr;
	}

	/**
	 * Returns the id of a product.
	 * 
	 * @return - int, the id of a product.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets a new id for a product.
	 * 
	 * @param id - int, new id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** 
	 * Returns the number of items on stock.
	 * 
	 * @return - int, number of items on stock.
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Sets a new stock number.
	 * 
	 * @param stock - int, new stock number.
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * Returns the price of a product.
	 * 
	 * @return - float, the price of a product.
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets a new price for a product.
	 * 
	 * @param price - float, new price of the product.
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Returns the description of a product.
	 * 
	 * @return - String, the description of a product.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets a new description for a product.
	 * 
	 * @param desc - String, new description.
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
}

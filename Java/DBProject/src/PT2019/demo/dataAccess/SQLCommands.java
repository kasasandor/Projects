package PT2019.demo.dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import PT2019.demo.Model.Client;
import PT2019.demo.Model.Order;
import PT2019.demo.Model.Product;

/**
 * This class contains all the necessary commands to get the required data from the data base,
 * as well as contains methods to modify the data inside the database.
 * 
 * @author Sándor
 *
 */
public class SQLCommands {

	/**
	 * Parameter that will execute the required query.
	 */
	private DBQuery dbq;
	
	/**
	 * Parameter that will store the result set from the query.
	 */
	private ResultSet rs;

	/**
	 * Constructor.
	 */
	public SQLCommands() {
		this.dbq = new DBQuery();
		this.rs = null;
	}

	/**
	 * This method inserts a client into the database.
	 * 
	 * @param c - an instance of the class Client.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void insertClient(Client c) throws SQLException {
		dbq.statement("INSERT INTO Client(id, first_name, last_name) VALUES (" + c.getId() + ", '" + c.getFirstName()
				+ "', '" + c.getLastName() + "');");
		dbq.closeStatement();
	}
	
	/**
	 * This method inserts a product into the database.
	 * 
	 * @param p - an instance of the class Product.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void insertProduct(Product p) throws SQLException {
		dbq.statement("INSERT INTO Products(id, price, stock, description) VALUES (" + p.getId() + ", " + p.getPrice()
				+ ", " + p.getStock() + ", '" + p.getDesc() + "');");
		dbq.closeStatement();
	}

	/**
	 * This class inserts an order into the database.
	 * 
	 * @param o - an instance of the class Order.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void insertOrder(Order o) throws SQLException {
		dbq.statement("INSERT INTO Orders(client_id, product_id, quantity, total) VALUES (" + o.getClientID() + ", "
				+ o.getProductID() + ", " + o.getQuantity() + ", " + o.getTotal() + ");");
		dbq.closeStatement();
	}

	/**
	 * This method updates an existing client.
	 * 
	 * @param c - instance of the class Client
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void updateClient(Client c) throws SQLException {
		dbq.statement("UPDATE Client SET first_name = '" + c.getFirstName() + "', last_name = '" + c.getLastName()
				+ "' WHERE id = " + c.getId() + ";");
		dbq.closeStatement();
	}

	/**
	 * This method updates an existing product.
	 * 
	 * @param p - instance of the class Product.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void updateProduct(Product p) throws SQLException {
		dbq.statement("UPDATE Products SET description = '" + p.getDesc() + "', price = " + p.getPrice() + ", stock = "
				+ p.getStock() + " WHERE id = " + p.getId() + ";");
		dbq.closeStatement();
	}

	/**
	 * This method removes an existing client from the database.
	 * 
	 * @param id - the id of the given client.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void removeClient(int id) throws SQLException {
		dbq.statement("DELETE FROM Client WHERE id = " + id + ";");
		dbq.closeStatement();
	}

	/**
	 * This method removes an existing product from the database.
	 * 
	 * @param id - the id of the given product.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void removeProduct(int id) throws SQLException {
		dbq.statement("DELETE FROM Products WHERE id = " + id + ";");
		dbq.closeStatement();
	}

	/**
	 * This method gets the data from an existing table.
	 * 
	 * @param tableName - A valid table name.
	 * @return - return a Vector<String[]> that will contain all the rows from the specified table.
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public Vector<String[]> getInfo(String tableName) throws SQLException {
		final DBQuery dbq = new DBQuery();
		dbq.statement("SELECT * FROM " + tableName + ";");
		rs = dbq.getResultSet();
		Vector<String[]> result = new Vector<String[]>();

		try {
			while (rs.next()) {
				if (tableName.equals("Client")) {
					String firstName, lastName;

					firstName = rs.getString("first_name");
					lastName = rs.getString("last_name");

					String[] aux = { firstName, lastName };
					result.add(aux);
				} else if (tableName.equals("Products")) {
					String desc, price, stock;

					desc = rs.getString("description");
					price = rs.getString("price");
					stock = rs.getString("stock");

					String[] aux = { price, stock, desc };
					result.add(aux);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No data!", "Error", JOptionPane.ERROR_MESSAGE);
		}

		return result;
	}
}

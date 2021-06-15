package PT2019.demo.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Class that contains methods to connect to a database.
 * Methods in the class:
 * 1.Loading the database connection driver.
 * 2.Creating a connection to a database.
 * 3.Disconnecting from a connected database.
 * 
 * @author Sándor
 *
 */
public class DBConnect {
	
	/**
	 * Variable that stores the connection to the database.
	 */
	private Connection conn;
	
	/**
	 * Class constructor.
	 * Initializes the connection.
	 */
	public DBConnect() {
		conn = null;
	}
	
	/**
	 * Method for loading the database connection driver.
	 */
	public void loadDriver() {
		try {
			/**
			 * Instruction for loading the driver.
			 */
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}
		catch(Exception e) {
			/**
			 * On error, show dialog.
			 */
			JOptionPane.showMessageDialog(null, "Cannot load  connection driver to your database!...", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Method to create a connection to the database.
	 */
	public void connect() {
		try {
			/**
			 * Creating the connection to the database.
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost/tp?user=root&password=");
		}
		catch(SQLException e) {
			/**
			 * On error, show dialog and exit.
			 */
			JOptionPane.showMessageDialog(null, "Cannot connect to your database...", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	/**
	 * Method for disconnecting from the database
	 */
	public void disconnect() {
		/**
		 * If the connection is null, the there is no connection, so it cannot be disconnected.
		 */
		if(conn != null) {
			try {
				/**
				 * Instruction for closing the connection to the database.
				 */
				conn.close();
				conn = null;
			}
			catch(SQLException e) {
				/**
				 * On error, show dialog.
				 */
				JOptionPane.showMessageDialog(null, "Cannot close conection to your database!...", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Method that returns the connection to the database.
	 * @return - connection to the database.
	 */
	public Connection getConnection() {
		return conn;
	}
}

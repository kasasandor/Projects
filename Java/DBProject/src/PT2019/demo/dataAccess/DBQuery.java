package PT2019.demo.dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * BDQuery class uses the statement method to get a valid MySQL statment
 * and get a result set based on the given query.
 * @author Sándor
 *
 */
public class DBQuery {

	/**
	 * Variable that gets the connection to the database.
	 */
	private DBConnect dbc;
	/**
	 * Variable that stores the given statement.
	 */
	private Statement selectStatement;
	/**
	 * Variable that stores the result set of the query.
	 */
	private ResultSet rs;

	/**
	 * Class constructor.
	 * Initializes the result set and the statement.
	 */
	public DBQuery() {
		dbc = new DBConnect();
		selectStatement = null;
		rs = null;
	}

	/**
	 * The method connects to the database, executes the given query and returns the result set.
	 * 
	 * @param query - String that contains the valid MySQL query.
	 * @throws SQLException - Throws SQLException if it cannot connect to the database.
	 */
	public void statement(String query) throws SQLException {
		dbc.connect();
		selectStatement = dbc.getConnection().createStatement();
		selectStatement.execute(query);
		rs = selectStatement.getResultSet();
	}

	/**
	 * The method closes an opened statement.
	 * 
	 * @throws SQLException - Throws SQLException in case of error.
	 */
	public void closeStatement() throws SQLException {
		if (selectStatement != null) {
			dbc.disconnect();
			selectStatement.close();
			selectStatement = null;
		}

		if (rs != null) {
			rs.close();
			rs = null;
		}
	}

	/**
	 * Method that returns the result set of the given query
	 * 
	 * @return - result set from the query
	 */
	public ResultSet getResultSet() {
		return rs;
	}

	/**
	 * If the result set has a next value then
	 * 
	 * @return - true else
	 * @return - false
	 */
	public boolean isResultSet() {
		try {
			return rs.next();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Result set has no next element...", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}
}

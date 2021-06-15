package PT2019.demo.presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import PT2019.demo.Model.Order;
import PT2019.demo.Model.Product;
import PT2019.demo.dataAccess.DBConnect;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class will display the window where the user can make an order.
 * 
 * @author Sándor
 *
 */
public class OrderPanel extends JFrame {

	/**
	 * Variable for the database commands.
	 */
	private SQLCommands comm;
	
	/**
	 * JTable that contains information about the clients.
	 */
	private JTable clients;
	
	/**
	 * JTable that contains information about the products.
	 */
	private JTable products;
	
	/**
	 * TextField where the user will enter the number of items.
	 */
	private JTextField quantityTF;
	/**
	 * Button, that will validate the order.
	 */
	private JButton orderBtn;

	/**
	 * Class constructor.
	 * It will create a new window and will add all the necessary fields, 
	 * buttons, action listeners and tables.
	 */
	public OrderPanel() {
		comm = new SQLCommands();
		DBConnect dbc = new DBConnect();
		dbc.connect();

		quantityTF = new JTextField(4);
		orderBtn = new JButton("Make Order");

		this.setTitle("Order");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);

		String[] c = { "First Name", "Last Name" };
		Vector<String> col = new Vector<String>();
		for (String s : c) {
			col.add(s);
		}
		clients = new JTable(null, col);
		getResult(clients, "Client");

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.LINE_AXIS));

		JScrollPane clientPanel = new JScrollPane(clients);
		clients.setFillsViewportHeight(true);

		String[] p = { "Price", "Stock", "Description" };
		Vector<String> prod = new Vector<String>();
		for (String s : p) {
			prod.add(s);
		}
		products = new JTable(null, prod);
		getResult(products, "Products");

		JScrollPane prodPanel = new JScrollPane(products);
		products.setFillsViewportHeight(true);

		JPanel intPanel = new JPanel();
		JLabel text = new JLabel("Quantity:");
		intPanel.add(text);
		intPanel.add(quantityTF);
		intPanel.add(orderBtn);

		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int clientToCheck = clients.getSelectedRow();
				int prodToCheck = products.getSelectedRow();
				int quantity = Integer.parseInt(quantityTF.getText());

				if (clientToCheck != -1 && prodToCheck != -1) {
					String[] s = getRowAt(prodToCheck);
					int tot = Integer.parseInt(s[1]);
					if (tot >= quantity) {
						Product p = new Product(prodToCheck + 1, Float.parseFloat(s[0]), s[2]);
						p.setStock(tot - quantity);
						Order o = new Order(clientToCheck + 1, prodToCheck + 1, quantity, quantity * Float.parseFloat(s[0]));
						try {
							comm.updateProduct(p);
							comm.insertOrder(o);
							JOptionPane.showMessageDialog(null, "Transaction successful!", "SUCCESS",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, "Failed to update product!\n" + ex.getMessage(),
									"ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Not enough products on stock!\n", "WARNING",
								JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Not enough rows selected", "WARNING",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		content.add(clientPanel);
		content.add(intPanel);
		content.add(prodPanel);

		this.add(content);
		this.setVisible(true);
	}

	/**
	 * This method will fill a JTable with information from the table 'name'
	 * present in the database.
	 * 
	 * @param t - JTable.
	 * @param name - String, name of the table from the database that will be processed.
	 */
	private void getResult(JTable t, String name) {
		try {
			Vector<String[]> rowData = comm.getInfo(name);
			DefaultTableModel model = (DefaultTableModel) t.getModel();
			for (String[] row : rowData) {
				model.addRow(row);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Failed to get results!\n" + ex.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Returns all the information in a given row.
	 * 
	 * @param index - int, index of the row.
	 * @return - String[], containing all the information in the row.
	 */
	public String[] getRowAt(int index) {
		String[] result = new String[3];
		for (int i = 0; i < 3; i++) {
			result[i] = (String) products.getModel().getValueAt(index, i);
		}

		return result;
	}

}

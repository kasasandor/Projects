package PT2019.demo.presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.Vector;

import PT2019.demo.dataAccess.DBConnect;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class will create the window, where the user can inspect
 * all the products present in the database.
 * As well as, add new products, modify existing ones, or delete them.
 * 
 * @author Sándor
 *
 */
public class ProductPanel extends JFrame {

	/**
	 * Variable for the database commands.
	 */
	private SQLCommands comm;
	
	/**
	 * Button that will add a new product to the database.
	 */
	private JButton addBtn = new JButton("Add");
	
	/**
	 * Button that will delect the selected product.
	 */
	private JButton deleteBtn = new JButton("Delete");
	
	/**
	 * Button that will display all the products present in the database.
	 */
	private JButton viewBtn = new JButton("View all");
	
	/**
	 * Button that will let the user edit the selected product.
	 */
	private JButton editBtn = new JButton("Edit");
	
	/**
	 * An array of String, containing the column headers.
	 */
	private static final String[] COLUMN_NAMES = { "Price", "Stock", "Description" };
	
	/**
	 * The number of products in the database.
	 */
	private int productCount;

	/**
	 * JTable, that contains all the products in the database.
	 */
	private JTable products;

	/**
	 * Class constructor.
	 * It will create the window and add the necessary fields, buttons and
	 * action listeners to the window.
	 * 
	 * @param pCount - int, number of products in the database.
	 */
	public ProductPanel(int pCount) {
		comm = new SQLCommands();
		DBConnect dbc = new DBConnect();
		dbc.connect();
		Vector<String> colData = getColumnData();
		products = new JTable(null, colData);
		productCount = pCount;

		this.setTitle("Products");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);

		viewBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				updateTable(products);
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int rowToDelete = products.getSelectedRow();
					if (rowToDelete != -1) {
						comm.removeProduct(rowToDelete + 1);
						productCount--;
						updateTable(products);
					} else {
						JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to delete product!\n" + ex.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowToEdit = products.getSelectedRow();
				if (rowToEdit != -1) {
					ProductEditPanel p = new ProductEditPanel(getRowAt(rowToEdit), rowToEdit + 1);
				} else {
					JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				productCount++;
				NewProductPanel p = new NewProductPanel(productCount);
			}

		});

		JPanel panel = new JPanel();
		panel.add(viewBtn);
		panel.add(addBtn);
		panel.add(deleteBtn);
		panel.add(editBtn);

		JScrollPane scroll = new JScrollPane(products);
		products.setFillsViewportHeight(true);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(panel);
		content.add(scroll);

		this.add(content);
		this.setVisible(true);
	}

	/**
	 * Method that will initialize the given table.
	 * 
	 * @param t - JTable, the table that will be initialized.
	 */
	public void initTable(JTable t) {
		int count = t.getRowCount();
		if (count != 0) {
			for (int i = count - 1; i >= 0; i--) {
				((DefaultTableModel) t.getModel()).removeRow(i);
			}
		}
	}

	/**
	 * Method that will update the entries of the given table.
	 * 
	 * @param t - JTable, the table that needs to be updated.
	 */
	public void updateTable(JTable t) {
		try {
			Vector<String[]> rowData = comm.getInfo("Products");
			initTable(products);
			DefaultTableModel model = (DefaultTableModel) products.getModel();
			for (String[] row : rowData) {
				model.addRow(row);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "No results found!\n" + ex.getMessage(), "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Returns the contents of a given row.
	 * 
	 * @param index -int, the index of the row.
	 * @return - String[], all the data in that row.
	 */
	public String[] getRowAt(int index) {
		String[] result = new String[3];
		for (int i = 0; i < 3; i++) {
			result[i] = (String) products.getModel().getValueAt(index, i);
		}

		return result;
	}
	
	/**
	 * This method will extract the column headers and return it as a Vector.
	 * 
	 * @return - Vector<String>.
	 */
	public Vector<String> getColumnData() {
		Vector<String> result = new Vector<String>();
		for (String col : COLUMN_NAMES) {
			result.add(col);
		}

		return result;
	}
}

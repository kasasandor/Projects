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
 * This class extends the JFrame class.
 * This class will create the main window to interact with te clients in the database.
 * 
 * @author Sándor
 *
 */
public class ClientPanel extends JFrame {

	/**
	 * Variable for the SQL commands.
	 */
	private SQLCommands comm;
	
	/**
	 * Button that will add a new client to the database.
	 */
	private JButton addBtn = new JButton("Add");
	
	/**
	 * Button that will delete a client from the database.
	 */
	private JButton deleteBtn = new JButton("Delete");
	
	/**
	 * Button that will list all the clients in the database.
	 */
	private JButton viewBtn = new JButton("View all");
	
	/**
	 * Button that will edit a given client.
	 */
	private JButton editBtn = new JButton("Edit");
	
	/**
	 * The strings that will be the resulting table's column headers.
	 */
	private static final String[] COLUMN_NAMES = { "First Name", "Last Name" };
	
	/**
	 * The number of clients present in the database.
	 */
	int clientCount;

	/**
	 * Table that will contain all the clients in the database.
	 */
	private JTable clients;

	/**
	 * Class constructor.
	 * It creates the window, and adds all the necessary fields, buttons and action listeners.
	 * 
	 * @param cCount - int, the number of clients in the database.
	 */
	public ClientPanel(int cCount) {
		comm = new SQLCommands();
		DBConnect dbc = new DBConnect();
		dbc.connect();
		Vector<String> colData = getColumnData();
		clients = new JTable(null, colData);
		clientCount = cCount;

		this.setTitle("Clients");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(Color.WHITE);

		viewBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				updateTable(clients);
			}
		});

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int rowToDelete = clients.getSelectedRow();
					if (rowToDelete != -1) {
						comm.removeClient(rowToDelete + 1);
						clientCount--;
						updateTable(clients);
					} else {
						JOptionPane.showMessageDialog(null, "No row selected!", "Warning!",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to delete client!\n" + ex.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowToEdit = clients.getSelectedRow();
				if (rowToEdit != -1) {
					ClientEditPanel c = new ClientEditPanel(getRowAt(rowToEdit), rowToEdit + 1);
				} else {
					JOptionPane.showMessageDialog(null, "No row selected!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientCount++;
				NewClientPanel c = new NewClientPanel(clientCount);
			}
		});

		JPanel panel = new JPanel();
		panel.add(viewBtn);
		panel.add(addBtn);
		panel.add(deleteBtn);
		panel.add(editBtn);

		JScrollPane scroll = new JScrollPane(clients);
		clients.setFillsViewportHeight(true);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(panel);
		content.add(scroll);

		this.add(content);
		this.setVisible(true);
	}

	/**
	 * This method will initialize the table that will contain all the clients.
	 * 
	 * @param t - JTable.
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
	 * Returns the data in the row with the index of 'index'.
	 * 
	 * @param index - int.
	 * @return - the contents of the row with the index 'index'.
	 */
	public String[] getRowAt(int index) {
		String[] result = new String[2];
		for (int i = 0; i < 2; i++) {
			result[i] = (String) clients.getModel().getValueAt(index, i);
		}

		return result;
	}

	/**
	 * This method will update a given table.
	 * 
	 * @param t - JTable.
	 */
	public void updateTable(JTable t) {
		try {
			Vector<String[]> rowData = comm.getInfo("Client");
			initTable(t);
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

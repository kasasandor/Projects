package PT2019.demo.presentation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import PT2019.demo.dataAccess.DBConnect;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class creates the main window of the application.
 * From this window, the user will be able to inspect the products in the database,
 * or the clients in the database.
 * 
 * @author Sándor
 *
 */
public class UserPanel extends JFrame {

	/**
	 * Variable for the database commands.
	 */
	private SQLCommands comm;
	
	/**
	 * Button that will let the user inspect the products in the database.
	 */
	private JButton prodBtn = new JButton("Show Products");
	
	/**
	 * Button that will let the user inspect the clients in the database.
	 */
	private JButton clientBtn = new JButton("Show Clients");
	
	/**
	 * Button that will let the user make a new order.
	 */
	private JButton orderBtn = new JButton("Make Order");
	
	/**
	 * JTable, that contains all the clients in the database.
	 */
	private JTable clients;
	
	/**
	 * JTable, that contains all the products in the database.
	 */
	private JTable products;

	/**
	 * Class constructor.
	 * It will create the main window of the application, and will add all
	 * the necessary fields, buttons and action listeners to the window.
	 * 
	 */
	public UserPanel() {
		comm = new SQLCommands();
		DBConnect dbc = new DBConnect();
		dbc.connect();

		this.setTitle("User Panel");
		this.setSize(700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		

		JLabel text = new JLabel("User Interface");
		text.setBounds(200, 20, 500, 100);
		text.setFont(new Font("Times New Roman", Font.PLAIN, 50));
		prodBtn.setBounds(100, 150, 500, 100);
		clientBtn.setBounds(100, 300, 500, 100);
		orderBtn.setBounds(100, 450, 500, 100);
		this.add(text);
		this.add(prodBtn);
		this.add(clientBtn);
		this.add(orderBtn);

		prodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<String[]> rowData = comm.getInfo("Products");
					int pCount = rowData.size();
					ProductPanel p = new ProductPanel(pCount);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to get results!\n" + ex.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		clientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Vector<String[]> rowData = comm.getInfo("Products");
					int cCount = rowData.size();
					ClientPanel p = new ClientPanel(cCount);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to get results!\n" + ex.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderPanel p = new OrderPanel();
			}
		});

		this.setVisible(true);
	}

	
}

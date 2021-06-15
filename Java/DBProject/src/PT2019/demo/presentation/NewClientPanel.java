package PT2019.demo.presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PT2019.demo.Model.Client;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class will create a new window to add a new client.
 * 
 * @author Sándor
 *
 */
public class NewClientPanel extends JFrame{

	/**
	 * TextField where the user can enter the first name of the client.
	 */
	private JTextField fName;
	
	/**
	 * TextField where the user can enter the last name of the client.
	 */
	private JTextField lName;
	
	/**
	 * Button that will validate the addition of the client into the database.
	 */
	private JButton addBtn;
	
	/**
	 * Class constructor.
	 * Adds the necessary fields, buttons and action listeners to the window.
	 * 
	 * @param clientCount - int, the number of clients in the database.
	 */
	public NewClientPanel(final int clientCount) {
		final SQLCommands comm = new SQLCommands();
		this.fName = new JTextField(30);
		this.lName = new JTextField(30);
		this.addBtn = new JButton("Add");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setBackground(Color.WHITE);
		
		JPanel fNamePanel = new JPanel();
		fNamePanel.add(new JLabel("First Name:"));
		fNamePanel.add(fName);
		
		JPanel lNamePanel = new JPanel();
		lNamePanel.add(new JLabel("Last Name:"));
		lNamePanel.add(lName);
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(fNamePanel);
		content.add(lNamePanel);
		content.add(addBtn);
		
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String fn = fName.getText();
					String ln = lName.getText();
					Client c = new Client(clientCount, fn, ln);
					comm.insertClient(c);
					JOptionPane.showMessageDialog(null, "Client added!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "Could not add new product!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		this.add(content);
		this.setVisible(true);
	}
}

package PT2019.demo.presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
 * This class extends the JFrame class.
 * It's purpose is to open a new window when the user wants to edit a clien's information.
 * @author Sándor
 *
 */
public class ClientEditPanel extends JFrame {

	/**
	 * TextField where the user can enter the new first name of a client.
	 */
	private JTextField fNameTF;
	
	/**
	 * TextField where the user can enter the new last name of a client.
	 */
	private JTextField lNameTF;
	
	/**
	 * Button that will validate the edits.
	 */
	private JButton editBtn;

	/**
	 * Class constructor.
	 * Puts the necessary elements into a new window.
	 * 
	 * @param row - String[], contains the previous information of a client.
	 * @param id - int, the id of the client.
	 */
	public ClientEditPanel(String[] row, final int id) {
		final SQLCommands comm = new SQLCommands();
		this.fNameTF = new JTextField(30);
		this.lNameTF = new JTextField(30);
		this.editBtn = new JButton("Update");

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setBackground(Color.WHITE);

		JPanel fNamePanel = new JPanel();
		fNamePanel.add(new JLabel("First Name:"));
		fNameTF.setText(row[0]);
		fNamePanel.add(fNameTF);

		JPanel lNamePanel = new JPanel();
		lNamePanel.add(new JLabel("Last Name:"));
		lNameTF.setText(row[1]);
		lNamePanel.add(lNameTF);

		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(fNamePanel);
		content.add(lNamePanel);
		content.add(editBtn);

		editBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String fName = fNameTF.getText();
				String lName = lNameTF.getText();
				Client c = new Client(id, fName, lName);
				try {
					comm.updateClient(c);
					JOptionPane.showMessageDialog(null, "Client updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to update client!\n" + ex.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		this.add(content);
		this.setVisible(true);
	}
}

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

import PT2019.demo.Model.Product;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class will create a new window to add a new product to the database.
 * 
 * @author Sándor
 *
 */
public class NewProductPanel extends JFrame{

	/**
	 * TextField where the user will enter the price of the product.
	 */
	private JTextField price;
	
	/**
	 * TextField where the user will enter the description of the product.
	 */
	private JTextField description;
	
	/**
	 * Button that will validate the addition of the product to the database.
	 */
	private JButton addBtn;
	
	/**
	 * Class constructor.
	 * 
	 * Creates a new window, adding all the necessary fields,
	 * buttons and action listeners.
	 * 
	 * @param productCount - int, number of products in the database.
	 * 
	 */
	public NewProductPanel(final int productCount) {
		final SQLCommands comm = new SQLCommands();
		this.price = new JTextField(3);
		this.description = new JTextField(30);
		this.addBtn = new JButton("Add");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setBackground(Color.WHITE);
		
		JPanel pricePanel = new JPanel();
		pricePanel.add(new JLabel("Product Price:"));
		pricePanel.add(price);
		
		JPanel descPanel = new JPanel();
		descPanel.add(new JLabel("Product description:"));
		descPanel.add(description);
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(pricePanel);
		content.add(descPanel);
		content.add(addBtn);
		
		addBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					String pr = price.getText();
					String desc = description.getText();
					Product p = new Product(productCount, Float.parseFloat(pr), desc);
					comm.insertProduct(p);
					JOptionPane.showMessageDialog(null, "Product added!", "Success", JOptionPane.INFORMATION_MESSAGE);
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

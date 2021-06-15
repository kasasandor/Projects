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

import PT2019.demo.Model.Product;
import PT2019.demo.dataAccess.SQLCommands;

/**
 * This class will create a new window when the user wants to modify a product's data.
 * 
 * @author Sándor
 *
 */
public class ProductEditPanel extends JFrame{

	/**
	 * TextField where the user can enter the new price of the product.
	 */
	private JTextField priceTF;
	
	/**
	 * TextField where the user can enter the new description of the product.
	 */
	private JTextField descTF;
	
	/**
	 * TextField where the user can enter the number of products in stock.
	 */
	private JTextField stockTF;
	
	/**
	 * Button that will validate the update of the database.
	 */
	private JButton editBtn;
	
	/**
	 * Class constructor.
	 * It will create a new window, adding the necessary fields, buttons,
	 * action listeners to the window.
	 * 
	 * @param row - String[], current characteristics of the product.
	 * @param id - int, id of the product that will be modified.
	 */
	public ProductEditPanel(String[] row, final int id) {
		final SQLCommands comm = new SQLCommands();
		this.priceTF = new JTextField(4);
		this.descTF = new JTextField(30);
		this.stockTF = new JTextField(4);
		this.editBtn = new JButton("Update");
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 400);
		this.setBackground(Color.WHITE);
		
		JPanel pricePanel = new JPanel();
		pricePanel.add(new JLabel("Price:"));
		priceTF.setText(row[0]);
		pricePanel.add(priceTF);
		
		JPanel stockPanel = new JPanel();
		stockPanel.add(new JLabel("Stock:"));
		stockTF.setText(row[1]);
		stockPanel.add(stockTF);
		
		JPanel descPanel = new JPanel();
		descPanel.add(new JLabel("Description:"));
		descTF.setText(row[2]);
		descPanel.add(descTF);
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(pricePanel);
		content.add(stockPanel);
		content.add(descPanel);
		content.add(editBtn);
		
		editBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String price = priceTF.getText();
				String stock = stockTF.getText();
				String desc = descTF.getText();
				Product p = new Product(id, Float.parseFloat(price), desc);
				p.setStock(Integer.parseInt(stock));
				try {
				comm.updateProduct(p);
				JOptionPane.showMessageDialog(null, "Product updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, "Failed to add product!\n" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		this.add(content);
		this.setVisible(true);
	}
}

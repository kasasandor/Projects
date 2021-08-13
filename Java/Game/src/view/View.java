package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.ViewController;
import model.Element;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 1150;
	private static final int WINDOW_HEIGHT = 750;
	
	private JButton newGameBtn;
	private JButton setDifficultyBtn;
	private JButton exitBtn;
	private JButton back = new JButton("Back");
	private JButton highlight = new JButton("Highlight optimal route");
	private JButton easy = new JButton("Easy");
	private JButton medium = new JButton("Medium");
	private JButton hard = new JButton("Hard");
	protected JPanel mainMenu;
	private JPanel difficultyPanel;
	private BoardCanvas board;
	private int difficulty = 5;
	private int width = 502;
	
	public View() {
		
		mainMenu = new JPanel();
		mainMenu.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		mainMenu.setLayout(null);
		
		newGameBtn = new JButton("New Game");
		newGameBtn.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 - 150, 100, 50);
		
		setDifficultyBtn = new JButton("Set Difficulty");
		setDifficultyBtn.setBounds(WINDOW_WIDTH / 2 - 125, WINDOW_HEIGHT / 2 - 50, 150, 50);
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 + 50, 100, 50);
		
		mainMenu.add(newGameBtn);
		mainMenu.add(setDifficultyBtn);
		mainMenu.add(exitBtn);
		this.add(mainMenu);
		
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void exit() {
		this.setVisible(false);
		this.dispose();
	}
	
	public JButton getEasyBtn() {
		return this.easy;
	}
	
	public JButton getMediumBtn() {
		return this.medium;
	}
	
	public JButton getHardBtn() {
		return this.hard;
	}
	
	public void addExitListener(ActionListener ex) {
		exitBtn.addActionListener(ex);
	}
	
	public void addHighlightListener(ActionListener ex) {
		highlight.addActionListener(ex);
	}
	
	public void addBackListener(ActionListener ex) {
		back.addActionListener(ex);
	}
	
	public void addDifficultyListener(ActionListener ex) {
		setDifficultyBtn.addActionListener(ex);
	}
	
	public void addDifficultyBtnListener(ActionListener ex) {
		easy.addActionListener(ex);
		medium.addActionListener(ex);
		hard.addActionListener(ex);
	}
	
	public void addNewGameListener(ActionListener ex) {
		newGameBtn.addActionListener(ex);
	}
	
	public void toMainMenu() {
		this.remove(board);
		this.setContentPane(mainMenu);
	}
	
	public void highlightRoute() {
		board.highlightRoute();
	}
	
	public void changeDifficulty() {
		difficultyPanel = new JPanel();
		difficultyPanel.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		difficultyPanel.setLayout(null);
		
		easy.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 - 150, 100, 50);
		
		medium.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 - 50, 100, 50);
		
		hard.setBounds(WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2 + 50, 100, 50);
		
		difficultyPanel.add(easy);
		difficultyPanel.add(medium);
		difficultyPanel.add(hard);
		
		this.setContentPane(difficultyPanel);
	}
	
	public void changeState(String txt) {
		if(txt.equalsIgnoreCase("easy")) {
			this.difficulty = 5;
			this.width = 502;
		}
		
		if(txt.equalsIgnoreCase("medium")) {
			this.difficulty = 7;
			this.width = 562;
		}
		
		if(txt.equalsIgnoreCase("hard")) {
			this.difficulty = 10;
			this.width = 602;
		}
		
		JOptionPane.showMessageDialog(this, "Difficulty set to " + txt + "!");
		
		this.setContentPane(mainMenu);
	}
	
	public void createBoard() {
		board = new BoardCanvas(difficulty, width);
		board.setSize(width, width);
		board.setFocusable(true);
		board.requestFocusInWindow();
		
		JPanel q = new JPanel();
		q.setLayout(null);
		q.setBounds(width, 0, WINDOW_WIDTH - width, WINDOW_HEIGHT);
		back.setBounds(WINDOW_WIDTH - width - 150, 600, 100, 50);
		highlight.setBounds(WINDOW_WIDTH - width - 250, 100, 200, 50);
		
		q.add(back);
		q.add(highlight);
		
		JPanel cont = new JPanel();
		cont.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		cont.setLayout(null);
		cont.add(board);
		cont.add(q);
		this.setContentPane(cont);
	}
}



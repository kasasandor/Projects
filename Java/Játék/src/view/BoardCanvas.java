package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;
import model.Element;
import model.Solver;
import model.Tile;

public class BoardCanvas extends JPanel implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Board board;
	private Element player;
	private int newX;
	private int newY;
	private final int DELAY = 10;
	private int difficulty;
	private int width;
	private static Character lastkey = null;
	private int totalSteps = 0;
	
	public BoardCanvas(int difficulty, int width) {
		
		this.difficulty = difficulty;
		this.width = width;
		
		setFocusable(true);
		this.board = new Board(difficulty, width);
		
		player = board.getPlayer();
		newX = player.getX();
		newY = player.getY();
		
		/*Solver s = new Solver();
		s.setUp(board);
		List<Tile> route = s.findRoute(board.getPlayerTile(), board.getGoalTile());
		board.colorRoute(route);*/
		
		this.setFocusable(true);
		this.addKeyListener(this);
		
		timer = new Timer(DELAY, this);
		timer.start();
		
	}

	public int getTotalSteps() {
		return totalSteps;
	}
	
	public void paintComponent(Graphics g) {	
		
		super.paintComponent(g);
		
		g.clearRect(0,0,this.getWidth(),this.getHeight());      
		g.setColor(new Color(0,0,0));     
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		
		board.Draw(g, this);
		
		Toolkit.getDefaultToolkit().sync();		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		step();
		this.requestFocusInWindow();
	}
	
	private void step() {
		repaint();
		if(board.modifyPos(player, newX, newY)) {
			JOptionPane.showMessageDialog(this, "Treasure found in " + totalSteps + " steps!");
			timer.stop();
		}
		
	}
	
	public void highlightRoute() {
		Solver s = new Solver();
		s.setUp(board);
		List<Tile> route = s.findRoute(board.getPlayerTile(), board.getGoalTile());
		board.colorRoute(route);
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent ex) {
		int key = ex.getKeyCode();
		if(lastkey == null || lastkey != key) {
			int size = (width-2)/difficulty;
			int lastX = player.getX() * size + 2;
			int lastY = player.getY() * size + 2;
			
			if(key == KeyEvent.VK_LEFT) {
				newX = lastX - size;
				if(newX < 0) newX = 2;
				newY = lastY;
			}
			
			if(key == KeyEvent.VK_RIGHT) {
				newX = lastX + size;
				if(newX > width - size) newX = width - size;
				newY = lastY;
			}
			
			if(key == KeyEvent.VK_UP) {
				newY = lastY - size;
				if(newY < 0) newY = 2;
				newX = lastX;
			}
			
			if(key == KeyEvent.VK_DOWN) {
				newY = lastY + size;
				if(newY > width - size) newY = width - size;
				newX = lastX;
			}
			
			newX = (newX - 2) / size;
			newY = (newY - 2) / size;
			
			totalSteps++;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ex) { }

	@Override
	public void keyTyped(KeyEvent ex) {	}
}

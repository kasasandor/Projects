package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import view.View;

public class ViewController{

	private View m_view;
	
	public ViewController(View view) {
		this.m_view = view;
		
		view.addExitListener(new ExitListener());
		view.addDifficultyListener(new DifficultyListener());
		view.addNewGameListener(new NewGameListener());
		view.addDifficultyBtnListener(new DifficultyBtnListener());
		view.addBackListener(new BackListener());
		view.addHighlightListener(new HighlightListener());
	}
	
	class HighlightListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			m_view.highlightRoute();
		}
	}
	
	class ExitListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			m_view.exit();
		}
	}
	
	class BackListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			m_view.toMainMenu();
		}
	}
	
	class DifficultyListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			m_view.changeDifficulty();
		}
	}
	
	class DifficultyBtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			m_view.changeState(btn.getText());
		}
	}
	
	class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			m_view.createBoard();
		}
	}
	
}


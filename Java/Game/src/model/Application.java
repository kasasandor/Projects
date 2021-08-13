package model;

import java.awt.EventQueue;

import controller.ViewController;
import view.View;

public class Application {

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				View view = new View();
				ViewController vc = new ViewController(view);
			}
		}
		
		);
	}
}

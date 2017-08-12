package workload.application;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import workload.menu.MainFrame;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame mainFrame = new MainFrame("Hauptmenu");
				mainFrame.setSize(500,400);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setVisible(true);
			}
		});

	}

}

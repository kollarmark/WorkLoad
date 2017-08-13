package workload.menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
					// Generierung der Datei
					File f = new File("eintragsliste01.dat");
					if(f.exists() && !f.isDirectory()) { 
						
					} else {
						try {
							f.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					File g = new File("planungsliste01.dat");
					if(g.exists() && !g.isDirectory()) { 
						
					} else {
						try {
							g.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWorkload = new JLabel("WorkLoad");
		lblWorkload.setBounds(10, -1, 424, 251);
		lblWorkload.setVerticalAlignment(SwingConstants.TOP);
		lblWorkload.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblWorkload);
		
		JButton btnEintrag = new JButton("Eintrag");
		btnEintrag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				EintragMenu em = new EintragMenu();
				em.setLocationRelativeTo(getParent());
				em.setVisible(true);
			}
		});	
		
		btnEintrag.setVerticalAlignment(SwingConstants.TOP);
		btnEintrag.setHorizontalAlignment(SwingConstants.CENTER);
		btnEintrag.setBounds(174, 37, 89, 23);
		contentPane.add(btnEintrag);
		
		JButton btnPlanung = new JButton("Planung");
		btnPlanung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PlanungMenu pm = new PlanungMenu();
				pm.setLocationRelativeTo(getParent());
				pm.setVisible(true);
			}
		});
		btnPlanung.setBounds(174, 71, 89, 23);
		contentPane.add(btnPlanung);
		
		JButton btnbersicht = new JButton("\u00DCbersicht");
		btnbersicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UebersichtsMenu um;
				try {
					um = new UebersichtsMenu();
					um.setLocationRelativeTo(getParent());
					um.setVisible(true);
				} catch (EOFException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnbersicht.setBounds(174, 105, 89, 23);
		contentPane.add(btnbersicht);
	}
}

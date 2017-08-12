package workload.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import workload.uebersicht.Uebersicht;

public class UebersichtsMenu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UebersichtsMenu dialog = new UebersichtsMenu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.pack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UebersichtsMenu() {
		setBounds(100, 100, 619, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			
			String [][] data = { {"", "", "", "", ""} };
			
			//File f = new File("eintragsliste01.dat");
			//if(f.exists() && !f.isDirectory()) { 
			
			
			
			
			Uebersicht u = new Uebersicht();
			
			data = new String[u.getUebersichtsElemente().size()][5];
			
			for (int i = 0; i < u.getUebersichtsElemente().size(); i++) {
				
				data[i][0] = u.getUebersichtsElemente().get(i).getDatum();/*u.getUebersichtsElemente().get(i).getDatum().toString()*/
				data[i][1] = u.getUebersichtsElemente().get(i).getModul();
				data[i][2] = u.getUebersichtsElemente().get(i).getGeplant();
				data[i][3] = u.getUebersichtsElemente().get(i).getGemacht();
				data[i][4] = u.getUebersichtsElemente().get(i).getDiff();
				
			}
			
			String[] headers = { "Datum" , "Modul", "Geplant", "Gemacht", "Diff" };
			table = new JTable(data, headers);
			
			contentPanel.add(table);
			contentPanel.add(new JScrollPane(table));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						//System.getProperty("user.dir");
						
						/*File f = new File("");
						if(f.exists() && !f.isDirectory()) { 
						    // do something
						}*/
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
